import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.DialogManager;
import biuoop.DrawSurface;

/**
 * . It will display the scores in the high-scores table, until a specified key
 * is pressed
 *
 * @author user
 *
 */
public class HighScoresAnimation implements Animation {
      private GUI gu;
      private boolean end = false;
      private HighScoresTable highScore;
      private int usersScore;
      private ScoreInfo scoreInf;

      /**
       * constructor.
       *
       * @param highScores
       *            the High Scores Table
       */
      public HighScoresAnimation(HighScoresTable highScores) {
            highScore = highScores;
      }

      /**
       * constructor.
       *
       * @param highScores
       *            the High Scores Table
       * @param gui
       *            the game gui
       * @param score
       *            the num of firsts scores
       */
      public HighScoresAnimation(HighScoresTable highScores, GUI gui, int score) {
            this.gu = gui;
            this.highScore = highScores;
            this.usersScore = score;
      }

      /**
       *
       * @return if should stop the High Scores Table
       */
      public boolean shouldStop() {
            return false;
      }

      /**
       * @param d
       *            the DrawSurface
       * @param dt
       *            the time passed
       */
      public void doOneFrame(DrawSurface d, double dt) {
            if (this.highScore.getRank(usersScore) < this.highScore.size()
                        || this.highScore.getHighScores().isEmpty()) {
                  DialogManager dialog = this.gu.getDialogManager();
                  String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                  System.out.println(name);
                  this.scoreInf = new ScoreInfo(name, this.usersScore);
                  this.highScore.add(this.scoreInf);
            }

            List<String> s = new ArrayList<String>();
            d.setColor(Color.yellow);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(5, 20, "High scores:", 20);
            for (ScoreInfo scores : this.highScore.getHighScores()) {
                  s.add((s.indexOf(scores) + 1) + " : " + scores.getName()
                             + "          " + scores.getScore() + " points");
            }
            for (String scoreString : s) {
                  d.drawText(5, 20 * (s.indexOf(scoreString) + 2), scoreString, 20);
            }

      }

}
