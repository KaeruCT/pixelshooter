package pixelshooter.bullet;
import pixelshooter.Explosion;
import jgame.JGObject;
import jgame.platform.JGEngine;

public abstract class Bullet extends JGObject{
	
	private double angle;
	public static JGEngine game;
	protected int velocity;
	public double damage;
	public int width;
	
	public Bullet(double x, double y, String anim, int colid,  double angle){
		super("bullet", true, x, y, colid, anim);
		
		this.velocity = 4;
		this.damage = 1;
		this.width = 8;
		this.angle = angle;
		this.expiry = -2;
		
		// center
		this.x -= width/2;
		
		if(!(angle >= 0 && angle <= Math.PI)){
			this.setAnim(anim+"u");
		}
	}
	
	public void move(){
		x += Math.cos(angle)*velocity*gamespeed;
		y -= Math.sin(angle)*velocity*gamespeed;
	}
	
	public void hit(JGObject obj){

		if(obj instanceof Bullet){
			new Explosion(x, y, "blue");
			this.remove();
		}
	}
}
