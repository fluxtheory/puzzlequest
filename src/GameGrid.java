import java.util.ArrayList;

/**
 * Gamegrid class holds information about the state of the game
 * 
 * 0 = Empty space
 * 1 = Player
 * 2 = Crate
 * 3 = Objective
 * 4 = Wall
 * We need Player on Objective
 *     and Crate on Objective
 */
public class GameGrid {
	ArrayList<ArrayList<Integer>> grid;
	
	/**
	 * Constructor for the gameGrid class
	 * Initializes grids with empty spaces (0)
	 * @param r The number of rows
	 * @param c The number of columns
	 */
	public GameGrid(int r, int c){
		grid = new ArrayList<ArrayList<Integer>>(r);
		
		// Add in rows of array list
		for(int i = 0; i < r; i++){
			grid.add(new ArrayList<Integer>());
		}
		
		for(ArrayList<Integer> col: grid){
			for(int i = 0; i< c; i++){
				col.add(0);
			}
		}
	}
	
	/**
	 * Prints the layout of the gamegrid into console, useful for debugging.
	 */
	public void printGrid(){
		for(ArrayList<Integer> row: grid){
			for(Integer col: row){
				System.out.print(col + " ");
			}
			System.out.print("\n");
		}
	}
}
