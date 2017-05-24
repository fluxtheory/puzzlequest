<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Random;

public class RandomLevel {
	
	private ArrayList<Part> starts;
	private ArrayList<Part> passes;
	private ArrayList<Part> objectives;
	private final static Integer numberOfParts=3;
	private Part p11;
	private Part p12;
	private Part p21;
	private Part p22;
	
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
	
	public void getRandomLevel(){
		//todo random methods
		Random r = new Random();
		p11 = starts.get(r.nextInt(starts.size()));
		p12 = passes.get(r.nextInt(passes.size())).rightToLeft();
		p22 = objectives.get(r.nextInt(objectives.size())).rightToLeft().upToDown();
		
		
=======
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
>>>>>>> branch 'master' of https://github.com/fluxtheory/puzzlequest.git
	}
	

}
