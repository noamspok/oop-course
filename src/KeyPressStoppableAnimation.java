import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimationstop the animation.
 *
 * @author User
 *
 */
public class KeyPressStoppableAnimation implements Animation {
      private KeyboardSensor kSensor;
      private String stopKey;
      private Animation anim;
      private boolean stop;
      private boolean isAlreadyPressed = true;

      /**
       * constructor.
       *
       * @param sensor
       *            ks sensor
       * @param key
       *            key
       * @param animation
       *            animation
       */
      public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
            this.anim = animation;
            this.kSensor = sensor;
            this.stopKey = key;
      }

      /**
       * Load table data from file.
       *
       * @param d
       *            DrawSurface
       * @param dt
       *            the time passed
       */
      public void doOneFrame(DrawSurface d, double dt) {

            this.anim.doOneFrame(d, dt);
            if (this.kSensor.isPressed(this.stopKey) && !isAlreadyPressed) {
                  this.stop = true;
            }
            if (!this.kSensor.isPressed(this.stopKey) && isAlreadyPressed) {
                  this.isAlreadyPressed = false;
            }

      }

      /**
       * Load table data from file.
       *
       * @return if should stop
       */

      public boolean shouldStop() {

            return this.stop;
      }
}