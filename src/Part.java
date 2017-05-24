import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part {
	
	private ArrayList<ArrayList<Integer>> p;
	private Integer boxCount;
	
	
	/*
	 * 1:start part
	 * 2:other part
	 * 3:objective part
	 */
	private Integer kindOfPart;
	
	
	public Part(int count){
		p=new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < 9; i++){
			this.p.add(new ArrayList<Integer>());
			for(int j = 0; j < 9; j++){
				this.p.get(i).add(0);
			}
		}
		
		
		Scanner sc = null;
		File f = new File("levels/parts/" + count + ".txt");
		boxCount=0;
		
		try{
			sc = new Scanner(f);
			String line = sc.nextLine();
			for(String c: line.split("")){
				int i = Integer.parseInt(c);
				kindOfPart = i;
			}
			for(int i = 0; i < 9; i++){
				line = sc.nextLine();
				this.setRow(i, this.StringToArrayList_Int(line));
			}
		}
		catch(FileNotFoundException e){}
		finally
	    {
	    	  if (sc != null) sc.close();
	    }
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(p.get(i).get(j)==3){
					boxCount++;
				}
			}
		}
	}
	
	private Part(ArrayList<ArrayList<Integer>> p,Integer boxCount, Integer kindOfPart){
		this.p=p;
		this.boxCount=boxCount;
		this.kindOfPart=kindOfPart;
	}
	
	public Integer getKind(){
		return kindOfPart;
	}
	
	private void setRow(int r, ArrayList<Integer> input){
		this.p.get(r).clear();
		for(int i = 0; i < 9; i++){
			this.p.get(r).add(input.get(i));
		}
	}
	
	/**
	 * Converts a string into an arraylist of integers.
	 * @param s
	 */
	private ArrayList<Integer> StringToArrayList_Int(String s){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(String c: s.split("")){
			if(c!=null){
				int i = Integer.parseInt(c);
				l.add(i);
			}
		}
		
		return l;
	}
	
	public void printPart(){
		for(ArrayList<Integer> row: this.p){
			for(Integer col: row){
				System.out.print(col + "  ");
			}
			System.out.print("\n");
		}
	}
	
	public ArrayList<ArrayList<Integer>> getPart() {
		return p;
	}
	
	public Part rightToLeft(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> row:p){
			ArrayList<Integer> newR = new ArrayList<Integer>();
			for (int i=8;i>=0;i--){
				newR.add(row.get(i));
			}
			newP.add(newR);
		}
		return new Part(newP,boxCount,kindOfPart);
	}
	
	public Part upToDown(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (int i=8;i>=0;i--){
			newP.add(p.get(i));
		}
		return new Part(newP,boxCount,kindOfPart);
	}
	
	public Part iToj(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (int j=8;j>=0;j--){
			ArrayList<Integer> newR = new ArrayList<Integer>();
			for (int i=8;i>=0;i--){
				newR.add(p.get(i).get(j));
			}
			newP.add(newR);
		}
		return new Part(newP,boxCount,kindOfPart);
	}
	
	public Integer getBoxCount(){
		return boxCount;
	}

}
