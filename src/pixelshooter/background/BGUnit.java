package pixelshooter.background;

import pixelshooter.Shooter;
import jgame.JGObject;

public class BGUnit extends JGObject{
	
	private Shooter game;
	public double scroll;
	public double ymax;
	public double w;
	
	public BGUnit(double x, double y, String graphic, double yspeed, Shooter game){
		super("bgimg", true, x, y, 0x99, graphic, 0, 0);
		this.expiry = -1;
		this.game = game;
		this.scroll = yspeed;
		this.w = game.getImage(this.getImageName()).getSize().y;
		this.ymax = Math.ceil(this.game.viewHeight()/w)*w;
	}
	
	public void move(){
		if(y > ymax){
			y = -w/2;
			y += scroll*2;
		}else{
			y += scroll;
		}
	}
}
