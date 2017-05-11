import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


	
public class Grid extends JPanel{

	
	// add additional presets for medium, large maps
	private static Color bkgrd = Color.black;
	private JPanel[][] panelArray = new JPanel[16][16]; 

	public Grid(int size){
	
		JPanel mainPanel = new JPanel(new GridLayout(16,16));   //manager divides up space in 3 rows and 3 columns.
	 
		
		for (int y = 0; y< 16; y++){
		
			for (int x = 0; x < 16; x++){
		
				panelArray[y][x] = new JPanel(); //creates the panels to populate layout.
				panelArray[y][x].setBackground(bkgrd);
				//panelArray[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
				mainPanel.add(panelArray[y][x]); 
				
			}
		}
	
		if(size == 1){
			
			 createSmallGrid(panelArray);
			 
		} else if (size == 2){
	
			createMediumGrid(panelArray);
			
		} else if (size == 4){

			createLargeGrid(panelArray);
		}
		
		
		setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);
	
	}
	
	public void createSmallGrid(JPanel[][] arr){   //should small resize? ideally yes. or we find some way of centering it.
		//only activates a small subset of the grids for use and populate with floor and walls sprite.
		//5x5 working space.  too small, that only leaves what, 9 grids for boxes, player And WALLS.
		//6x6 in reality.
		//activated grids turn green.
		
		for (int y = 5; y <= 10; y++){
			for (int x = 5; x <= 10; x++){
				arr[y][x].setBackground(new Color(13,86,75));;
				arr[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
		}
	}
	
	public void createMediumGrid(JPanel[][] arr){	
		//activates a greater portion
		//10x10
		for (int y = 3; y < 13; y++){
			for (int x = 3; x < 13; x++){
				arr[y][x].setBackground(new Color(13,86,75));;
				arr[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
		}
		
	}
	
	public void createLargeGrid(JPanel[][] arr){
		//16x16
		for (int y = 0; y < 16; y++){
			for (int x = 0; x < 16; x++){
				arr[y][x].setBackground(new Color(13,86,75));;
				arr[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
		}
	}
	
	
	
}
