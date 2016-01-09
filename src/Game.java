import java.util.Scanner;

/**
 * Created by Lucy on 1/8/2016.
 */
public class Game
{
    private Board board;
    private boolean playersTurn;
    private Scanner input;

    public Game()
    {
        board = new Board();
        playersTurn = true;
        input = new Scanner(System.in);
    }



    public int runGame()
    {
        while(!board.gameOver())
        {
            System.out.println(board);
            if(playersTurn)
            {
                takePlayer1Turn();
            }
            else
            {
                takePlayer2Turn();
            }
            playersTurn = !playersTurn;
        }
        System.out.println(board);
        return board.currentWinner();
    }

    public void takePlayer1Turn()
    {
        System.out.println("[P1] Enter a valid move.");
        while(!board.addPiece(1, input.nextInt()))
        {
            System.out.println("[P1] Enter a valid move.");
        }
    }

    public void takePlayer2Turn()
    {
        System.out.println("[P2] Enter a valid move.");
        while(!board.addPiece(2, input.nextInt()))
        {
            System.out.println("[P2] Enter a valid move.");
        }
    }
}
