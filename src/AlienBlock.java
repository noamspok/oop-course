import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

public class AlienBlock extends Block{
	private Velocity vel;
    private String image;
    private Image imagee = null;
    private Counter hit;
    private boolean addvel = true;
    private Point  startingPoint;
	public AlienBlock(int xpos, int ypos,Velocity ve, String imag) {
		super(new Point(xpos, ypos));
		this.startingPoint=new Point(xpos, ypos);
		this.hit= new Counter(1);
		this.vel=ve;
		this.image=imag;
		try {
            this.imagee = ImageIO
                          .read(new File(this.image));
     } catch (IOException e) {
            System.out.println("error with image");
     }
		
	}
	
	public AlienBlock(Point upper,Velocity ve, String imag) {
		super(upper);
		this.startingPoint=upper;
		this.vel=ve;
		this.hit= new Counter(1);
		this.image=imag;
		try {
            this.imagee = ImageIO
                          .read(new File(this.image));
     } catch (IOException e) {
            System.out.println("error with image");
     }
		
	}

	@Override
	public Rectangle getCollisionRectangle() {
		return super.getCollisionRectangle();
	}
	public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
		String hitLine = this.getrectBlock().collidedLine(collisionPoint);
        if (hitLine.equals("up")) {
            Velocity newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            if (hit.getValue() >= 0) {
                hit.decrease(1);
            }
            return newVelocity;
        }
        if (hitLine.equals("bottom")) {
            Velocity newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            this.notifyHit(hitter);
            if (this.hit.getValue() >= 0) {
                this.hit.decrease(1);
            }
            return newVelocity;
        }
        if (hitLine.equals("left")) {
            Velocity newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            if (hit.getValue() >= 0) {
                hit.decrease(1);
            }
            return newVelocity;
        }
        if (hitLine.equals("right")) {
            Velocity newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            if (hit.getValue() >= 0) {
                hit.decrease(1);
            }
            return newVelocity;
        }
        if (hitLine.equals("corner")) {
            Velocity newVelocity = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            if (hit.getValue() >= 0) {
                hit.decrease(1);
            }
            return newVelocity;
        } else {
            return null;
        }
    
		
	}
	@Override
	public void drawOn(DrawSurface surface) {
		surface.drawImage((int) super.getrectBlock().getUpperLeft().getX(),
                (int) super.getrectBlock().getUpperLeft().getY(), this.imagee);
	}

	
	@Override
	public void timePassed(double dt) {
		this.moveonestep();
	}

	public void moveonestep() {
		if(this.addvel){
			this.setUpperLeft(new Point( super.getrectBlock().getUpperLeft().getX()+this.vel.getDx(),
					super.getrectBlock().getUpperLeft().getY()+this.vel.getDy()));
		}
		if(!this.addvel){
			this.addvel=true;
		}
		
	}

	@Override
	public void addToGame(GameLevel g) {
		super.addToGame(g);
	}

	@Override
	public void removeFromGame(GameLevel gameLevel) {
		super.removeFromGame(gameLevel);
	}

	@Override
	public void addHitListener(HitListener hl) {
		super.addHitListener(hl);
	}

	@Override
	public void removeHitListener(HitListener hl) {
		super.removeHitListener(hl);
	}

	@Override
	public int getHitPoints() {
		return this.hit.getValue();
	}

public Velocity getVelocity(){
	return this.vel;
}
public void setUpperLeft(Point upper){
	super.getrectBlock().SetUpper(upper);
}
public void setaddvell(){
	this.addvel=false;
} 
public void shoot(GameLevel g){
	 Ball b = new Ball((int)(super.getrectBlock().getUpperLeft().getX()+super.getrectBlock().getWidth()/2),
			 (int)(super.getrectBlock().getUpperLeft().getY()+super.getrectBlock().getHeight()+1), 5,Color.red, g.getGameEnv() );
	 b.setVelocity(new Velocity(0, 1));
	 b.addToGame(g);
}
public void setVel(Velocity v){
	this.vel=v;
}
public Point getStartingPoint(){
	return this.startingPoint;
}
public void notifyHit(Ball hitter) {
	super.notifyHit(hitter);
}
}
