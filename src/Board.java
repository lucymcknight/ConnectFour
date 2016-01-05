/**
 * Created by Lucy on 1/4/2016.
 */
public class Board {
    //board has 7 cols, 6 rows
    char[][] board;

    public Board()
    {
        board = new char[7][6];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = '_';
            }
        }
    }

    public String toString()
    {
        String result = "| 1 2 3 4 5 6 7 |\n";
        for (int i = 5; i >= 0; i--)
        {
            result += "| ";
            for (int j = 0; j < 7; j++)
            {
                result += board[j][i] + " ";
            }
            result += "|\n";
        }
        return result;
    }
}
