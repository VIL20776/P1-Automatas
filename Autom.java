import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Clase base de un automata no determinista
 */
public class Autom {
    protected List<Integer> states;
    protected List<Trans> transitions;
    protected Set<Character> symbols;
    private int aceptance;

    //Constructores

    /*
     * Constructor base
     */
    public Autom(){
        this.states = new ArrayList <Integer> ();
        this.transitions = new ArrayList <Trans> ();
        this.symbols = new HashSet<>();
        this.aceptance = 0;
    }
    /*
     * Constructor que genera una cantidad inicial de estados.
     * @param size -Cantidad de estados a generar.
     */
    public Autom(int size){
        this.states = new ArrayList <Integer> ();
        this.transitions = new ArrayList <Trans> ();
        this.symbols = new HashSet<>();
        this.aceptance = 0;
        this.setStateSize(size);
    }
    
    /*
     * Constructor que genera un automata con una trancision inicial.
     * @param c -Simbolo de la trancision.
     */
    public Autom(char c){
        this.states = new ArrayList<Integer> ();
        this.transitions = new ArrayList <Trans> ();
        this.symbols = new HashSet<>();
        this.setStateSize(2);
        this.aceptance = 1;
        this.transitions.add(new Trans(0, 1, c));
        this.symbols.add(c);
    }

    /*
     * Agrega una trancision al automata.
     * @param t -Trancision a agregar
     */
    public void addTransition (Trans t)
    {
        this.transitions.add(t);
        this.symbols.add(t.symbol);
    }

    /*
     * Getter que devuelve un conjunto de simbolos
     * @return symbols -Conjunto de simbolos
     */
    public Set<Character> getSymbols() {
        return symbols;
    }

    /*
     * Setter que cambia los estados previos, por los estados dados
     * @param states -Nueva lista de estados
     */
    public void setStates(List<Integer> states) {
        this.states = states;
    }

    /*
     * Setter que cambia el estado de aceptacion original por el estado dado
     * @param aceptance -Nuevo estado de aceptacion
     */
    public void setAceptance(int aceptance) {
        this.aceptance = aceptance;
    }

    /*
     * Setter que genera nuevos estados segun el parametro dado
     * @param size -Cantidad de estados nuevos
     */
    public void setStateSize(int size){
        for (int i = 0; i < size; i++)
            this.states.add(i);
    }

    public List<Integer> getStates() {
        return states;
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

    @Override
    public String toString() {
        String info = "";

        String states = "ESTADOS: { ";
        String symbols = "SIMBOLOS: { ";
        String transitions = "TRANSICIONES: { ";
        String aceptance = "ACEPTANCE: { ";

        states = states + this.states + " }\n";

        symbols = symbols + this.symbols + " }\n";

        this.transitions.forEach(t -> {
            transitions += t.toString();
        });
        transitions += " }\n";

        aceptance = aceptance + this.aceptance + " }\n";

        return states + symbols + transitions + aceptance;
    }
}
