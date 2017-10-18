
import java.util.ArrayList;
import java.util.Arrays;

public class Board implements State{
    int PUZZLE_SIZE = 16;
    int h1 = 0;  //misplaced tiles
    char[] GOAL = {'E','E','E','E','E','A','E','E','E','B','E','E','E','C','E','O'};
    char[] currentBoard;
    int manDist =0;
    int outOfPlace =0;

    public Board(char[] board) {
        currentBoard = board;
        setManDist();
        setOutOfPlace();
    }

    public int setOutOfPlace() {
        for (int i = 0; i < currentBoard.length; i++)
        {
            if (currentBoard[i] != GOAL[i])
            {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    public int setManDist() {

        if(currentBoard[5] !='A'){
            manDist+=1;
        }
        if(currentBoard[9] !='B'){
            manDist+=1;
        }

        if(currentBoard[13] !='C'){
            manDist+=1;
        }
        return manDist;
    }




    private int getHole() {
        int holeIndex = -1;

        for (int i = 0; i < PUZZLE_SIZE; i++)
        {
            if (currentBoard[i] == 'O')
                holeIndex = i;
        }
        return holeIndex;
    }

    @Override
    public double findCost()
    {
        return 1;
    }

    public int getMisplacedTiles() {
        for (int i = 0; i < currentBoard.length; i++)
        {
            if (currentBoard[i] != GOAL[i])
            {
                h1++;
            }
        }
        return h1;
    }

    private char[] copyBoard(char[] state)
    {
        char[] board = new char[PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++)
        {
            board[i] = state[i];
        }
        return board;
    }

    @Override
    public ArrayList<State> generateSuccessors()
    {
        ArrayList<State> successors = new ArrayList<>();
        int hole = getHole();

        if (hole != 0 && hole != 4 && hole != 8 && hole != 12) //left
        {
            swapAndStore(hole - 1, hole, successors);
        }

        if (hole != 12 && hole != 13 && hole != 14 && hole != 15)  //down
        {
            swapAndStore(hole + 4, hole, successors);
        }

        if (hole != 0 && hole != 1 && hole != 2 && hole != 3)  //up
        {
            swapAndStore(hole - 4, hole, successors);
        }

        if (hole != 3 && hole != 7 && hole != 11 && hole != 15) //right
        {
            swapAndStore(hole + 1, hole, successors);
        }

        return successors;
    }

    private void swapAndStore(int a, int b, ArrayList<State> s)
    {
        char[] copy = copyBoard(currentBoard);
        char temp = copy[a];
        copy[a] = currentBoard[b];
        copy[b] = temp;
        s.add(new Board(copy));
    }

    @Override
    public boolean equals(State s)
    {
        if (Arrays.equals(currentBoard, ((Board) s).getCurrentBoard()))
        {
            return true;
        }
        else
            return false;
    }

    public char[] getCurrentBoard()
    {
        return currentBoard;
    }


    @Override
    public boolean isGoal()
    {
        if(currentBoard[5] == 'A' && currentBoard[9] =='B' && currentBoard[13] == 'C'){
            return true;
        }
        return false;
    }

    @Override
    public void printState()
    {
        for (int i =0;i<16;i++){
            System.out.print(" "+currentBoard[i]+" ");
            if((i+1) % 4 == 0){
                System.out.println();
            }
        }
    }

}
