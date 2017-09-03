import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * . HighScoresTable
 *
 * @author user
 */
class HighScoresTable implements Serializable {
      private List<ScoreInfo> highScoresList;
      private int size;

      /**
       * constructor. Create an empty high-scores table with the specified size.
       * The size means that the table holds up to size top scores.
       *
       * @param size
       *            the size of high scores
       */
      public HighScoresTable(int size) {
            this.size = size;
            this.highScoresList = new ArrayList<ScoreInfo>();
      }

      /**
       * get the fie name of high scores.
       *
       * @return the file name
       */
      public static String getFileName() {
            return "highscores.txt";
      }

      /**
       * Create an empty high-scores table with the specified size. The size means
       * that the table holds up to size top scores.
       *
       * @param score
       *            the score to be added
       */
      public void add(ScoreInfo score) {
            this.highScoresList.add(score);
      }

      /**
       * @return table size.
       */
      public int size() {
            return this.highScoresList.size();
      }

      /**
       * Return the current high scores The list is sorted such that the highest
       * scores come first.
       *
       * @return list highScores the High Scores Table
       */
      public List<ScoreInfo> getHighScores() {
            return this.highScoresList;
      }

      /**
       * return the rank of the current score: where will it be on the list if
       * added? Rank 1 means the score will be highest on the list. Rank `size`
       * means the score will be lowest. Rank > `size` means the score is too low
       * and will not be added to the list.
       *
       * @param score
       *            the score
       * @return the rank
       */
      public int getRank(int score) {
            int tempSize = 1;
            for (int i = 0; i < highScoresList.size(); i++) {
                  if (this.highScoresList.get(i).getScore() > score) {
                        tempSize++;
                  }
            }
            return tempSize;
      }

      /**
       * . Clears the table
       */
      public void clear() {
            this.highScoresList.clear();
      }

      /**
       * Save table data to the specified file..
       *
       * @param filename
       *            the file
       * @throws IOException
       *             - -the exc
       */
      public void load(File filename) throws IOException {
            try {
                  FileInputStream fis = new FileInputStream(filename);
                  ObjectInputStream oin = new ObjectInputStream(fis);
                  HighScoresTable his = (HighScoresTable) oin.readObject();
                  this.size = his.size();
                  this.highScoresList = his.getHighScores();
                  fis.close();
            } catch (IOException ex) {
                  System.out.println("fail");
            } catch (ClassNotFoundException e) {
                  e.printStackTrace();
            }
      }

      /**
       * Save table data to the specified file..
       *
       * @param filename
       *            the file
       * @throws IOException
       *             - -the exc
       */
      public void save(File filename) throws IOException {
            try {
                  FileOutputStream fos = new FileOutputStream(filename);
                  ObjectOutputStream oos = new ObjectOutputStream(fos);
                  HighScoresTable hst = new HighScoresTable(3);
                  oos.writeObject(hst);
                  oos.flush();
                  oos.close();
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

      /**
       * Read a table from file and return it. If the file does not exist, or
       * there is a problem with reading it, an empty table is returned. Save
       * table data to the specified file..
       *
       * @param filename
       *            the file
       * @return HighScoresTable
       */
      public static HighScoresTable loadFromFile(File filename) {
            HighScoresTable his = new HighScoresTable(3);
            try {
                  his.load(filename);
            } catch (IOException e) {
                  e.printStackTrace();
            }
            return his;

      }
}