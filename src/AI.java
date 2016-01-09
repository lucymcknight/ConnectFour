/**
 * Created by Lucy on 1/9/2016.
 */
public class AI
{
    public int getMoveForBoardAndPlayer(Board inBoard, int player)
    {
        int bestMove=0;
        int bestMoveRating = 0;
        for(int i=1; i<=7; i++)
        {
            if(inBoard.colFree(i))
            {
                Board curBoard = new Board(inBoard);
                curBoard.addPiece(player, i);
                int curMoveRating = getRatingForBoardAndPlayer(curBoard, player);
                if (bestMove == 0 || curMoveRating > bestMoveRating) {
                    bestMoveRating = curMoveRating;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    //higher is better
    private int getRatingForBoardAndPlayer(Board board, int player)
    {
        int otherPlayer = 1;
        if(player == 1)
        {
            otherPlayer = 2;
        }

        if(board.longestStreakForPlayer(player) >= 4)
        {
            return 10;
        }
        else if (board.longestStreakForPlayer(otherPlayer) >= 4)
        {
            return -10;
        }
        else
        {
            return board.longestOpenStreakForPlayer(player) - board.longestOpenStreakForPlayer(otherPlayer);
        }
    }
}
