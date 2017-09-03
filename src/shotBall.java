
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

public class shotBall implements Sprite {

    private List<HitListener> hitListeners = new ArrayList<>();
    private GameEnvironment game;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    
    /**
     * constructors.
     *
     * @param x
     *            center point
     * @param y
     *            center point
     * @param r
     *            the size of radius
     * @param color
     *            the color of the circle
     * @param game
     *            the game environment
     */
    public shotBall(int x, int y, int r, java.awt.Color color, GameEnvironment game) {
          this.center = new Point((double) x, (double) y);
          this.r = r;
          this.color = color;
          this.game = game;
    }
    public void drawOn(DrawSurface d){
    	
    }
    public void timePassed(double dt){
    	
    }

    /**
     * constructor without game environment.
     *
     * @param x
     *            center point
     * @param y
     *            center point
     * @param r
     *            the size of radius
     * @param color
     *            the color of the circle
     */
    public shotBall(int x, int y, int r, java.awt.Color color) {
          this.center = new Point((double) x, (double) y);
          this.r = r;
          this.color = color;
    }

    /**
     * @param center
     *            x,y for the center of circle
     * @param r
     *            size of radius
     * @param color
     *            color of circle
     */
    public shotBall(Point center, int r, java.awt.Color color) {
          this.r = r;
          this.color = color;
    }
    /**
     * @param dx
     *            sets the velocity of the ball for horizontal direction
     * @param dy
     *            sets the velocity of the ball for vertical direction
     */
    public void setVelocity(double dx, double dy) {
          this.v = new Velocity(dx, dy);
    }

    /**
     * accessories.
     *
     * @return the x value of the center of the ball
     */
    public int getX() {
          return (int) this.center.getX();
    }

    /**
     * @return the y value of the center of the ball
     */
    public int getY() {
          return (int) this.center.getY();
    }

    /**
     * @return the radius size of the ball
     */
    public int getSize() {
          return (int) this.r;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
          return this.color;
    }
    /**
     * adding the class to the game.
     *
     * @param g
     *            the game in which the game is stored in.
     */
	public void addToGame(GameLevel gameLevel) {
		gameLevel.addSprite(this);
	}
    /**
     * remove a sprite from game level.
     *
     * @param g
     *            the game to be removed from.
     */

    public void removeFromGame(GameLevel g) {
          g.removeSprite(this);
    }
}