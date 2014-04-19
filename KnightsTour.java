//********************************************************************
//  KnightsTour.java       Author: Greg Murray (111919), Shaun Ready (111829)
//
//  This class generates a chess board based on inputs, then attempts
//  to find a path that allows a Knight chess piece to traverse the
//  board from a user defined starting position, visiting each position
//  on the board only once - returning a boolean based upon the outcome.
//********************************************************************

import java.text.DecimalFormat;
import java.util.*;

public class KnightsTour
{
	private final int TRIED = 99;	//Constant that marks an array entry when the knight has visited the position in its current path
	private int path;	//variable that marks the successful path in the order it was completed
	private int size;	//Total number of positions on the board
	private ArrayList<Square> movePath = new ArrayList<Square>();

	DecimalFormat fmt = new DecimalFormat("00");

	private int[][] board;	//Array that represents the chess board

	public KnightsTour(int boardX, int boardY)	//Constructor that gets the board's dimensions
	{
		board = new int[boardY][boardX];

		for(int count1 = 0; count1 < board.length; count1++)	//Initialize the board to 0 (Unvisited space)
			{
				for(int count2 = 0; count2 < board[count1].length; count2++)
				{
					board[count1][count2] = 0;
				}
			}
		path = boardX*boardY;
		size = boardX*boardY;

	}

	public boolean traverse(int row, int col)	//Recursive method to find a path for the knight
	{
		boolean done = false;
		int numVisited = 0;

		if(valid(row, col))	//Check to see if the position is valid (Within bounds, not visisted)
		{
			board[row][col] = TRIED;

			for(int count1 = 0; count1 < board.length; count1++)	//Check to see if every space has been visited, resulting in a successful tour
			{
				for(int count2 = 0; count2 < board[count1].length; count2++)
				{
					if(board[count1][count2] == TRIED)
						numVisited++;
				}
			}

			if(numVisited == size)	//If every space has been visited, mark as done
				done = true;
			else	//Otherwise, go to another space in to order determined below
			{
				done = traverse(row+2, col+1);//down 2, right 1
				if(!done)
					done = traverse(row+2, col-1);//down 2, left 1
				if(!done)
					done = traverse(row+1, col+2);//down 1, right 2
				if(!done)
					done = traverse(row+1, col-2);//down 1, left 2
				if(!done)
					done = traverse(row-2, col+1);//up 2, right 1
				if(!done)
					done = traverse(row-2, col-1);//up 2, left 1
				if(!done)
					done = traverse(row-1, col+2);//up 1, right 2
				if(!done)
					done = traverse(row-1, col-2);//up 1, left 2
			}

			if(done)	//If done, mark the space as part of the completed path, then continue up the recursion stack
			{
				board[row][col] = path;
				Square newSquare = new Square(col,row,path);
				movePath.add(newSquare);
				path--;
			}
			else	//Otherwise, unmark the space as visited (So as not to contribute to the total number of successful visits), and return to the previous position
			{
				board[row][col] = 0;
			}
		}

		return done;	//Return result
	}

	private boolean valid(int row, int col)	//Method to make sure the position is valid
	{
		boolean result = false;

		if(row >= 0 && row < board.length && col >= 0 && col < board[row].length)
			if(board[row][col] == 0)
				result = true;	//If the space is within the array bounds and has not been visited, then it is valid

		return result;
	}

	public Square[] getSolution()
	{
		Square[] solution = new Square[movePath.size()];

		for(int i=0; i < movePath.size(); i++)
			solution[i] = movePath.get(i);

		for(int i = 1; i < solution.length;i++)
		{
			Square key = solution[i];
			int position = i;

			while(position > 0 && solution[position-1].getOrder() > key.getOrder())
			{
				solution[position] = solution[position-1];
				position--;
			}
			solution[position] = key;
		}

		return solution;
	}

	public String toString()	//Method to print out board
	{
		String result = "\n";

		for(int count1 = 0; count1 < board.length; count1++)
		{
			for(int count2 = 0; count2 < board[count1].length; count2++)
				result += fmt.format(board[count1][count2]) + " ";	//Format each array entry to 2 places
			result += "\n";
		}

		return result;	//Return the string
	}


}