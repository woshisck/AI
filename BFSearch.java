
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSearch {

    public static void search(char[] board) {
        SearchNode root = new SearchNode(new Board(board));
        Queue<SearchNode> queue = new LinkedList<SearchNode>();
        queue.add(root);
        performSearch(queue);
    }
    private static boolean checkRepeats(SearchNode n) {
        boolean retValue = false;
        SearchNode checkNode = n;

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

    public static void performSearch(Queue<SearchNode> q) {
        int searchCount = 1;

        while (!q.isEmpty())
        {
            SearchNode tempNode =q.poll();

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
                        q.add(newNode);
                    }
                }
                searchCount++;
            }
            else
            {
                Stack<SearchNode> sol = new Stack<SearchNode>();
                sol.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null)
                {
                    sol.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                sol.push(tempNode);

                int loopSize = sol.size();

                for (int i = 0; i < loopSize; i++)
                {
                    tempNode = sol.pop();
                    tempNode.getCurrentState().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The path cost was: " + tempNode.getCost());

                    System.out.println("The number of nodes: "
                            + searchCount);

            }
        }
    }
}
