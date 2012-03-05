package pixelshooter.ship.player;

import pixelshooter.Shooter;
import pixelshooter.cannon.*;
import pixelshooter.ship.PlayerShip;

public class StenoShot extends PlayerShip {
	
	public StenoShot(int x, int y, Shooter game) {
		super(x, y, "player1", 70, game);
		this.velocity = 3.2;
		this.acc = 0.3;
		this.explosionColor = "green";
		this.width = 16;
		this.cannonPrototypes[0] = new DiscCannon();
		this.cannonPrototypes[1] = new SparkCannon();
		this.cannonPrototypes[2] = new CombinedCannon(new Cannon[]{
				new NanoCannon(), new BitWaveCannon(), new DiscCannon()
		});
		
		this.cannons.add(cannonPrototypes[0]);
	}
}
