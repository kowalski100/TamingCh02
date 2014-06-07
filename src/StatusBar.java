import java.awt.*;

import javax.swing.*;
public class StatusBar extends JPanel {
	private JLabel status;
	public static JLabel  whatstatus; 
	
	public StatusBar (){
		 status = new JLabel ("Status:   ");
		 whatstatus = new JLabel ("");
	     setLayout(new BorderLayout());
		 setLayout(new BorderLayout());
	     setBorder(javax.swing.BorderFactory.createEtchedBorder());
	     setBackground(Color.white);
	     add(status, BorderLayout.WEST);
	     add(whatstatus, BorderLayout.CENTER);  
		
	}
	
	public static void updateStatus(String s){
		whatstatus.setText(s);
	}
	public Insets getInsets (){
		return new Insets(3,3,3,3);
	}
	
}
