package pixelshooter.wave;

import pixelshooter.LevelReader;
import pixelshooter.AndroidGame;

public class SparkWave extends Wave {
	
	public SparkWave(AndroidGame game, LevelReader r, int maxAmount) {
		super(game, r, maxAmount);
		this.setFreq(100);
		this.setShipClass("SparkDefender");
	}
	
	public void spawn(){
		double center = game.pfWidth()/2;
		
		super.spawn(
			game.random(center-64, center+64),
			-8,
			game.random(1, 2),
			Math.PI*3/2-game.random(-0.5, 0.5)
		);
	}

}