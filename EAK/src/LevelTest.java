

import ucigame.*;
import java.util.ArrayList;

public class LevelTest extends Ucigame
{
	//constants
	int windowW = 400;
	int windowH = 300;
	
	int gravity = 4;
	int maxAir = 10;
	
	Sprite square;
	int airTime;
	
	ArrayList<Sprite> screens = new ArrayList<Sprite>();
	int index;
	
	//floors are any non-enemy, background sprites that give jumps back
	ArrayList<Sprite> floors = new ArrayList<Sprite>();
	
	
	public void setup()
	{
		window.size(windowW, windowH);
		window.title("Level Testing");
		
		framerate(60);
		
		square = makeSprite(getImage("art/square.png", 0, 255, 255));
		square.position(0, 150);
		
		airTime = 0;
		
		Sprite screen1 = makeSprite(getImage("art/screen1.png", 0, 255, 255));
		screen1.position(0, 0);
		
		Sprite screen2 = makeSprite(getImage("art/screen2.png", 0, 255, 255));
		screen2.position(0, 0);
		
		Sprite floor1 =  makeSprite(getImage("art/floor.png", 0, 255, 255));
		floor1.position(0, canvas.height() - floor1.height());
		
		Sprite floor2 =  makeSprite(getImage("art/floor.png", 0, 255, 255));
		floor2.position(canvas.width() - floor2.width(), canvas.height() - floor2.height());
		
		Sprite floor3 =  makeSprite(getImage("art/platform.png", 0, 255, 255));
		floor3.position(canvas.width()/2 - floor3.width()/2, 4*canvas.height()/5);
		
		index = 0;
		screens.add(screen1);
		screens.add(screen2);
		screens.add(screen1);
		screens.add(screen2);
		
		floors.add(floor1);
		floors.add(floor2);
		floors.add(floor3);
		
		startScene("PlayTest");
	}
	
	public void drawPlayTest()
	{
		canvas.clear();
		
		if(index < screens.size() && !screens.isEmpty())
		{
			screens.get(index).draw();
		}
		
		for(int i = 0; i < floors.size(); i++)
		{
			floors.get(i).draw();
		}
		
		square.draw();
		
		//player movement and gravity
		square.move();
		
		if(square.y() > canvas.height())
		{
			square.nextY(canvas.height());
			
			//restarts game
			setup();
		}
		else
		{
			square.motion(0, 4);
		}
		
		if(square.yspeed() < -8)
		{
			square.motion(square.xspeed(), 0);
		}
		
		//collisions with floors
		for(int f = 0; f < floors.size(); f++)
		{
			square.checkIfCollidesWith(floors.get(f));
			if(square.collided())
			{
				if(square.y() <= (floors.get(f).y() - square.height()))
				{
					//square.nextY((canvas.height() - floors.get(f).height()) - square.height());
					square.nextY(floors.get(f).y() - square.height());
					//square.motion(square.xspeed(), 0);
					airTime = 0;
				}
				else
				{
					if(square.y() >= (floors.get(f).y() + floors.get(f).height()))
					{
						//accounting for ceiling-like collisions
						//consider platforms you can jump through
					}
					else
					{
						square.nextX(square.x());
					}
				}
			}
		}
		
		//screen change is handled here
		if(square.x() >= canvas.width())//- square.width())
		{
			if(index < screens.size() - 1)
			{
				index++;
			}
			square.position(0, square.y());
		}
	}
	
	public void onKeyPressPlayTest()
	{
		//if(keyboard.isDown(keyboard.UP))
		//up may be used for some other things later
		if(keyboard.isDown(keyboard.SPACE))
		{
			if(airTime < maxAir)
			{
				if(square.y() > 0)
				{
					square.motion(0, -8, ADDONCE);
				}
				else
				{
					//square.position(square.x(), 0);
					square.nextY(0);
				}
				
				airTime++;
			}
		}
		if(keyboard.isDown(keyboard.DOWN))
		{
			//down many be used for other things later
			/*if(square.y() < canvas.height() - square.height())
			{
				square.motion(0, 4, ADDONCE);
			}
			else
			{
				square.position(square.x(), canvas.height() - square.height());
			}*/
		}
		if(keyboard.isDown(keyboard.RIGHT))
		{
			square.motion(4, 0, ADDONCE);
		}
		if(keyboard.isDown(keyboard.LEFT))
		{
			if(square.x() >= 4)
			{
				square.motion(-4, 0, ADDONCE);
			}
			else
			{
				//square.position(0, square.y() + gravity);
				//square.motion(0, square.yspeed());
				square.nextX(0);
			}
		}
	}
}