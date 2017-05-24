import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
	public GameGrid loadGame(JFrame frame){
		JFileChooser c = new JFileChooser();
		int returnVal = c.showOpenDialog(frame);
		
		
		GameGrid gg = null;
		Scanner sc = null;
		int i = 1;
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		
			File file = c.getSelectedFile();
			
			try{
				sc = new Scanner(file);
				gg = new GameGrid();
				while(sc.hasNextLine()){
					String line = sc.nextLine();
					gg.setRow(i, this.StringToArrayList_Int(line));
					i++;
				}
			}
			
			catch(FileNotFoundException e){
				//
			}
		    
			finally
		    {
		    	  if (sc != null) sc.close();
		    }

		}
		
		return gg;
	}
	
	
	/**
	 * saveLevel saves a gameGrid into a file.
	 * @param gg The gamegrid to be saved
	 * @param index The index of the save file.
	 */
	public void saveGame(GameGrid gg, JFrame frame) throws IOException{
		
		JFileChooser c = new JFileChooser();
		int returnVal = c.showSaveDialog(frame);
		
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
		        try {
		        	File file = new File(c.getSelectedFile()+".sav");
					PrintWriter writer = new PrintWriter(file);
					
					for(int i = 0; i < gg.getRowCount(); i++){
						for(int j = 0; j < gg.getColCount(); j++){
							//System.out.println(gg.getRow(i + 1).get(j));
							writer.write(Integer.toString(gg.getRow(i + 1).get(j)));
						}
						if(i != gg.getColCount() - 1){
							writer.write("\n");
						}
					}
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Cannot Save");
					e.printStackTrace();
				}
		         
		 }
		
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
