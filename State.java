import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
    private int id;
    private Map<Character,Set<Integer>> states;
    private boolean aceptance;

    State ()
    {
        this.aceptance = false;
        this.id = 0;
    }

    State (boolean aceptance, int id)
    {
        this.aceptance = aceptance;
        this.states = new HashMap<>();
        this.id = id;
    }

    public void addStates (char c, int s)
    {
        Set<Integer> newState = new HashSet<>();
        newState.add(s);

        Set<Integer> node = this.states.putIfAbsent(c, newState);
        if (node != null) {
            node.addAll(newState);
            states.replace(c, node);
        }
    }

    public boolean isAceptance ()
    {
        return aceptance;
    }

    public Map<Character, Set<Integer>> getStates() {
        return states;
    }

    public int getId() {
        return id;
    }
}
