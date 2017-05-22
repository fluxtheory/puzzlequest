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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
	private boolean currentGame;
	public MediaPlayer m;
	
		public FrontEnd(){
			JFXPanel fxPanel = new JFXPanel();
			initUI();
			
		}
		
		public void initUI(){

			JPanel startScreen = new MenuScreen();
			menuBar();
			add(startScreen);
			setTitle("Puzzle Quest");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationByPlatform(true);
	        setResizable(false);
	        pack();

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
	private Image[] titles = new Image[14];
    private Dimension imgSize;
    private GameMenuPainter painter;
    
	
	public MenuScreen(){
		
		//GameMenu menu = new GameMenu( ,true,true);
		
		try {
			img = ImageIO.read(new File("unnamed.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Cant find background image");
			e1.printStackTrace();
		}
		
		for (int i = 0; i <= 13; i++){
			String loc = "pic/TITLE" + i + ".gif";
			titles[i] = Toolkit.getDefaultToolkit().getImage(loc);
		}
		
		
        imgSize = new Dimension(img.getWidth(),img.getHeight());
        setPreferredSize(imgSize);
        painter = new GameMenuPainter();
        menuItems = new ArrayList<>(25);
        menuItems.add("Start Game");
        menuItems.add("Load Game");
        menuItems.add("Options");
        menuItems.add("Exit");
        
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
        
        homePanel = (JPanel) getContentPane();
	}
	
		@Override
	    public void invalidate() {
	        menuButtons = null;
	        super.invalidate();
	    }
	
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
        
        Random ng = new Random();
        
        g2d.drawImage(titles[lastInt], 20, 45, this);
        
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
				
				/*if(){ //check if in game, flash a warning dialogue      //back to title music.
					
				}*/
				
				currentGame = false;
				setResizable(true);
				setContentPane(homePanel);
				setResizable(false);
				pack();
				
				try {
					m.stop();
					chooseMusic();
					m.play();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			
			JMenuItem newGame = new JMenuItem("New Game");
			newGame.addActionListener((ActionEvent event)-> {
				if(currentGame == true){
					//create new JPanel
					createGameSpace();
				} else {
					createGameSpace();
				}
				
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
			
		}
		
		public void gameModePicker(){
			
			List<String> menuItems;
			String focusedItem;
			Map<String, Rectangle> menuButtons;
			GameMenuPainter painter;
			
			
			
			
			
			//;
		}
		
		public void createGameSpace(){

			Grid grid = new Grid(2);
			//panelContainer.add(grid);
			add(grid);
			grid.requestFocus();
			setContentPane(grid);
			validate();
			setSize(new Dimension(600,600));
			setResizable(false);
			currentGame = true;
			try {
				m.stop();
				chooseMusic();
				m.play();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void aboutPage(){
			JFrame about = new JFrame();
			about.setSize(200, 100);
			about.setLocationRelativeTo(rootPane);
			about.setVisible(true);
		}
		
		public void settingsPage(){   //suspend work on this. make the front page look good!
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
		
		public void saveGame(){
			JFileChooser c = new JFileChooser();
			c.showSaveDialog(this);
		}
		
		public void loadGame(){
			JFileChooser c = new JFileChooser();
			c.showOpenDialog(this);
			
		}
		
		
		
		public void chooseMusic() throws InterruptedException{
			Random randomGenerator = new Random();
			
		    int randomInt = randomGenerator.nextInt(14);
		    
		    while(randomInt == lastInt){
		    	randomInt = randomGenerator.nextInt(14);
		    }
		    
		    lastInt = randomInt;
		    String loc;
		    
		    if(currentGame == false){
		    	loc = "audio/title.mp3";
		    } else {
		    	loc = "audio/"+ randomInt + ".mp3";
		    }
		    
		    Media song = new Media(new File(loc).toURI().toString());
		    m = new MediaPlayer(song);
		    
		    

 
		}
		
		
	
}
