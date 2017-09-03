
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

/**
 * run the sleeper.
 * @author user
 *
 */
public class AnimationRunner {
    private GUI gui = new GUI("arkanoid", 800, 600);
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();
    private KeyboardSensor keyboard = this.gui.getKeyboardSensor();
    /**
     * constructors.
     * an empty constructor of AnimationRunner
     */
    public AnimationRunner() {
        this.framesPerSecond = 60;
    }
    /**
     * constructors.
     *
     * @param fPS the frequency per second.
     *            .
     */
    public AnimationRunner(int fPS) {
        this.framesPerSecond = fPS;
    }

    /**
     * run the game till the game should stop.
     * @param animation - the animation of the game
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, (1 / framesPerSecond));

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * @return KeyboardSensor object in order to move the paddle.
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboard;
    }
    /**
     * @return GUI object in order to draw it on the screen.
     */
    public GUI getGUI() {
        return this.gui;
    }
}
