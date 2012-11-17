
import ucigame.*;

public class Bullet extends Sprite
{
	int direction; //0 is right, 1 is left
	
	int type;
	int startFrame;
	int fireRate; //in frames
	int damage;
	
	public Bullet(int t, int sf, int d, Image img)
	{
		
		super(40, 32);
		
		type = t;
		startFrame = sf;
		direction = d;
		fireRate = 0; //default
		
		switch(type)
		{
			case 0:
				fireRate = 20;
				damage = 1;
				this.addFrames(img,
						0, 7,
						44, 7,
						89, 7,
						152, 7);
				this.framerate(30);
				break;
			case 1:
				fireRate = 15;
				damage = 2;
				this.addFrames(img,
						0, 70,
						45, 70,
						95, 70,
						147, 70,
						200, 70,
						253, 70);
				this.framerate(20);
				break;
		}
	}
	
	public int getStartFrame()
	{
		return startFrame;
	}
	
	public int getFireRate()
	{
		return fireRate;
	}
	
	public void move()
	{
		switch(type)
		{
			case 0:
				defaultMove();
				break;
			case 1:
				twinMove();
				break;
		}
	}
	
	private void defaultMove()
	{
		int defaultSpeed = 7;
		
		if(direction == 0)
		{
			this.motion(defaultSpeed, 0);
		}
		else if(direction == 1)
		{
			this.motion(-defaultSpeed, 0);
		}
		
		super.move();
	}
	
	private void twinMove()
	{
		int twinSpeed = 8;
		
		if(direction == 0)
		{
			this.motion(twinSpeed, 0);
		}
		else if(direction == 1)
		{
			this.motion(-twinSpeed, 0);
		}
		
		super.move();
	}
	
	public void draw()
	{
		if(direction == 1)
		{
			this.flipHorizontal();
		}
		
		super.draw();
	}
}
