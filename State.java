import java.util.ArrayList;

public interface State {
    boolean isGoal();


    ArrayList<State> generateSuccessors();


    double findCost();

    // print the current state
    public void printState();

    // compare the actual state data
    public boolean equals(State s);

}
