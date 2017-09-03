import java.awt.Color;

import java.util.ArrayList;
import java.util.List;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * MenuAnimation class is used in order to determine the second level
 * background.
 *
 * @author user
 * @param <T>
 *            -the task
 *
 */
public class MenuAnimation<T> implements Menu<T> {
      private String menuTitle;
      private Boolean shouldStop = false;
      private List<String> keys;
      private List<String> messages;
      private List<Task<T>> tasks;
      private T val;
      private AnimationRunner animRun;
      private KeyboardSensor keyb;

      /**
       * draw the sprite to the screen.
       *
       * @param menuTitle
       *            menuTitle.
       * @param key
       *            key.
       * @param anim
       *            anim.
       */
      public MenuAnimation(String menuTitle, KeyboardSensor key, AnimationRunner anim) {
            this.menuTitle = menuTitle;
            this.keyb = key;
            this.keys = new ArrayList<String>();
            this.messages = new ArrayList<String>();
            this.tasks = new ArrayList<Task<T>>();
            this.animRun = anim;
      }

      /**
       * . get Keys list
       *
       * @return a list of keys for menu
       */
      public List<String> getKey() {
            return this.keys;
      }

      /**
       * . get list of tasks
       *
       * @return a list of tasks for menu
       */
      public List<Task<T>> getTask() {
            return this.tasks;
      }

      /**
       * . get list of messages
       *
       * @return a list of messages of the menu
       */
      public List<String> getMessage() {
            return this.messages;
      }

      /**
       * draw the sprite to the screen.
       *
       * @param key
       *            key.
       * @param message
       *            message.
       * @param returnVal
       *            returnVal.
       */
      public void addSelection(String key, String message, Object returnVal) {
            this.keys.add(key);
            this.messages.add(message);
            this.tasks.add((Task<T>) returnVal);
      }

      /**
       * doOneFrame.
       *
       * @param d
       *            d.
       * @param dt
       *            dt.
       */
      public void doOneFrame(DrawSurface d, double dt) {
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.yellow);
            d.drawText(20, 40, menuTitle, 50);
			d.setColor(Color.green);
            for (int i = 0; i < this.keys.size(); i++) {

                  d.drawText(20, 90 + i * 35, "to " + this.messages.get(i) + " press " + this.keys.get(i), 25);

            }
            for (int j = 0; j < this.keys.size(); j++) {
                  if (this.keyb.isPressed(this.keys.get(j))) {
                        this.val = (T) this.tasks.get(j);
                        this.shouldStop = true;
                  }
            }

      }

      /**
       * draw the sprite to the screen.
       *
       * @param key
       *            key.
       * @param message
       *            message.
       * @param subMenu
       *            subMenu.
       */
      public void addSubMenu(String key, String message, Menu<T> subMenu) {
            addSelection(key, message, new Task<T>() {

				@Override
				public T run() {
					
					return null;
				}
			});
      }

      /**
       * when to stop.
       *
       * @return true if should stop
       */
      public boolean shouldStop() {
            return this.shouldStop;
      }

      /**
       * did the user press.
       *
       * @return the task.
       */
      public T getStatus() {
            return this.val;
      }

      /**
       * set run animation.
       *
       * @param animR
       *            the animation runner.
       */
      public void setAnimRun(AnimationRunner animR) {
            this.animRun = animR;
      }

      /**
       * reset.
       */
      public void reset() {
            this.shouldStop = false;
      }
}
