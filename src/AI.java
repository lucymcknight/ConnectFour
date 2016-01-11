import java.util.ArrayList;

/**
 * Created by Lucy on 1/9/2016.
 */
public class AI
{
    private int numMovesToLookAhead = 1;

    public int getMoveForBoardAndPlayer(Board inBoard, int player)
    {
        return getMoveForBoardAndPlayer(inBoard, player, numMovesToLookAhead);
    }

    private int getMoveForBoardAndPlayer(Board inBoard, int player, int numMovesToLookAhead)
    {
        int otherPlayer = 1;
        if(player == 1)
        {
            otherPlayer = 2;
        }

        int bestMove=0;
        double bestMoveRating = 0;

        for(int i=1; i<=7; i++)
        {
            if(inBoard.colFree(i))
            {
                Board curBoard = new Board(inBoard);
                curBoard.addPiece(player, i);

                double curMoveRating = 0;
                /*if(numMovesToLookAhead == 1)
                {
                */
                    curMoveRating = getEstimatedRatingAfterOpponentMoves(curBoard, player);
                /*}
                else
                {
                    int otherPlayersMove = getMoveForBoardAndPlayer(curBoard, otherPlayer, 1);
                    curBoard.addPiece(otherPlayer, otherPlayersMove);
                    curMoveRating = getEstimatedRatingAfterIMove(curBoard, player);
                }

                if(player == 2 && numMovesToLookAhead == this.numMovesToLookAhead)
                {
                    System.out.println("[P" + player + "] I think that if I choose " + i + " " +
                            "then my avg payoff will be " + curMoveRating);
                }
                */

                if (bestMove == 0 || curMoveRating > bestMoveRating) {
                    bestMoveRating = curMoveRating;
                    bestMove = i;
                }
                else if(curMoveRating == bestMoveRating)
                {
                    if(Math.random() > .5)
                    {
                        bestMoveRating = curMoveRating;
                        bestMove = i;
                    }
                }
            }
        }
        return bestMove;
    }

    //(weights best outcome higher)
    private double getEstimatedRatingAfterIMove(Board inBoard, int player)
    {
        double curMoveRating;
        int otherPlayer = 1;
        if(player == 1)
        {
            otherPlayer = 2;
        }

        double bestOutcome=Integer.MIN_VALUE;
        ArrayList<Double> results = new ArrayList<Double>();
        double rating;
        for(int i=1; i<=7; i++)
        {
            //
            if (inBoard.colFree(i)) {
                Board curBoard = new Board(inBoard);
                curBoard.addPiece(player, i);
                rating = getEstimatedRatingAfterOpponentMoves(curBoard, player);
                results.add(rating);
                if (rating > bestOutcome) {
                    bestOutcome = rating;
                }
            }
        }
        if(results.size() > 0)
        {
            curMoveRating = 2*results.size() * bestOutcome;
            for (Double r : results)
            {
                curMoveRating += r;
            }
            curMoveRating /= (3 * results.size());
        }
        else
        {
            curMoveRating = getRatingForBoardAndPlayer(inBoard, player);
        }
        return curMoveRating;
    }


    //(weights the worst possible outcome highest)
    private double getEstimatedRatingAfterOpponentMoves(Board inBoard, int player)
    {
        double curMoveRating;
        int otherPlayer = 1;
        if(player == 1)
        {
            otherPlayer = 2;
        }
        double worstOutcome=Integer.MAX_VALUE;
        ArrayList<Double> results = new ArrayList<Double>();
        double rating;
        for(int j=1;j<=7;j++) {
            if (inBoard.colFree(j)) {
                Board curBoard = new Board(inBoard);
                curBoard.addPiece(otherPlayer, j);
                rating = getRatingForBoardAndPlayer(curBoard, player);
                results.add(rating);
                if (rating < worstOutcome) {
                    worstOutcome = rating;
                }
            }
        }
        if(results.size() > 0)
        {
            curMoveRating = 2*results.size() * worstOutcome;
            for (Double r : results)
            {
                curMoveRating += r;
            }
            curMoveRating /= (3 * results.size());
        }
        else
        {
            curMoveRating = getRatingForBoardAndPlayer(inBoard, player);
        }
        return curMoveRating;
    }

    //higher is better
    private double getRatingForBoardAndPlayer(Board board, int player)
    {
        int otherPlayer = 1;
        if(player == 1)
        {
            otherPlayer = 2;
        }

        if(board.longestStreakForPlayer(player) >= 4)
        {
            return 100;
        }
        else if (board.longestStreakForPlayer(otherPlayer) >= 4)
        {
            return -100;
        }
        else
        {
            return 1.5*board.longestOpenStreakForPlayer(player) - board.longestOpenStreakForPlayer(otherPlayer);
        }
    }
}
