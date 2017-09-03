/**
 * . this class gathers all of the objects we need to draw on the screen.
 *
 * @author User
 * @param <T>
 *            -task
 */
public interface Task<T> {
      /**
       * . constructor with list initialized.
       *
       * @return t
       */
      T run();
}