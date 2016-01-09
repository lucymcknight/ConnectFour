import java.util.Scanner;

/**
 * Created by Lucy on 1/4/2016.
 */
public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!\n\n");

        Board b = new Board();

        Scanner sc = new Scanner(System.in);
        boolean pl1 = true;
        while(true)
        {
            System.out.println(b);
            boolean success = false;
            if(pl1)
            {
                success = b.addPiece(1,sc.nextInt());
            }
            else
            {
                success = b.addPiece(2,sc.nextInt());
            }
            if(success)
            {
                pl1 = !pl1;
            }
        }
    }
}
