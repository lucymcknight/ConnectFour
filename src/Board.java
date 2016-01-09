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

    public Board(Board boardToCopy)
    {
        board = new char[NUM_COLS][NUM_ROWS];
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                int playerAtPos = boardToCopy.getPlayerAtPosition(i+1,j+1);
                if(playerAtPos == 1)
                {
                    board[i][j] = PLYR1_CHAR;
                }
                else if(playerAtPos == 2)
                {
                    board[i][j] = PLYR2_CHAR;
                }
                else
                {
                    board[i][j] = BLANK_CHAR;
                }
            }
        }
    }

    public int getPlayerAtPosition(int x, int y)
    {
        if(x <= 0 || y <= 0 || x > NUM_COLS || y > NUM_ROWS)
        {
            return -1;
        }
        char piece = board[x-1][y-1];
        if(piece == BLANK_CHAR)
        {
            return 0;
        }
        if(piece == PLYR1_CHAR)
        {
            return 1;
        }
        if(piece == PLYR2_CHAR)
        {
            return 2;
        }
        return -2;
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

    //not the most efficient but works well enough
    //may be improved later
    public int longestStreakForPlayer(int player)
    {
        char sym = PLYR1_CHAR;
        if(player == 2)
        {
            sym = PLYR2_CHAR;
        }
        int longestStreakSoFar = 0;
        for(int i=0; i<NUM_COLS; i++)
        {
            for(int j=0; j<NUM_ROWS; j++)
            {
                if(board[i][j] == sym)
                {
                    //check vertical
                    int jToCheck = j+1;
                    int curStreak = 1;
                    boolean streakOver = false;
                    while(!streakOver && jToCheck<NUM_ROWS)
                    {
                        if(board[i][jToCheck] == sym)
                        {
                            curStreak++;
                            jToCheck++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(curStreak > longestStreakSoFar)
                    {
                        longestStreakSoFar = curStreak;
                    }

                    //check horizontal
                    int iToCheck = i+1;
                    curStreak = 1;
                    streakOver = false;
                    while(!streakOver && iToCheck<NUM_COLS)
                    {
                        if(board[iToCheck][j] == sym)
                        {
                            curStreak++;
                            iToCheck++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(curStreak > longestStreakSoFar)
                    {
                        longestStreakSoFar = curStreak;
                    }

                    //check right diag
                    iToCheck = i+1;
                    jToCheck = j+1;
                    curStreak = 1;
                    streakOver = false;
                    while(!streakOver && iToCheck<NUM_COLS && jToCheck<NUM_ROWS)
                    {
                        if(board[iToCheck][jToCheck] == sym)
                        {
                            curStreak++;
                            iToCheck++;
                            jToCheck++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(curStreak > longestStreakSoFar)
                    {
                        longestStreakSoFar = curStreak;
                    }

                    //check left diag
                    iToCheck = i-1;
                    jToCheck = j+1;
                    curStreak = 1;
                    streakOver = false;
                    while(!streakOver && iToCheck>=0 && jToCheck<NUM_ROWS)
                    {
                        if(board[iToCheck][jToCheck] == sym)
                        {
                            curStreak++;
                            iToCheck--;
                            jToCheck++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(curStreak > longestStreakSoFar)
                    {
                        longestStreakSoFar = curStreak;
                    }
                }
            }
        }
        return longestStreakSoFar;
    }

    //not the most efficient but works well enough
    //may be improved later
    public int longestOpenStreakForPlayer(int player)
    {
        char sym = PLYR1_CHAR;
        if(player == 2)
        {
            sym = PLYR2_CHAR;
        }
        int longestOpenStreakSoFar = 0;
        for(int i=0; i<NUM_COLS; i++)
        {
            for(int j=0; j<NUM_ROWS; j++)
            {
                if(board[i][j] == sym)
                {
                    //check vertical
                    int jToCheck = j+1;
                    int curStreak = 1;
                    boolean streakOver = false;
                    while(!streakOver && jToCheck<NUM_ROWS)
                    {
                        if(board[i][jToCheck] == sym)
                        {
                            jToCheck++;
                            curStreak++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(jToCheck < NUM_ROWS && board[i][jToCheck] == BLANK_CHAR && curStreak > longestOpenStreakSoFar)
                    {
                        longestOpenStreakSoFar = curStreak;
                    }

                    //check horizontal
                    int iToCheck = i+1;
                    curStreak = 1;
                    streakOver = false;
                    while(!streakOver && iToCheck<NUM_COLS)
                    {
                        if(board[iToCheck][j] == sym)
                        {
                            iToCheck++;
                            curStreak++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(iToCheck < NUM_COLS && board[iToCheck][j] == BLANK_CHAR && curStreak > longestOpenStreakSoFar)
                    {
                        //open space to right of streak
                        longestOpenStreakSoFar = curStreak;
                    }
                    if(i-1 >= 0 && board[i-1][j] == BLANK_CHAR && curStreak > longestOpenStreakSoFar)
                    {
                        //open space to left of streak
                        longestOpenStreakSoFar = curStreak;
                    }

                    //check right diag
                    iToCheck = i+1;
                    jToCheck = j+1;
                    curStreak = 1;
                    streakOver = false;
                    while(!streakOver && iToCheck<NUM_COLS && jToCheck<NUM_ROWS)
                    {
                        if(board[iToCheck][jToCheck] == sym)
                        {
                            iToCheck++;
                            jToCheck++;
                            curStreak++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(iToCheck<NUM_COLS && jToCheck<NUM_ROWS && board[iToCheck][jToCheck] == BLANK_CHAR && curStreak > longestOpenStreakSoFar)
                    {
                        //open space to right of streak
                        longestOpenStreakSoFar = curStreak;
                    }
                    if(i-1>=0 && j-1>=0 && board[i-1][j-1] == BLANK_CHAR &&curStreak > longestOpenStreakSoFar)
                    {
                        //open space to left of streak
                        longestOpenStreakSoFar = curStreak;
                    }

                    //check left diag
                    iToCheck = i-1;
                    jToCheck = j+1;
                    curStreak = 1;
                    streakOver = false;
                    while(!streakOver && iToCheck>=0 && jToCheck<NUM_ROWS)
                    {
                        if(board[iToCheck][jToCheck] == sym)
                        {
                            iToCheck--;
                            jToCheck++;
                            curStreak++;
                        }
                        else
                        {
                            streakOver = true;
                        }
                    }
                    if(iToCheck>=0 && jToCheck<NUM_ROWS && board[iToCheck][jToCheck] == BLANK_CHAR && curStreak > longestOpenStreakSoFar)
                    {
                        //open space to left of streak
                        longestOpenStreakSoFar = curStreak;
                    }
                    if(i+1<NUM_COLS && j-1>=0 && board[i+1][j-1] == BLANK_CHAR && curStreak > longestOpenStreakSoFar)
                    {
                        //open space to left of streak
                        longestOpenStreakSoFar = curStreak;
                    }
                }
            }
        }
        return longestOpenStreakSoFar;
    }

    //-1 if no winner
    public int currentWinner()
    {
        if(longestStreakForPlayer(1) >= 4)
        {
            return 1;
        }
        else if(longestStreakForPlayer(2) >= 4)
        {
            return 2;
        }
        return -1;
    }

    public boolean gameOver()
    {
        if(currentWinner() != -1)
        {
            return true;
        }
        else
        {
            for(int i=1; i<=NUM_COLS; i++)
            {
                if(colFree(i))
                {
                    return false;
                }
            }
        }
        return true;
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
