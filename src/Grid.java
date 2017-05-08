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

	private static int sml_side = 3; 
	// add additional presets for medium, large maps
	private static Color bkgrd = Color.black;
	private JLabel[][] fieldGrid = new JLabel[9][9];  

	public Grid(){
	
		JPanel mainPanel = new JPanel(new GridLayout(3,3));   //manager divides up space in 3 rows and 3 columns.
		//mainPanel.setBorder(BorderFactory.createEmptyBorder(gap,gap,gap,gap));
		JPanel[][] smlPanels = new JPanel[3][3];   //creates the panels to populate layout.
	
		for (int y = 0; y<smlPanels.length; y++){
		
			for (int x = 0; x <smlPanels.length; x++){
			
				smlPanels[y][x] = new JPanel(new GridLayout(3,3, 0,0)); //subdivides larger gridlayout into smaller 3x3 layouts.
				smlPanels[y][x].setBackground(bkgrd);
				mainPanel.add(smlPanels[y][x]); 
			}
		}
	
		for (int y = 0; y < fieldGrid.length; y++){
		
			for (int x = 0; x < fieldGrid.length; x++){
				fieldGrid[y][x] = createField();
				int k = y/3;
				int l = x/3;
				smlPanels[k][l].add(fieldGrid[y][x]);
			}
		}
	
		setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);
	
	}
	
	public JLabel createField(){
		
		JLabel label = new JLabel();
		//label.setHorizontalAlignment(1);
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		//label.setBackground(Color.WHITE);
		return label;
	}
	
}

