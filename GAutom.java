import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Automata factory
 */
public class GAutom {

    static Autom thompson (String expresion)
    {
        Deque<State> nodes = new ArrayDeque<>();
        int count = 0;
        for (char c : expresion.toCharArray()) {
            switch (c) {
                case '.':
                    State m = nodes.pop();
                    State n = nodes.pop();

                    n.addStates(c, m.getId());
                    break;

                case '|':
                case '+':
                    //



                    break;

                case '*':

                    break;
            
                default:
                    // Add hollow state to the stack
                    State s = new State(false, count);
                    nodes.push(s);
                    count++;
                    break;
            }
        }
        return null;
    }
}