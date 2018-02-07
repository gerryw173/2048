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
	public void generateTile()
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
	public boolean full()
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
	public void moveUp()
	{
		ArrayList<Integer> column = new ArrayList<Integer>();

		for (int c = 0; c < board[0].length; c++)
		{
			// fills out column ArrayList with the current column
			for (int r = 0; r < board.length; r++)
			{
				column.add(board[r][c]);
			}

			// removes all the 0's to make it easier to add em together
			for (int i = column.size() - 1; i >= 0; i--)
			{
				if(column.get(i) == 0)
				{
					column.remove(i);
				}
			}

			for (int i = 0; i < column.size() - 1; i++)
			{
				Integer x = column.get(i); // current Integer

				if(x == column.get(i+1)) // if current Integer is equal to the next one
				{
					column.set(i, x + column.get(i + 1));
					column.remove(i + 1); // after adding it removes it to get rid of duplicates
				}
			}

			int length = board.length - column.size(); // difference in the board length and column size to add in 0s later to column

			for (int i = column.size() - 1; i < board.length; i++)
			{
				column.add(0);
			}

			for (int r = 0; r < board.length; r++)
			{
				board[r][c] = column.get(r);
			}

			column.clear(); // allows next iteration of loop to add new elements
		}
	}
	
	/**
	 * Shifts all the tiles down, combines like tiles that collide.
	 */
	public void moveDown()
	{
		ArrayList<Integer> column = new ArrayList<Integer>();

		for (int c = 0; c < board[0].length; c++)
		{
			// fills out column ArrayList with the current column
			for (int r = 0; r < board.length; r++)
			{
				column.add(board[r][c]);
			}

			// removes all the 0's to make it easier to add em together
			for (int i = column.size() - 1; i >= 0; i--)
			{
				if(column.get(i) == 0)
				{
					column.remove(i);
				}
			}

			for (int i = 0; i < column.size() - 1; i++)
			{
				Integer x = column.get(i); // current Integer

				if(x == column.get(i+1)) // if current Integer is equal to the next one
				{
					column.set(i, x + column.get(i + 1));
					column.remove(i + 1); // after adding it removes it to get rid of duplicates
				}
			}

			int length = board.length - column.size(); // difference in the board length and column size to add in 0s later to column

			for (int i = 0; i < length; i++)
			{
				column.add(0,0); //adds 0 in the front
			}

			for (int r = board.length - 1; r >= 0; r--)
			{
				board[r][c] = column.get(r);
			}

			column.clear(); // allows next iteration of loop to add new elements
		}
	}
	
	/**
	 * Shifts all the tiles left, combines like tiles that collide.
	 */
	private void moveLeft()
	{
		ArrayList<Integer> row = new ArrayList<Integer>();

		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[0].length; c++)
			{
				row.add(board[r][c]);
			}

			// removes all the 0's to make it easier to add em together
			for (int i = row.size() - 1; i >= 0; i--)
			{
				if(row.get(i) == 0)
				{
					row.remove(i);
				}
			}
		}
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
