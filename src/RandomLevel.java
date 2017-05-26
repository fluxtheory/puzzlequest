import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class RandomLevel
{
  GameGrid gg = new GameGrid();
  ArrayList<Part> parts;
  
  public RandomLevel() {
    gg = new GameGrid();
    parts = initializeParts();
  }
  
  public GameGrid getRandomLevel()
  {
    gg.clearEverything();
    


    Part playerPart = getRandomPlayerPart();
    

    Part fillerPart1 = getFillerPart(3, 0, 2);
    

    Part fillerPart2 = getFillerPart(0, 0, 3);
    

    Part fillerPart3 = getFillerPart(0, 3, 1);
    
    connectVerticalPart(playerPart, 0);
    connectVerticalPart(fillerPart3, 0);
    connectVerticalPart(fillerPart1, 1);
    connectVerticalPart(fillerPart2, 1);
    
    return gg;
  }
  


  private ArrayList<Part> initializeParts()
  {
    ArrayList<Part> parts = new ArrayList();
    File folder = new File("levels/parts/");
    int i = 0;
    int j = 0;
    for (File f1 : folder.listFiles()) {
      for (File f2 : f1.listFiles()) {
        parts.add(new Part(f2, i, j));
        i++;
      }
      j++;
    }
    
    return parts;
  }
  







  private void connectVerticalPart(Part p, int offset)
  {
    int init = 1 + offset * p.getRowCount().intValue();
    
    int j = 1;
    
    for (int i = init; i < init + p.getRowCount().intValue(); i++) {
      gg.getRow(i).addAll(p.getRow(j));
      j++;
    }
  }
  




  private Part getPartOfId(int id)
  {
    for (Part part : parts) {
      if (part.getId() == id) {
        System.out.println("Found part of id " + id);
        part.printPartMeta();
        part.printPart();
        return part;
      }
    }
    
    return null;
  }
  




  private Part getRandomPlayerPart()
  {
    ArrayList<Part> playerParts = new ArrayList();
    
    for (Part p : parts) {
      if (p.isPlayerPresent()) {
        playerParts.add(p);
      }
    }
    

    Random r = new Random();
    int i = r.nextInt(playerParts.size());
    
    return (Part)playerParts.get(i);
  }
  




  private Part getFillerPart(int boxCount, int objCount, int _p)
  {
    ArrayList<Part> fillerParts = new ArrayList();
    
    for (Part p : parts) {
      if (p.getPosition().intValue() == _p) {
        fillerParts.add(p);
      }
    }
    




    Random r = new Random();
    Part p = null;
    
    while ((p == null) && (!fillerParts.isEmpty())) {
      int i = r.nextInt(fillerParts.size());
      Part temp = (Part)fillerParts.get(i);
      
      if ((temp.getBoxCount().intValue() == boxCount) && (temp.getObjCount().intValue() == objCount)) {
        p = temp;
      }
      else
      {
        fillerParts.remove(temp);
      }
    }
    
    return p;
  }
}