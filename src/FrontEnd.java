

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
	
	
public class FrontEnd extends JFrame{
	
	private static final int sml = 5;
	private static final int med = 10;
	private static final int lrg = 4;
		
		public FrontEnd(){
			initUI();
		}
		
		public void initUI(){
			menuBar();
			setTitle("Puzzle Quest");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationByPlatform(true);
			setSize(800, 800);
	        setLocationRelativeTo(null);
	        startScreen();
		}
		
		public void startScreen(){
			JPanel startpanel = new JPanel();
			
			JButton newG = new JButton("NEW GAME");
			newG.addActionListener((ActionEvent event)-> {
				difficultyPicker();
				
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
			startpanel.setBorder(new EmptyBorder(new Insets(250,50,150,200)));
			startpanel.add(newG);
			startpanel.add(Box.createRigidArea(new Dimension(0,15)));
			startpanel.add(loadSave);
			startpanel.add(Box.createRigidArea(new Dimension(0,15)));
			startpanel.add(exit);
			
			setContentPane(startpanel);
			pack();
			validate();
			
			
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
				//check if game is running
				//if yes, throw warning page.
				//if not, go to diff
				
				//createGameSpace();
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
		
		public void difficultyPicker(){
			
			JPanel diff = new JPanel();
			diff.setBorder(new EmptyBorder(150,150,10,10));
			BoxLayout layout = new BoxLayout(diff,BoxLayout.Y_AXIS);
			
			JButton easy = new JButton("Easy");
			easy.addActionListener((ActionEvent event) -> {
				createGameSpace(sml);
			});
			
			JButton medium = new JButton("Medium");
			medium.addActionListener((ActionEvent event) -> {
				createGameSpace(med);
			});
			
			JButton hard = new JButton("Hard");
			hard.addActionListener((ActionEvent event) -> {
				createGameSpace(lrg);
			});
			
			JButton back = new JButton("Back");
			back.addActionListener((ActionEvent event) -> {
				startScreen();
			});
			
			diff.setLayout(layout);
			diff.add(easy);
			diff.add(Box.createRigidArea(new Dimension(0,15)));
			diff.add(medium);
			diff.add(Box.createRigidArea(new Dimension(0,15)));
			diff.add(hard);
			diff.add(Box.createRigidArea(new Dimension(0,40)));
			diff.add(back);
			
			setContentPane(diff);
			validate();
			//;
		}
		
		public void createGameSpace(int size){
			Grid grid = new Grid(size);
			
			setContentPane(grid);
			validate();
			setSize(new Dimension(600,600));
			setResizable(false);
		}
		
		public void aboutPage(){
			
		}
		
		public void settingsPage(){
			// what settings?
			//audio
			//map size
			//game resolution etc
			//disable grid
			//num crates
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
		
	
	
}
