//********************************************************************
//  KnightTester.java       Author: Greg Murray (111919), Shaun Ready (111829)
//
//  A driver program for the KnightsTour.java class. This class gathers
//  user input for the board size and the knight's initial starting
//  position, then creates a KnightsTour object and calls the traverse
//  method, display the results once its completed.
//********************************************************************

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class KnightTester
{
	private static boolean error = false;

	public static void main (String[] args)
	{
		int startX = 0, startY = 0;	//Variables for initial starting position
		int boardX = 6, boardY = 6;	//Variables for board dimensions

		do{
			error = false;
			try
			{
				String strSize0 = JOptionPane.showInputDialog("Enter the board size:");	//Get board dimensions from user
				String strSize1 = "", strSize2 = "";

				int i = 0;
				while(strSize0.charAt(i) != ' ')
				{
					strSize1 = strSize1 + strSize0.charAt(i);
					i++;
				}
				strSize2 = strSize0.substring(i+1);
				boardY = Integer.parseInt(strSize1);
				boardX = Integer.parseInt(strSize2);
			}
			catch(NumberFormatException ex)
			{
				error = true;
			}
			catch(NullPointerException npe)
			{
				System.exit(1);
			}
		}while(error == true || boardX <= 0 || boardY <= 0);	//Check for negative values

		KnightsTour knight = new KnightsTour(boardX, boardY);

		Board board = new Board(boardX, boardY);

		do{
			error = false;
			String strStart0 = JOptionPane.showInputDialog("Enter the starting positon:");	//Get starting positon from user
			String strStart1 = "", strStart2 = "";

			int i = 0;
			while(strStart0.charAt(i) != ' ')
				strStart1 = strStart1 + strStart0.charAt(i++);

			strStart2 = strStart0.substring(i+1);

			try
			{
				startX = Integer.parseInt(strStart1) - 1;
				startY = Integer.parseInt(strStart2) - 1;
			}
			catch(Exception ex)
			{
				error = true;
			}
		}while(error == true || startX < 0 || startY < 0 || startX >= boardX || startY >= boardY);	//Check for negatives

		if (knight.traverse (startX, startY))	//Display results
		{
			Square[] solution = knight.getSolution();

			board.setPosition(solution[0]);
			for(int i=1; i < solution.length; i++)
			{
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				board.move(solution[i-1],solution[i]);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"No Solution.");
			System.exit(1);
		}
	}
}
