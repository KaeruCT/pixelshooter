package pixelshooter.ship;
import pixelshooter.Shooter;


public abstract class EnemyShip extends Ship {

	public EnemyShip(double x, double y, String anim, double maxHealth, Shooter game) {
		super(x, y, "enemy", game.ENEMY_ID, anim, maxHealth, game);

		this.velocity = 1;
		this.angle = Math.PI*3/2;
		this.width = 16;
	}
}
