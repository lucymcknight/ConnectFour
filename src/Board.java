/**
 * Created by Lucy on 1/4/2016.
 */
public class Board {

    private static final int NUM_COLS = 7;
    private static final int NUM_ROWS = 6;
    private static final char PLYR1_CHAR = 'O';
    private static final char PLYR2_CHAR = 'X';
    private static final char BLANK_CHAR = '_';

    char[][] board;

    public Board()
    {
        board = new char[NUM_COLS][NUM_ROWS];
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                board[i][j] = BLANK_CHAR;
            }
        }
    }

    public boolean colFree(int position) {
        if(position > 0 && position <= NUM_COLS)
        {
            return (board[position-1][NUM_ROWS-1] == BLANK_CHAR);
        }
        return false;
    }

    public boolean addPiece(int player, int position)
    {
        boolean done = false;
        if(position > 0 && position <= NUM_COLS)
        {
            if(player == 1 || player == 2)
            {
                if(colFree(position))
                {
                    char symbol = PLYR1_CHAR;
                    if(player == 2)
                    {
                        symbol = PLYR2_CHAR;
                    }

                    int i=0;
                    while(!done)
                    {
                        if(board[position-1][i] == BLANK_CHAR)
                        {
                            board[position-1][i] = symbol;
                            done = true;
                        }
                        i++;
                    }
                }
            }
        }
       return done;
    }

    public String toString()
    {
        String result = "| ";
        for (int i = 1; i <= NUM_COLS; i++)
        {
            if(colFree(i))
            {
                result += i;
            }
            else
            {
                result += " ";
            }
            result += " ";
        }
        result += "|\n";

        for (int i = NUM_ROWS-1; i >= 0; i--)
        {
            result += "| ";
            for (int j = 0; j < NUM_COLS; j++)
            {
                result += board[j][i] + " ";
            }
            result += "|\n";
        }
        return result;
    }
}
