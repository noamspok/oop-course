import java.awt.Color;

import biuoop.DrawSurface;

public class Alienbg implements Sprite {

	@Override
	public void drawOn(DrawSurface d) {
		d.setColor(Color.BLACK);
		d.drawRectangle(0, 0, 800, 600);

	}

	@Override
	public void timePassed(double dt) {
		
	}

}
