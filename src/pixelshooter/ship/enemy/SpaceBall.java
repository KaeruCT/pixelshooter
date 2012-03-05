package pixelshooter.ship.enemy;
import pixelshooter.Shooter;
import pixelshooter.cannon.*;
import pixelshooter.ship.EnemyShip;
import pixelshooter.ship.PlayerShip;

public class SpaceBall extends EnemyShip {

	private boolean lockedOn;
	private double offset;
	private double radius;
	
	public SpaceBall(double x, double y, double vel, double angle, Shooter game) {
		super(x, y, "spaceball", 5, game);

		this.velocity = vel;
		this.angle = angle;
		this.cannons.add(new BitWaveCannon());
		this.explosionColor = "red";
		this.width = 16;
		this.radius = 32; // distance from player to "orbit"
		this.offset = 0;
		this.lockedOn = false;
		this.expiry = -1;
	}
	
	public void move(){
		super.move();
		
		PlayerShip p = game.getPlayer();
		
		if(this.lockedOn){

			x = p.x - Math.cos(-offset)*radius;
			y = p.y + Math.sin(offset)*radius;
			
			if(clock%20 == 0 && game.random(0, 2, 1)==1){
				
				double ang = game.atan2(y-p.y, p.x-x);
				shoot(ang);
			}
			
			offset += 0.05;
		}else{

			double dFromPlayer = Math.sqrt(Math.pow(p.x-x, 2)+Math.pow(p.y-y, 2));
			double ang;
			
			if(clock%80==0){
				ang = game.atan2(y-p.y, p.x-x);
				this.angle = ang;
			}
			
			if(dFromPlayer < radius){
				ang = game.atan2(y-p.y, p.x-x);
				this.lockedOn = true;
				this.offset = ang;
			}
			
			
		}
	}
}
