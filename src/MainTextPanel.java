import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.*;

import java.awt.*;
public class MainTextPanel extends JPanel {
	static JTextPane textpanel;
	public MainTextPanel(){
		setLayout(new BorderLayout());
		textpanel = new JTextPane();
		textpanel.setBorder(new LineBorder(new Color(0,0,0),1,true));
		textpanel.setEditable(false);
		mouseHandler mHandler = new mouseHandler ();
		textpanel.addMouseListener(mHandler);
		textpanel.setForeground(Color.red);
		add(new JScrollPane(textpanel),BorderLayout.CENTER);
	}

	public Insets getInsets (){
		return new Insets (0,0,0,5);
	}
	
	private class mouseHandler extends MouseAdapter {
		
		public void mouseEntered (MouseEvent e){
			StatusBar.whatstatus.setText("This Panel Displays the Yelp Response Output");
		}
		
		public void mouseExited (MouseEvent e){
			StatusBar.whatstatus.setText("");
		}
	}
}
