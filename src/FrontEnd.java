import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
	
public class FrontEnd extends JFrame {
	
	private JFrame master;
	private JPanel homePanel;
	private static int lastInt;
	private boolean currentGameState;
	private GameGrid currentGame;
	private GridUI currentGrid;
	private JMenuItem save;
	private JMenuItem returnB;
	private JMenuItem restartB;
	private JMenuItem undoB;
   	private MenuScreen mode;
	public MediaPlayer m;
	
	
	private int lastSliderVal = 35;
	
		public FrontEnd(){
			JFXPanel fxPanel = new JFXPanel();
			initUI();
			master = this;
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
				m.setVolume( ((double) lastSliderVal )/100 );
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
	                    		createSingleGameSpace();
	                    		
	                    	} else if(newItem == "Timed Game"){
	                    		createStepLimitGameSpace();
	                    		
	                    	} else if(newItem == "Co-op Game"){
	                    		createDoubleGameSpace();
	                    		
	                    	} else if(newItem == "Back"){
	                    		setResizable(true);
			        			setPreferredSize(homePanel.getPreferredSize());
			        			setContentPane(homePanel);
			        			setResizable(false);
			        			setLocationRelativeTo(null);
			        			pack();
	                    		
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
	
		public void setNewMenuItems(List<String> _menuItems){
			menuItems = _menuItems;
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
			JMenu g = new JMenu("Game");
			g.setMnemonic(KeyEvent.VK_F);
			
			JMenuItem ret = new JMenuItem("Return");
			ret.addActionListener((ActionEvent event)->{
				
				if(currentGameState){ 
					
					String message = "Are you sure? You will lose all progress if you quit now!";
					gameConfirmation("return", message, this);
					
				} else { 
					setResizable(true);
					setPreferredSize(homePanel.getPreferredSize());
					setContentPane(homePanel);
					setResizable(false);
					setLocationRelativeTo(null);
					pack();
				}

			});
			
			JMenuItem Restart = new JMenuItem("Restart");
			Restart.addActionListener((ActionEvent event)-> {
				
			});
			
			JMenuItem Undo = new JMenuItem("Undo");
			Undo.addActionListener((ActionEvent event)-> {
				if(currentGrid.returnUndoCounter() > 0){
					currentGrid.undo();
				} else {
					JOptionPane.showMessageDialog(this, "No more moves left!");
				}
			});
			
			JMenuItem newGame = new JMenuItem("New Game");
			newGame.addActionListener((ActionEvent event)-> {
				
				if(currentGameState == true){
					
					String message = "Are you sure? You will lose all progress if you start a new game!";
					gameConfirmation("new", message, this);
					
				} else {
					gameModePicker();
				}
				
			});
			
			JMenuItem saveGame = new JMenuItem("Save Game");
			saveGame.addActionListener((ActionEvent event)->{
				saveGame(currentGame);
			});
			
			JMenuItem loadSave = new JMenuItem("Load Game");
			loadSave.addActionListener((ActionEvent event)-> {
				if(currentGameState == true){
					
					String message = "Loading an old save will cause all unsaved progress to be lost!";
					gameConfirmation("load", message, this);
					
				} else {
					loadGame();
				}
			});
			
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener((ActionEvent event)-> {
				System.exit(0); 
			});
			
			g.add(ret);
			g.add(Undo);
			g.add(Restart);
			g.addSeparator();
			g.add(newGame);
			g.add(saveGame);
			g.add(loadSave);
			g.addSeparator();
			g.add(exit);
			
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
			
			menubar.add(g);
			menubar.add(options);
			menubar.add(Help);
			setJMenuBar(menubar);
			
			save = saveGame;
			returnB = ret;
			undoB = Undo;
			restartB = Restart;
			
			disableMenuItems();
			
		}
		
		public void enableMenuItems(){
			if(currentGameState){
				save.setEnabled(true);  
				returnB.setEnabled(true);
				undoB.setEnabled(true);
				restartB.setEnabled(true);
			}
		}
		
		public void disableMenuItems(){
			if(!currentGameState){
				save.setEnabled(false);  
				returnB.setEnabled(false);
				undoB.setEnabled(false);
				restartB.setEnabled(false);
			}
		}
		
		public void gameModePicker(){
			if(!currentGameState){
				List<String> menu = new ArrayList<>();
				menu.add("Solo Play");
				menu.add("Timed Game");
				menu.add("Co-op Game");
				menu.add("Back");
				mode = new MenuScreen(menu, false,false);
				add(mode);
				setContentPane(mode);
				validate();
				returnB.setEnabled(true);
			}else{
				currentGameState=!currentGameState;
				List<String> menu = new ArrayList<>();
				menu.add("Solo Play");
				menu.add("Timed Game");
				menu.add("Co-op Game");
				menu.add("Back");
				mode.setNewMenuItems(menu);
				add(mode);
				setContentPane(mode);
				validate();
				returnB.setEnabled(true);
			}

		}
		
		public void createSingleGameSpace(){

			GridUI grid = new GridUI(false);
			add(grid);
			grid.requestFocus();
			setContentPane(grid);
			validate();
			setSize(new Dimension(600,645));
			setResizable(false);
			currentGameState = true;
			playMusic();
			currentGame = grid.returnGame();
			currentGrid = grid;
			
			enableMenuItems();
			
		}
		
		public void createStepLimitGameSpace(){
			JPanel timedGrid = new JPanel();
			
			timedGrid.setLayout(new BorderLayout());
			
			JPanel timerPanel = new JPanel();
			
			timerPanel.setBackground(Color.BLACK);
			timerPanel.setSize(new Dimension(0,35));
			
			
			StepGrid grid = new StepGrid();
			
			JButton undo = new JButton("Undo (" + grid.undoCounter + ")" );
			undo.addActionListener((ActionEvent event)->{
				if(grid.undoCounter > 0){
					grid.undo();
					((JButton) event.getSource()).setText("Undo (" + grid.undoCounter + ")");
				} else {
					undo.setEnabled(false);
					grid.requestFocus();
				}
			});
			
			JLabel stepCountRemaining = new JLabel("Steps Remaining:    " + grid.pl.steps);
			stepCountRemaining.setForeground(Color.cyan);  //not updating.
			grid.setTimerPanel(stepCountRemaining);
			
			timerPanel.add(undo, BorderLayout.CENTER);
			timerPanel.add(stepCountRemaining);
			timedGrid.add(timerPanel, BorderLayout.PAGE_START);
			timedGrid.add(grid);
			
			add(timedGrid);
			grid.requestFocus();
			setContentPane(timedGrid);
			validate();
			setSize(new Dimension(600,700));
			
			setResizable(false);
			currentGameState = true;
			playMusic();
			
			currentGame = grid.returnGame();
			
			enableMenuItems();
		}
		
		public void createDoubleGameSpace(){
			setResizable(true);
			
			TwoPGrid grid = new TwoPGrid();
			add(grid);
			grid.requestFocus();
			setContentPane(grid);
			validate();
			
			setSize(new Dimension(1210,600));
			setResizable(false);
			currentGameState = true;
			playMusic();
		}
		
		public void aboutPage(){
			JFrame about = new JFrame();
			about.setSize(200, 100);
			about.setLocationRelativeTo(rootPane);
			about.setVisible(true);
		}
		
		public void settingsPage(){   
			
			//game resolution etc
			JFrame setting = new JFrame("Settings");
	        TitledBorder audio = new TitledBorder("Audio");
	        TitledBorder game = new TitledBorder("Difficulty");
	        TitledBorder ginterface = new TitledBorder("Interface");
	        
	        Border paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);

	        //First pane: simple borders
	        JPanel BorderComp = new JPanel();
	        BorderComp.setBorder(paneEdge);
	        BorderComp.setLayout(new BoxLayout(BorderComp, BoxLayout.Y_AXIS));
	        
	       // addInterfaceComp(ginterface, BorderComp);
	        addAudioComp(audio, BorderComp);
	        //addGameSettComp(game, BorderComp);
	        
	        setting.setContentPane(BorderComp);
	        setting.pack();
	        setting.setLocationRelativeTo(null);
	        setting.setResizable(false);
	        setting.setVisible(true);
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
			
			if(currentGame != null){

				GridUI grid = new GridUI(false);
				add(grid);
				setContentPane(grid);
				grid.updateGrid(currentGame);
				grid.requestFocus();
				validate();
				
				setSize(new Dimension(600,600));
				setResizable(false);
				currentGameState = true;
				playMusic();
				
				currentGame = grid.returnGame();
				currentGrid = grid;
				enableMenuItems();
			} 
			
		}
		
		
		void addAudioComp(Border border, Container container) {
			JPanel comp = new JPanel(new GridLayout(1, 1), false);
			
			JCheckBox muteButton = new JCheckBox("Mute");
			
			muteButton.addActionListener((ActionEvent event)-> {
				if(muteButton.isSelected()){
					m.setMute(true);
				} else {
					m.setMute(false);
				}
			});
			JSlider volume = new JSlider(0, 100);
			volume.setValue(lastSliderVal);
			volume.setPaintTicks(true);
			volume.setMajorTickSpacing(10);
			volume.addChangeListener(new SliderListener());
			
			comp.add(muteButton);
			comp.add(volume);
			comp.setBorder(border);
			
			container.add(Box.createRigidArea(new Dimension(0, 10)));
			container.add(comp);
		}
		
		public class SliderListener implements ChangeListener {
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        int sliderVal = source.getValue();
		        lastSliderVal = sliderVal;
		        //System.out.println("Initial Volume is " + m.getVolume());
		        Double vol = (double) (sliderVal) /100;
		        //System.out.println("Converted Volume is " + vol);
		        m.setVolume(vol);  //takes in a double value.

		    }
		}
		
		void addInterfaceComp(Border border, Container container) {
			JPanel comp = new JPanel(new GridLayout(1, 1), false);
			JLabel res = new JLabel("Resolution");
			String[] resolutions = {"800x600", "1280×800", "1440×900", "1680×1050", "1920×1200"};
			JComboBox resSelect = new JComboBox(resolutions);
			
			resSelect.addActionListener((ActionEvent event)-> {
				
			});
			
			JCheckBox fullScreen = new JCheckBox("Full Screen");
			fullScreen.addActionListener((ActionEvent event)-> {
				if(currentGameState){
					setExtendedState(JFrame.MAXIMIZED_BOTH); 
					setUndecorated(true);
				}   //needs work.
				
			});
			
			comp.add(res);
			comp.add(resSelect);
			comp.add(Box.createRigidArea(new Dimension(10, 1)));
			comp.add(fullScreen);
			comp.setBorder(border);

			container.add(Box.createRigidArea(new Dimension(0, 10)));
			container.add(comp);
		}
		
		void addGameSettComp(Border border, Container container) {   //map size also?
			JPanel comp = new JPanel(new GridLayout(1, 2), false);
			comp.setBorder(border);
			
			JLabel nBox = new JLabel("Box");
			JLabel diff = new JLabel("Difficulty");
			String[] boxCount = {"1","2","3","4","5"};
			String[] mapSize = {"Small", "Medium", "Large"};
			JComboBox numBoxes = new JComboBox(boxCount);
			JComboBox mSize = new JComboBox(mapSize);
			
			comp.add(nBox);
			comp.add(numBoxes);
			comp.add(Box.createRigidArea(new Dimension(10, 10)));
			comp.add(diff);
			comp.add(mSize);
			container.add(Box.createRigidArea(new Dimension(0, 10)));
			container.add(comp);
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
				Double vol = (double) (lastSliderVal) /100;
				
				m.stop();
				chooseMusic();
				m.play();
				m.setVolume(vol);

				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public void gameConfirmation(String purpose, String message, JFrame frame){
			
			int reply = JOptionPane.showConfirmDialog(this,message);
			
			if(reply == JOptionPane.YES_OPTION){
				
			
				if(purpose.equals("return")){
					
					currentGameState = false;
					setResizable(true);
					setPreferredSize(homePanel.getPreferredSize());
					
					setContentPane(homePanel);
					setResizable(false);
					pack();
					setLocationRelativeTo(null);
					playMusic();
					
				} else if (purpose.equals("new")){
					setResizable(true);
					setPreferredSize(homePanel.getPreferredSize());
					setContentPane(homePanel);
					setResizable(false);
					setLocationRelativeTo(null);
					pack();
					playMusic();
					gameModePicker(); //errors.
					
				} else if (purpose.equals("load")){
					
					loadGame();
					
				}
			}

		}
}
