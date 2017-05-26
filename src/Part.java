import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;





public class Part
{
  private int id;
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public static Integer TOPLEFT = Integer.valueOf(0);
  public static Integer TOPRIGHT = Integer.valueOf(1);
  public static Integer BOTTOMLEFT = Integer.valueOf(2);
  public static Integer BOTTOMRIGHT = Integer.valueOf(3);
  
  private ArrayList<ArrayList<Integer>> p;
  
  private Integer boxCount;
  
  private Integer objCount;
  
  private boolean playerPresent;
  private Integer position;
  private Integer colCount;
  private Integer rowCount;
  private Integer partArea;
  Integer kindOfPart;
  
  public Part(File f, int _id, int _p)
  {
    rowCount = Integer.valueOf(10);
    colCount = Integer.valueOf(10);
    id = _id;
    position = Integer.valueOf(_p);
    
    boxCount = Integer.valueOf(0);
    objCount = Integer.valueOf(0);
    playerPresent = false;
    
    p = new ArrayList();
    

    for (int i = 0; i < rowCount.intValue(); i++) {
      p.add(new ArrayList());
      for (int j = 0; j < rowCount.intValue(); j++) {
        ((ArrayList)p.get(i)).add(Integer.valueOf(0));
      }
    }
    

    Scanner sc = null;
    try {
      sc = new Scanner(f);
      


      for (int i = 0; i < rowCount.intValue(); i++) {
        String line = sc.nextLine();
        setRow(i, StringToArrayList_Int(line));
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();

    }
    finally
    {
      if (sc != null) { sc.close();
      }
    }
    updateMetaInfo();
  }
  






  private void updateMetaInfo()
  {
    for (int i = 0; i < rowCount.intValue(); i++) {
      for (int j = 0; j < colCount.intValue(); j++) {
        int value = ((Integer)((ArrayList)p.get(i)).get(j)).intValue();
        if (value == 3) {
          boxCount = Integer.valueOf(boxCount.intValue() + 1);

        }
        else if ((value >= 5) && (value <= 8)) {
          playerPresent = true;

        }
        else if (value == 4) {
          objCount = Integer.valueOf(objCount.intValue() + 1);
        }
      }
    }
  }
  


  private Part(ArrayList<ArrayList<Integer>> p, Integer boxCount, Integer kindOfPart)
  {
    this.p = p;
    this.boxCount = boxCount;
    this.kindOfPart = kindOfPart;
  }
  
  public Integer getKind() {
    return kindOfPart;
  }
  
  private void setRow(int r, ArrayList<Integer> input) {
    ((ArrayList)p.get(r)).clear();
    for (int i = 0; i < rowCount.intValue(); i++) {
      ((ArrayList)p.get(r)).add((Integer)input.get(i));
    }
  }
  



  public ArrayList<Integer> getRow(int r)
  {
    return (ArrayList)p.get(r - 1);
  }
  




  private ArrayList<Integer> StringToArrayList_Int(String s)
  {
    ArrayList<Integer> l = new ArrayList();
    for (String c : s.split("")) {
      if (c != null) {
        int i = Integer.parseInt(c);
        l.add(Integer.valueOf(i));
      }
    }
    
    return l;
  }
  
  public void printPart() {
    for (ArrayList<Integer> row : p) {
      for (Integer col : row) {
        System.out.print(col + "  ");
      }
      System.out.print("\n");
    }
  }
  


  public void printPartMeta()
  {
    System.out.println("Boxes, Objectives, Player Present: (" + boxCount + " , " + objCount + ", " + playerPresent + ")");
    System.out.println("Position: " + position);
  }
  
  public ArrayList<ArrayList<Integer>> getPart()
  {
    return p;
  }
  
  public Part rightToLeft() {
    ArrayList<ArrayList<Integer>> newP = new ArrayList();
    for (ArrayList<Integer> row : p) {
      ArrayList<Integer> newR = new ArrayList();
      for (int i = 8; i >= 0; i--) {
        newR.add((Integer)row.get(i));
      }
      newP.add(newR);
    }
    return new Part(newP, boxCount, kindOfPart);
  }
  
  public Part upToDown() {
    ArrayList<ArrayList<Integer>> newP = new ArrayList();
    for (int i = 8; i >= 0; i--) {
      newP.add((ArrayList)p.get(i));
    }
    return new Part(newP, boxCount, kindOfPart);
  }
  
  public Part iToj() {
    ArrayList<ArrayList<Integer>> newP = new ArrayList();
    for (int j = 8; j >= 0; j--) {
      ArrayList<Integer> newR = new ArrayList();
      for (int i = 8; i >= 0; i--) {
        newR.add((Integer)((ArrayList)p.get(i)).get(j));
      }
      newP.add(newR);
    }
    return new Part(newP, boxCount, kindOfPart);
  }
  
  public Integer getBoxCount() {
    return boxCount;
  }
  


  public Integer getColCount()
  {
    return colCount;
  }
  
  public void setColCount(Integer colCount) {
    this.colCount = colCount;
  }
  
  public Integer getRowCount() {
    return rowCount;
  }
  
  public void setRowCount(Integer rowCount) {
    this.rowCount = rowCount;
  }
  
  public boolean isPlayerPresent() {
    return playerPresent;
  }
  
  public void setPlayerPresent(boolean playerPresent) {
    this.playerPresent = playerPresent;
  }
  
  public Integer getObjCount() {
    return objCount;
  }
  
  public void setObjCount(Integer objCount) {
    this.objCount = objCount;
  }
  
  public Integer getPosition() {
    return position;
  }
  
  public void setPosition(Integer position) {
    this.position = position;
  }
  
  public void setBoxCount(Integer boxCount) {
    this.boxCount = boxCount;
  }
}