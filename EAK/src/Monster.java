
import ucigame.*;

//this class includes enemy projectiles, to make
//things simpler
public class Monster extends Sprite
{
	int type;
	int startFrame;
	int attackRate; //in frames
	int damage;
	int health;
	
	boolean gravity;
	boolean left;
	boolean right;
	boolean directionChange;
	
	public Monster(int t, int sf, Image img, int x, int y)
	{
		super(x, y);
		
		type = t;
		startFrame = sf;
		directionChange = false;
		
		attackRate = 0; //set to 0 here as a default for
						//monsters with no attacks (monsters
						//that only move, etc)
		
		//defaults
		gravity = false;
		left = true;
		right = false;
		
		switch(type)
		{
			case 0:
				this.addFrames(img,
						2, 3,
						22, 3,
						42, 3,
						61, 3,
						80, 3,
						100, 3,
						120, 3,
						138, 3);
				this.framerate(20);
				
				gravity = true;
				
				break;
		}
	}
	
	public int getStartFrame()
	{
		return startFrame;
	}
	
	public int getAttackRate()
	{
		return attackRate;
	}
	
	public boolean isGravity()
	{
		return gravity;
	}
	
	public void changeDirection()
	{
		left = !left;
		right = !right;
		directionChange = !directionChange;
	}
	
	public void draw()
	{
		if(directionChange)
		{
			this.flipHorizontal();
		}
		super.draw();
	}
	
	public void move()
	{
		switch(type)
		{
			case 0:
				standardGroundMove();
				break;
		}
	}
	
	private void standardGroundMove()
	{
		if(left)
		{
			this.motion(-2, 0);
		}
		else if(right)
		{
			this.motion(2, 0);
		}
		super.move();
	}
	
	public void handleFloorCollision(Sprite f)
	{
		switch(type)
		{
			case 0:
				standardFloorCollision(f);
				break;
		}
	}
	
	private void standardFloorCollision(Sprite f)
	{
		if(this.y() <= (f.y() - this.height()))
		{
			this.nextY(f.y() - this.height());
		}
		else
		{
			if(this.y() >= (f.y() + f.height()))
			{
				this.nextY(f.y() + f.height());
			}
			else
			{
				this.nextX(this.x());
				this.changeDirection();
			}
		}
	}
}