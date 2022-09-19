import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFA extends Autom {
    private Set<Integer> finals = new HashSet<>();

    DFA () {
        super();
    }

    DFA (List<Integer> states, List<Trans> transitions, Set<Character> symbols, Set<Integer> finals) {
        this.states = states;
        this.symbols = symbols;
        this.transitions = transitions;
        this.finals = finals;
    }

    public Set<Integer> getFinals() {
        return finals;
    }

    void addAceptance (int a)
    {
        finals.add(a);
    }
}
