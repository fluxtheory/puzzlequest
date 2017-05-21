import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;

public class GameMenuPainter {
	
	public Dimension getPreferredSize(Graphics2D g2d, String text) {
        return g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().getSize();
    }
	
	public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isFocused){
		
		FontMetrics f = g2d.getFontMetrics();
		
		if(isFocused){
			g2d.setColor(Color.WHITE); 
			paintBackground(g2d, bounds, Color.BLACK, Color.ORANGE);    //borderless?
		} else {
			g2d.setColor(Color.BLUE);
			paintBackground(g2d, bounds, Color.RED.darker(), Color.WHITE);
		}
		
		int x = bounds.x + ((bounds.width - f.stringWidth(text)) / 2);
		int y = bounds.y + ((bounds.height - f.getHeight()) / 2) + f.getAscent();
		
	
		g2d.drawString(text, x, y);
	}
	
	protected void paintBackground(Graphics2D g2d, Rectangle bounds, Color background, Color foreground){
		
		g2d.setColor(background);
		g2d.fill(bounds);
		g2d.setColor(foreground);
		g2d.draw(bounds);
		
	}
	
}
