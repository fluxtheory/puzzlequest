import java.util.ArrayList;

/**
 * Gamegrid class holds information about the state of the game
 * 
 */
public class GameGrid {

	public final int EMPTY_SPACE = 0;
	public final int WALL = 1;
	public final int GROUND = 2;
	public final int BOX = 3;
	public final int OBJECTIVE = 4;
	public final int PLAYER_DOWN = 5;
	public final int PLAYER_LEFT = 6;
	public final int PLAYER_RIGHT = 7;
	public final int PLAYER_UP = 8;
	public final int OBJECTIVE_BOX = 9;
	public final int OBJECTIVE_PLAYER_DOWN = 10;
	public final int OBJECTIVE_PLAYER_LEFT = 11;
	public final int OBJECTIVE_PLAYER_RIGHT = 12;
	public final int OBJECTIVE_PLAYER_UP = 13;
	
	private ArrayList<ArrayList<Integer>> grid;
	private int rowCount;
	private int colCount;
	
	/**
	 * <p>Constructor for the gamegrid class.</p>
	 * <p>This creates an empty gamegrid, with no rows and columns</p>
	 * <p>Use addRow and addColumn, then setRow and setColumn to edit gameGrid contents.</p>
	 */
	public GameGrid(){
		this.grid = new ArrayList<ArrayList<Integer>>();
		
		rowCount = 20;
		colCount = 20;
		
		for(int i = 0; i < 20; i++){
			this.grid.add(new ArrayList<Integer>());
			for(int j = 0; j < 20; j++){
				this.grid.get(i).add(0);
			}
		}
	}
	
	/**
	 * Prints the layout of the gamegrid into console, useful for debugging.
	 */
	public void printGrid(){
		for(ArrayList<Integer> row: this.grid){
			for(Integer col: row){
				if(col <= PLAYER_UP && col >= PLAYER_DOWN){
				System.out.print("(" + col + ")");
				}
				
				else if(col == EMPTY_SPACE){
					// Do nothing
				}
				
				else{
					System.out.print(col + "  ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Gets the value at a coordinate
	 * @param r The row
	 * @param c The column
	 * @return The value at the coordinate
	 */
	public Integer getCoordinateValue(int r, int c){
		return this.grid.get(r - 1).get(c - 1);
	}
	
	/**
	 * Sets the value at a coordinate
	 * @param r The row
	 * @param c The column
	 * @param value The value for the coordinate to be set to
	 */
	public void SetCoordinateValue(int r, int c, int value){
		this.grid.get(r - 1).set(c - 1, value);
	}
	
	/**
	 * A method to get player coordinates
	 * @return An array of [row, col, whether player is on objective]
	 */
	public int[] getPlayerCoordinate(){
		int[] coordinate = new int[2];
		
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if(this.grid.get(r).get(c) >= PLAYER_DOWN && this.grid.get(r).get(c) <= PLAYER_UP){
					coordinate[0] = r + 1;
					coordinate[1] = c + 1;
				}
			}
		}
		
		return coordinate;
	}
	
	/**<p>Sets row to a given array list.</p>
	 * <p>Trims exceedingly long array lists.</p>
	 * <p>Adjusts colCount if colCount equals 0</p>
	 * @param r The row to be editted
	 * @param input The arraylist of integers that the row should be editted into
	 */
	public void setRow(int r, ArrayList<Integer> input){
		if(this.colCount == 0){
			colCount = input.size();
		}
		
		this.grid.get(r - 1).clear();
		for(int i = 0; i < colCount; i++){
			if(i < input.size()){
				this.grid.get(r - 1).add(input.get(i));
			}
			
			else{
				this.grid.get(r - 1).add(0);
			}
		}
	}
	
	/**
	 * Get row gets you a row!
	 * @param r
	 */
	public ArrayList<Integer> getRow(int r){
		return this.grid.get(r - 1);
	}
	
	
	/**
	 * Set column to a given array list
	 * Trims exceedingly long array lists.
	 * Will adjust rowCount if gamegrid is 0 x 0
	 */
	public void setColumn(int c, ArrayList<Integer> input){
		if(this.rowCount == 0){
			rowCount = input.size();
		}
		
		for(int i = 0; i < rowCount; i++){
			if(i < input.size()){
				this.grid.get(i).set(c - 1, input.get(i));
			}
			
			else{
				this.grid.get(i).set(c - 1, 0);
			}
		}
	}
	
	// Getters and Setters
	public ArrayList<ArrayList<Integer>> getGrid() {
		return grid;
	}

	public void setGrid(ArrayList<ArrayList<Integer>> grid) {
		this.grid = grid;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}
	
}
