import java.util.ArrayList;

public class RandomLevel {
	
	private ArrayList<Part> starts;
	private ArrayList<Part> passes;
	private ArrayList<Part> objectives;
	private final static Integer numberOfParts=5;
	
	public RandomLevel(){
		for(int i = 0;i<numberOfParts;i++){
			Part p=new Part(i);
			switch(p.getKind()){
				case 1:starts.add(p);break;
				case 2:passes.add(p);break;
				case 3:objectives.add(p);break;
			}
		}
	}
	
	public GameGrid getRandomLevel(){
		GameGrid gg = new GameGrid();
		//todo random methods
		return gg;
	}
	

}
