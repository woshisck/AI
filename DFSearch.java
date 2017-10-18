
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
public class DFSearch {
    final int limite=50;
    static Map<State,Integer> levelDepth;
    static int value;
    public static void search(char[] board)
    {
        SearchNode root = new SearchNode(new Board(board));
        Stack<SearchNode> stack = new Stack<SearchNode>();

        stack.add(root);

        Search(stack);
    }

    private static boolean checkRepeats(SearchNode n)
    {
        boolean retValue = false;
        SearchNode checkNode = n;

        if(!levelDepth.containsKey(n.getCurrentState()))
        {
            value++;
            levelDepth.put(n.getCurrentState(),value);
        }
        while (n.getParent() != null && !retValue)
        {
            if (n.getParent().getCurrentState().equals(checkNode.getCurrentState()))
            {
                retValue = true;
            }
            n = n.getParent();
        }

        return retValue;
    }

    public static void Search(Stack<SearchNode> s)
    {
        levelDepth = new HashMap<State, Integer>();
        int searchCount = 1;

        while (!s.isEmpty())
        {
            SearchNode tempNode = s.pop();

            if (!tempNode.getCurrentState().isGoal())
            {

                ArrayList<State> tempSuccessors = tempNode.getCurrentState()
                        .generateSuccessors();


                for (int i = 0; i < tempSuccessors.size(); i++)
                {
                    SearchNode newNode = new SearchNode(tempNode,
                            tempSuccessors.get(i), tempNode.getCost()
                            + tempSuccessors.get(i).findCost(), 0);

                    if (!checkRepeats(newNode))
                    {
                        s.add(newNode);
                    }
                }
                searchCount++;
            }
            else
            {
                Stack<SearchNode> solutionPath = new Stack<>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null)
                {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);
                int loopSize = solutionPath.size();

                for (int i = 0; i < loopSize; i++)
                {
                    tempNode = solutionPath.pop();
                    tempNode.getCurrentState().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The path cost: " + tempNode.getCost());

                    System.out.println("The number of nodes: "
                            + searchCount);
            }
        }
    }
}
