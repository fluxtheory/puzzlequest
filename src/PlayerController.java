import java.util.ArrayList;
import java.util.Stack;

/**
 * Player Controller class, used to control the player in the grid
 * @author Asus
 *
 */
public class PlayerController {
	private int manRow;
	private int manCol;
	private int[] player = new int[2];
	int steps = 0;
	
	Stack<Integer> undoStackOrig = new Stack<Integer>();
	int stackCounter = 0;
	int stackCounterOri = 0;
	Stack<Integer> undoStackNext = new Stack<Integer>();
	
	private boolean canMove;
	
	public PlayerController(GameGrid gg){
		this.player = gg.getPlayerCoordinate();
		this.manRow = player[0];
		this.manCol = player[1];
	}
	
	public boolean iswin(GameGrid gg){
		for(int i = 1; i < gg.getRowCount() + 1; i++){
			for(int j = 1; j < gg.getColCount() + 1; j++){
				if(gg.getCoordinateValue(i, j)==4 && gg.getCoordinateValue(i, j)!=9){
					return false;
				}
				
				else if(gg.getCoordinateValue(i, j) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(i, j) <= gg.OBJECTIVE_PLAYER_UP){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * moveRight moves a player right by one column in the GameGrid
	 * @param gg The game grid to be modified
	 */
	public void moveRight(GameGrid gg){
		steps++;
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow, manCol + 1) == gg.GROUND){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(10);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(10);
			}
		}

		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.BOX){

			if(gg.getCoordinateValue(manRow, manCol + 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(11);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(11);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol + 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(12);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(12);
				}
			}
		}
		
		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(13);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(13);
			}
		}
		
		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow, manCol + 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(14);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(14);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol + 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(15);
					stackCounter++;
				}
				else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(15);
				}
			}
		}
		
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				if(stackCounterOri != 10){
					undoStackOrig.push(10);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(10);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
				if(stackCounterOri != 10){
					undoStackOrig.push(11);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(11);
				}
			}
			manCol++;
		}
		printStack();
	}
	
	public void moveLeft(GameGrid gg){
		steps++;
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow, manCol - 1) == gg.GROUND){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(20);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(20);
			}
		}

		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.BOX){

			if(gg.getCoordinateValue(manRow, manCol - 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(21);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(21);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol - 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(22);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(22);
				}
			}
		}
		
		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(23);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(23);
			}
		}
		
		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow, manCol - 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(24);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(24);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol - 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(25);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(25);
				}
			}
		}
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				if(stackCounterOri != 10){
					undoStackOrig.push(20);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(20);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
				if(stackCounterOri != 10){
					undoStackOrig.push(21);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(21);
				}
			}
			manCol--;
		}
	}
	
	public void moveUp(GameGrid gg){
		steps++;
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow - 1, manCol) == gg.GROUND){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(30);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(30);
			}
		}

		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.BOX){

			if(gg.getCoordinateValue(manRow - 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(31);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(31);
				}
			}
			
			else if(gg.getCoordinateValue(manRow - 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(32);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(32);
				}
			}
		}
		
		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_UP);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(33);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(33);
			}
		}
		
		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow - 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_UP);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(34);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(34);
				}
			}
			
			else if(gg.getCoordinateValue(manRow - 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_RIGHT);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(35);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(35);
				}
			}
		}
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				if(stackCounterOri != 10){
					undoStackOrig.push(30);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(30);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
				if(stackCounterOri != 10){
					undoStackOrig.push(31);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(31);
				}
			}
			manRow--;
		}
	}
	
	public void moveDown(GameGrid gg){
		steps++;
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow + 1, manCol) == gg.GROUND){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(40);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(40);
			}
		}

		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.BOX){
			if(gg.getCoordinateValue(manRow + 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(41);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(41);
				}
			}
			
			else if(gg.getCoordinateValue(manRow + 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(42);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(42);
				}
			}
		}
		
		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
			canMove = true;
			if(stackCounter != 10){
				undoStackNext.push(43);
				stackCounter++;
			}else{
				RemoveOldest(undoStackNext);
				undoStackNext.push(43);
			}
		}
		
		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow + 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(44);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(44);
				}
			}
			
			else if(gg.getCoordinateValue(manRow + 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
				canMove = true;
				if(stackCounter != 10){
					undoStackNext.push(45);
					stackCounter++;
				}else{
					RemoveOldest(undoStackNext);
					undoStackNext.push(45);
				}
			}
		}
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				if(stackCounterOri != 10){
					undoStackOrig.push(40);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(40);
				}
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
				if(stackCounterOri != 10){
					undoStackOrig.push(41);
					stackCounterOri++;
				}else{
					RemoveOldest2(undoStackOrig);
					undoStackOrig.push(41);
				}
			}
			manRow++;
		}
	}
	
	/**
	 * Backright undos you last move in which you move right.
	 * Ie: Go back left now.
	 * @param next
	 * @param orig
	 * @param gg
	 */
	public void backRight(int next, int orig, GameGrid gg){
		// Configure the previous "next" position
		if(next == 10){ // Right was ground
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
		}
		
		else if(next == 11){ // Right was box, box moved to ground.
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow, manCol + 1, gg.GROUND);
		}
		
		else if(next == 12){
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE);
		}
		
		else if(next == 13){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
		}
		
		else if(next == 14){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow, manCol + 1, gg.GROUND);
		}
		
		else if(next == 15){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE);
		}
		
		
		// Configure the previous "original" position
		if(orig == 10){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_RIGHT);
		}
		
		else if(orig == 11){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_RIGHT);
		}
		manCol--;
	}
	
	public void backLeft(int next, int orig, GameGrid gg){
		// Configure the previous "next" position
		if(next == 20){ // Right was ground
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
		}
		
		else if(next == 21){ // Right was box, box moved to ground.
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow, manCol - 1, gg.GROUND);
		}
		
		else if(next == 22){
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE);
		}
		
		else if(next == 23){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
		}
		
		else if(next == 24){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow, manCol - 1, gg.GROUND);
		}
		
		else if(next == 25){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE);
		}
		
		
		// Configure the previous "original" position
		if(orig == 20){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_LEFT);
		}
		
		else if(orig == 21){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_LEFT);
		}
		manCol++;
	}
	
	/**
	 * Move's your character back down. A type of undo step
	 * @param next
	 * @param orig
	 * @param gg
	 */
	public void backUp(int next, int orig, GameGrid gg){
		// Configure the previous "next" position
		if(next == 30){ // Right was ground
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
		}
		
		else if(next == 31){ // Right was box, box moved to ground.
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow - 1, manCol, gg.GROUND);
		}
		
		else if(next == 32){
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE);
		}
		
		else if(next == 33){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
		}
		
		else if(next == 34){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow - 1, manCol, gg.GROUND);
		}
		
		else if(next == 35){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE);
		}
		
		
		// Configure the previous "original" position
		if(orig == 30){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_UP);
		}
		
		else if(orig == 31){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_UP);
		}
		manRow++;
	}
	
	public void backDown(int next, int orig, GameGrid gg){
		// Configure the previous "next" position
		if(next == 40){ // Right was ground
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
		}
		
		else if(next == 41){ // Right was box, box moved to ground.
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow + 1, manCol, gg.GROUND);
		}
		
		else if(next == 42){
			gg.SetCoordinateValue(manRow, manCol, gg.BOX);
			gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE);
		}
		
		else if(next == 43){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
		}
		
		else if(next == 44){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow + 1, manCol, gg.GROUND);
		}
		
		else if(next == 45){
			gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE_BOX);
			gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE);
		}
		
		
		// Configure the previous "original" position
		if(orig == 40){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_DOWN);
		}
		
		else if(orig == 41){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
		}
		manRow--;
	}
	
	public int backNext(){
		stackCounter--;
		return undoStackNext.pop();
	}
	

	public int backOrig(){
		stackCounterOri--;
		return undoStackOrig.pop();
	}
	
	public Stack<Integer> getUndoStackOrig() {
		return undoStackOrig;
	}

	public void setUndoStackOrig(Stack<Integer> undoStackOrig) {
		this.undoStackOrig = undoStackOrig;
	}

	public Stack<Integer> getUndoStackNext() {
		return undoStackNext;
	}

	public void setUndoStackNext(Stack<Integer> undoStackNext) {
		this.undoStackNext = undoStackNext;
	}
	
	// Prints the content of the stacks, used for debugging
	public void printStack(){
		System.out.print("Next: ");
		for(int i: undoStackNext){
			System.out.print(i + ", ");
		}
		System.out.print("\nOrig: ");
		for(int i: undoStackOrig){
			System.out.print(i + ", ");
		}
		System.out.println("");
	}
	public void RemoveOldest(Stack<Integer> stack){
		Stack<Integer> temp = new Stack<Integer>(); 
		while(!stack.isEmpty()){
			temp.push(stack.pop());
		}
		temp.pop();
		while(!temp.isEmpty()){
			stack.push(temp.pop());
		}
	}
	public void RemoveOldest2(Stack<Integer> stack){
		Stack<Integer> temp = new Stack<Integer>(); 
		while(!stack.isEmpty()){
			temp.push(stack.pop());
		}
		temp.pop();
		while(!temp.isEmpty()){
			stack.push(temp.pop());
		}
	}
	
}
