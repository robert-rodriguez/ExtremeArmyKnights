

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import ucigame.*;


/**
 * Class that all menus are created from 
 * Takes an array of strings for titles or
 * options of the menu menu entries have references to next connecting object
 * background for each
 * 
 */
public class Menu extends Main{
	
	

		
		int windowW = 640;
		int windowH = 480;
		private static final int NUM_MAIN_MENU_BUTTONS = 4;
		private static final int BTN_HEIGHT = 55;
//		private ArrayList<Sprite> menuOptions;
		private Sprite startbtn,leaderbtn,levelbtn,quitbtn;
		private Image mainMenu,titleScreen;
		
	

	public void setup(){
	
		window.size(windowW, windowH);
		
		window.title("Extreme Army Knights");
				
		startbtn = makeButton("StartBtn", getImage("images/menus/StartButton.png", 0, 0, 0),447, 55);
//		menuOptions.add(startbtn);
		leaderbtn = makeButton("LeaderboardBtn",getImage("images/menus/LeaderBoardBtn.png", 0, 0, 0), 447, 55);
//		menuOptions.add(leaderbtn);
		levelbtn = makeButton("LevelSelectBtn",getImage("images/menus/levelSelectBtn.png", 0, 0, 0), 447, 55);
//		menuOptions.add(levelbtn);
		quitbtn = makeButton("QuitBtn",getImage("images/menus/quitBtn.png", 0, 0, 0),447, 55);
//		menuOptions.add(quitbtn);
		mainMenu = getImage("images/menus/MainMenuBackDrop.png");
		titleScreen = getImage("images/menus/titleScreen.png");
		
		framerate(60);
		startScene("TitleScreen");
	}
	
	public void drawTitleScreen(){
		canvas.clear();
		canvas.background(titleScreen);
	}
	
	public void onKeyPressTitleScreen(){
		if(keyboard.isDown(keyboard.ENTER)){
			startScene("MainMenu");
		}
	}
	
	
	public void drawMainMenu(){
		double firstBtnVerticalPos = canvas.height()/2.0 - NUM_MAIN_MENU_BUTTONS/2.0;
		canvas.clear();
		canvas.background(mainMenu);
//		addMenuButtons();
		startbtn.position(centerSpriteHorizontal(startbtn), setSpriteVerticalPosition(startbtn,0));
		startbtn.draw();
		leaderbtn.position(centerSpriteHorizontal(leaderbtn), setSpriteVerticalPosition(leaderbtn,1));
		leaderbtn.draw();
		levelbtn.position(centerSpriteHorizontal(levelbtn), setSpriteVerticalPosition(levelbtn,2));
		levelbtn.draw();
		quitbtn.position(centerSpriteHorizontal(quitbtn), setSpriteVerticalPosition(quitbtn,3));
		quitbtn.draw();
	}
	
	
	private double centerSpriteHorizontal(Sprite toCenter){
		return canvas.width()/2 - toCenter.width()/2;
	}
	
	private double centerSpriteVertical(Sprite toCenter){
		return canvas.height()/2 - toCenter.height()/2;
	}
	
	private double setSpriteVerticalPosition(Sprite toCenter, double position){
		return canvas.height()/2 - ((toCenter.height()*NUM_MAIN_MENU_BUTTONS)/2) + toCenter.height()*position;
	}

	
//	private void addMenuButtons(){
//		
//		int heightAllButtons = menuOptions.size() *menuOptions.get(1).height();//assume all buttons have same height
//		double firstBtnVerticalPos = canvas.height()/2.0 - heightAllButtons/2.0;
//		for(int i = 0; i < menuOptions.size() ; i++){
//			
//			menuOptions.get(i).position(centerSpriteHorizontal(menuOptions.get(i)), (i+1)*firstBtnVerticalPos);
//			menuOptions.get(i).draw();
//		}
//		
//		
//	}
	
    public void onClickStartBtn()
    {
        super.setup();
    }
    
    public void onClickLeaderboardBtn()
    {
        print("LeaderBoard functionality\n");
    }
    
    public void onClickLevelSelectBtn()
    {
        print("Level Select functionality\n");
    }
    
    public void onClickQuitBtn()
    {
        print("quit functionality\n");
        
    }
}
