import java.util.Scanner;

/**
 * Created by Lucy on 1/4/2016.
 */
public class Main {
    public static void main(String[] args)
    {
        Game game = new Game();
        int result = game.runGame();
        if(result == -1)
        {
            System.out.println("DRAW!");
        }
        else
        {
            System.out.println("WINNER: PLAYER " + result + "!");
        }
    }
}
