import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
	
public class FrontEnd extends JFrame {
	
	private JPanel homePanel;
	private static int lastInt;
	private boolean currentGameState;
	private GameGrid currentGame;
	private JMenuItem save;
	public MediaPlayer m;
	
		public FrontEnd(){
			JFXPanel fxPanel = new JFXPanel();
			initUI();
			
		}
		
		public void initUI(){
			List<String> menu = new ArrayList<>();
			menu.add("Start Game");
			menu.add("Load Game");
			menu.add("Options");
			menu.add("Exit");
			
			menuBar();
			JPanel startScreen = new MenuScreen(menu,true,true);
			add(startScreen);
			setTitle("Warehouse Boss");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationByPlatform(true);
	        setResizable(false);
	        pack();
	        setLocationRelativeTo(null);
	        try {
				chooseMusic();
				m.play();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
		}

	public class MenuScreen extends JPanel{       //currently startscreen.
		
		private List<String> menuItems;
	    private String focusedItem;
		private Map<String, Rectangle> menuButtons;
		private BufferedImage img;
		private Image[] titleCache = new Image[14];
	    private Dimension imgSize;
	    private GameMenuPainter painter;
	    
		
		public MenuScreen(List<String> _menuItems, boolean title, boolean background){
			
			menuItems = _menuItems;
			
			if(background){
				
				try {
					img = ImageIO.read(new File("unnamed.png"));
					imgSize = new Dimension(img.getWidth(),img.getHeight());
			        setPreferredSize(imgSize);
		
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Cant find background image");
					e1.printStackTrace();
				}
				
			} else {
				setBackground(Color.BLACK);
				
			}
			
			if(title){
				for (int i = 0; i <= 13; i++){
					String loc = "pic/TITLE" + i + ".gif";
					titleCache[i] = Toolkit.getDefaultToolkit().getImage(loc);
				}
			}

	        painter = new GameMenuPainter();
	        
	        MouseAdapter m = new MouseAdapter() {
	        	
	        	@Override
	            public void mouseClicked(MouseEvent e) {
	        		String newItem = null;
	                for (String text : menuItems) {  
	                	Rectangle bounds = menuButtons.get(text);
	                    if (bounds.contains(e.getPoint())) {
	                        newItem = text;
	                        
	                        if (newItem == "Start Game"){
	                        	
	                    		gameModePicker();
	                    		
	                    	} else if(newItem == "Load Game"){
	                    		
	                    		loadGame();
	                    		
	                    	} else if(newItem == "Options"){
	                    		
	                    		settingsPage();
	                    		
	                    	} else if(newItem == "Exit"){
	                    		System.exit(0);
	                    		
	                    	} else if(newItem == "Solo Play"){
	                    		createGameSpace();
	                    		
	                    	} else if(newItem == "Timed Game"){
	                    		createGameSpace();
	                    		
	                    	} else if(newItem == "Co-op Game"){
	                    		createGameSpace();
	                    		
	                    	} else if(newItem == "Back"){
	                    		//*
	                    		
	                    	}
	                        
	                    }
	                	
	                }
	                
	            }
	        	
	        	 @Override
	             public void mouseMoved(MouseEvent e) {
	                 focusedItem = null;
	                 for (String text : menuItems) {
	                     Rectangle bounds = menuButtons.get(text);
	
	                     if (bounds.contains(e.getPoint())) {
	                         focusedItem = text;
	                         repaint();
	                         break;
	                     }
	                 }
	             }
	
	        };
	        
	        addMouseListener(m);
	        addMouseMotionListener(m);
	        
	        if(title && background){
	        	homePanel = (JPanel) getContentPane();
	        	homePanel.setPreferredSize(imgSize);
	        }
		}
	
		/*@Override
	    public void invalidate() {
	        menuButtons = null;
	        super.invalidate();
	    }*/
	
	    @Override
	    public Dimension getPreferredSize() {
	        return imgSize;
	    }
	    
	    @Override
	    protected void paintComponent(Graphics g) {

       		super.paintComponent(g);
 	        Graphics2D g2d = (Graphics2D) g.create();
 	        if (menuButtons == null) {
            	menuButtons = new HashMap<>(menuItems.size());
            	int width = 0;
            	int height = 0;

            	for (String text : menuItems) {
                	Dimension dim = painter.getPreferredSize(g2d, text);
                	width = Math.max(width, dim.width);
                	height = Math.max(height, dim.height);
            	}

            	int x = (getWidth() - (width + 10)) / 2;

            	int totalHeight = (height + 10) * menuItems.size();
            	totalHeight += 5 * (menuItems.size() - 1);

            	int y = (getHeight() - totalHeight) / 2;

            	for (String text : menuItems) {
            		menuButtons.put(text, new Rectangle(x, y, width + 10, height + 10));
            		y += height + 10 + 5;
            	}

 	        }
        
 	        g2d.drawImage(img,0,0,null);
 	        g2d.drawImage(titleCache[lastInt], 20, 45, this);
        
 	        for (String text : menuItems) {     //button drawers
 	        	Rectangle bounds = menuButtons.get(text);
 	        	boolean isFocused = text.equals(focusedItem);
 	        	painter.paint(g2d, text, bounds, isFocused);
 	        }
 	        g2d.dispose();
	    }
	
	}

		public void menuBar(){
			
			JMenuBar menubar = new JMenuBar();
			JMenu file = new JMenu("File");
			file.setMnemonic(KeyEvent.VK_F);
			
			JMenuItem ret = new JMenuItem("Return");
			ret.addActionListener((ActionEvent event)->{
				
				if(currentGameState){ 
					//check if in game, flash a warning dialogue      
					
				}
				
				currentGameState = false;
				setResizable(true);
				setPreferredSize(homePanel.getPreferredSize());
				
				setContentPane(homePanel);
				setResizable(false);
				pack();
				setLocationRelativeTo(null);
				
				

			});
			
			JMenuItem newGame = new JMenuItem("New Game");
			newGame.addActionListener((ActionEvent event)-> {
				if(currentGameState == true){
					//create new JPanel
					createGameSpace();
				} else {
					createGameSpace();
				}
				
			});
			
			JMenuItem saveGame = new JMenuItem("Save Game");
			saveGame.addActionListener((ActionEvent event)->{
				saveGame(currentGame);
			});
			
			JMenuItem loadSave = new JMenuItem("Load Game");
			loadSave.addActionListener((ActionEvent event)-> {
				loadGame();
			});
			
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener((ActionEvent event)-> {
				System.exit(0); 
			});
			
			file.add(ret);
			file.addSeparator();
			file.add(newGame);
			file.add(saveGame);
			file.add(loadSave);
			file.addSeparator();
			file.add(exit);
			
			JMenu Help = new JMenu("Help");
			JMenuItem about = new JMenuItem("About");
			about.addActionListener((ActionEvent event)-> {
				aboutPage();
			});
			Help.add(about);
			JMenu options = new JMenu("Options");
			JMenuItem settings = new JMenuItem("Settings");
			settings.addActionListener((ActionEvent event)-> {
				settingsPage();
			});
			options.add(settings);
			menubar.add(file);
			menubar.add(options);
			menubar.add(Help);
			setJMenuBar(menubar);
			
			if(!currentGameState){
				saveGame.setEnabled(false);  
				save = saveGame;
			}
			
		}
		
		public void gameModePicker(){
			
			List<String> menu = new ArrayList<>();
			menu.add("Solo Play");
			menu.add("Timed Game");
			menu.add("Co-op Game");
			menu.add("Back");
			MenuScreen mode = new MenuScreen(menu, false,false);
			add(mode);
			setContentPane(mode);
			validate();

		}
		
		public void createGameSpace(){

			Grid grid = new Grid();
			add(grid);
			grid.requestFocus();
			setContentPane(grid);
			validate();
			setSize(new Dimension(600,600));
			setResizable(false);
			currentGameState = true;
			playMusic();
			
			currentGame = grid.returnGame();
			
			save.setEnabled(true);
		}
		
		public void aboutPage(){
			JFrame about = new JFrame();
			about.setSize(200, 100);
			about.setLocationRelativeTo(rootPane);
			about.setVisible(true);
		}
		
		public void settingsPage(){   
			// what settings?
			//audio
			//map size
			//game resolution etc
			//disable grid
			//num crates
			//other features
			TitledBorder title;
			JFrame sett = new JFrame();
			JPanel difficulty = new JPanel();
			JPanel audio = new JPanel();
			
			title = BorderFactory.createTitledBorder("Audio");
			audio.setBorder(title);
			
			title = BorderFactory.createTitledBorder("Difficulty");
			difficulty.setBorder(title);
			
			
			sett.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			                //natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
			
			sett.add(audio);
			sett.add(difficulty);
			
			
			sett.setSize(800, 600);
			sett.setLocationRelativeTo(rootPane);
			sett.setVisible(true);
		}
		
		public void saveGame(GameGrid gg){
			LevelManager sv = new LevelManager();
			try {
				sv.saveGame(currentGame, this);  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void loadGame(){
			
			
			// Loads a level
			LevelManager ld = new LevelManager();
			currentGame = ld.loadGame(this);
			
			// Create a gamespace
			Grid grid = new Grid();
			add(grid);
			setContentPane(grid);
			grid.updateGrid(currentGame);
			grid.requestFocus();
			validate();
			
			// Sexify our game
			setSize(new Dimension(600,600));
			setResizable(false);
			currentGameState = true;
			playMusic();
			
			currentGame = grid.returnGame();
			
			save.setEnabled(true);
			 
			
		}
		
		/**
		 * Converts a string into an arraylist of integers.
		 * @param s
		 */
		private ArrayList<Integer> StringToArrayList_Int(String s){
			ArrayList<Integer> l = new ArrayList<Integer>();
			for(String c: s.split("")){
				int i = Integer.parseInt(c);
				l.add(i);
			}
			
			return l;
		}
		
		
		public void chooseMusic() throws InterruptedException{
			Random randomGenerator = new Random();
			
		    int randomInt = randomGenerator.nextInt(14);
		    
		    while(randomInt == lastInt){
		    	randomInt = randomGenerator.nextInt(14);
		    }
		    
		    lastInt = randomInt;
		    String loc;
		    
		    if(currentGameState == false){
		    	loc = "audio/title.mp3";
		    } else {
		    	loc = "audio/"+ randomInt + ".mp3";
		    }
		    
		    Media song = new Media(new File(loc).toURI().toString());
		    m = new MediaPlayer(song);

		}
		
		public void playMusic(){
			try {
				m.stop();
				chooseMusic();
				m.play();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
