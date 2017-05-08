
/*	Note:
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
	
	
public class FrontEnd extends JFrame{
		
		public puzzlequest(){
			initUI();
		}
		
		public void initUI(){
			menuBar();
			setTitle("Puzzle Quest");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationByPlatform(true);
			setSize(800, 1200);
	        setLocationRelativeTo(null);
	        startScreen();
		}
		
		public void startScreen(){
			JPanel startpanel = new JPanel();
			
			JButton newG = new JButton("NEW GAME");
			newG.addActionListener((ActionEvent event)-> {
				createGameSpace();
			});
			
			JButton loadSave = new JButton("LOAD GAME");
			loadSave.addActionListener((ActionEvent event)-> {
				loadGame();
			});
			
			JButton exit = new JButton("EXIT");
			
			exit.addActionListener((ActionEvent event)-> {
				System.exit(0);
			});
			
			
			BoxLayout layout = new BoxLayout(startpanel, BoxLayout.Y_AXIS);
			
			
			startpanel.setLayout(layout);
			startpanel.setBorder(new EmptyBorder(new Insets(150,200,150,200)));
			startpanel.add(newG);
			startpanel.add(Box.createRigidArea(new Dimension(0,15)));
			startpanel.add(loadSave);
			startpanel.add(Box.createRigidArea(new Dimension(0,15)));
			startpanel.add(exit);
			
			add(startpanel);	
			pack();
			
			
			//minimum size
			// button location bottom left  //boxlayout cannot handle alignment.
		}
		
		public void menuBar(){
			
			JMenuBar menubar = new JMenuBar();
			JMenu file = new JMenu("File");
			file.setMnemonic(KeyEvent.VK_F);
			
			JMenuItem titleScreen = new JMenuItem("Return");
			titleScreen.addActionListener((ActionEvent event)-> {
				startScreen();
			});
			
			JMenuItem newGame = new JMenuItem("New Game");
			newGame.addActionListener((ActionEvent event)-> {
				createGameSpace();
			});
			
			JMenuItem saveGame = new JMenuItem("Save Game");
			saveGame.addActionListener((ActionEvent event)->{
				saveGame();
			});
			
			JMenuItem loadSave = new JMenuItem("Load Game");
			loadSave.addActionListener((ActionEvent event)-> {
				loadGame();
			});
			
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener((ActionEvent event)-> {
				System.exit(0); 
			});
			file.add(titleScreen);
			file.addSeparator();
			file.add(newGame);
			file.add(saveGame);
			file.add(loadSave);
			file.addSeparator();
			file.add(exit);
			JMenu Help = new JMenu("Help");
			JMenuItem about = new JMenuItem("About");
			//about.addActionListener(arg0);   open up a new panel.
			Help.add(about);
			JMenu options = new JMenu("Options");
			JMenuItem settings = new JMenuItem("Settings");
			//settings.addActionListener(l);
			options.add(settings);
			menubar.add(file);
			menubar.add(options);
			menubar.add(Help);
			setJMenuBar(menubar);
			
		}
		
		public void createGameSpace(){
			Grid grid = new Grid();
			
			setContentPane(grid);
			validate();
			setSize(new Dimension(800,1200));
		}
		
		public void aboutPage(){
			
		}
		
		public void settingsPage(){
			// what settings?
			//audio
			//map size
			//game resolution etc
			//other features
		}
		
		public void saveGame(){
			JFileChooser c = new JFileChooser();
			c.showSaveDialog(this);
		}
		
		public void loadGame(){
			JFileChooser c = new JFileChooser();
			c.showOpenDialog(this);
		}
		
		public static void main(String[] args){
			FrontEnd start = new puzzlequest();
			start.setVisible(true);
		}
		
	}
	
}
