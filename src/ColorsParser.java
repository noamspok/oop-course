
import java.awt.Color;

import java.lang.reflect.Field;

/**
 * ColorsParser this class parse the colors.
 *
 * @author User
 *
 */
public class ColorsParser {
      /**
       * Notify the object that we collided with it at collisionPoint with a given
       * velocity.
       *
       * @param s
       *            string of color
       * @return color
       */
      public static Color colorFromString(String s) {
            try {
                  if (!s.contains("RGB")) {
                        Field field = Class.forName("java.awt.Color").getField(getColorFromString(s));
                        Color colorObj = (Color) field.get(null);
                        return colorObj;
                  } else {
                        return ColorsParser.getColorByRGB(getColorFromString(s));
                  }

            } catch (Exception ex) {

                  System.out.println(ex.toString());
                  return null;
            }
      }

      /**
       * Notify the object that we collided with it at collisionPoint with a given
       * velocity.
       *
       * @param fillValue
       *            string of color
       * @return color
       */
      public static Color getColorByRGB(String fillValue) {
            String subSring = fillValue.substring(fillValue.indexOf("(") + 1, fillValue.indexOf(")"));
            String[] arrRgb = subSring.split(",");
            return Color.getHSBColor(Float.parseFloat(arrRgb[0]), Float.parseFloat(arrRgb[1]),
                  Float.parseFloat(arrRgb[2]));
      }

      /**
       * get Color From String velocity.
       *
       * @param stringColor
       *            string of color
       * @return color
       */
      public static String getColorFromString(String stringColor) {
            if (stringColor.startsWith("color(RGB")) {
                  return stringColor.substring(stringColor.indexOf('(') + 1, stringColor.indexOf(')') + 1);
            } else {
                  return stringColor.substring(stringColor.indexOf('(') + 1, stringColor.indexOf(')'));
            }

      }

}
