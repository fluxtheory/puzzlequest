import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Level loader class, used to load levels
 * Generates grid for BackEnd
 */
public class LevelManager {

	public GameGrid loadEmptyLevel(){
		GameGrid gg = new GameGrid();
		return gg;
	}
	
	/**
	 * loadLevel loads a level into the gamegrid.
	 * It reads the level from .wml files in levels directory.
	 * @param level
	 * @return
	 */
	public GameGrid createLevel(int level){
		GameGrid gg = null;
		Scanner sc = null;
		int i = 1;
		File f = new File("levels/" + level + ".map");
		try{
			sc = new Scanner(f);
			gg = new GameGrid();
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				gg.setRow(i, this.StringToArrayList_Int(line));
				i++;
			}
		}
		
		catch(FileNotFoundException e){}
	    
		finally
	    {
	    	  if (sc != null) sc.close();
	    }	
		
		return gg;
	}
	
	public GameGrid loadGame(int index){
		GameGrid gg = null;
		Scanner sc = null;
		int i = 1;
		File f = new File("saves/" + index + ".sav");
		try{
			sc = new Scanner(f);
			gg = new GameGrid();
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				gg.setRow(i, this.StringToArrayList_Int(line));
				i++;
			}
		}
		
		catch(FileNotFoundException e){
			return null;
		}
	    
		finally
	    {
	    	  if (sc != null) sc.close();
	    }
		
		return gg;
	}
	
	/**
	 * saveLevel saves a gameGrid into a file.
	 * @param gg The gamegrid to be saved
	 * @param index The index of the save file.
	 */

	
	
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
