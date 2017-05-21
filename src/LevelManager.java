import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Level loader class, used to load levels
 * Generates grid for BackEnd
 */
public class LevelManager {



	/**
	 * createLevel loads a level into the gamegrid.
	 * Levels are loaded from "levels/*.wbl"
	 * @param index The index of the save file
	 * @return The gamegrid if found, null if not found.
	 */
	public GameGrid createLevel(int index){
		GameGrid gg = null;
		Scanner sc = null;
		int i = 1;
		
		File f = new File("levels/" + index + ".wbl");
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
	 * loadGame loads a save file into the gamegrid.
	 * Saves are loaded from "saves/*.sav"
	 * @param index The index of the save file
	 * @return The gamegrid if found, null if not found.
	 */
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
	public void saveGame(GameGrid gg, int index) throws IOException{
		
		File file = new File("saves/" + index + ".sav");
		file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		
		for(int i = 0; i < gg.getRowCount(); i++){
			for(int j = 0; j < gg.getColCount(); j++){
				System.out.println(gg.getRow(i + 1).get(j));
				writer.write(Integer.toString(gg.getRow(i + 1).get(j)));
			}
			if(i != gg.getColCount() - 1){
				writer.write("\n");
			}
		}
		writer.flush();
		writer.close();
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
