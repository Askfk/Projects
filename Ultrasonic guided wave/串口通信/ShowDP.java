import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;







import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ShowDP extends JPanel {  

	
    public ShowDP() {  
    
    
    }  
      
    @Override  
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);  
    	Graphics2D g2d = (Graphics2D) g;  
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);   
        setSize(500,320);
        g.setColor(new Color(255,255,255));  
        g.fillRect(0, 0, 500, 320);  
        g.setColor(Color.BLACK);
        g.drawLine(0, 20, 500, 20);       
        g.drawLine(0, 300, 500, 300);
        g.setColor(Color.red);
        g.drawLine(0, 293, 500, 293);
        g.drawLine(0, 195, 500, 195);
        
        
        
        g.setColor(new Color(0,136,144));   
        for(int i=0;i<500;i++){
        	g.drawLine(i,300-(int)(ShowJP.paint[i]*280/800),i+1,300-(int)(ShowJP.paint[i+1]*280/800));
        }
        
        
        g.dispose();
    }  
}
