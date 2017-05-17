import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


	
public class Grid extends JPanel{

	
	LevelManager lm = new LevelManager();
	GameGrid gg;
	Image[] myImage = new Image[10];
	private int size;
	
	public Grid(int size, int level){
		this.size = size;
		gg = lm.loadLevel(level);
		setBounds(15,50,600,600);
		setBackground(Color.WHITE);
		for(int i =0; i < 10; i++){
			myImage[i] = Toolkit.getDefaultToolkit().getImage("pic/"+ i + ".gif");
		}
	
	}
	
	public void paint(Graphics g){
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				g.drawImage(myImage[gg.getGrid().get(i).get(j)], j*30, i*30, null);
			}
		}
	}
	
	
	
}
