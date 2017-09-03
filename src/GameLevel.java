
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * in this class we construct all of the games variables together.
 *
 * @author user
 *
 */
public class GameLevel implements Animation {
      /**
       * Decelerations.
       */
	private AlienCollection aliencoll;
	private long passedTime;
	private long shootElien;
	private Counter numOflevel=new Counter();
	private List<shotBall> shootBalllist;
	private List<Ball> shootalienlist;
      private ArrayList<Ball> ballList = new ArrayList<Ball>();
      private SpriteCollection spriteCollection;
      private GameEnvironment gameEnvironment;
      private AnimationRunner runner;
      private boolean running;
      private List<shotBall> shotList;
      private LevelInformation levInfo;
      private Counter notRemovedBlocks = new Counter();
      private Counter lives = new Counter();
      private Counter numOfBalls = new Counter();
      private Counter score = new Counter();
      private PrintingHitListener p = new PrintingHitListener();
      private Paddle paddle;
      private KeyboardSensor keyboard;
      private boolean isReadyToShot;
      private int fps;
      private boolean pad=false;
      private AlienMovement alm=new AlienMovement();

      /**
       * constructors.
       */
      public GameLevel() {
            this.spriteCollection = new SpriteCollection();
            this.gameEnvironment = new GameEnvironment();
            BlockRemover bk= new BlockRemover(this,new Counter(1));
            passedTime=0;
            shootBalllist = new ArrayList<shotBall>();
            aliencoll  = new AlienCollection();
            shootalienlist  = new ArrayList<Ball>();
      }

      /**
       * moves the ball to its new place on the screen.
       *
       * @param level
       *            - the curr level.
       * @param keyb
       *            - the Keyboard Sensor of the paddle.
       * @param run
       *            - the Animation Runner of the game.
       * @param numOflives
       *            - the num Of lives the user has.
       * @param scor
       *            - the score the user got.
       */
      public GameLevel(LevelInformation level, KeyboardSensor keyb, AnimationRunner run, Counter numOflives,
                  Counter scor) {
            this.spriteCollection = new SpriteCollection();
            this.gameEnvironment = new GameEnvironment();
            this.levInfo = level;
            this.keyboard = keyb;
            this.score = scor;
            this.lives = numOflives;
            this.runner = run;

      }

      /**
       * adding variables which the ball can collide with.
       *
       * @param c
       *            the interface which we store in all of the collidable
       *            variables.
       */
      public void addCollidable(Collidable c) {
            this.gameEnvironment.addCollidable(c);
      }

      /**
       * add variables to the sprite interface.
       *
       * @param s
       *            the interface which we store in all of the sprite variables.
       *
       */
      public void addSprite(Sprite s) {
            this.spriteCollection.addSprite(s);
      }

      /**
       * Initialize a new game: create the Blocks,Ball and Paddle, and add them to
       * the game.
       */
      public void initialize() {
            spriteCollection.addSprite(this.levInfo.getBackground());
            ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
            ScoreIndicator scoreInd = new ScoreIndicator(this.score);
            spriteCollection.addSprite(scoreInd);
            LevelNameIndicator level = new LevelNameIndicator(this.levInfo.levelName(),this.numOflevel.getValue());
            spriteCollection.addSprite(level);
            LivesIndicator livesInd = new LivesIndicator(this.lives);
            spriteCollection.addSprite(livesInd);
            BallRemover br = new BallRemover(this, numOfBalls);
            notRemovedBlocks = new Counter(levInfo.numberOfBlocksToRemove());
            BlockRemover b = new BlockRemover(this, notRemovedBlocks);
            Block blockLUpperEdge = new Block(new Point(0, 20),
                        this.runner.getGUI().getDrawSurface().getWidth(), 20,
                        Color.black, new Counter(-3));
            blockLUpperEdge.addHitListener(br);
            blockLUpperEdge.addToGame(this);
            Block blockBottomEdge = new Block(new Point(0,
                        this.runner.getGUI().getDrawSurface().getHeight() - 20),
                        this.runner.getGUI().getDrawSurface().getWidth(),
                        20, Color.black, new Counter(-3));
            blockBottomEdge.addToGame(this);
            blockBottomEdge.addHitListener(br);
            for (Block bl : this.levInfo.blocks()) {
                  bl.addHitListener(p);
                  bl.addHitListener(b);
                  bl.addHitListener(scoreTracker);
                  bl.addHitListener(br);
                  bl.addToGame(this);
                 
            }
           
            // shir//
    		Block shield;
    		for (int j = 0; j < 4; j++) {
    			for (int i = 0; i < 28; i++) {
    				for (int k = 0; k < 3; k++) {
    					shield = new Block(60 + (i * 5) + (k * 270), 520 + (j * 5),
    							5, 5, Color.cyan, new Counter(1));
    					shield.addHitListener(b);
    					shield.addHitListener(br);
    					shield.addToGame(this);
    				}
    			}
    		}
      }

      /**
       * Run the game -- start the animation loop.
       */
      public void run() {
            this.lives.increase(2);
            while (this.lives.getValue() != 0 ) {
            	if(this.notRemovedBlocks.equals(0)){
            		this.numOflevel.increase(1);
            		this.initialize();
            	}
            	if (this.pad) {
					this.alm.returnToStart();
				}
                  playOneTurn();
                  this.paddle.removeFromGame(this);

            }
            this.runner.getGUI().close();
            return;
      }

      /**
       * Run one turn of the game .
       */
      public void playOneTurn() {
            this.running = true;
            this.initializeBallsAndPaddle();
            this.runner.run(new CountdownAnimation(3, 2, this.spriteCollection));
            this.runner.run(this);
      }

      /**
       * initialize Balls And Paddle of the level game .
       */
      public void initializeBallsAndPaddle() {
            int x = (800 - this.levInfo.paddleWidth()) / 2;
            this.paddle = new Paddle(new Point(x, this.runner.getGUI().getDrawSurface().getHeight() - 40),
                        this.levInfo.paddleWidth(), 15, Color.yellow, this.keyboard,
                        this.runner.getGUI().getDrawSurface().getWidth());
            paddle.addToGame(this);
            for (int i = 0; i < 10; i++) {
            	ArrayList<AlienBlock> bll=new ArrayList<>();
            	for (int j = 0; j < 5; j++) {
					bll.add((AlienBlock)this.levInfo.blocks().get((5*i)+j));
				}
				this.alm.getAlienCollection().addList(bll);
			}
            if(this.numOflevel.getValue()>0){
            this.alm.setLevelVelAliens(this.numOflevel.getValue());
            }

            Ball[] balls = new Ball[this.levInfo.numberOfBalls()];
            for (int i = 0; i < balls.length; i++) {
                  balls[i] = new Ball(
                              (int) (this.paddle.getCollisionRectangle().getUpperLeft().getX()
                                          + this.paddle.getCollisionRectangle().getWidth() / 2),
                              (int) (this.paddle.getCollisionRectangle().getUpperLeft().getY() - 5),
                              5, java.awt.Color.white,
                              this.gameEnvironment);
                  balls[i].setVelocity(this.levInfo.initialBallVelocities().get(i));
                  balls[i].addToGame(this);
                  ballList.add(balls[i]);
            }
            numOfBalls.increase(ballList.size());
      }

      /**
       * handles the remove of Collidable .
       *
       * @param c
       *            the Collidable to remove.
       */
      public void removeCollidable(Collidable c) {
            this.gameEnvironment.removeCollidable(c);
            if(c instanceof AlienBlock){
            this.alm.getAlienCollection().removeAlien((AlienBlock)c);
            }
      }

      /**
       * handles the remove of Sprite.
       *
       * @param s
       *            the Collidable to remove. .
       */
      public void removeSprite(Sprite s) {
            this.spriteCollection.removeSprite(s);
      }

      /**
       * get a list of Balls.
       * @return balllist.
       */
      public ArrayList<Ball> getBallList() {
            return this.ballList;
      }

      /**
       * the function remove a Collidable.
       *
       * @param d
       *            the draw surface of the game.
     * @param dt
     * time.
       */
      public void doOneFrame(DrawSurface d, double dt) {
            this.spriteCollection.drawAllOn(d);
            this.alm.setVelAliens();
            this.spriteCollection.notifyAllTimePassed();
            if (notRemovedBlocks.getValue() == 0) {
                  score.increase(100);
                  this.running = false;
            }

            if (this.keyboard.isPressed("p")) {
                  this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                              KeyboardSensor.SPACE_KEY, new PauseScreen()));
            }
            
            if ( (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY) &&  (System.currentTimeMillis()-this.passedTime)/1000
            		>=0.35)) {
            	this.paddle.shoot(this);
            	this.passedTime=System.currentTimeMillis();
    		}
            if ((System.currentTimeMillis()-shootElien)/1000 >= 0.5)
            {
            	this.alm.getAlienCollection().shooter().shoot(this);
            	shootElien = System.currentTimeMillis();
            }
/*            if (aliencoll.restart == true)
            {
            	life--;
            	this.running= false;
            	
            }*/
      }

  	
  	public void addShot(shotBall s) {
  		this.shotList.add(s);
  	}

      /**
       * determine if the game should stop or not.
       *
       * @return a bool value- the game should stop or not. .
       */
      public boolean shouldStop() {
            return !this.running;
      }

      /**
       * get the Number Of Not Removed Blocks.
       * @return num of not removed blocks.
       */
      public int getNumOfNotRemovedBlocks() {
            return this.notRemovedBlocks.getValue();

      }
      public GameEnvironment getGameEnv(){
    	  return this.gameEnvironment;
      }
}