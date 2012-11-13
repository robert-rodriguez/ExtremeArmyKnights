
import ucigame.*;
import java.util.ArrayList;

public class Main extends Ucigame
{
	//constants and other
	//global variable
	///////////
	
	int windowW = 500;
	int windowH = 400;
	
	int gravity = 3;
	int maxAir = 15;
	
	int speed = 4;
	
	int frameCounter = 0;
	
	boolean isGameplay = false;
	boolean isMarathon = true;
	
	int currentLevel = 0;
	int maxLevels = 2;
	
	boolean leftPressed = false;
	boolean rightPressed = false;
	
	//these get initialized in setup
	//(also, these are variables that
	//need to be re-initialized to a
	//specific starting state)
	///////////////////////////////
	
	Sprite player;
	int airTime;
	boolean isRunning;
	boolean isFacingLeft;
	boolean isFacingRight;
	boolean isFiring;
	
	ArrayList<Sprite> screens;
	int index;
	
	//floors are any non-enemy, background sprites that give jumps back
	ArrayList<Sprite> floors;
	int floorDrawLimit;
	
	
	//set up things
	public void setup()
	{
		window.size(windowW, windowH);
		window.title("Extreme Army Knights");
		
		framerate(60);
		
		player = makeSprite(34, 35);
		player.addFrames(getImage("images/player.png", 0, 255, 255),
				5, 3,
				61, 3,
				109, 3,
				153, 3,
				198, 3,
				256, 3,
				318, 3,
				375, 3,
				431, 3,
				493, 3,
				556, 3,
				619, 3); //12 frames added for standing/running
		player.addFrames(getImage("images/player.png", 0, 255, 255),
				9, 196,
				5, 265,
				69, 265,
				130, 265,
				188, 265,
				252, 265,
				313, 265,
				375, 265,
				445, 265,
				512, 265,
				579, 265); //11 frames added for firing standing/running
		player.framerate(24);
		player.defineSequence("Standing", 0);
		player.defineSequence("Running", 1,2,3,4,5,
				6,7,8,9,10,11);
		player.defineSequence("StandFiring", 12);
		player.defineSequence("RunFiring", 13,14,15,
				16,17,18,19,20,21,22);
		
		
		airTime = 0;
		isRunning = false;
		isFacingLeft = false;
		isFacingRight = true;
		isFiring = false;
		
		screens = new ArrayList<Sprite>();
		index = 0;
		
		floors = new ArrayList<Sprite>();
		floorDrawLimit = 0;
		
		//chooses the level
		switch(currentLevel)
		{
			case 0:
				level1Setup();
				break;
			case 1:
				level2Setup();
				break;
		}
	}
	
	
	/////////
	//Level 1
	/////////
	
	public void level1Setup()
	{
		//change and set the starting point for the player
		player.position(0, 324);
		
		//screens
		/////////
		
		Sprite screen1 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen1.position(0, 0);
		
		Sprite screen2 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen2.position(0, 0);
		
		Sprite screen3 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen3.position(0, 0);
		
		Sprite screen4 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen4.position(0, 0);
		
		Sprite screen5 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen5.position(0, 0);
		
		Sprite screen6 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen6.position(0, 0);
		
		Sprite screen7 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen7.position(0, 0);
		
		Sprite screen8 = makeSprite(getImage("images/level1_01.png", 0, 255, 255));
		screen8.position(0, 0);
		
		screens.add(screen1);
		screens.add(screen2);
		screens.add(screen3);
		//screens.add(screen4);
		//screens.add(screen5);
		//screens.add(screen6);
		//screens.add(screen7);
		//screens.add(screen8);
		
		//floors
		////////
		
		//1
		Sprite floor1_01 = makeSprite(getImage("images/floor1_01.png", 0, 255, 255));
		floor1_01.position(0, canvas.height() - floor1_01.height());
		
		Sprite floor1_02 = makeSprite(getImage("images/floor1_02.png", 0, 255, 255));
		floor1_02.position(canvas.width() - floor1_02.width(), canvas.height() - floor1_02.height());
		
		floors.add(floor1_01);
		floors.add(floor1_02);
		
		//2
		Sprite floor2_01 = makeSprite(getImage("images/floor1_03.png", 0, 255, 255));
		floor2_01.position(0, canvas.height() - floor2_01.height());
		
		Sprite floor2_02 = makeSprite(getImage("images/floor1_03.png", 0, 255, 255));
		floor2_02.position(270, canvas.height() - floor2_02.height());
		
		Sprite floor2_03 = makeSprite(getImage("images/floor1_03.png", 0, 255, 255));
		floor2_03.position(350, canvas.height() - floor2_03.height());
		
		Sprite platform2_01 = makeSprite(getImage("images/platform1_02.png", 0, 255, 255));
		platform2_01.position(200, 310);
		
		floors.add(floor2_01);
		floors.add(floor2_02);
		floors.add(floor2_03);
		floors.add(platform2_01);
		
		//3
		Sprite platform3_01 = makeSprite(getImage("images/platform1_01.png", 0, 255, 255));
		platform3_01.position(0, canvas.height() - platform3_01.height());
		
		Sprite platform3_02 = makeSprite(getImage("images/platform1_02.png", 0, 255, 255));
		platform3_02.position(platform3_01.x() + platform3_01.width() - platform3_02.width(), 340);
		
		Sprite floor3_01 = makeSprite(getImage("images/floor1_04.png", 0, 255, 255));
		floor3_01.position(platform3_01.x() + platform3_01.width(), canvas.height() - floor3_01.height());
		
		Sprite floor3_02 = makeSprite(getImage("images/floor1_04.png", 0, 255, 255));
		floor3_02.position(floor3_01.x() + floor3_01.width() + 120, canvas.height() - 60);
		
		floors.add(platform3_01);
		floors.add(platform3_02);
		floors.add(floor3_01);
		floors.add(floor3_02);
		
		//4
		Sprite floor4_01 = makeSprite(getImage("images/floor1_04.png", 0, 255, 255));
		floor4_01.position(0, canvas.height() - 60);
		
		floors.add(floor4_01);
		
		startScene("Level1");
	}
	
	public void drawLevel1()
	{
		canvas.clear();
		
		drawScreens();
		
		//draw the floors for the specified screen
		switch(index)
		{
			case 0:
				floorDrawLimit = 2;
				break;
			case 1:
				floorDrawLimit = 4;
				break;
			case 2:
				floorDrawLimit = 4;
				break;
			case 3:
				floorDrawLimit = 1;
				break;
			default:
				floorDrawLimit = 0;
		}
		
		drawFloors();
		
		animationCheck();
		
		playerMovement();
		
		floorCollision();
		
		screenChange();
		
		incrementFrames();
	}
	
	public void onKeyPressLevel1()
	{
		keyPressLevels();
	}
	
	
	/////////
	//Level 2
	/////////
	
	public void level2Setup()
	{
		startScene("Level2");
	}
	
	public void drawLevel2()
	{
		canvas.clear();
		
		//TODO level 2
		canvas.background(0, 255, 255);
	}
	
	public void onKeyPressLevel2()
	{
		keyPressLevels();
	}
	
	
	////////////////////
	//Level Functions
	////////////////////
	
	//Key press in levels handles here
	//////////////////////////////////
	public void keyPressLevels()
	{
		if(keyboard.isDown(keyboard.SPACE))
		{
			if(airTime < maxAir)
			{
				if(player.y() > 0)
				{
					player.motion(0, -2*speed, ADDONCE);
				}
				else
				{
					player.nextY(0);
				}
				
				airTime++;
			}
		}
		if(keyboard.isDown(keyboard.RIGHT))
		{
			isFacingLeft = false;
			isFacingRight = true;
			
			rightPressed = true;
			
			if(leftPressed)
			{
				isRunning = false;
			}
			else
			{
				isRunning = true;
			}
			
			player.motion(speed, 0, ADDONCE);
		}
		if(keyboard.isDown(keyboard.LEFT))
		{
			isFacingLeft = true;
			isFacingRight = false;
			
			leftPressed = true;
			
			if(rightPressed)
			{
				isRunning = false;
			}
			else
			{
				isRunning = true;
			}
			
			if(player.x() >= 4)
			{
				player.motion(-speed, 0, ADDONCE);
			}
			else
			{
				player.nextX(0);
			}
		}
		if(keyboard.isDown(keyboard.F))
		{
			isFiring = true;
		}
	}
	
	//screen drawing
	////////////////
	public void drawScreens()
	{
		if(index < screens.size() && !screens.isEmpty())
		{
			screens.get(index).draw();
		}
	}
	
	//floor drawing
	///////////////
	public void drawFloors()
	{
		for(int i = 0; i < floorDrawLimit; i++)
		{
			floors.get(i).draw();
		}
	}
	
	//animation check
	/////////////////
	public void animationCheck()
	{
		if(!isRunning)
		{
			if(isFacingLeft)
			{
				player.flipHorizontal();
			}
			
			if(isFiring)
			{
				player.play("StandFiring");
			}
			else
			{
				player.play("Standing");
			}
			
			player.draw();
		}
		else
		{
			if(isFacingLeft)
			{
				player.flipHorizontal();
			}
			
			if(isFiring)
			{
				player.play("RunFiring");
			}
			else
			{
				player.play("Running");
			}
			
			player.draw();
		}
		
		isRunning = false;
		isFiring = false;
		
		leftPressed = false;
		rightPressed = false;
	}
	
	//player movement and gravity effect
	////////////////////////////////////
	public void playerMovement()
	{
		player.move();
		
		if(player.y() > canvas.height())
		{
			player.nextY(canvas.height());
			
			//restarts game
			setup();
		}
		else
		{
			player.motion(0, speed);
		}
		
		if(player.yspeed() < -8)
		{
			player.motion(player.xspeed(), 0);
		}
	}
	
	//collisions with floors
	////////////////////////
	public void floorCollision()
	{
		for(int f = 0; f < floorDrawLimit; f++)
		{
			player.checkIfCollidesWith(floors.get(f));
			if(player.collided())
			{
				if(player.y() <= (floors.get(f).y() - player.height()))
				{
					player.nextY(floors.get(f).y() - player.height());
					airTime = 0;
				}
				else
				{
					if(player.y() >= (floors.get(f).y() + floors.get(f).height()))
					{
						if(airTime < maxAir)
						{
							player.nextY(floors.get(f).y() + floors.get(f).height());
							airTime = maxAir;
						}
					}
					else
					{
						player.nextX(player.x());
					}
				}
			}
		}
	}
	
	//screen change is handled here
	///////////////////////////////
	public void screenChange()
	{
		if(player.x() >= canvas.width())
		{
			if(index < screens.size())
			{
				index++;
			}
			
			player.position(0, player.y());
			
			//remove previous floors
			for(int a = 0; a < floorDrawLimit; a++)
			{
				floors.remove(0);
			}
			
			//changes to score menu at level's end
			if(index >= screens.size())
			{
				startScene("ScoreMenu");
			}
		}
	}
	
	//frame counter increment
	/////////////////////////
	public void incrementFrames()
	{
		frameCounter++;
		
		if(frameCounter % 2400 == 0)
		{
			frameCounter = 0;
		}
	}
	
	///////////////
	//Menus
	//////////////
	
	//Score Menu
	////////////
	public void drawScoreMenu()
	{
		canvas.clear();
		
		//TODO score menu
		canvas.background(0, 0, 0);
	}
	
	public void onKeyPressScoreMenu()
	{
		if(keyboard.isDown(keyboard.ENTER))
		{
			if(isMarathon && currentLevel < maxLevels - 1)
			{
				currentLevel++;
				setup();
			}
			else
			{
				currentLevel = 0;
				
				//TODO go to main menu
			}
		}
	}
}