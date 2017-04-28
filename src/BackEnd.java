
/**
 * Backend handles all backend stuff in WarehouseBoss
 * 
 *
 */
public class BackEnd {
	
	private GameGrid gg;
	private PlayerController pc;
	private LevelManager lm;
	
	// Methods
	public void loadTestLevel(){
		gg = lm.loadTestLevel();
	}
	
	public void loadLevel(int level){
		gg = lm.loadLevel(level);
	}
	
	public void printLevel(){
		gg.printGrid();
	}
	
	// Default Constructor
	public BackEnd(){
		pc = new PlayerController();
		lm = new LevelManager();
	}
	
	// Getters and Setters
	public GameGrid getGg() {
		return gg;
	}

	public void setGg(GameGrid gg) {
		this.gg = gg;
	}
}
