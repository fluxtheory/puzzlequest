import java.util.ArrayList;
import java.util.Stack;

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
	Stack<Integer> mystack = new Stack<Integer>();
	
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
			mystack.push(40);
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
				mystack.push(41);
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
					mystack.push(41);
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
					mystack.push(41);
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
					mystack.push(41);
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
			mystack.push(30);
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
				mystack.push(31);
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
					mystack.push(31);
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
					mystack.push(31);
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
				mystack.push(31);
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
			mystack.push(10);
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
				mystack.push(11);
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
					mystack.push(11);
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
					mystack.push(11);
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
				mystack.push(11);
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
			mystack.push(20);
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
				mystack.push(21);
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
					mystack.push(21);
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
					mystack.push(21);
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
				mystack.push(21);
				}else{
					gg.SetCoordinateValue(manRaw, manCol, 5);
				}
		}else if(gg.getCoordinateValue(manRaw + 1, manCol) == 1){
			gg.SetCoordinateValue(manRaw, manCol, 5);
		}
	}
	public void backup(int t, GameGrid gg){
		if(t == 10){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
		}else if(t == 11){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 9);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 3);
			}
			if(ggtemp.getCoordinateValue(manRaw - 1 , manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw - 1 , manCol) == 9){
				gg.SetCoordinateValue(manRaw - 1, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw - 1, manCol, 2);
			}
		}
		gg.SetCoordinateValue(manRaw + 1, manCol, 8);
		manRaw++;
	}
	public void backdown(int t, GameGrid gg){
		if(t == 20){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
		}else if(t == 21){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 9);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 3);
			}
			if(ggtemp.getCoordinateValue(manRaw + 1 , manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw + 1 , manCol) == 9){
				gg.SetCoordinateValue(manRaw + 1, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw + 1, manCol, 2);
			}
		}
		gg.SetCoordinateValue(manRaw - 1, manCol, 5);
		manRaw--;
	}
	public void backleft(int t, GameGrid gg){
		if(t == 30){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
		}else if(t == 31){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 9);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 3);
			}
			if(ggtemp.getCoordinateValue(manRaw, manCol - 1) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol - 1) == 9){
				gg.SetCoordinateValue(manRaw, manCol - 1, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol - 1, 2);
			}
		}
		gg.SetCoordinateValue(manRaw, manCol + 1, 6);
		manCol++;
	}
	public void backright(int t, GameGrid gg){
		if(t == 40){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 2);
			}
		}else if(t == 41){
			if(ggtemp.getCoordinateValue(manRaw, manCol) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol) == 9){
				gg.SetCoordinateValue(manRaw, manCol, 9);
			}else{
				gg.SetCoordinateValue(manRaw, manCol, 3);
			}
			if(ggtemp.getCoordinateValue(manRaw, manCol + 1) == 4 ||
				ggtemp.getCoordinateValue(manRaw, manCol + 1) == 9){
				gg.SetCoordinateValue(manRaw, manCol + 1, 4);
			}else{
				gg.SetCoordinateValue(manRaw, manCol + 1, 2);
			}
		}
		gg.SetCoordinateValue(manRaw, manCol - 1, 7);
		manCol--;
	}
	
	
	public boolean isMystackEmpty(){
		return mystack.isEmpty();
	}
	public int back(){
		return mystack.pop();
	}

}
