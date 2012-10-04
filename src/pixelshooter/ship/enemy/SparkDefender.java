package pixelshooter.ship.enemy;
import pixelshooter.AndroidGame;
import pixelshooter.cannon.*;
import pixelshooter.ship.EnemyShip;

public class SparkDefender extends EnemyShip {

	public SparkDefender(double x, double y, double vel, double angle, AndroidGame game) {
		super(x, y, "sparkdefender", 15, game);

		this.velocity = vel;
		this.angle = angle;
		this.cannons.add(new SparkCannon());
		this.explosionColor = "green";
		this.width = 16;
	}
	
	public void move(){
		super.move();
		
		if(game.t%30 == 0){
			shoot(Math.PI*3/2);
		}
		// only attempt to stay in screen if not near boundaries
		if(x >= 8 && x+8 <= game.viewWidth()){
			if(y+32 >= game.viewHeight() && game.goingDown(angle) ||
				y <= 32 && game.goingUp(angle)){
				
				this.angle = -this.angle;
			}
		}
	}
}
