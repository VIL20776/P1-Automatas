import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Generacion de automata usando el algoritmo de Thompson
 */
public class Thompson {

    private static Autom kleene (Autom m)
    {
        Autom result = new Autom(m.size() + 2);
        result.addTransition(new Trans(0, 1, '$'));

        List<Trans> mTransitions = m.getTransitions();

        mTransitions.forEach(t -> {
            result.addTransition(new Trans(t.origin + 1, t.destiny + 1, t.symbol));
        });

        result.addTransition(new Trans(m.size(), 1, '$'));
        result.addTransition(new Trans(0, m.size() + 1, '$'));

        result.setAceptance(m.size() + 1);
        return result;
    }

    private static Autom concat (Autom m, Autom n)
    {
        Autom result = new Autom(m.size() + n.size());

        List<Trans> mTransitions = m.getTransitions();
        List<Trans> nTransitions = n.getTransitions();

        mTransitions.forEach(t -> {
            result.addTransition(new Trans(t.origin, t.destiny, t.symbol));
        });

        result.addTransition(new Trans(m.getAceptance(), m.size(), '$'));
 
        nTransitions.forEach(t -> {
            result.addTransition(new Trans(t.origin + m.size(), t.destiny + m.size(), t.symbol));
        });

        result.setAceptance(m.getAceptance() + n.getAceptance() - 1);
        return result;
    }

    private static Autom union (Autom m, Autom n)
    {
        Autom result = new Autom(m.size() + n.size() + 2);

        result.addTransition(new Trans(0, 1, '$'));

        List<Trans> mTransitions = m.getTransitions();
        List<Trans> nTransitions = n.getTransitions();

        mTransitions.forEach(t -> {
            result.addTransition(new Trans(t.origin + 1, t.destiny + 1, t.symbol));
        });

        result.addTransition(new Trans(m.size(), m.size() + n.size() + 1, '$'));

        result.addTransition(new Trans(0, m.size() + 1, '$'));

        nTransitions.forEach(t -> {
            result.addTransition(new Trans(t.origin + m.size() + 1, t.destiny + m.size() + 1, t.symbol));
        });

        result.addTransition(new Trans(m.size() + n.size(), m.size() + n.size() + 1, '$'));

        result.setAceptance(m.size() + n.size() + 1);
        return result;
    }

    static Autom thompson (String expresion)
    {
        Deque<Autom> nodes = new ArrayDeque<>();
        
        for (char c : expresion.toCharArray()) {
            switch (c) {
                case '.':
                    Autom b = nodes.pop();
                    Autom a = nodes.pop();

                    nodes.push(concat(a, b));
                    break;

                case '|':
                case '+':
                    Autom y = nodes.pop();
                    Autom x = nodes.pop();

                    nodes.push(union(x, y));
                    break;

                case '*':
                    Autom z = nodes.pop();

                    nodes.push(kleene(z));
                    break;
            
                default:
                    // Add hollow state to the stack
                    Autom s = new Autom(c);
                    nodes.push(s);
                    break;
            }
        }
        return nodes.pop();
    }
}