import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Autom {
    private List<Integer> states;
    private List<Trans> transitions;
    private Set<Character> symbols;
    private int aceptance;

    public Autom(){
            this.states = new ArrayList <Integer> ();
            this.transitions = new ArrayList <Trans> ();
            this.symbols = new HashSet<>();
            this.aceptance = 0;
        }
        public Autom(int size){
            this.states = new ArrayList <Integer> ();
            this.transitions = new ArrayList <Trans> ();
            this.symbols = new HashSet<>();
            this.aceptance = 0;
            this.setStateSize(size);
        }
        public Autom(char c){
            this.states = new ArrayList<Integer> ();
            this.transitions = new ArrayList <Trans> ();
            this.symbols = new HashSet<>();
            this.setStateSize(2);
            this.aceptance = 1;
            this.transitions.add(new Trans(0, 1, c));
            this.symbols.add(c);
        }

        public void addTransition (Trans t)
        {
            this.transitions.add(t);
            this.symbols.add(t.symbol);
        }

        public Set<Character> getSymbols() {
            return symbols;
        }

        public void setAceptance(int aceptance) {
            this.aceptance = aceptance;
        }

        public void setStateSize(int size){
            for (int i = 0; i < size; i++)
                this.states.add(i);
        }

        public List<Trans> getTransitions() {
            return transitions;
        }

        public int getAceptance() {
            return aceptance;
        }

        public int size(){
            return states.size();
        }
}
