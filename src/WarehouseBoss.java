/**
 * Warehouse Boss: A COMP2911 project
 * 
 *
 */

public class WarehouseBoss {
	FrontEnd fe;
	BackEnd be;

	public WarehouseBoss(){
		fe = new FrontEnd();
		be = new BackEnd();
	}

	public static void main(String argv[]){
		WarehouseBoss wb = new WarehouseBoss();
		wb.getBe().loadLevel(2);
		wb.getBe().printLevel();
	}

	public FrontEnd getFe() {
		return fe;
	}

	public void setFe(FrontEnd fe) {
		this.fe = fe;
	}

	public BackEnd getBe() {
		return be;
	}

	public void setBe(BackEnd be) {
		this.be = be;
	}
	
	
}
