import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TwoPGrid extends JPanel implements KeyListener{

	private Grid Player1;
	private Grid Player2;
	
	public TwoPGrid(){
		setBackground(Color.BLACK);
		setBounds(0,0,1410,600);
		setLayout(new GridLayout(1,2));
		Player1 = new Grid(false);   //remove the buttons!
		Player2 = new Grid(true);
		add(Player1);
		add(Player2);
		
		addKeyListener(this);
	}
	

	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				g.drawImage(Player1.myImage[Player1.gg.getGrid().get(j).get(i)], i*30, j*30, this);
				g.drawImage(Player2.myImage[Player2.gg.getGrid().get(j).get(i)], i*30, j*30, this);
			}
		}
		
		/*for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				g.drawImage(Player2.myImage[Player2.gg.getGrid().get(j).get(i)], (i)*30, j*30, this);
			}
		}*/
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_UP){
			Player2.pl.moveUp(Player2.returnGame());
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			Player2.pl.moveDown(Player2.returnGame());
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			Player2.pl.moveLeft(Player2.returnGame());
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			Player2.pl.moveRight(Player2.returnGame());
			repaint();
		}
		if(Player2.pl.iswin(Player2.returnGame())){
			String msg = "Player 2 Wins!";
			int type = JOptionPane.YES_NO_OPTION;
			String title = "Pass";
			int choice = 0;
			choice = JOptionPane.showConfirmDialog(null, msg, title, type);
			if(choice == 1){
				System.exit(0);
			} else if(choice == 0){
				
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_W){
			Player1.pl.moveUp(Player1.returnGame());
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			Player1.pl.moveDown(Player1.returnGame());
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_A){
			Player1.pl.moveLeft(Player1.returnGame());
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			Player1.pl.moveRight(Player1.returnGame());
			repaint();
		}
		if(Player1.pl.iswin(Player1.returnGame())){
			String msg = "Player 1 Wins!";
			int type = JOptionPane.YES_NO_OPTION;
			String title = "Pass";
			int choice = 0;
			choice = JOptionPane.showConfirmDialog(null, msg, title, type);
			
			if(choice == 1){
				System.exit(0);
			}
			else if(choice == 0){
				
			}
		}
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
