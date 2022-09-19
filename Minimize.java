import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Minimize {

    private static boolean[][] initializeMatrix (int states, Set<Integer> finals)
    {
        boolean matrix [][] = new boolean [states][states];
        int i = 1;
        for (int y = 0; y < states; y++) {
            for (int x = i; x < states; x++) {
                if (finals.contains(x)) {
                    matrix[y][x] = true;
                } else {
                    matrix[y][x] = false;
                }
            }
            i++;
        }
        return matrix;
    }

    private static Deque<Integer []> statePairs (boolean matrix [][])
    {
        Deque<Integer []> pairs = new ArrayDeque<>();
        int i = 1;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = i; x < matrix.length; x++) {
                if (matrix[y][x]) {
                    Integer [] pair = {y, x};                     
                    pairs.push(pair);
                }
            }
            i++;
        }
        return pairs;
    }

    private static Map<Integer, Integer> mapStates (boolean matrix [][])
    {
        Map<Integer, Integer> newStates = new HashMap<>();

        int i = 1;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = i; x < matrix.length; x++) {
                if (!matrix[y][x]){
                    newStates.putIfAbsent(y, y);
                    newStates.putIfAbsent(x, y);
                }
            }
            i++;
        }
        return newStates;
    }
    
    static DFA minimize(DFA autom)
    {   
        int size = autom.size();
        List<Integer> states = autom.getStates();
        List<Trans> trans = autom.getTransitions();
        Set<Character> symbols = autom.getSymbols();
        Set<Integer> finals = autom.getFinals();
        boolean matrix [][] = initializeMatrix(size, finals);

        Deque<Integer []> initialPairs = statePairs(matrix);
        Deque<Integer []> newPairs = new ArrayDeque<>();

        do {
            Integer [] refPair = initialPairs.pop();
            for (char c : symbols) {
                for (Trans t0 : trans) {
                    if (refPair[0] == t0.destiny && c == t0.symbol) {
                        for (Trans t1 : trans) {
                            if (refPair[1] == t1.destiny && c == t1.symbol) {
                                Integer [] newPair = {t0.origin, t1.origin};
                                if (!newPairs.contains(newPair)){
                                    matrix[t0.origin][t1.origin] = true;
                                    newPairs.push(newPair);
                                }
                            }
                        }
                    }
                }
            }
            if (initialPairs.isEmpty()) {
                if (!newPairs.isEmpty())
                    initialPairs.addAll(newPairs);
            }

        } while (!initialPairs.isEmpty());

        Map<Integer,Integer> newStates = mapStates(matrix);

        states.forEach(s -> s = newStates.getOrDefault(s, s));

        Set<Integer> resizeStates = new HashSet<>(states);
        states.clear();
        states.addAll(resizeStates);

        trans.forEach(t -> {
            t.origin = newStates.getOrDefault(t.origin, t.origin);
            t.destiny = newStates.getOrDefault(t.destiny, t.destiny);
        });

        Set<Trans> resizeTrans = new HashSet<>(trans);
        trans.clear();
        trans.addAll(resizeTrans);

        finals.removeIf(f -> (!states.contains(f)));

        DFA result = new DFA(states, trans, symbols, finals);

        return result;
    }
}
