import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * 
 */

/**
 * @author user.
 *
 */
public class AlienInformaton implements LevelInformation {
	private int padSpeed;
    private int padWidth;
    private int numOfBloks;
    private String levelName;
    private List<Block> blocks = new ArrayList<Block>();
    private List<Velocity> ballV = new ArrayList<Velocity>();
    private Alienbg bg=new Alienbg();
 
	
    public AlienInformaton(){
    	for (int i = 0; i<10; i++) {
			for (int j = 0; j < 5; j++){
				AlienBlock b =new AlienBlock(new Point(0+50*i,30+40*j), new Velocity (1,0), "enemy.png");
				this.blocks.add(b);
			}
		}
		this.levelName="Battle No' ";
		this.numOfBloks=50;
		this.padSpeed= 1;
		this.padWidth=60;
    }

	public int numberOfBalls() {
		return 0;
	}
	public List<Velocity> initialBallVelocities() {
		return null;
	}

	
	
	public int paddleSpeed() {
		return this.padSpeed;
	}

	
	public int paddleWidth() {
		return this.padWidth;
	}

	
	public String levelName() {
	
		return this.levelName;
	}

	
	public Sprite getBackground() {

		return this.bg;
	}

	
	public List<Block> blocks() {
		
		return this.blocks;
	}
	
	public int numberOfBlocksToRemove() {
		
		return this.numOfBloks;
	}

}
