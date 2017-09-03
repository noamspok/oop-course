/**
 *
 * @author noamspok
 *
 */
public class ScoreInfo {
      private String name;
      private int score;

      /**
       * constructor.
       *
       * @param nam
       *            name.
       * @param scor
       *            score.
       */
      public ScoreInfo(String nam, int scor) {
            this.name = nam;
            this.score = scor;
      }

      /**
       *
       * @return the name.
       */
      public String getName() {
            return this.name;
      }

      /**
       *
       * @return the score.
       */
      public int getScore() {
            return this.score;
      }
}