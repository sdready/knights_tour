import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class BoardPanel extends JPanel
{
	private final int SQUARE = 100;
	private int xSize, ySize, xPos, yPos;
	private Image image;
	private ImageIcon icon;

	public BoardPanel(int x, int y)
	{
		xSize = x;
		ySize = y;
		xPos = 0;
		yPos = 0;

		icon = new ImageIcon("knight.png");
		image = icon.getImage();

		setPreferredSize(new Dimension(x*SQUARE,y*SQUARE));
	}

	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);

		for(int i=0; i < xSize; i++)
		{
			xPos = i * SQUARE;
			for(int j=0; j < ySize; j++)
			{
				yPos = j * SQUARE;
				if((i+j)%2 == 0)
					page.setColor(Color.white);
				else
					page.setColor(Color.gray);
				page.fillRect(xPos,yPos,SQUARE,SQUARE);
			}
		}
	}

	public void move(Square start, Square end)
	{
		Graphics page = this.getGraphics();

		if((start.getX()+start.getY()) % 2 == 0)
			page.setColor(Color.white);
		else
			page.setColor(Color.gray);

		page.fillRect(start.getX()*SQUARE, start.getY()*SQUARE,SQUARE,SQUARE);

		page.drawImage(image,(end.getX()*SQUARE)+10,
			(end.getY()*SQUARE)+10,null);

		page.setColor(Color.red);
		String temp1 = "" + start.getOrder();
		String temp2 = "" + end.getOrder();
		char[]info1 = new char[temp1.length()];
		char[] info2 = new char[temp2.length()];

		for(int i=0; i < temp1.length(); i++)
			info1[i] = temp1.charAt(i);
		for(int i=0; i < temp2.length(); i++)
			info2[i] = temp2.charAt(i);

		page.drawChars(info1, 0, info1.length,
			(start.getX()*SQUARE)+45, (start.getY()*SQUARE)+75);
		page.drawChars(info2, 0, info2.length,
			(end.getX()*SQUARE)+45, (end.getY()*SQUARE)+75);
	}

	public void setPosition(Square position)
	{
		Graphics page = this.getGraphics();

		page.drawImage(image,(position.getX()*SQUARE)+10,
			(position.getY()*SQUARE)+10,null);

		String temp = "" + position.getOrder();
		char[] info = new char[temp.length()];
		for(int i=0; i < temp.length(); i++)
			info[i] = temp.charAt(i);

		page.setColor(Color.red);
		page.drawChars(info, 0, info.length,
			(position.getX()*SQUARE)+45, (position.getY()*SQUARE)+75);
	}
}