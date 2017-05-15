import java.util.ArrayList;

/**
 * Gamegrid class holds information about the state of the game
 * 
 * 0 = Empty space
 * 1 = Player
 * 2 = Crate
 * 3 = Objective
 * 4 = Wall
 * 5 = Player on Objective
 * 6 = Crate on Objective
 */
public class GameGrid {
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
		rowCount = 0;
		colCount = 0;
	}
	
	/**
	 * Prints the layout of the gamegrid into console, useful for debugging.
	 */
	public void printGrid(){
		for(ArrayList<Integer> row: this.grid){
			for(Integer col: row){
				System.out.print(col + " ");
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
		int[] coordinate = new int[3];
		
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if(this.grid.get(r).get(c) == 1){
					coordinate[0] = r + 1;
					coordinate[1] = c + 1;
					coordinate[2] = 0;
				}
				
				else if(this.grid.get(r).get(c) == 5){
					coordinate[0] = r + 1;
					coordinate[1] = c + 1;
					coordinate[2] = 1;
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
			this.grid.get(r - 1).add(input.get(i));
		}
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
			this.grid.get(i).set(c - 1, input.get(i));
		}
	}
	
	
	/**
	 * addRow appends an empty row
	 */
	public void addRow(){
		this.grid.add(new ArrayList<Integer>());
		this.rowCount++;
	}
	
	/**
	 * addCol appends an empty column
	 */
	public void addCol(){
		for(int i = 0; i < rowCount; i++){
			this.grid.get(i).add(0);
		}
		this.colCount++;
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
