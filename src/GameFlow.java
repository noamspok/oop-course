import java.util.List;

import biuoop.KeyboardSensor;

/**
 * GameFlow is used in order to manage the game flow and . run the levels by
 * order.
 *
 * @author user
 *
 */
public class GameFlow {
      private AnimationRunner animation;
      private KeyboardSensor keyb;
      private Counter score = new Counter();
      private Counter lives = new Counter(7);
      private boolean win = false;
      private EndAnimation end;
      private HighScoresAnimation scoreAni;
      private HighScoresTable highScores;
/**
 * constructor.
 * @param ar
 * the runner.
 * @param ks
 * the kb sensor.
 */
      public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
            this.animation = ar;
            this.keyb = ks;

      }

      /**
       * initialize the levels and run the level till the number of lives . or the
       * blocks is 0.
       *
       * @param levels
       *            - a list of the levels to run.
       */
      public void runLevels(List<LevelInformation> levels) {
            end = new EndAnimation(this.score, this.win);
            for (LevelInformation levelInfo : levels) {

                  GameLevel level = new GameLevel(levelInfo, this.keyb, this.animation, this.lives, this.score);

                  level.initialize();

                  while (this.lives.getValue() != 0 && level.getNumOfNotRemovedBlocks() != 0) {
                        level.playOneTurn();
                  }

                  if (this.lives.getValue() == 0) {
                        this.animation.run(end);

                        break;
                  }

            }
            this.win = true;
            this.animation.run(end);
      }
/**
 *
 * @return the animatio runner.
 */
      public AnimationRunner getAnimRunner() {
            return this.animation;
      }
/**
 *
 * @return the score.
 */
      public int getScore() {
            return this.score.getValue();
      }
}
