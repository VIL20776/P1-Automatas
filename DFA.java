import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFA extends Autom {
    private Set<Integer> finals = new HashSet<>(); //Conjunto de estados de aceptacion

    /*
     * Constructor que llama al constructor base dAutom
     */
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

    @Override
    public String toString() {
        String states = "ESTADOS: ";
        String symbols = "SIMBOLOS: ";
        String transitions = "TRANSICIONES: ";
        String aceptance = "ACEPTANCION: ";

        states = states + this.states + "\n";

        symbols = symbols + this.symbols + "\n";

        // for(Trans t: this.transitions){
        //     transitions = transitions + t.toString();
        // }
        transitions = transitions + this.transitions + "\n";

        aceptance = aceptance + this.finals + "\n";

        return states + symbols + transitions + aceptance;
    }
}
