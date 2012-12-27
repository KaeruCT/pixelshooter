package pixelshooter;

import java.awt.event.KeyEvent;

import jgame.JGColor;

public class ShooterTitle {
	
	private AndroidGame game;
	public int selectedShip;
	private String[] shipNames;
	
	public ShooterTitle(AndroidGame game){
		this.game = game;
		this.selectedShip = 0;
		
		this.shipNames = new String[]{"spin-turn", "nimak-runner", "steno-shot"};
	}
	
	private boolean getKey(int k){
		return this.game.getKey(k);
	}
	
	private void clearKey(int k){
		this.game.clearKey(k);
	}
	
	public void start() {
		game.addStars(128);
	}
	
	public void doFrame() {
		game.moveObjects(null, 0);
		
		if(getKey(KeyEvent.VK_RIGHT) || getKey(KeyEvent.VK_UP)
				|| getKey(KeyEvent.VK_D) || getKey(KeyEvent.VK_W)){
			
			clearKey(KeyEvent.VK_UP);
			clearKey(KeyEvent.VK_RIGHT);
			clearKey(KeyEvent.VK_D);
			clearKey(KeyEvent.VK_W);
			
			this.selectedShip++;
			
		}else if(getKey(KeyEvent.VK_LEFT) || getKey(KeyEvent.VK_DOWN)
				|| getKey(KeyEvent.VK_A) || getKey(KeyEvent.VK_S)){
			
			clearKey(KeyEvent.VK_DOWN);
			clearKey(KeyEvent.VK_LEFT);
			clearKey(KeyEvent.VK_A);
			clearKey(KeyEvent.VK_S);
			
			this.selectedShip--;
			
		}else if(getKey(KeyEvent.VK_1)){
			
			this.selectedShip = 0;
			
		}else if(getKey(KeyEvent.VK_2)){
			
			this.selectedShip = 1;
			
		}else if(getKey(KeyEvent.VK_3)){
			
			this.selectedShip = 2;
			
		}else if(getKey(KeyEvent.VK_SPACE) || getKey(KeyEvent.VK_ENTER)){
			clearKey(KeyEvent.VK_SPACE);
			
			this.game.setGameState("InGame");
		}
		
		if(this.selectedShip < 0) this.selectedShip = 2;
		if(this.selectedShip > 2) this.selectedShip = 0;
	}
	
	public void paintFrame() {
		int centerY = this.game.viewHeight()/2;
		int centerX = this.game.viewWidth()/2;
		
		this.game.drawString("-=pixel shooter=-", centerX, centerY-32, 0, "blue");
		this.game.drawString("press SPACE to begin", centerX, centerY-16, 0);
		this.game.drawString("move with WASD or ARROW keys", centerX, centerY-8, 0);
		this.game.drawString("shoot with SPACE or Z", centerX, centerY, 0);
		this.game.drawString("change mode with [1, 2, 3] keys", centerX, centerY+8, 0);
		
		this.game.drawString("SELECT YOUR SHIP:", centerX, centerY+24, 0);
		this.game.drawString(shipNames[this.selectedShip], centerX, centerY+32, 0, "yellow");
	}
}
