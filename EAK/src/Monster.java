import java.awt.geom.Point2D;

import ucigame.Image;
import ucigame.Sprite;


public class Monster extends Sprite {
	
	Point2D.Double start , end; 

	public Monster(Image image) {
		super(image);
	}
	


	/***
	 * 
	 * @param image
	 * @param startPosition
	 */
	public Monster(Image image, Point2D.Double startPosition){
		super(image);
		this.position(startPosition.x, startPosition.y);
		this.start = startPosition;
	}
	

	/**
	 * 
	 * @param start
	 * @param end
	 * @param speed
	 */
	public void patrolPath(Point2D.Double end, double speed){

		speed = speed/100.0;
		this.end = end;		
		this.motion(speed*(end.x - start.x), speed*(end.y-start.y));
	}	
	
	public void patrol(){
		
		if((this.x()+this.xspeed() < start.x )|| (this.x()+this.xspeed() > end.x)){
			this.motion(-1, 1, ucigame.Ucigame.MULTIPLY);
		}
		
		if((this.y()+this.yspeed() < start.y )|| (this.y()+this.yspeed() > end.y)){
			this.motion(1, -1, ucigame.Ucigame.MULTIPLY);
			
		}
	}

}