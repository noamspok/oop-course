/**
 *
 * @author user this class draws lines and checks middle, meeting point etc..
 * @param <T>
 *            - THE TASK
 */

public interface Menu<T> extends Animation {
      /**
      * draw the sprite to the screen.
      *
      * @param key
      *            ks.
      * @param message
      *            message.
      * @param returnVal
      *            returnVal.
      */
      void addSelection(String key, String message, T returnVal);

      /**
      * . dgetStatus
      *
      * @return task
      */
      T getStatus();

      /**
      * . dgetStatus
      *
      * @param key
      *            ks.
      * @param message
      *            message.
      * @param subMenu
      *            subMenu.
      */
      void addSubMenu(String key, String message, Menu<T> subMenu);
}