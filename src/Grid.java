import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


	
public class Grid extends JPanel implements KeyListener{

	
	LevelManager lm = new LevelManager();
	LevelManager lmtemp = new LevelManager();
	GameGrid gg = lm.loadLevel(2);
	GameGrid ggtemp = lm.loadLevel(2);
	Image[] myImage = new Image[10];
	PlayerController pl = new PlayerController(gg, ggtemp);
	
	
	public Grid(int level){
		JButton redo = new JButton("Redo");
		add(redo);
		
		setBounds(0,0,600,600);
		addKeyListener(this);
		for(int i =0; i < 10; i++){
			myImage[i] = Toolkit.getDefaultToolkit().getImage("pic/"+ i + ".gif");
		}
		
	
	}
	
	
	
	
	public void redo(){
	// function stub	
		
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				g.drawImage(myImage[gg.getGrid().get(j).get(i)], i*30, j*30, this);
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			pl.moveUp(gg);
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			pl.moveDown(gg);
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			pl.moveLeft(gg);
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			pl.moveRight(gg);
			repaint();
		}
		if(pl.iswin(gg)){
			String msg = "Congratulations, you passed level " + "2" + "!!!";
			int type = JOptionPane.YES_NO_OPTION;
			String title = "Pass";
			int choice = 0;
			choice = JOptionPane.showConfirmDialog(null, msg, title, type);
			if(choice == 1) System.exit(0);
			else if(choice == 0){
				//
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
