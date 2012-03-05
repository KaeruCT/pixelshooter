package pixelshooter.ship.enemy;
import pixelshooter.Shooter;
import pixelshooter.cannon.*;
import pixelshooter.ship.EnemyShip;
import pixelshooter.ship.PlayerShip;

public class FireStriker extends EnemyShip {

	public FireStriker(double x, double y, double vel, double angle, Shooter game) {
		super(x, y, "firestriker", 5, game);

		this.velocity = vel;
		this.angle = angle;
		this.cannons.add(new FireCannon());
		this.explosionColor = "red";
		this.width = 16;
	}
	
	public void move(){
		super.move();
		
		if(this.clock%10==0){
			
			if(this.clock%50 == 0){
				this.angle = Math.PI*3/2;
			}
			
			PlayerShip p = game.getPlayer();
			double dFromPlayer = Math.sqrt(Math.pow(p.x-x, 2)+Math.pow(p.y-y, 2));
			
			if(dFromPlayer < 128){
				this.angle = Math.PI*3/2;
				this.velocity += 1.2;
			}
		}

		if(game.t%10 == 0 && game.random(0, 1, 1)==1){
			shoot(Math.PI*3/2);
		}
	}
}
