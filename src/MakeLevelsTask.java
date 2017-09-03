import java.util.List;

/**
 * MakeLevelsTask class is used in order to determine the second level
 * background.
 *
 * @author user
 *
 */
public class MakeLevelsTask implements Task<Void> {
      private List<LevelInformation> levelSpecList;
      private GameFlow game;

      /**
       * draw the sprite to the screen.
       *
       * @param levList
       *            the surface to draw on.
       * @param gam
       *            the game
       */
      public MakeLevelsTask(List<LevelInformation> levList, GameFlow gam) {
            this.levelSpecList = levList;
            this.game = gam;
      }

      @Override
      public Void run() {
            this.game.runLevels(levelSpecList);
            return null;
      }

}
