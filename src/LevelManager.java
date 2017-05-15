import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Level loader class, used to load levels
 * Generates grid for BackEnd
 */
public class LevelManager {

	
	public GameGrid loadEmptyLevel(){
		GameGrid gg = new GameGrid(5, 5);
		return gg;
	}
	
	/**
	 * loadLevel loads a level into the gamegrid.
	 * It reads the level from .wml files in levels directory.
	 * @param level
	 * @return
	 */
	public GameGrid loadLevel(int level){
		GameGrid gg = null;
		Scanner sc = null;
		File f = new File("levels/" + level + ".map");
		try{
			sc = new Scanner(f);
			gg = new GameGrid(0, 0);
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				gg.addRow(this.StringToArrayList_Int(line), gg.getRowCount());
			}
		}
		
		catch(FileNotFoundException e){}
	    
		finally
	    {
	    	  if (sc != null) sc.close();
	    }	
		
		return gg;
	}
	
	/**
	 * Converts a string into an arraylist of integers.
	 * @param s
	 */
	private ArrayList<Integer> StringToArrayList_Int(String s){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(String c: s.split("")){
			int i = Integer.parseInt(c);
			l.add(i);
		}
		
		return l;
	}
}
