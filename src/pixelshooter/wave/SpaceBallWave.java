package pixelshooter.wave;

import pixelshooter.LevelReader;
import pixelshooter.Shooter;

public class SpaceBallWave extends Wave {
	
	public SpaceBallWave(Shooter game, LevelReader r, int maxAmount) {
		super(game, r, maxAmount);
		this.setFreq(300);
		this.setShipClass("SpaceBall");
	}
	
	public void spawn(){
	
		super.spawn(
			game.random(0, game.viewWidth()),
			game.viewHeight()-8,
			game.random(0.7, 1.6),
			Math.PI/2-game.random(-0.5, 0.5)
		);
	}

}