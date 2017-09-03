
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * in this class we make the paddle for the game, using the sprite and
 * collidable interfaces.
 *
 * @author user
 *
 */
public class Paddle implements Sprite, Collidable {
      private KeyboardSensor keyboard;
      private Rectangle paddle;
      private GameEnvironment gameEnvironment;
      private Color color;
      private int max = 530, min = 20;
      private double speed = 0;
      private GameLevel gl;
      private int fps;
  	  private LevelInformation levInfo;
  	private List<HitListener> hitListeners = new ArrayList<>();

      /**
      * constructors.
      *
      * @param rec
      *           the rectangle definitions.
      * @param color
      *           color of paddle.
      * @param keyboard
      *           the keyboard from drawsurface so we can control the paddle
      *           with the keyboard.
      * @param screenWidth
      *           Controlling the paddle won't get out of boarders.
      */
      public Paddle(Rectangle rec, java.awt.Color color, KeyboardSensor keyboard, int screenWidth) {
           this.paddle = rec;
           this.color = color;
           max = screenWidth - 20 - (int) this.paddle.getWidth();
           speed = this.paddle.getWidth() / 4;
           this.keyboard = keyboard;
           this.gameEnvironment = new GameEnvironment();
      }

      /**
      *
      * @param upperLeft
      *           the upper left starting point of paddle.
      * @param width
      *           width of the paddle.
      * @param height
      *           height of the paddle.
      * @param color
      *           color of the paddle.
      * @param keyboard
      *           keyboard controling the paddle.
      */
      public Paddle(Point upperLeft, double width, double height, java.awt.Color color, KeyboardSensor keyboard) {
           this.paddle = new Rectangle(upperLeft, width, height);
           this.color = color;
           this.keyboard = keyboard;
           speed = this.paddle.getWidth() / 4;
        this.gameEnvironment = new GameEnvironment();
      } 
      /**
      *
      * @param upperLeft
      *           the upper left starting point of paddle.
      * @param width
      *           width of the paddle.
      * @param height
      *           height of the paddle.
      * @param color
      *           color of paddle.
      * @param keyboard
      *           the keyboard from drawsurface so we can control the paddle
      *           with the keyboard.
      * @param screenWidth
      *           Controlling the paddle won't get out of boarders.
      */
      public Paddle(Point upperLeft, double width, double height, java.awt.Color color, KeyboardSensor keyboard,
                int screenWidth) {
           this.paddle = new Rectangle(upperLeft, width, height);
           this.color = color;
           this.keyboard = keyboard;
           max = screenWidth - 20 - (int) this.paddle.getWidth();
           speed = this.paddle.getWidth() / 4;
      }

      /**
      * function for moving the paddle left.
      */
      public void moveLeft(double dt) {
           if (this.paddle.getUpperLeft().getX() - speed < min) {
                this.paddle = new Rectangle(new Point(min, this.paddle.getUpperLeft().getY()), this.paddle.getWidth(),
                          this.paddle.getHeight());
           } else {
                this.paddle = new Rectangle(
                          new Point(this.paddle.getUpperLeft().getX() - speed, this.paddle.getUpperLeft().getY()),
                          this.paddle.getWidth(), this.paddle.getHeight());
           }

      }

      /**
      * function for moving the paddle right.
      */
      public void moveRight(double dt) {
           if (this.paddle.getUpperLeft().getX() + speed > max) {
                this.paddle = new Rectangle(new Point(max, this.paddle.getUpperLeft().getY()), this.paddle.getWidth(),
                          this.paddle.getHeight());
           } else {
                this.paddle = new Rectangle(
                          new Point(this.paddle.getUpperLeft().getX() + speed, this.paddle.getUpperLeft().getY()),
                          this.paddle.getWidth(), this.paddle.getHeight());
           }
      }

      /**
      * Sprite parts. this function gets the paddle to move in the time laps.
      * checks which case the user used to know where to move.
      */
      public void timePassed(double dt) {
           if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                this.moveLeft(dt);
           }
           if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                this.moveRight(dt);
           }
      }

      //shir
      public shotBall shoot(){
  			double dx= 0;// 0, 500 frm angle and speed
  			double dy= (double)1 / (double) this.fps;
  			int yPos=(int)this.paddle.getUpperLeft().getY();
  			int xPos=(int)this.paddle.getUpperLeft().getX();
  			int paddleWidth = (int)this.paddle.getWidth();
  			shotBall s = new shotBall((int)xPos+paddleWidth/2, (int)yPos, 4 ,Color.white, this.gameEnvironment);
  			s.setVelocity(dx, dy);
  			//this.gl.addShot(s);
  			s.addToGame(this.gl);
  			return s;
      }

      /**
      * function for drawing the paddle.
      *
      * @param surface
      *           the surface on which the paddle is drawn on.
      */
      public void drawOn(DrawSurface surface) {
           DrawSurface d = surface;
           d.setColor(Color.black);
           d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                     (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
           d.setColor(this.color);
           d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                     (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
      }

      /**
      * Collidable parts.
      *
      * @return which kind of collidable this is.
      */
      public Rectangle getCollisionRectangle() {
           return this.paddle;
      }

      /**
      * tells the ball how to act when hitting this class.
      * @param hitter
      * the ball that hits.
      * @param collisionPoint
      *           the collision point.
      * @param currentVelocity
      *           current velocity of the ball.
      * @return the new velocity.
      */
      // shir// check 
      public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
          String s = this.paddle.collidedLine(collisionPoint);
          if (s.equals("up")) {
                int part = getPartOfPaddle(collisionPoint);
                if (part == 3) {
                      return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }

                double speed1 = Math.sqrt((currentVelocity.getDx()) * (currentVelocity.getDx())
                            + (currentVelocity.getDy()) * (currentVelocity.getDy()));
                Velocity newV = currentVelocity.fromAngleAndSpeed((double) (30 * (part - 3)), speed1);
                return new Velocity(newV.getDx(), -newV.getDy());

          }
          return currentVelocity;

    }

      /**
      * Add this paddle to the game.
      *
      * @param g
      *           the interface in which all the game data is stored.
      */
      public void addToGame(GameLevel g) {
           g.addCollidable(this);
           g.addSprite(this);
      }

      /**
      * function that devides the paddle into parts so each part has a different
      * angle in which it throws the ball back.
      *
      * @param collisionPoint
      *           the collision point.
      * @return in which part of the paddle the collision point was.
      */
      public int getPartOfPaddle(Point collisionPoint) {
           int coPart = 0;
           double paddlePart = Math.round(this.paddle.getWidth() / 5);
           Line[] linePart = new Line[5];
           for (int i = 0; i < linePart.length; i++) {
                Point start = new Point(this.paddle.getUpperLeft().getX() + i * paddlePart,
                          this.paddle.getUpperLeft().getY());
                Point end = new Point(start.getX() + paddlePart, this.paddle.getUpperLeft().getY());
                linePart[i] = new Line(start, end);
           }
           for (int j = 0; j < linePart.length; j++) {
                if (linePart[j].isPointInRec(linePart[j], collisionPoint)) {
                     coPart = j + 1;
                }
           }
           return coPart;
      }

      /**
      * get in what angle did the ball hit the paddle.
      *
      * @param cPoint
      *           collision point of the ball.
      * @param v
      *           velocity of the ball.
      * @return the angle the ball hit the paddle.
      */
      public double getAngleOfImpact(Point cPoint, Velocity v) {
           Point beforeV = new Point(cPoint.getX() - v.getDx(), cPoint.getY() - v.getDy());
           double lengthOfHight = v.getDy();
           double lengthOfHypotenuse = cPoint.distance(beforeV);
           return Math.round(Math.asin(lengthOfHypotenuse / lengthOfHight));
      }
/**
 * remove the paddle from game.
 * @param gameLevel
 * the game to remove from.
 */
      public void removeFromGame(GameLevel gameLevel) {
           gameLevel.removeCollidable(this);
           gameLevel.removeSprite(this);
      }
/**
 * set speed of paddle.
 * @param newSpeed
 * the new speed for the paddle.
 */
      public void setSpeed(double newSpeed) {
           this.speed = newSpeed;
      }
      public void shoot(GameLevel g){
    		 Ball b = new Ball((int)(this.paddle.getUpperLeft().getX()+this.paddle.getWidth()/2),
    				 (int)(this.paddle.getUpperLeft().getY()-1), 5,Color.WHITE, g.getGameEnv() );
    		 b.setVelocity(new Velocity(0, -1));
    		 b.addToGame(g);
    	}
}
