
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class AStarSearch {
    public static void search(char[] board)
    {
        SearchNode root = new SearchNode(new Board(board));
        Queue<SearchNode> queue = new LinkedList<>();
        queue.add(root); //adding the root search-node

        int count = 0;

        while (!queue.isEmpty()) {
            SearchNode tempNode = queue.poll();

            if (!tempNode.getCurrentState().isGoal()) {
                ArrayList<State> tempSuccess =
                        tempNode.getCurrentState().generateSuccessors();
                ArrayList<SearchNode>
                        n_Success = new ArrayList<>();

                for (int i = 0; i < tempSuccess.size(); i++) {
                    SearchNode checkedNode;
                        checkedNode = new SearchNode(tempNode, tempSuccess.get(i), tempNode.getCost() + tempSuccess.get(i).findCost(), ((Board) tempSuccess.get(i)).getMisplacedTiles());
                    if (!checkRepeats(checkedNode))
                    {
                        n_Success.add(checkedNode);
                    }
                }

                if (n_Success.isEmpty())
                    continue;

                SearchNode lowestNode = n_Success.get(0);

                for (int i = 0; i < n_Success.size(); i++)
                {
                    if (lowestNode.getFCost() > n_Success.get(i).getFCost())
                    {
                        lowestNode = n_Success.get(i);
                    }
                }

                int lowestValue = (int) lowestNode.getFCost();

                for (int i = 0; i < n_Success.size(); i++)
                {
                    if (n_Success.get(i).getFCost() == lowestValue)
                    {
                        queue.add(n_Success.get(i));
                    }
                }

                count++;
            }
            else {
                Stack<SearchNode> solutionPath = new Stack<>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);

                int loopSize = solutionPath.size();

                for (int i = 0; i < loopSize; i++) {
                    tempNode = solutionPath.pop();
                    tempNode.getCurrentState().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The total path cost is: " + tempNode.getCost());
                System.out.println("The number of nodes generated : " + count);
                break;
            }
        }
    }

    private static boolean checkRepeats(SearchNode n) {
        boolean returnValue = false;
        SearchNode checkNode = n;

        while (n.getParent() != null && !returnValue) {
            if (n.getParent().getCurrentState().equals(checkNode.getCurrentState()))
            {
                returnValue = true;
            }
            n = n.getParent();
        }
        return returnValue;
    }
}
