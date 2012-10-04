package pixelshooter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;

import pixelshooter.wave.Wave;

public class LevelReader {
	
	private BufferedReader reader;
	private String[] waveClasses;
	private int[] maxAmounts;
	private String currentLine;
	private AndroidGame game;
	private int wavesDone;
	
	public LevelReader(AndroidGame game, String file) throws UnsupportedEncodingException{
		
		this.reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file), "UTF-8"));
		this.currentLine = null;
		this.wavesDone = 0;
		this.game = game;
	}
	
	public void init(){
		this.executeNextLine();
	}
	
	private void executeNextLine(){
		try {
			this.currentLine = reader.readLine();
		} catch (IOException e) {
			this.game.dbgPrint(e.toString());
		}
		
		if(this.currentLine != null){
			executeCurrentLine();
		}else{
			this.levelFinished();
		}
	}
	
	private void executeCurrentLine(){
		wavesDone = 0;
		String[] params = currentLine.split("\\+");
		waveClasses = new String[params.length];
		maxAmounts = new int[params.length];
		
		for(int i = 0; i < params.length; i++){
			params[i] = params[i].trim();
			String[] cl = params[i].split("[ \t]");
			waveClasses[i] = cl[0].trim();
			maxAmounts[i] = Integer.parseInt(cl[1].trim());
		}
		
		if(waveClasses.length > 0){
			createWaves(waveClasses, maxAmounts);
		}
	}
	
	private void createWaves(String[] classNames, int[] maxAmounts){
		
		for(int i = 0; i < classNames.length; i++){
			try {
				Class<Wave> wave = (Class<Wave>) Class.forName("pixelshooter.wave."+classNames[i]);
				Constructor<Wave> c = wave.getConstructor(AndroidGame.class, LevelReader.class, int.class);
				
				Wave w = c.newInstance(this.game, this, maxAmounts[i]);
				this.game.waves.add(w);
				
			} catch (Exception e) {
				this.game.dbgPrint(e.toString());
			}
		}
	}

	public void waveDone() {
		this.wavesDone++;
		
		if(this.wavesDone >= this.waveClasses.length){
			this.executeNextLine();
		}
	}
	
	public void levelFinished(){
		try {
			this.reader.close();
		} catch (IOException e) {
			this.game.dbgPrint(e.toString());
		}
		this.game.levelFinished();
	}
}
