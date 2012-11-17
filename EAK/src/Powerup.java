
import ucigame.*;

public class Powerup extends Sprite
{
	int type;
	
	public Powerup(int t, Image img, int x, int y)
	{
		super(x, y);
		
		type = t;
		
		switch(type)
		{
			case 0:
				this.addFrames(img,
						20, 0);
				break;
			case 1:
				this.addFrames(img,
						0, 0);
				break;
		}
	}
	
	public int getType()
	{
		return type;
	}
}