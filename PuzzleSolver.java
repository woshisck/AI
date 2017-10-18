
public class PuzzleSolver {

    public static char[] board ={'E','E','E','E','E','E','E','E','E','E','E','E','A','B','C','O'};

    public static void main(String[] args)
    {
        BFSearch.search(board);
        AStarSearch.search(board);
        DFSearch.search(board);

    }

}
