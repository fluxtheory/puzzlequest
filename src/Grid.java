import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


	
public class Grid extends JPanel{

	
	LevelManager lm = new LevelManager();
	GameGrid gg = lm.loadLevel(2);
	Image[] myImage = new Image[10];
	
	
	public Grid(int size){
		setBounds(15,50,600,600);
		setBackground(Color.WHITE);
		for(int i =0; i < 10; i++){
			myImage[i] = Toolkit.getDefaultToolkit().getImage("pic/"+ i + ".gif");
		}
	
	}
	
	public void paint(Graphics g){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				g.drawImage(myImage[gg.getGrid().get(i).get(j)], i*30, j*30, this);
			}
		}
	}
	
	
	
}
