
public class SearchNode {
    private State currentState;
    private SearchNode parent;
    private double cost; // cost to get to this state
    private double hCost; // heuristic cost
    private double fCost; // f(n) cost

    public SearchNode(State s)
    {
        currentState = s;
        parent = null;
        cost = 0;
        hCost = 0;
        fCost = 0;
    }

    public SearchNode(SearchNode prev, State s, double c, double h)
    {

        parent = prev;
        currentState = s;
        cost = c;
        hCost = h;
        fCost = cost + hCost;
    }

    public State getCurrentState()
    {
        return currentState;
    }

    public SearchNode getParent()
    {
        return parent;
    }

    public double getCost()
    {
        return cost;
    }



    public double getFCost()
    {
        return fCost;
    }
}
