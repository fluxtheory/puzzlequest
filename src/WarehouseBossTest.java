import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class WarehouseBossTest {

	@Test
	public void test() throws IOException {
		
		WarehouseBoss wb = new WarehouseBoss();
		LevelManager lm = new LevelManager();
		GameGrid gg = lm.loadGame(0);
		
		gg.printGrid();
		
		
		// fail("Not yet implemented");
	}

}
