import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class AlienCollection {

	private ArrayList<ArrayList<AlienBlock>> AlienBlocks= new ArrayList<ArrayList<AlienBlock>>() ;
	
	public AlienCollection() {
		
	}
	
	public AlienCollection (ArrayList<ArrayList<AlienBlock>> blo){
		this.AlienBlocks=blo;
	}
	
	public void addAlienBlock(AlienBlock b,int listnum){
		this.AlienBlocks.get(listnum).add(b);
	}
	public void setList(ArrayList<AlienBlock> bl, int listnum){
		if(listnum<this.AlienBlocks.size()){
			this.AlienBlocks.remove(listnum);
		this.AlienBlocks.add(listnum, bl);
			}
		else{
			this.AlienBlocks.add(bl);
		}
	}
	public AlienBlock getAlienBlock(int listnum, int place){
		return this.AlienBlocks.get(listnum).get(place);
	}
	public ArrayList<AlienBlock> getList(int listnum){
		return this.AlienBlocks.get(listnum);
	}


	public ArrayList<ArrayList<AlienBlock>> getAlienBlocksList() {
		return this.AlienBlocks;
	}
public void addList(ArrayList<AlienBlock> bl){
this.AlienBlocks.add(bl);
}

	public void setAlienBlocks(ArrayList<ArrayList<AlienBlock>> AlienBlock) {
		this.AlienBlocks = AlienBlock;
	}
	
public AlienBlock shooter(){
	int counter=this.AlienBlocks.size()-1;
	Random r= new Random();
	int block= r.nextInt(counter);
	return this.AlienBlocks.get(block).get(this.AlienBlocks.get(block).size()-1);
}
public void removeAlien(AlienBlock b){
	for (int i = 0; i < this.AlienBlocks.size(); i++) {
		if(this.AlienBlocks.get(i).contains(b)){
			this.AlienBlocks.get(i).remove(this.AlienBlocks.get(i).indexOf(b));
			
		}
	}{
		
	}
}
}
