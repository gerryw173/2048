/**
 * Created by 1137399 on 2/5/2018.
 */
public class Tester
{
    public static void main(String[] args)
    {
        int[][] board = new int[4][4];

        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                board[r][c] = 0;
            }
        }

        for (int c = 0; c < board[0].length; c++)
        {
            board[0][c] = 4;
            board[3][c] = 4;
        }

        _2048 kappa = new _2048(board);

        System.out.println(kappa);

        kappa.moveDown();

        System.out.println(kappa);

        for (int c = 0; c < board[0].length; c++)
        {
            board[0][c] = 8;
        }

        System.out.println(kappa);

        kappa.moveUp();

        System.out.println(kappa);
    }
}
