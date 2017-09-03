import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import biuoop.KeyboardSensor;

/**
 * in this class we run game.
 *
 * @author user
 *
 */
public class AlienGame {

	/**
	 * main-here we make the game work.
	 *
	 * @param args
	 *            the argument from the command line.
	 * @throws IOException
	 *             if doe'snt succeed.
	 */
	public static void main(String[] args) throws IOException {

		AnimationRunner animation = new AnimationRunner();
		MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Space Invaders", animation.getKeyboardSensor(),
				animation);
		KeyboardSensor key = animation.getKeyboardSensor();
		GameFlow gameLevel = new GameFlow(animation, key);
		HighScoresTable hst = new HighScoresTable(3);
		HighScoresAnimation highscore = new HighScoresAnimation(hst, animation.getGUI(), gameLevel.getScore());
		KeyPressStoppableAnimation stopAnim = new KeyPressStoppableAnimation(animation.getKeyboardSensor(),
				KeyboardSensor.SPACE_KEY, highscore);
		List<LevelInformation> levels=new ArrayList<>();
		levels.add(new AlienInformaton());
		try {
			File f = new File(HighScoresTable.getFileName());
			if (!f.exists()) {
				hst.save(f);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		menu.addSelection("s", "Start Game" , new MakeLevelsTask(levels ,gameLevel ));
		menu.addSelection("h", "show high scores table", new ShowHiScoresTask(animation, stopAnim));
		menu.addSelection("q", "Quit", new Task<Void>() {
			public Void run() {
				System.exit(0);
				return null;
			}
		});

		while (true) {
			animation.run(menu);
			Task<Void> task = menu.getStatus();
			task.run();
			menu.reset();

		}
	}
}
