import gnu.io.CommPortIdentifier;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;




import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


@SuppressWarnings("serial")
public class ShowJP extends JPanel{
	static Enumeration<?> portList;	
	public static int num=0;
	public static int[] paint=new int[501];
	public static JTextField t_distance,t_count; 
	public void CreateJFrame(String title) {
	    	
		
        
		for(int i=0;i<=500;i++) paint[i]=0;
	    	
	    JFrame jp = new JFrame();
	    jp.setTitle(title);
	    jp.setSize(560, 500);
	    
	    jp.setLocationRelativeTo(null);
	    jp.setResizable(false);
	    Container container=jp.getContentPane();
	    container.setLayout(null);
	    container.setBackground(new Color(240,240,240));
	       
	        
	    ShowDP jp1 = new ShowDP(); 
	    JScrollPane js1 = new JScrollPane(jp1);
	    js1.setBounds(30,40,500,320);
	    jp.add(js1);
	    
	    
	    
	    
	    jp.setVisible(true);
	    validate();
	    jp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
		
