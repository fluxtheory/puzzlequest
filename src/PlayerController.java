import java.util.ArrayList;

/**
 * Player Controller class, used to control the player in the grid
 * @author Asus
 *
 */
public class PlayerController {
	private int manRow;
	private int manCol;
	private int[] player = new int[2];
	
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
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow, manCol + 1) == gg.GROUND){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
			canMove = true;
		}

		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.BOX){

			if(gg.getCoordinateValue(manRow, manCol + 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow, manCol + 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
				canMove = true;
			}
		}
		
		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
			canMove = true;
		}
		
		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow, manCol + 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow, manCol + 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol + 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
				canMove = true;
			}
		}
		
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
			}
			manCol++;
		}
	}
	
	public void moveLeft(GameGrid gg){
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow, manCol - 1) == gg.GROUND){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
			canMove = true;
		}

		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.BOX){

			if(gg.getCoordinateValue(manRow, manCol - 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow, manCol - 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
				canMove = true;
			}
		}
		
		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
			canMove = true;
		}
		
		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow, manCol - 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow, manCol - 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol - 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
				canMove = true;
			}
		}
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
			}
			manCol--;
		}
	}
	
	public void moveUp(GameGrid gg){
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow - 1, manCol) == gg.GROUND){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
			canMove = true;
		}

		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.BOX){

			if(gg.getCoordinateValue(manRow - 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow - 1, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow - 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
				canMove = true;
			}
		}
		
		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_UP);
			canMove = true;
		}
		
		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow - 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_UP);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow - 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow - 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_RIGHT);
				canMove = true;
			}
		}
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
			}
			manRow--;
		}
	}
	
	public void moveDown(GameGrid gg){
		canMove = false;
		// Consider the next
		if(gg.getCoordinateValue(manRow + 1, manCol) == gg.GROUND){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
			canMove = true;
		}

		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.BOX){

			if(gg.getCoordinateValue(manRow + 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow + 1, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow + 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
				canMove = true;
			}
		}
		
		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
			canMove = true;
		}
		
		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.OBJECTIVE_BOX){
			if(gg.getCoordinateValue(manRow + 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
				canMove = true;
			}
			
			else if(gg.getCoordinateValue(manRow + 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow + 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
				canMove = true;
			}
		}
		// Then consider current position
		if(canMove){
			if(gg.getCoordinateValue(manRow, manCol) >= gg.PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			}
			
			else if(gg.getCoordinateValue(manRow, manCol) >= gg.OBJECTIVE_PLAYER_DOWN && gg.getCoordinateValue(manRow, manCol) <= gg.OBJECTIVE_PLAYER_UP){
				gg.SetCoordinateValue(manRow, manCol, gg.OBJECTIVE);
			}
			manRow++;
		}
	}
}
