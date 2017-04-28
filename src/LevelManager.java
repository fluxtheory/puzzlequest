
/**
 * Level loader class, used to load levels
 * Generates grid for BackEnd
 */
public class LevelManager {

	
	public GameGrid loadTestLevel(){
		GameGrid gg = new GameGrid(5, 5);
		return gg;
	}
	
	public GameGrid loadLevel(int level){
		GameGrid gg = new GameGrid(5, 5);
		return gg;
	}
}
