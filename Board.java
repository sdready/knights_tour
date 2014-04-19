import java.awt.*;
import javax.swing.*;

public class Board
{
	private BoardPanel boardPanel;
	public Board(int x, int y)
	{
		JFrame frame = new JFrame("Knight's Tour");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boardPanel = new BoardPanel(x,y);
		frame.getContentPane().add(boardPanel);
		frame.pack();
		frame.setVisible(true);
	}

	public void move(Square start, Square end)
	{
		boardPanel.move(start,end);
	}

	public void setPosition(Square position)
	{
		boardPanel.setPosition(position);
	}
}