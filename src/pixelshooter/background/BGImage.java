package pixelshooter.background;
import pixelshooter.Shooter;
import jgame.JGImage;

public class BGImage{
	
	private Shooter game;
	private BGUnit[] sprites;
	private double width;
	private double height;
	private String graphic;
	private double scroll;
	
	public BGImage(String graphic, double scroll, Shooter game){
		this.game = game;
		
		JGImage img = game.getImage(graphic);
		
		this.graphic = graphic;
		this.width = img.getSize().x;
		this.height = img.getSize().y;
		this.scroll = scroll;
		
		createSprites();
	}
	
	public void createSprites(){
		
		int xtile = 1+(int)Math.ceil(game.viewWidth()/width);
		int ytile = 2+(int)Math.ceil(game.viewHeight()/height);
		int spcnt = 0;
		double xoff = game.viewXOfs();
		double yoff = game.viewYOfs();

		sprites = new BGUnit[xtile*ytile];
		
		for(int i = 0; i < xtile; i++){
			for(int j = 0; j < ytile; j++){
				BGUnit sp = new BGUnit(xoff + i*width/2,
						yoff + j*height/2,
						this.graphic, scroll, game);

				sprites[spcnt++] = sp;
			}
		}
	}
	
	public void setScroll(double scroll){
		this.scroll = scroll;
		for(int i = 0; i < sprites.length; i++){
			sprites[i].scroll = scroll;
		}
	}
}
