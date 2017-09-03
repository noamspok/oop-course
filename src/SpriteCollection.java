import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * this class gathers all of the objects we need to draw on the screen.
 *
 * @author User
 *
 */

public class SpriteCollection {

      private ArrayList<Sprite> cList;

      /**
       * constructor.
       */
      public SpriteCollection() {
            this.cList = new ArrayList<Sprite>();
      }

      /**
       * constructor with list initialized.
       *
       * @param cList
       *            sprite list.
       */
      public SpriteCollection(ArrayList<Sprite> cList) {
            this.cList = cList;
      }

      /**
       * add an object to the list of sprites.
       *
       * @param s
       *            the object to add.
       */
      public void addSprite(Sprite s) {
            this.cList.add(s);
      }
      /**
       * remove an object to the list of sprites.
       *
       * @param s
       *            the object to add.
       */
      public void removeSprite(Sprite s) {
            this.cList.remove(s);
      }
      /**
       * call timePassed() on all sprites.
       */
      public void notifyAllTimePassed() {
         ArrayList<Sprite> cList2 = new ArrayList<Sprite>(this.cList);
         for (Sprite s : cList2) {
                  s.timePassed(60);
            }
      }

      /**
       * call drawOn(d) on all sprites.
       *
       * @param d
       *            the surface to draw on.
       */
      public void drawAllOn(DrawSurface d) {
         ArrayList<Sprite> cList2 = new ArrayList<Sprite>(this.cList);
         for (Sprite s : cList2) {
                  s.drawOn(d);
            }
      }
}