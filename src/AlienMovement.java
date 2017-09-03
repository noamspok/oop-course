
public class AlienMovement {

	private AlienCollection bl;
	private Velocity currentVel= new Velocity(0, 0);
	private int distance=0;
	
	public AlienMovement(){
		this.bl=new AlienCollection();
	}
	public AlienMovement(AlienCollection blo){
		this.bl=blo;
	}
	public AlienCollection getAlienMovement() {
		return bl;
	}
	public void setAlienCollection(AlienCollection bloc) {
		this.bl = bloc;
	}
	
	public Velocity getmovement(){
		this.currentVel= this.bl.getAlienBlock(0, 0).getVelocity();
		for (int i = 0; i < this.bl.getAlienBlocksList().size(); i++) {
			
			if (this.currentVel.getDx()<0){
			if(!this.bl.getList(i).isEmpty()){
				if(this.bl.getList(i).get(0).getCollisionRectangle().getUpperLeft().getX()
						+this.currentVel.getDx()<=0){
					this.setdistance (this.bl.getList(i).get(0).getCollisionRectangle().getUpperLeft().getX());
					this.setNewUpperLeft(-1);
						return new Velocity(-1.1*(this.currentVel.getDx()),0);
					}
				}
				
			}
			else if (this.currentVel.getDx()>0){
				if(!this.bl.getList(this.bl.getAlienBlocksList().size()-i-1).isEmpty()){
					if(this.bl.getList(this.bl.getAlienBlocksList().size()-i-1).get(0).getCollisionRectangle().
							getUpperLeft().getX()+this.currentVel.getDx()>=760){
						this.setdistance(-(this.bl.getList(this.bl.getAlienBlocksList().size()-i-1).get(0).
								getCollisionRectangle().getUpperLeft().getX()));
						this.setNewUpperLeft(1);
							return new Velocity(-1.1*(this.currentVel.getDx()),0);
						}
			}
		}
	}
		return null;
	}
	public void setNewUpperLeft(int vel){
		for (int j = 0; j < this.bl.getAlienBlocksList().size(); j++) {
			if(!this.bl.getList(j).isEmpty()){
			for (int i = 0; i < this.bl.getList(j).size(); i++) {
				this.bl.getAlienBlock(j, i).setUpperLeft(new Point(this.bl.getAlienBlock(j, i).
						getCollisionRectangle().getUpperLeft().getX()+vel*this.distance,
						this.bl.getAlienBlock(j, i).getCollisionRectangle().getUpperLeft().getY()+40));
			}
			}
		}
	}
	public void setdistance(double distance){
		if(distance<0){
		this.distance= 800+(int)distance-40;
		}
		else{
			this.distance= (int)distance;
		}
	}
	public void setVelAliens(){
		this.currentVel=this.getmovement();
	if (this.currentVel!=null){
		for (int j = 0; j < this.bl.getAlienBlocksList().size(); j++) {
			if(!this.bl.getList(j).isEmpty()){
			for (int i = 0; i < this.bl.getList(j).size(); i++) {
				this.bl.getAlienBlock(j, i).setVel(this.currentVel);;
			}}
	}}
	}
	public AlienCollection getAlienCollection(){
		return this.bl;
	}
	public void returnToStart(){
		for (int j = 0; j < this.bl.getAlienBlocksList().size(); j++) {
			if(!this.bl.getList(j).isEmpty()){
			for (int i = 0; i < this.bl.getList(j).size(); i++) {
				this.bl.getAlienBlock(j, i).setUpperLeft(this.bl.getAlienBlock(j, i).getStartingPoint());
			}
		}}
	}
	public void setLevelVelAliens(int v){
		this.currentVel= this.bl.getAlienBlock(0, 0).getVelocity();
	if (this.currentVel!=null){
		for (int j = 0; j < this.bl.getAlienBlocksList().size(); j++) {
			if(!this.bl.getList(j).isEmpty()){
			for (int i = 0; i < this.bl.getList(j).size(); i++) {
				this.bl.getAlienBlock(j, i).setVel(new Velocity(v*this.currentVel.getDx(),this.currentVel.getDy()));
			}}
	}}
	}
}
