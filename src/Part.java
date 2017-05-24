<<<<<<< HEAD

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part {
	
	private ArrayList<ArrayList<Integer>> p;
	private Integer rowCount;
	private Integer colCount;
	private Integer boxCount;
	
	
	/*
	 * 1:start part
	 * 2:other part
	 * 3:objective part
	 */
	private Integer kindOfPart;
	
	
	public Part(int count){
		Scanner sc = null;
		File f = new File("parts/" + count + ".txt");
		boxCount=0;
		
		try{
			sc = new Scanner(f);
			if(sc.hasNextLine()){
				String line = sc.nextLine();
				for(String c: line.split("")){
					int i = Integer.parseInt(c);
					kindOfPart = i;
				}
			}
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				this.addRow();
				this.setRow(this.getRowCount(), this.StringToArrayList_Int(line));
			}
		}
		catch(FileNotFoundException e){}
		finally
	    {
	    	  if (sc != null) sc.close();
	    }
		
		for(int i=0;i<rowCount;i++){
			for(int j=0;j<colCount;j++){
				if(p.get(i).get(j)==3){
					boxCount++;
				}
			}
		}
	}
	
	private Part(ArrayList<ArrayList<Integer>> p, Integer rowCount,Integer colCount,Integer boxCount, Integer kindOfPart){
		this.p=p;
		this.rowCount=rowCount;
		this.colCount=colCount;
		this.boxCount=boxCount;
		this.kindOfPart=kindOfPart;
	}
	
	public Integer getKind(){
		return kindOfPart;
	}
	
	private void setRow(int r, ArrayList<Integer> input){
		if(this.colCount == 0){
			colCount = input.size();
		}
		
		this.p.get(r - 1).clear();
		for(int i = 0; i < colCount; i++){
			this.p.get(r - 1).add(input.get(i));
		}
	}
	
	private void addRow(){
		this.p.add(new ArrayList<Integer>());
		this.rowCount++;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public int getColCount() {
		return colCount;
	}
	
	/**
	 * Converts a string into an arraylist of integers.
	 * @param s
	 */
	private ArrayList<Integer> StringToArrayList_Int(String s){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(String c: s.split("")){
			int i = Integer.parseInt(c);
			l.add(i);
		}
		
		return l;
	}
	
	public ArrayList<ArrayList<Integer>> getPart() {
		return p;
	}
	
	public Part rightToLeft(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> row:p){
			ArrayList<Integer> newR = new ArrayList<Integer>();
			for (int i=rowCount-1;i>=0;i--){
				newR.add(row.get(i));
			}
			newP.add(newR);
		}
		return new Part(newP,rowCount,colCount,boxCount,kindOfPart);
	}
	
	public Part upToDown(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (int i=colCount-1;i>=0;i--){
			newP.add(p.get(i));
		}
		return new Part(newP,rowCount,colCount,boxCount,kindOfPart);
	}
	
	public Part iToj(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (int j=rowCount-1;j>=0;j--){
			ArrayList<Integer> newR = new ArrayList<Integer>();
			for (int i=rowCount-1;i>=0;i--){
				newR.add(p.get(i).get(j));
			}
			newP.add(newR);
		}
		return new Part(newP,rowCount,colCount,boxCount,kindOfPart);
	}
	
	public Integer getBoxCount(){
		return boxCount;
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part {
	
	private ArrayList<ArrayList<Integer>> p;
	private Integer rowCount;
	private Integer colCount;
	
	
	/*
	 * 1:start part
	 * 2:other part
	 * 3:objective part
	 */
	private Integer kindOfPart;
	
	
	public Part(int count){
		Scanner sc = null;
		File f = new File("parts/" + count + ".map");
		try{
			sc = new Scanner(f);
			if(sc.hasNextLine()){
				String line = sc.nextLine();
				for(String c: line.split("")){
					int i = Integer.parseInt(c);
					kindOfPart = i;
				}
			}
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				this.addRow();
				this.setRow(this.getRowCount(), this.StringToArrayList_Int(line));
			}
		}
		catch(FileNotFoundException e){}
		finally
	    {
	    	  if (sc != null) sc.close();
	    }
	}
	
	private Part(ArrayList<ArrayList<Integer>> p, Integer rowCount,Integer colCount,Integer kindOfPart){
		this.p=p;
		this.rowCount=rowCount;
		this.colCount=colCount;
		this.kindOfPart=kindOfPart;
	}
	
	public Integer getKind(){
		return kindOfPart;
	}
	
	private void setRow(int r, ArrayList<Integer> input){
		if(this.colCount == 0){
			colCount = input.size();
		}
		
		this.p.get(r - 1).clear();
		for(int i = 0; i < colCount; i++){
			this.p.get(r - 1).add(input.get(i));
		}
	}
	
	private void addRow(){
		this.p.add(new ArrayList<Integer>());
		this.rowCount++;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public int getColCount() {
		return colCount;
	}
	
	/**
	 * Converts a string into an arraylist of integers.
	 * @param s
	 */
	private ArrayList<Integer> StringToArrayList_Int(String s){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(String c: s.split("")){
			int i = Integer.parseInt(c);
			l.add(i);
		}
		
		return l;
	}
	
	public ArrayList<ArrayList<Integer>> getPart() {
		return p;
	}
	
	public Part rightToLeft(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> row:p){
			ArrayList<Integer> newR = new ArrayList<Integer>();
			for (int i=rowCount-1;i>=0;i--){
				newR.add(row.get(i));
			}
			newP.add(newR);
		}
		return new Part(newP,rowCount,colCount,kindOfPart);
	}
	
	public Part upToDown(){
		ArrayList<ArrayList<Integer>> newP = new  ArrayList<ArrayList<Integer>>();
		for (int i=colCount-1;i>=0;i--){
			newP.add(p.get(i));
		}
		return new Part(newP,rowCount,colCount,kindOfPart);
>>>>>>> branch 'master' of https://github.com/fluxtheory/puzzlequest.git
	}

}
