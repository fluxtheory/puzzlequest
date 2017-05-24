import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.JPanel;

public class TwoPGrid extends JPanel{

	private Grid Player1;
	private Grid Player2;
	
	public TwoPGrid(){
		setBackground(Color.BLACK);
		setBounds(0,0,1210,600);
		
		Player1 = new Grid(false);   //remove the buttons!
		
		
		//Player2 = new Grid(true);
		//add(Player2);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Player1);
	}
	

	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				g.drawImage(Player1.myImage[Player1.gg.getGrid().get(j).get(i)], i*30, j*30, this);
				//g.drawImage(Player2.myImage[Player2.gg.getGrid().get(j).get(i)], i*30, j*30, this);
			}
		}
	}
	
}
