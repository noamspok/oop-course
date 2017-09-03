import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * making blocks for the game, using the collidable, sprite and HitNotifier
 * interfaces.
 *
 * @author user
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
       private Rectangle block;
       private Counter hit;
       private Color color;
       private Color stroke;
       private List<HitListener> hitListeners = new ArrayList<>();
       

       /**
        * constructors.
        *
        * @param start
        *            upper left point of rectangle.
        * @param width
        *            width of rectangle
        * @param height
        *            height of rectangle
        * @param color
        *            color of rectangle
        */
       public Block(Point start, double width, double height, java.awt.Color color) {
              this.setrectBlock(new Rectangle(start, width, height));
              this.color = color;
              this.hit = new Counter();
              this.stroke = null;
       }

      
       public Block(Point start) {
           this.setrectBlock(new Rectangle(start, 40, 30));
       }

       
       /**
        *
        * @param start
        *            upper left point of rectangle.
        * @param width
        *            width of rectangle
        * @param height
        *            height of rectangle
        * @param color
        *            color of rectangle
        * @param hits
        *            number of max hits.
        */
       public Block(Point start, double width, double height, Color color, Counter hits) {
              this.setrectBlock(new Rectangle(start, width, height));
              this.color = color;
              this.hit = hits;
              this.stroke = null;
       }

       
       /**
       *
       * @param x
       *            x value of upper left of rectangle.
       * @param y
       *            y value of upper left of rectangle.
       * @param width
       *            width of rectangle
       * @param height
       *            height of rectangle
       * @param color
       *            color of rectangle
       *            * @param hits
       *            number of max hits.
       */
      public Block(int x, int y, int width, int height, Color color, Counter hits) {
             Point start = new Point(x, y);
             this.setrectBlock(new Rectangle(start, width, height));
             this.color = color;
             this.stroke = null;
             this.hit= hits;
      }

      

       
       /**
        * @return collidable type.
        */
       public Rectangle getCollisionRectangle() {
              return this.getrectBlock();
       }

       /**
        * tells the ball how to act when hitting this class.
        *
        * @param collisionPoint
        *            the collision point.
        * @param currentVelocity
        *            current velocity of the ball.
        * @param hitter
        *            the ball which hits.
        * @return the new velocity.
        */

       public void hit(Ball hitter,GameLevel gl) {
    	   hitter.removeFromGame(gl);
       }

       /**
        * tells the ball how to act when hitting this class.
        *
        * @param collisionPoint
        *            the collision point.
        * @param currentVelocity
        *            current velocity of the ball.
        * @return the new velocity.
        */

       public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
           String hitLine = this.getrectBlock().collidedLine(collisionPoint);
           this.notifyHit(hitter);
           if (hitLine.equals("up")) {
               Velocity newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
               if (hit.getValue() >= 0) {
                   hit.decrease(1);
               }
               return newVelocity;
           }
           if (hitLine.equals("bottom")) {
               Velocity newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
               if (hit.getValue() >= 0) {
                   hit.decrease(1);
               }
               return newVelocity;
           }
           if (hitLine.equals("left")) {
               Velocity newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
               if (hit.getValue() >= 0) {
                   hit.decrease(1);
               }
               return newVelocity;
           }
           if (hitLine.equals("right")) {
               Velocity newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
               if (hit.getValue() >= 0) {
                   hit.decrease(1);
               }
               return newVelocity;
           }
           if (hitLine.equals("corner")) {
               Velocity newVelocity = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
               if (hit.getValue() >= 0) {
                   hit.decrease(1);
               }
               return newVelocity;
           } else {
               return null;
           }
       }
     


       /**
        * @param surface
        *            get size of surface check if ball isn't initialized out of
        *            right|upper boarder draw the ball on the given DrawSurface
        */

       public void drawOn(DrawSurface surface) {
              DrawSurface d = surface;
                     d.setColor(this.color);
                     d.fillRectangle((int) this.getrectBlock().getUpperLeft().getX(), (int) this.getrectBlock().getUpperLeft().getY(),
                                   (int) this.getrectBlock().getWidth(), (int) this.getrectBlock().getHeight());
              
       }


       /**
        * Sprite parts. this function gets the paddle to move in the time laps.
        * checks which case the user used to know where to move. for now, empty.
        * @param dt
        * time
        */
       public void timePassed(double dtt) {
    	   

       }

       /**
        * add the block to the database.
        *
        * @param g
        *            the data base.
        */
       public void addToGame(GameLevel g) {
              g.addCollidable(this);
              g.addSprite(this);
       }

       /**
        * add the block to the database.
        *
        * @param gameLevel
        *            a level at the game.
        */
       public void removeFromGame(GameLevel gameLevel) {
              gameLevel.removeCollidable(this);
              gameLevel.removeSprite(this);
       }

       /**
        * Notify all listeners about a hit event.
        *
        * @param hitter
        *            the hitter ball.
        */
       public void notifyHit(Ball hitter) {
              // Make a copy of the hitListeners before iterating over them.
              List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
              // Notify all listeners about a hit event:
              for (HitListener hl : listeners) {
                     hl.hitEvent(this, hitter);
              }
       }

       /**
        * add hit listener from the list.
        *
        * @param hl
        *            the hit listener.
        */
       public void addHitListener(HitListener hl) {
              if (hl != null) {
                     this.hitListeners.add(hl);
              }
       }

       /**
        * remove hit listener from the list.
        *
        * @param hl
        *            the hit listener.
        */
       public void removeHitListener(HitListener hl) {
              if (hl != null) {
                     this.hitListeners.remove(hl);
              }

       }

       /**
        * get the collision point.
        * @return hit points
        */
       public int getHitPoints() {
              return this.hit.getValue();
       }


	public Rectangle getrectBlock() {
		return this.block;
	}


	public void setrectBlock(Rectangle block) {
		this.block = block;
	}
       

}
