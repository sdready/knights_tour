public class Square
{
	private int xPos,yPos,order;

	public Square()
	{
	}

	public Square(int x, int y, int o)
	{
		xPos = x;
		yPos = y;
		order = o;
	}

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public int getOrder()
	{
		return order;
	}
}