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
			}
		}
		return true;
	}

	public void moveRight(GameGrid gg){
		if(gg.getCoordinateValue(manRow, manCol + 1) == gg.GROUND){
			gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			manCol++;
		}

		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.BOX){

			if(gg.getCoordinateValue(manRow, manCol + 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow, manCol + 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
			}
			
			else if(gg.getCoordinateValue(manRow, manCol + 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow, manCol + 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol + 1, gg.PLAYER_RIGHT);
			}
			manCol++;
		}
		
		else if(gg.getCoordinateValue(manRow, manCol + 1) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			gg.SetCoordinateValue(manRow, manCol + 1, gg.OBJECTIVE_PLAYER_RIGHT);
			manCol++;
		}
	}
	
	public void moveLeft(GameGrid gg){
		if(gg.getCoordinateValue(manRow, manCol - 1) == gg.GROUND){
			gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			manCol--;
		}

		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.BOX){

			if(gg.getCoordinateValue(manRow, manCol - 2) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow, manCol - 2, gg.BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
			}
			
			else if(gg.getCoordinateValue(manRow, manCol - 2) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow, manCol - 2, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow, manCol - 1, gg.PLAYER_LEFT);
			}
			manCol--;
		}
		
		else if(gg.getCoordinateValue(manRow, manCol - 1) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			gg.SetCoordinateValue(manRow, manCol - 1, gg.OBJECTIVE_PLAYER_LEFT);
			manCol--;
		}
	}
	
	public void moveUp(GameGrid gg){
		if(gg.getCoordinateValue(manRow - 1, manCol) == gg.GROUND){
			gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			manRow--;
		}

		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.BOX){

			if(gg.getCoordinateValue(manRow - 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow - 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
			}
			
			else if(gg.getCoordinateValue(manRow - 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow - 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow - 1, manCol, gg.PLAYER_UP);
			}
			manRow--;
		}
		
		else if(gg.getCoordinateValue(manRow - 1, manCol) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			gg.SetCoordinateValue(manRow - 1, manCol, gg.OBJECTIVE_PLAYER_UP);
			manRow--;
		}
	}
	
	public void moveDown(GameGrid gg){
		if(gg.getCoordinateValue(manRow + 1, manCol) == gg.GROUND){
			gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			manRow++;
		}

		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.BOX){

			if(gg.getCoordinateValue(manRow + 2, manCol) == gg.GROUND){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow + 2, manCol, gg.BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
			}
			
			else if(gg.getCoordinateValue(manRow + 2, manCol) == gg.OBJECTIVE){
				gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
				gg.SetCoordinateValue(manRow + 2, manCol, gg.OBJECTIVE_BOX);
				gg.SetCoordinateValue(manRow + 1, manCol, gg.PLAYER_DOWN);
			}
			manRow++;
		}
		
		else if(gg.getCoordinateValue(manRow + 1, manCol) == gg.OBJECTIVE){
			gg.SetCoordinateValue(manRow, manCol, gg.GROUND);
			gg.SetCoordinateValue(manRow + 1, manCol, gg.OBJECTIVE_PLAYER_DOWN);
			manRow++;
		}
	}
}
