import static org.junit.Assert.*;

import org.junit.Test;

public class WarehouseBossTest {

	@Test
	public void test() {
		
		WarehouseBoss wb = new WarehouseBoss();
		wb.getBe().loadEmptyLevel();
		wb.getBe().printLevel();
		wb.getFe().RenderGrid(wb.getBe().getGg());
		
		// fail("Not yet implemented");
	}

}
