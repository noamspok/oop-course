import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author user The CountdownAnimation will display the given gameScreen, for
 *         numOfSeconds seconds, and on top of them it will show a countdown
 *         from countFrom back to 1, where each number will appear on the screen
 *         for (numOfSeconds / countFrom) seconds, before it is replaced with
 *         the next one.
 */

public class CountdownAnimation implements Animation {
    private double seconds;
    private int countDown;
    private SpriteCollection game;
    private boolean stop;
    private int i = 0;
    private long startTime = System.currentTimeMillis();

    /**
     * constructors.
     *
     * @param numOfSeconds
     *            the number of seconds of the game.
     * @param countFrom
     *            the number of seconds to count from till 1.
     * @param gameScreen
     *            the collection of sprites to show inthe game. .
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.countDown = countFrom;
        this.game = gameScreen;

    }

    /**
     * moves the ball to its new place on the screen.
     *
     * @param d
     *            - the drawsurface to be print on the screen.
     * @param dt
     * time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        long endTime = startTime + (long) (seconds + 0.5) * 1000;
        double appearanceTime = seconds / countDown * 1000;
        String s = String.valueOf(countDown - i);
        this.game.drawAllOn(d);
        d.setColor(Color.white);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, s, 32);
        if (System.currentTimeMillis() > startTime + (1 + i) * appearanceTime) {
            i++;
        }
        if (System.currentTimeMillis() > endTime) {
            this.stop = true;
        }
    }

    /**
     * moves the ball to its new place on the screen.
     *
     * @return a flag value if the game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
