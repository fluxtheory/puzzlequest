import java.util.ArrayList;

/**
 * Player Controller class, used to control the player in the grid
 * @author Asus
 *
 */
public class PlayerController {
	private int manRaw;
	private int manCol;
	GameGrid ggtemp;
	private int[] player = new int[2];
	
	public PlayerController(GameGrid gg, GameGrid ggtemp){
		this.ggtemp = ggtemp;
		this.player = gg.getPlayerCoordinate();
		this.manRaw = player[0];
		this.manCol = player[1];
	}
	
	public boolean iswin(GameGrid gg){
		for(int i=0;i<gg.getRowCount();i++){
			for(int j=0;j<gg.getColCount();j++){
				if(ggtemp.getCoordinateValue(i, j)==4 && gg.getCoordinateValue(i, j)!=9){
					return false;
				}
			}
		}
		return true;
	}
	
	public void moveRight(GameGrid gg){
		if(gg.getCoordinateValue(manRaw, manCol + 1) == 2 || 
			gg.getCoordinateValue(manRaw, manCol + 1) == 4){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
			gg.SetCoordinateValue(manRaw, manCol + 1, 7);
			manCol++;
		}else if(gg.getCoordinateValue(manRaw, manCol + 1) == 3){
			if(gg.getCoordinateValue(manRaw, manCol + 2) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw, manCol + 1, 7);
				gg.SetCoordinateValue(manRaw, manCol + 2, 9);
				manCol++;
			}else if(gg.getCoordinateValue(manRaw, manCol + 2) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
						ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
						gg.SetCoordinateValue(manRaw, manCol, 4);
					}else{
						gg.SetCoordinateValue(manRaw, manCol, 2);
					}
					gg.SetCoordinateValue(manRaw, manCol + 1, 7);
					gg.SetCoordinateValue(manRaw, manCol + 2, 3);
					manCol++;
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 7);
			}
		}else if(gg.getCoordinateValue(manRaw, manCol + 1) == 9){
			if(gg.getCoordinateValue(manRaw, manCol + 2) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
					gg.SetCoordinateValue(manRaw, manCol + 1, 7);
					gg.SetCoordinateValue(manRaw, manCol + 2, 9);
					manCol++;
			}else if(gg.getCoordinateValue(manRaw, manCol + 2) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
						ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
						gg.SetCoordinateValue(manRaw, manCol, 4);
					}else{
						gg.SetCoordinateValue(manRaw, manCol, 2);
					}
					gg.SetCoordinateValue(manRaw, manCol + 1, 7);
					gg.SetCoordinateValue(manRaw, manCol + 2, 3);
					manCol++;
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 7);
			}
		}else if(gg.getCoordinateValue(manRaw, manCol + 1) == 1){
			gg.SetCoordinateValue(manRaw, manCol, 7);
		}
	}
	
	public void moveLeft(GameGrid gg){
		if(gg.getCoordinateValue(manRaw, manCol - 1) == 2 || 
			gg.getCoordinateValue(manRaw, manCol - 1) == 4){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
			gg.SetCoordinateValue(manRaw, manCol - 1, 6);
			manCol--;
		}else if(gg.getCoordinateValue(manRaw, manCol - 1) == 3){
			if(gg.getCoordinateValue(manRaw, manCol - 2) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw, manCol - 1, 6);
				gg.SetCoordinateValue(manRaw, manCol - 2, 9);
				manCol--;
			}else if(gg.getCoordinateValue(manRaw, manCol - 2) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
					}else{
						gg.SetCoordinateValue(manRaw, manCol, 2);
					}
					gg.SetCoordinateValue(manRaw, manCol - 1, 6);
					gg.SetCoordinateValue(manRaw, manCol - 2, 3);
					manCol--;
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 6);
			}
		}else if(gg.getCoordinateValue(manRaw, manCol - 1) == 9){
			if(gg.getCoordinateValue(manRaw, manCol - 2) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
				}
					gg.SetCoordinateValue(manRaw, manCol - 1, 6);
					gg.SetCoordinateValue(manRaw, manCol - 2, 9);
					manCol--;
			}else if(gg.getCoordinateValue(manRaw, manCol - 2) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
						ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
						gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw, manCol - 1, 6);
				gg.SetCoordinateValue(manRaw, manCol - 2, 3);
				manCol--;
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 6);
				}
		}else if(gg.getCoordinateValue(manRaw, manCol - 1) == 1){
			gg.SetCoordinateValue(manRaw, manCol, 6);
		}
	}
	
	public void moveUp(GameGrid gg){
		if(gg.getCoordinateValue(manRaw - 1, manCol) == 2 || 
			gg.getCoordinateValue(manRaw - 1, manCol) == 4){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
			gg.SetCoordinateValue(manRaw - 1, manCol, 8);
			manRaw--;
		}else if(gg.getCoordinateValue(manRaw - 1, manCol) == 3){
			if(gg.getCoordinateValue(manRaw - 2, manCol) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw - 1, manCol, 8);
				gg.SetCoordinateValue(manRaw - 2, manCol, 9);
				manRaw--;
			}else if(gg.getCoordinateValue(manRaw - 2, manCol) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
					}else{
						gg.SetCoordinateValue(manRaw, manCol, 2);
					}
					gg.SetCoordinateValue(manRaw - 1, manCol, 8);
					gg.SetCoordinateValue(manRaw - 2, manCol, 3);
					manRaw--;
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 8);
			}
		}else if(gg.getCoordinateValue(manRaw - 1, manCol) == 9){
			if(gg.getCoordinateValue(manRaw - 2, manCol) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
				}
					gg.SetCoordinateValue(manRaw - 1, manCol, 8);
					gg.SetCoordinateValue(manRaw - 2, manCol, 9);
					manRaw--;
			}else if(gg.getCoordinateValue(manRaw - 2, manCol) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
						ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
						gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw - 1, manCol, 8);
				gg.SetCoordinateValue(manRaw - 2, manCol, 3);
				manRaw--;
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 8);
				}
		}else if(gg.getCoordinateValue(manRaw - 1, manCol) == 1){
			gg.SetCoordinateValue(manRaw, manCol, 8);
		}
	}
	
	public void moveDown(GameGrid gg){
		if(gg.getCoordinateValue(manRaw + 1, manCol) == 2 || 
			gg.getCoordinateValue(manRaw + 1, manCol) == 4){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
			gg.SetCoordinateValue(manRaw + 1, manCol, 5);
			manRaw++;
		}else if(gg.getCoordinateValue(manRaw + 1, manCol) == 3){
			if(gg.getCoordinateValue(manRaw + 2, manCol) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw + 1, manCol, 5);
				gg.SetCoordinateValue(manRaw + 2, manCol, 9);
				manRaw++;
			}else if(gg.getCoordinateValue(manRaw + 2, manCol) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
					}else{
						gg.SetCoordinateValue(manRaw, manCol, 2);
					}
					gg.SetCoordinateValue(manRaw + 1, manCol, 5);
					gg.SetCoordinateValue(manRaw + 2, manCol, 3);
					manRaw++;
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 5);
			}
		}else if(gg.getCoordinateValue(manRaw + 1, manCol) == 9){
			if(gg.getCoordinateValue(manRaw + 2, manCol) == 4){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
					ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
					gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
				}
					gg.SetCoordinateValue(manRaw + 1, manCol, 5);
					gg.SetCoordinateValue(manRaw + 2, manCol, 9);
					manRaw++;
			}else if(gg.getCoordinateValue(manRaw + 2, manCol) == 2){
				if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
						ggtemp.getCoordinateValue(manRaw, manCol) == 9 ){
						gg.SetCoordinateValue(manRaw, manCol, 4);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 2);
				}
				gg.SetCoordinateValue(manRaw + 1, manCol, 5);
				gg.SetCoordinateValue(manRaw + 2, manCol, 3);
				manRaw++;
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 5);
				}
		}else if(gg.getCoordinateValue(manRaw + 1, manCol) == 1){
			gg.SetCoordinateValue(manRaw, manCol, 5);
		}
	}
}
