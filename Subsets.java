import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Subsets {

    private static Set<Integer> sLock (List<Trans> transitions, int s)
    {
        Set<Integer> lock = new HashSet<>();
        lock.add(s);

        transitions.forEach(t -> {
            if (t.origin == s && t.symbol == '$') {
                lock.add(t.destiny);
                lock.addAll(sLock(transitions, t.destiny));
            }
        });

        return lock;
    }

    private static Set<Integer> destinySet (Set<Integer> origin, char symbol, List<Trans> trans)
    {
        Set<Integer> destiny = new HashSet<>();
        origin.forEach(s -> {
            trans.forEach(t -> {
                if (s == t.origin && symbol == t.symbol) {
                    destiny.add(t.destiny);
                }
            });
        });
        return destiny;
    }

    static Autom subsets (Autom autom)
    {
        DFA result = new DFA();

        Set<Character> symbols = autom.getSymbols();
        List<Trans> transitions = autom.getTransitions();
        int aceptance = autom.getAceptance();

        Set<Integer> initSet = new HashSet<>( sLock(autom.getTransitions(), 0) );

        Map<Set<Integer>, Integer> subsets = new HashMap<>();
        subsets.put(initSet, 0);

        Deque<Set<Integer>> stateSets = new ArrayDeque<>();
        stateSets.push(initSet);

        int count = 0;
        while (!stateSets.isEmpty()) {
            Set<Integer> originSet = stateSets.pop();
            for (Character c : symbols) {
                Set<Integer> destSet = new HashSet<>();

                for (Integer s : destinySet(originSet, c, transitions)) {
                    destSet.addAll(sLock(autom.getTransitions(), s));
                }

                if (destSet == new HashMap<>())
                {
                    subsets.putIfAbsent(destSet, -1);
                }

                if (!subsets.containsKey(destSet)) {
                    count++;
                    subsets.put(destSet, count);
                    stateSets.push(destSet);
                    if (destSet.contains(aceptance)) {
                        result.addAceptance(count);
                    }
                }

                result.addTransition(new Trans(subsets.get(originSet), subsets.get(destSet), c));
            }

            if (subsets.containsValue(-1)) {
                symbols.forEach(c -> result.addTransition(new Trans(-1, -1, c)));
            }

            result.setStateSize(count + 1);
        }

        return result;
    }
}
