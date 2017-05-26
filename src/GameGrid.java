import java.io.PrintStream;
import java.util.ArrayList;




public class GameGrid
{
  public final int EMPTY_SPACE = 0;
  public final int WALL = 1;
  public final int GROUND = 2;
  public final int BOX = 3;
  public final int OBJECTIVE = 4;
  public final int PLAYER_DOWN = 5;
  public final int PLAYER_LEFT = 6;
  public final int PLAYER_RIGHT = 7;
  public final int PLAYER_UP = 8;
  public final int OBJECTIVE_BOX = 9;
  public final int OBJECTIVE_PLAYER_DOWN = 10;
  public final int OBJECTIVE_PLAYER_LEFT = 11;
  public final int OBJECTIVE_PLAYER_RIGHT = 12;
  public final int OBJECTIVE_PLAYER_UP = 13;
  
  private ArrayList<ArrayList<Integer>> grid;
  
  private int rowCount;
  
  private int colCount;
  

  public GameGrid()
  {
    grid = new ArrayList();
    
    rowCount = 20;
    colCount = 20;
    
    for (int i = 0; i < 20; i++) {
      grid.add(new ArrayList());
      for (int j = 0; j < 20; j++) {
        ((ArrayList)grid.get(i)).add(Integer.valueOf(0));
      }
    }
  }
  


  public void printGrid()
  {
    for (ArrayList<Integer> row : grid) {
      for (Integer col : row) {
        if ((col.intValue() <= 8) && (col.intValue() >= 5)) {
          System.out.print("(" + col + ")");

        }
        else if (col.intValue() != 0)
        {



          System.out.print(col + "  ");
        }
      }
      System.out.print("\n");
    }
  }
  





  public Integer getCoordinateValue(int r, int c)
  {
    return (Integer)((ArrayList)grid.get(r - 1)).get(c - 1);
  }
  





  public void SetCoordinateValue(int r, int c, int value)
  {
    ((ArrayList)grid.get(r - 1)).set(c - 1, Integer.valueOf(value));
  }
  



  public int[] getPlayerCoordinate()
  {
    int[] coordinate = new int[2];
    
    for (int r = 0; r < rowCount; r++) {
      for (int c = 0; c < colCount; c++) {
        if ((((Integer)((ArrayList)grid.get(r)).get(c)).intValue() >= 5) && (((Integer)((ArrayList)grid.get(r)).get(c)).intValue() <= 8)) {
          coordinate[0] = (r + 1);
          coordinate[1] = (c + 1);
        }
      }
    }
    
    return coordinate;
  }
  





  public void setRow(int r, ArrayList<Integer> input)
  {
    if (colCount == 0) {
      colCount = input.size();
    }
    
    ((ArrayList)grid.get(r - 1)).clear();
    for (int i = 0; i < colCount; i++) {
      if (i < input.size()) {
        ((ArrayList)grid.get(r - 1)).add((Integer)input.get(i));
      }
      else
      {
        ((ArrayList)grid.get(r - 1)).add(Integer.valueOf(0));
      }
    }
  }
  






  public void clearEverything()
  {
    for (ArrayList<Integer> row : grid) {
      row.clear();
    }
  }
  




  public ArrayList<Integer> getRow(int r)
  {
    return (ArrayList)grid.get(r - 1);
  }
  





  public void setColumn(int c, ArrayList<Integer> input)
  {
    if (rowCount == 0) {
      rowCount = input.size();
    }
    
    for (int i = 0; i < rowCount; i++) {
      if (i < input.size()) {
        ((ArrayList)grid.get(i)).set(c - 1, (Integer)input.get(i));
      }
      else
      {
        ((ArrayList)grid.get(i)).set(c - 1, Integer.valueOf(0));
      }
    }
  }
  
  public ArrayList<ArrayList<Integer>> getGrid()
  {
    return grid;
  }
  
  public void setGrid(ArrayList<ArrayList<Integer>> grid) {
    this.grid = grid;
  }
  
  public int getRowCount() {
    return rowCount;
  }
  
  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }
  
  public int getColCount() {
    return colCount;
  }
  
  public void setColCount(int colCount) {
    this.colCount = colCount;
  }
}