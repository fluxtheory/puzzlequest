import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class RandomLevel {
	
	private ArrayList<Part> starts;
	private ArrayList<Part> passes;
	private ArrayList<Part> objectives;
	private final static Integer numberOfParts=4;
	private Part p0;
	private Part p11;
	private Part p12;
	private Part p21;
	private Part p22;
	
	public RandomLevel(){
		starts=new ArrayList<Part>();
		passes=new ArrayList<Part>();
		objectives=new ArrayList<Part>();
		for(int i = 0;i<numberOfParts;i++){
			Part p=new Part(i);
			switch(p.getKind()){
				case 0:p0=p;break;
				case 1:starts.add(p);break;
				case 2:passes.add(p);break;
				case 3:objectives.add(p);break;
			}
		}
	}
	
	public void getRandomLevel(){
		//todo random methods
		Random r = new Random();
		p11 = starts.get(r.nextInt(starts.size()));
		p12 = passes.get(r.nextInt(passes.size())).rightToLeft();
		p21 = p0;
		p22 = objectives.get(r.nextInt(objectives.size())).rightToLeft().upToDown();
		
		try {
        	File file = new File("levels/random.wml");
			PrintWriter writer = new PrintWriter(file);
			
			for(int i = 0; i < 21; i++){
				if(i==0 || i==10 || i==20){
					for(int j = 0; j < 21; j++){
						if( i==10 &&((j==5 && p11.getPart().get(4).get(8)>1 && p21.getPart().get(4).get(0)>1) || j==15 && p12.getPart().get(4).get(8)>1 && p22.getPart().get(4).get(0)>1)){
							writer.write(Integer.toString(2));
						}else if(i==0 && ((j>0 && j<10 && p11.getPart().get(0).get(j-1)>1) || (j>10 && j<20 && p12.getPart().get(j-11).get(0)>1))){
							writer.write(Integer.toString(1));
						}else if(i==20 && ((j>0 && j<10 && p21.getPart().get(8).get(j-1)>1) || (j>10 && j<20 && p22.getPart().get(j-11).get(8)>1))){
							writer.write(Integer.toString(1));
						}else if(i== 10){
							writer.write(Integer.toString(1));
						}else{
							writer.write(Integer.toString(0));
						}
					}
				}else if(i<10){
					if(p11.getPart().get(0).get(0)==0){
						writer.write(Integer.toString(0));
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p11.getPart().get(i-1).get(j-1)));
						}
						writer.write(Integer.toString(1));
					}else{
						writer.write(Integer.toString(1));
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p11.getPart().get(i-1).get(j-1)));
						}
						if(i==5){
							writer.write(Integer.toString(2));
						}else{
							writer.write(Integer.toString(1));
						}
					}
					
					if(p12.getPart().get(0).get(0)==0){
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p12.getPart().get(i-1).get(j-1)));
						}
						writer.write(Integer.toString(0));
					}else{
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p12.getPart().get(i-1).get(j-1)));
						}
						writer.write(Integer.toString(1));
					}
				}else{
					if(p21.getPart().get(0).get(0)==0){
						writer.write(Integer.toString(0));
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p21.getPart().get(i-11).get(j-1)));
						}
						writer.write(Integer.toString(1));
					}else{
						writer.write(Integer.toString(1));
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p21.getPart().get(i-11).get(j-1)));
						}
						if(i==5){
							writer.write(Integer.toString(2));
						}else{
							writer.write(Integer.toString(1));
						}
					}
					
					if(p22.getPart().get(0).get(0)==0){
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p22.getPart().get(i-11).get(j-1)));
						}
						writer.write(Integer.toString(0));
					}else{
						for(int j = 1; j < 10; j++){
							writer.write(Integer.toString(p22.getPart().get(i-11).get(j-1)));
						}
						writer.write(Integer.toString(1));
					}
				}
				writer.write("\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Save");
			e.printStackTrace();
		}
		
		
		
	}
	
}
