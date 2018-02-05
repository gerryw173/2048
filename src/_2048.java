import java.util.Random;
import java.util.ArrayList;

public class _2048
{
	private final int rows = 4;
	private final int cols = 4;
	private int[][] board;
	private int[][] previousBoard;
	private int score;
	private int previousScore;
	
	/**
	 * Initializes board and previousBoard using rows and cols.
	 * Uses the generateTile method to add two random tiles to board.
	 */
	public _2048()
	{
		board = new int[4][4];

		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				board[r][c] = 0;
			}
		}

		previousBoard = new int[4][4];
	}
	
	/**
	 * Initializes the board of this object using the specified board.
	 * Initializes previousBoard using rows and cols.
	 * 
	 * Precondition: the specified board is a 4x4 2D Array.
	 * 
	 * @param board
	 */
	public _2048(int[][] board)
	{
		this.board = board;

		previousBoard = new int[4][4];

//		for (int r = 0; r < board.length; r++)
//		{
//			for (int c = 0; c < board[r].length; c++)
//			{
//				previousBoard[r][c] = board[r][c];
//			}
//		}
	}
	
	/**
	 * Generates a tile and add it to an empty spot on the board.
	 * 80% chance to generate a 2
	 * 20% chance to generate a 4
	 * 
	 * Does nothing if the board is full.
	 */
	private void generateTile()
	{
		Random randy = new Random();
		int tile = 0;
		int chance = randy.nextInt(11) + 1;

		if(chance <= 2)
		{
			tile = 4;
		}

		else if(chance > 2)
		{
			tile = 2;
		}

		int randomRow = randy.nextInt(4);
		int randomColumn = randy.nextInt(4);

		while(board[randomRow][randomColumn] != 0)
		{
			randomRow = randy.nextInt(4);
			randomColumn = randy.nextInt(4);
		}

		board[randomRow][randomColumn] = tile;
	}
	
	/**
	 * Returns false if the board contains a 0, true otherwise.
	 * @return
	 */
	private boolean full()
	{
		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				if(board[r][c] == 0)
				{
					return false;
				}
			}
		}

		return true;
	}
	
	/**
	 * Returns the board.
	 * @return
	 */
	public int[][] getBoard()
	{
		return board;
	}
	
	/**
	 * Returns the score.
	 * @return
	 */
	public int getScore()
	{
		int score = 0;

		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				score += board[r][c];
			}
		}

		return score;
	}
	
	/**
	 * Saves board into previousBoard and score into previousScore
	 * then performs a move based on the specified direction:
	 * 
	 * Valid directions (not case sensitive):
	 *  up
	 *  down
	 *  left
	 *  right
	 *  
	 * Adds a new tile to the board using the generateTile method.
	 * 
	 * @param direction
	 */
	public void move(String direction)
	{
		// saves current board into previousBoard
		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				previousBoard[r][c] = board[r][c];
			}
		}
	}
	
	/**
	 * Shifts all the tiles up, combines like tiles that collide.
	 */
	private void moveUp()
	{
		ArrayList<Integer> column = new ArrayList<Integer>();

		for (int c = 0; c < board[0].length; c++)
		{
			// fills out column with the current row
			for (int r = 0; r < board.length; r++)
			{
				column.add(board[r][c]);
			}

			for (int i = column.size(); i >= 0; i--)
			{
				if(i == 0)
				{

				}

				//fix
				else if(column.get(i) == column.get(i - 1))
				{
					column.set(i - 1, column.get(i) * 2);
					column.remove(i);
				}
			}
		}
	}
	
	/**
	 * Shifts all the tiles down, combines like tiles that collide.
	 */
	private void moveDown()
	{
	
	}
	
	/**
	 * Shifts all the tiles left, combines like tiles that collide.
	 */
	private void moveLeft()
	{

	}
	
	/**
	 * Shifts all the tiles right, combines like tiles that collide.
	 */
	private void moveRight()
	{

	}
	
	/**
	 * Sets board to previousBoard and score to previousScore
	 */
	public void undo()
	{
		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				board[r][c] = previousBoard[r][c];
			}
		}
	}
	
	/**
	 * Returns true if the game is over, false otherwise.
	 * @return
	 */
	public boolean gameOver()
	{
		//try to move in all directions
		return false;
	}
	
	/**
	 * Returns a String representation of this object.
	 */
	public String toString()
	{
		String rtn = "";
		
		for(int[] row : board)
		{
			rtn += "|";
			for(int num : row)
				if(num != 0)
				{
					String str = num + "";
					while(str.length() < 4)
						str = " " + str;
					rtn += str;
					
				}
				else
					rtn += "    ";
			rtn += "|\n";
		}
		
		rtn += "Score: " + score + "\n";
		
		return rtn;
	}
}
