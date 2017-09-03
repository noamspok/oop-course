
/**
 * Counter is a simple class that is used for counting things.
 *
 * @author user
 *
 */

public class Counter {
      private int i;

      /**
       * constructor.
       */
      public Counter() {
            this.i = 0;
      }
      /**
       * constructor.
       *
       * @param j
       * starts with j value.
       */
      public Counter(int j) {
            this.i = j;
      }
      /**
       * add number to current count.
       *
       * @param number
       * the number
       */
      void increase(int number) {
            this.i += number;
      }

      /**
       * subtract number from current count.
       *
       * @param number
       * the number
       */
      void decrease(int number) {
            this.i -= number;
      }

      /**
       * get current count.
       *
       * @return current count.
       */
      int getValue() {
            return this.i;
      }
}
