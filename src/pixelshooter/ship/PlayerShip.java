package pixelshooter.ship;
import java.awt.event.KeyEvent;

import pixelshooter.Shooter;
import pixelshooter.cannon.*;

import jgame.platform.JGEngine;

public abstract class PlayerShip extends Ship {

	public Cannon[] cannonPrototypes;
	protected double acc;
	
	public PlayerShip(int x, int y, String anim, int health, Shooter game) {
		super(x, y, "player", game.PLAYER_ID, anim, health, game);
		this.velocity = 2;
		this.acc = 0.1;
		this.angle = Math.PI/2;
		this.explosionColor = "blue";
		this.width = 16;
		this.cannonPrototypes = new Cannon[3];
	}
	
	private boolean getKey(int k){
		return this.game.getKey(k);
	}
	
	private void clearKey(int k){
		this.game.clearKey(k);
	}
	
	public void move(){
		super.update();
		
		if(getKey(KeyEvent.VK_1)){
			this.cannons.clear();
			this.cannons.add(this.cannonPrototypes[0]);
		}else if(getKey(KeyEvent.VK_2)){
			this.cannons.clear();
			this.cannons.add(this.cannonPrototypes[1]);
		}else if(getKey(KeyEvent.VK_3)){
			this.cannons.clear();
			this.cannons.add(this.cannonPrototypes[2]);
		}
		// shooting
		else if(getKey(KeyEvent.VK_SPACE) || getKey(KeyEvent.VK_Z)) {
			
			shoot();
			
			// bounce
			if(yspeed < velocity)
				this.yspeed += acc*0.1;
			
			game.playAudio("sparkchn", "laser", false);
		}
		
		// movement
		if(getKey(JGEngine.KeyLeft) || getKey(KeyEvent.VK_A)){

			if(xspeed > -velocity)
			this.xspeed -= acc;
			
		}else if(getKey(JGEngine.KeyRight) || getKey(KeyEvent.VK_D)){

			if(xspeed < velocity)
			this.xspeed += acc;
			
		}
		if(getKey(JGEngine.KeyUp) || getKey(KeyEvent.VK_W)){
			
			if(yspeed > -velocity)
				this.yspeed -= acc;
			
		}else if (getKey(JGEngine.KeyDown) || getKey(KeyEvent.VK_S)){
			
			if(yspeed < velocity)
				this.yspeed += acc;
			
		}
		
		// correcting bounds (wrap horizontally)
		if(x <= -8)
			x = game.pfWidth()-9;
		if(y <= -8)
			y = -8;
		if(x+8 >= game.pfWidth())
			x = -8;
		if(y+8 >= game.pfHeight())
			y = game.pfHeight()-8;
	}
}
