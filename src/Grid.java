import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


	
public class Grid extends JPanel implements KeyListener{

	
	LevelManager lm = new LevelManager();
	LevelManager lmtemp = new LevelManager();
	GameGrid gg = lm.createRandomLevel();
	Image[] myImage = new Image[14];
	PlayerController pl = new PlayerController(gg);
	int undoCounter = 10;
	boolean secondary;
	int playerID;
	
	
	public Grid(boolean _secondary){
		secondary = _secondary; //second panel for coop double games
		
		if(secondary){
			playerID = 2;
			setBounds(600,0,600,600);
		} else {
			playerID = 1;
			setBounds(0,0,600,600);
		}

		
		addKeyListener(this);
		for(int i =0; i < 14; i++){
			myImage[i] = Toolkit.getDefaultToolkit().getImage("pic/"+ i + ".gif");
		}
	
	}
	
	public void updateGrid(GameGrid gameGrid){
		gg = gameGrid;
		pl = new PlayerController(gg);
	}
	
	public int returnUndoCounter(){
		return undoCounter;
	}
	
	
	public void undo(){
		if(pl.getUndoStackNext().isEmpty()){
			JOptionPane.showMessageDialog(this, "You haven't moved!!!");
		}
		
		else{
			undoCounter--;
			switch(pl.backNext()){
		case 10: pl.backRight(10, pl.backOrig(), gg);
			break;
		case 11: pl.backRight(11, pl.backOrig(), gg);
			break;
		case 12: pl.backRight(12, pl.backOrig(), gg);
			break;
		case 13: pl.backRight(13, pl.backOrig(), gg);
			break;
		case 14: pl.backRight(14, pl.backOrig(), gg);
			break;
		case 15: pl.backRight(15, pl.backOrig(), gg);
			break;
			
		case 20: pl.backLeft(20, pl.backOrig(), gg);
			break;
		case 21: pl.backLeft(21, pl.backOrig(), gg);
			break;
		case 22: pl.backLeft(22, pl.backOrig(), gg);
			break;
		case 23: pl.backLeft(23, pl.backOrig(), gg);
			break;
		case 24: pl.backLeft(24, pl.backOrig(), gg);
			break;
		case 25: pl.backLeft(25, pl.backOrig(), gg);
			break;
			

		case 30: pl.backUp(30, pl.backOrig(), gg);
			break;
		case 31: pl.backUp(31, pl.backOrig(), gg);
			break;
		case 32: pl.backUp(32, pl.backOrig(), gg);
			break;
		case 33: pl.backUp(33, pl.backOrig(), gg);
			break;
		case 34: pl.backUp(34, pl.backOrig(), gg);
			break;
		case 35: pl.backUp(35, pl.backOrig(), gg);
			break;
			
		case 40: pl.backDown(40, pl.backOrig(), gg);
			break;
		case 41: pl.backDown(41, pl.backOrig(), gg);
			break;
		case 42: pl.backDown(42, pl.backOrig(), gg);
			break;
		case 43: pl.backDown(43, pl.backOrig(), gg);
			break;
		case 44: pl.backDown(44, pl.backOrig(), gg);
			break;
		case 45: pl.backDown(45, pl.backOrig(), gg);
			break;			
			}
		}
		repaint();
		this.requestFocus();	
	}
	
	
	
	public GameGrid returnGame(){
		return gg;
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
				String msg = "Player" + playerID + " Wins!";
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
