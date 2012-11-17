
import ucigame.*;

import java.util.ArrayList;

public class Main extends Ucigame
{
	//constants and other
	//global variable;
	///////////

	int windowW = 1279;
	int windowH = 300;

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
		player.position(0, 200);

		//screens
		/////////

		Sprite screen1 = makeSprite(getImage("images/level1/screen1/Level1_Screen1.png", 0, 255, 255));
		screen1.position(0, 0);

		Sprite screen2 = makeSprite(getImage("images/level1/screen2/Level1_Screen2.png", 0, 255, 255));
		screen2.position(0, 0);

		Sprite screen3 = makeSprite(getImage("images/level1/screen3/Level1_Screen3.png", 0, 255, 255));
		screen3.position(0, 0);

		Sprite screen4 = makeSprite(getImage("images/level1/screen4/Level1_Screen4.png", 0, 255, 255));
		screen4.position(0, 0);

		Sprite screen5 = makeSprite(getImage("images/level1/screen5/Level1_Screen5.png", 0, 255, 255));
		screen5.position(0, 0);

		Sprite screen6 = makeSprite(getImage("images/level1/screen6/Level1_Screen6.png", 0, 255, 255));
		screen6.position(0, 0);

		Sprite screen7 = makeSprite(getImage("images/level1/screen7/Level1_Screen7.png", 0, 255, 255));
		screen7.position(0, 0);

		Sprite screen8 = makeSprite(getImage("images/level1/screen8/Level1_Screen8.png", 0, 255, 255));
		screen8.position(0, 0);

		screens.add(screen1);
		screens.add(screen2);
		screens.add(screen3);
		screens.add(screen4);
		screens.add(screen5);
		screens.add(screen6);
		screens.add(screen7);
		screens.add(screen8);

		//floors
		////////

		//Screen 1 Floors
		//Sprite floor1_01 = makeSprite(getImage("images/floor1_01.png", 0, 255, 255));
		//floor1_01.position(0, canvas.height() - floor1_01.height());

		//JD
		int position = 0;
		Sprite floor1_01 =  makeSprite(getImage("images/level1/screen1/floor1_1.png", 0, 255, 255));
		floor1_01.position(position, canvas.height() - floor1_01.height());
		position+=floor1_01.width();

		Sprite floor1_02 = makeSprite(getImage("images/level1/screen1/floor1_2.png", 0, 255, 255));
		floor1_02.position(position, canvas.height() - floor1_02.height());
		position+=floor1_02.width();

		Sprite floor1_03 = makeSprite(getImage("images/level1/screen1/floor1_3.png", 0, 255, 255));
		floor1_03.position(position, canvas.height() - floor1_03.height());
		position+=floor1_03.width();

		Sprite floor1_04 = makeSprite(getImage("images/level1/screen1/floor1_4.png", 0, 255, 255));
		floor1_04.position(position, canvas.height() - floor1_04.height());
		position+=floor1_04.width();

		Sprite floor1_05 = makeSprite(getImage("images/level1/screen1/floor1_5.png", 0, 255, 255));
		floor1_05.position(position, canvas.height() - floor1_05.height());
		position+=floor1_05.width();

		Sprite floor1_06 = makeSprite(getImage("images/level1/screen1/floor1_6.png", 0, 255, 255));
		floor1_06.position(1098, 167);
		position+=floor1_06.width();

		Sprite floor1_07 = makeSprite(getImage("images/level1/screen1/floor1_7.png", 0, 255, 255));
		floor1_07.position(canvas.width() - floor1_07.width(), canvas.height() - floor1_07.height());

		floors.add(floor1_01);
		floors.add(floor1_02);
		floors.add(floor1_03);
		floors.add(floor1_04);
		floors.add(floor1_05);
		floors.add(floor1_06);
		floors.add(floor1_07);

		//Screen 2 Floors
		position = 0;
		Sprite floor2_01 = makeSprite(getImage("images/level1/screen2/floor2_1.png", 0, 255, 255));
		floor2_01.position(0, canvas.height() - floor2_01.height());

		Sprite floor2_02 = makeSprite(getImage("images/level1/screen2/floor2_2.png", 0, 255, 255));
		floor2_02.position(118, canvas.height() - floor2_02.height());
		position += floor2_02.width()+118;//special case pit in between

		Sprite floor2_03 = makeSprite(getImage("images/level1/screen2/floor2_3.png", 0, 255, 255));
		floor2_03.position(position, canvas.height() - floor2_03.height());
		position += floor2_03.width();

		Sprite floor2_04 = makeSprite(getImage("images/level1/screen2/floor2_4.png", 0, 255, 255));
		floor2_04.position(position, canvas.height() - floor2_04.height());
		position += floor2_04.width();

		Sprite floor2_05 = makeSprite(getImage("images/level1/screen2/floor2_5.png", 0, 255, 255));
		floor2_05.position(position, canvas.height() - floor2_05.height());
		position += floor2_05.width();

		Sprite floor2_06 = makeSprite(getImage("images/level1/screen2/floor2_6.png", 0, 255, 255));
		floor2_06.position(position, canvas.height() - floor2_06.height());
		position += floor2_06.width();

		Sprite floor2_07 = makeSprite(getImage("images/level1/screen2/floor2_7.png", 0, 255, 255));
		floor2_07.position(position, canvas.height() - floor2_07.height());
		position += floor2_07.width();

		Sprite floor2_08 = makeSprite(getImage("images/level1/screen2/floor2_8.png", 0, 255, 255));
		floor2_08.position(position, canvas.height() - floor2_08.height());
		position += floor2_08.width();

		Sprite floor2_09 = makeSprite(getImage("images/level1/screen2/floor2_9.png", 0, 255, 255));
		floor2_09.position(907, canvas.height() - floor2_09.height());
		position = floor2_09.width() + 907;

		Sprite floor2_10 = makeSprite(getImage("images/level1/screen2/floor2_10.png", 0, 255, 255));
		floor2_10.position(position, canvas.height() - floor2_10.height());
		position += floor2_10.width();

		Sprite floor2_11 = makeSprite(getImage("images/level1/screen2/floor2_11.png", 0, 255, 255));
		floor2_11.position(position, canvas.height() - floor2_11.height());
		position += floor2_11.width();

		Sprite floor2_12 = makeSprite(getImage("images/level1/screen2/floor2_12.png", 0, 255, 255));
		floor2_12.position(canvas.width() - floor2_12.width(), canvas.height() - floor2_12.height());
		position += floor2_12.width() + 236;

		//Sprite platform2_01 = makeSprite(getImage("images/platform1_02.png", 0, 255, 255));
		//platform2_01.position(200, 310);

		floors.add(floor2_01);
		floors.add(floor2_02);
		floors.add(floor2_03);
		floors.add(floor2_04);
		floors.add(floor2_05);
		floors.add(floor2_06);
		floors.add(floor2_07);
		floors.add(floor2_08);
		floors.add(floor2_09);
		floors.add(floor2_10);
		floors.add(floor2_11);
		floors.add(floor2_12);

		//floors.add(platform2_01);

		//Screen 3 Floors
		position = 0;
		Sprite floor3_01 = makeSprite(getImage("images/level1/screen3/floor3_1.png", 0, 255, 255));
		floor3_01.position(0, canvas.height() - floor3_01.height());
		position += floor3_01.width(); 
		System.out.println("position: " + position);


		Sprite floor3_02 = makeSprite(getImage("images/level1/screen3/floor3_2.png", 0, 255, 255));
		floor3_02.position(position, canvas.height() - floor3_02.height());
		position += floor3_02.width();

		System.out.println("position: " + position);
		Sprite floor3_03 = makeSprite(getImage("images/level1/screen3/floor3_3.png", 0, 255, 255));
		floor3_03.position(position, canvas.height() - floor3_03.height());
		position += floor3_03.width();

		System.out.println("position: " + position);
		Sprite floor3_04 = makeSprite(getImage("images/level1/screen3/floor3_4.png", 0, 255, 255));
		floor3_04.position(position, canvas.height() - floor3_04.height());
		position += floor3_04.width();

		System.out.println("position: " + position);
		Sprite floor3_05 = makeSprite(getImage("images/level1/screen3/floor3_5.png", 0, 255, 255));
		floor3_05.position(331, canvas.height() - floor3_05.height());
		position = floor3_05.width()+ 331;

		Sprite floor3_06 = makeSprite(getImage("images/level1/screen3/floor3_6.png", 0, 255, 255));
		floor3_06.position(position, canvas.height() - floor3_06.height());
		position += floor3_06.width();

		Sprite floor3_07 = makeSprite(getImage("images/level1/screen3/floor3_7.png", 0, 255, 255));
		floor3_07.position(position, canvas.height() - floor3_07.height());
		position += floor3_07.width();

		Sprite floor3_08 = makeSprite(getImage("images/level1/screen3/floor3_8.png", 0, 255, 255));
		floor3_08.position(569, canvas.height() - floor3_08.height());
		position += floor3_08.width();

		Sprite floor3_09 = makeSprite(getImage("images/level1/screen3/floor3_9.png", 0, 255, 255));
		floor3_09.position(position, canvas.height() - floor3_09.height());
		position += floor3_09.width();

		Sprite floor3_10 = makeSprite(getImage("images/level1/screen3/floor3_10.png", 0, 255, 255));
		floor3_10.position(1142, canvas.height() - floor3_10.height());
		position = floor3_10.width() + 1142;

		Sprite floor3_11 = makeSprite(getImage("images/level1/screen3/floor3_11.png", 0, 255, 255));
		floor3_11.position(position, canvas.height() - floor3_11.height());
		position += floor3_11.width();

		floors.add(floor3_01);
		floors.add(floor3_02);
		floors.add(floor3_03);
		floors.add(floor3_04);
		floors.add(floor3_05);
		floors.add(floor3_06);
		floors.add(floor3_07);
		floors.add(floor3_08);
		floors.add(floor3_09);
		floors.add(floor3_10);
		floors.add(floor3_11);


		//Screen 4 Floors
		position = 0;
		Sprite floor4_01 = makeSprite(getImage("images/level1/screen4/floor4_1.png", 0, 255, 255));
		floor4_01.position(0, canvas.height() - floor4_01.height());

		Sprite floor4_02 = makeSprite(getImage("images/level1/screen4/floor4_2.png", 0, 255, 255));
		floor4_02.position(341, canvas.height() - floor4_02.height());
		position += floor4_02.width() + 341;

		Sprite floor4_03 = makeSprite(getImage("images/level1/screen4/floor4_3.png", 0, 255, 255));
		floor4_03.position(position, canvas.height() - floor4_03.height());
		position += floor4_03.width();

		Sprite floor4_04 = makeSprite(getImage("images/level1/screen4/floor4_4.png", 0, 255, 255));
		floor4_04.position(position, canvas.height() - floor4_04.height());
		position += floor4_04.width();

		floors.add(floor4_01);
		floors.add(floor4_02);
		floors.add(floor4_03);
		floors.add(floor4_04);

		//Screen 5 Floors
		position = 0;
		Sprite floor5_01 = makeSprite(getImage("images/level1/screen5/floor5_1.png", 0, 255, 255));
		floor5_01.position(0, canvas.height() - floor5_01.height());

		Sprite floor5_02 = makeSprite(getImage("images/level1/screen5/floor5_2.png", 0, 255, 255));
		floor5_02.position(328, 167);

		Sprite floor5_03 = makeSprite(getImage("images/level1/screen5/floor5_3.png", 0, 255, 255));
		floor5_03.position(443, canvas.height() - floor5_03.height());

		Sprite floor5_04 = makeSprite(getImage("images/level1/screen5/floor5_4.png", 0, 255, 255));
		floor5_04.position(484, 119);

		Sprite floor5_05 = makeSprite(getImage("images/level1/screen5/floor5_5.png", 0, 255, 255));
		floor5_05.position(515, 66);
		position += floor5_05.width() + 515;

		Sprite floor5_06 = makeSprite(getImage("images/level1/screen5/floor5_6.png", 0, 255, 255));
		floor5_06.position(position, canvas.height() - floor5_06.height());
		position += floor5_06.width();

		Sprite floor5_07 = makeSprite(getImage("images/level1/screen5/floor5_7.png", 0, 255, 255));
		floor5_07.position(position, canvas.height() - floor5_07.height());
		position += floor5_07.width();

		Sprite floor5_08 = makeSprite(getImage("images/level1/screen5/floor5_8.png", 0, 255, 255));
		floor5_08.position(position, canvas.height() - floor5_08.height());

		floors.add(floor5_01);
		floors.add(floor5_02);
		floors.add(floor5_03);
		floors.add(floor5_04);
		floors.add(floor5_05);
		floors.add(floor5_06);
		floors.add(floor5_07);
		floors.add(floor5_08);

		//Screen 6
		position = 0;
		Sprite floor6_01 = makeSprite(getImage("images/level1/screen6/floor6_1.png", 0, 255, 255));
		floor6_01.position(0, canvas.height() - floor6_01.height());
		position += floor6_01.width();

		Sprite floor6_02 = makeSprite(getImage("images/level1/screen6/floor6_2.png", 0, 255, 255));
		floor6_02.position(position, canvas.height() - floor6_02.height());
		position += floor6_02.width();

		Sprite floor6_03 = makeSprite(getImage("images/level1/screen6/floor6_3.png", 0, 255, 255));
		floor6_03.position(position, canvas.height() - floor6_03.height());
		position += floor6_03.width();

		Sprite floor6_04 = makeSprite(getImage("images/level1/screen6/floor6_4.png", 0, 255, 255));
		floor6_04.position(position, canvas.height() - floor6_04.height());
		position += floor6_04.width();

		Sprite floor6_05 = makeSprite(getImage("images/level1/screen6/floor6_5.png", 0, 255, 255));
		floor6_05.position(position, canvas.height() - floor6_05.height());
		position += floor6_05.width();

		Sprite floor6_06 = makeSprite(getImage("images/level1/screen6/floor6_6.png", 0, 255, 255));
		floor6_06.position(position, canvas.height() - floor6_06.height());
		position += floor6_06.width();

		Sprite floor6_07 = makeSprite(getImage("images/level1/screen6/floor6_7.png", 0, 255, 255));
		floor6_07.position(position, canvas.height() - floor6_07.height());
		position += floor6_07.width();
		
		floors.add(floor6_01);
		floors.add(floor6_02);
		floors.add(floor6_03);
		floors.add(floor6_04);
		floors.add(floor6_05);
		floors.add(floor6_06);
		floors.add(floor6_07);

		//Screen 7
		position = 0;
		Sprite floor7_01 = makeSprite(getImage("images/level1/screen7/floor7_1.png", 0, 255, 255));
		floor7_01.position(position, canvas.height() - floor7_01.height()+5);
		position += floor7_01.width();
		
		Sprite floor7_02 = makeSprite(getImage("images/level1/screen7/floor7_2.png", 0, 255, 255));
		floor7_02.position(position, canvas.height() - floor7_02.height());
		position += floor7_02.width();

		Sprite floor7_03 = makeSprite(getImage("images/level1/screen7/floor7_3.png", 0, 255, 255));
		floor7_03.position(position, canvas.height() - floor7_03.height());
		position += floor7_03.width();

		Sprite floor7_04 = makeSprite(getImage("images/level1/screen7/floor7_4.png", 0, 255, 255));
		floor7_04.position(position, canvas.height() - floor7_04.height());
		position += floor7_01.width();

		floors.add(floor7_01);
		floors.add(floor7_02);
		floors.add(floor7_03);
		floors.add(floor7_04);

		//Screen 8
		position = 0;
		Sprite floor8_01 = makeSprite(getImage("images/level1/screen8/floor8_1.png", 0, 255, 255));
		floor8_01.position(position, canvas.height() - floor8_01.height());
		position += floor8_01.width();
		
		Sprite floor8_02 = makeSprite(getImage("images/level1/screen8/floor8_2.png", 0, 255, 255));
		floor8_02.position(position, canvas.height() - floor8_02.height());
		position += floor8_02.width();
		
		floors.add(floor8_01);
		floors.add(floor8_02);

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
			floorDrawLimit = 7;//7 floor objects in screen1
			break;
		case 1:
			floorDrawLimit = 12;//12 floor objects in screen2
			break;
		case 2:
			floorDrawLimit = 11;//11 floor objects in screen3
			break;
		case 3:
			floorDrawLimit = 4;//4 floor objects in screen 4
			break;
		case 4:
			floorDrawLimit = 8;//8 floor objects in screen 5
			break;
		case 5:
			floorDrawLimit = 7;//7 floor objects in screen 6
			break;
		case 6:
			floorDrawLimit = 4;//4 floor objects in screen 7
			break;
		case 7:
			floorDrawLimit = 2;//7 floor objects in screen 8
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