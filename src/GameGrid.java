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
	private ArrayList<ArrayList<Integer>> grid;
	private int rowCount;
	private int colCount;
	
	/**
	 * Constructor for the gameGrid class
	 * Initializes grids with empty spaces (0)
	 * @param r The number of rows
	 * @param c The number of columns
	 */
	public GameGrid(int r, int c){
		this.grid = new ArrayList<ArrayList<Integer>>(r);
		
		rowCount = 0;
		colCount = 0;
		
		this.addRow(r, 0);
		this.addCol(c, 0);
	}
	
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
	 * addRow adds count rows of 0s in offset
	 * @param count
	 * @param offset
	 */
	public void addRow(int count, int offset){
		
		for(int i = 0; i < count; i++){
			this.grid.add(offset, new ArrayList<Integer>());
			for(int j = 0; j < colCount; j++){
				this.grid.get(offset).add(0);
			}
		}
		
		this.rowCount += count;
	}
	
	/**
	 * addRow adds the specified arraylist into offset.
	 * @param row
	 * @param offset
	 */
	public void addRow(ArrayList<Integer> row, int offset){
		this.grid.add(offset, row);
		this.rowCount++;
	}
	
	/**
	 * addCol adds count columns of 0s into offset
	 * @param count
	 * @param offset
	 */
	public void addCol(int count, int offset){
		for(int i = 0; i < rowCount; i++){
			for(int j = 0; j < count; j++){
				this.grid.get(i).add(offset, 0);
			}
		}
		
		this.colCount += count;
	}
	
	/**
	 * addCol adds the specified arrayList of integers into the offset
	 * @param col
	 * @param offset
	 */
	public void addCol(ArrayList<Integer> col, int offset){
		for(int i = 0; i < rowCount; i++){
			this.grid.get(i).add(offset, col.get(i));
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
