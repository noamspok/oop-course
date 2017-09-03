import java.util.ArrayList;

/**
 * making the game Environment.
 *
 * @author user
 *
 */
public class GameEnvironment {
      private ArrayList<Collidable> cList;

      /**
       * constructor.
       */
      public GameEnvironment() {
            this.cList = new ArrayList<Collidable>();
      }

      /**
       * add the given collidable to the environment.
       *
       * @param c
       *            the object that collides.
       */
      public void addCollidable(Collidable c) {
            this.cList.add(c);
      }

      /**
       * remove the given collidable to the environment.
       *
       * @param c
       *            the object that collides.
       */
      public void removeCollidable(Collidable c) {
            this.cList.remove(c);
      }

      /**
       * Assume an object moving from line.start() to line.end(). If this object
       * will not collide with any of the collidables in this collection, return
       * null. Else, return the information about the closest collision that is
       * going to occur.
       *
       * @param trajectory
       *            the line of the ball that we check if collides.
       * @return null for no collision else return collision information
       */
      public CollisionInfo getClosestCollision(Line trajectory) {
            Point ballLocation = trajectory.start(), closestCollision = null;
            double min = 0;
            int index = 0;
            for (int i = 0; i < this.cList.size(); i++) {
                  if (trajectory.closestIntersectionToStartOfLine(this.cList.get(i).getCollisionRectangle()) != null) {
                        if (ballLocation
                                    .distance(trajectory
                                                .closestIntersectionToStartOfLine(
                                                            this.cList.get(i).getCollisionRectangle())) < min
                                    || min == 0) {
                              closestCollision = trajectory
                                          .closestIntersectionToStartOfLine(
                                                      this.cList.get(i).getCollisionRectangle());
                              min = ballLocation.distance(
                                          trajectory.closestIntersectionToStartOfLine(
                                                      this.cList.get(i).getCollisionRectangle()));
                              index = i + 1;
                        }
                  }
            }
            if (index == 0) {
                  return null;
            }
            return new CollisionInfo(closestCollision, this.cList.get(index - 1));

      }

}
