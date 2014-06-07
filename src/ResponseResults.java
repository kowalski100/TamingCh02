import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
public class ResponseResults extends JPanel{

	JLabel span;
	static JLabel latitude_delta;
	static JLabel longitude_delta;
	JLabel center;
	static JLabel latitude;
	static JLabel longitude;
	static JLabel totalResults;
	
	public ResponseResults (){
		setLayout(new BorderLayout());
		Box wrap = Box.createVerticalBox();
		JPanel p1 = new JPanel ();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel result = new JLabel("Results:");
		result.setFont(new Font (Font.SERIF,Font.BOLD,20));
		result.setForeground(new Color(16,78,139));
		p1.add(result);
		wrap.add(p1);
		
		JPanel p2 = new JPanel ();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel spn = new JLabel("Span: ");
		spn.setFont(new Font (Font.SERIF,Font.BOLD,15));
		spn.setForeground(new Color(176,23,31));
		p2.add(spn);
		span = new JLabel("");
		p2.add(span);
		wrap.add(p2);
		
		JPanel p3 = new JPanel ();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.add(new JLabel ("D-Lat: "));
		latitude_delta = new JLabel("");
		p3.add(latitude_delta);
		wrap.add(p3);
		
		JPanel p4 = new JPanel ();
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.add(new JLabel ("D-Long: "));
		longitude_delta = new JLabel("");
		p4.add(longitude_delta);
		wrap.add(p4);
		
		
		JPanel p5 = new JPanel ();
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel cntr = new JLabel("Center: ");
		cntr.setFont(new Font (Font.SERIF,Font.BOLD,15));
		cntr.setForeground(new Color(176,23,31));
		p5.add(cntr);
		wrap.add(p5);
		
		JPanel p6 = new JPanel ();
		p6.setLayout(new FlowLayout(FlowLayout.LEFT));
		p6.add(new JLabel ("Lat: "));
		latitude = new JLabel("");
		p6.add(latitude);
		wrap.add(p6);

		JPanel p7 = new JPanel ();
		p7.setLayout(new FlowLayout(FlowLayout.LEFT));
		p7.add(new JLabel ("Long: "));
		longitude = new JLabel("");
		p7.add(longitude);
		wrap.add(p7);
		
		JPanel p8 = new JPanel ();
		p8.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel rzlt = new JLabel("Total Results: ");
		rzlt.setFont(new Font (Font.SERIF,Font.BOLD,15));
		rzlt.setForeground(new Color(176,23,31));		
		p8.add(rzlt);
		totalResults = new JLabel("");
		p8.add(totalResults);
		wrap.add(p8);
		
		mouseHandler mHandler = new mouseHandler();
		this.addMouseListener(mHandler);
		add(wrap,BorderLayout.CENTER);
		this.setBorder(new LineBorder(Color.black,1,true));
	}
	
	private class mouseHandler extends MouseAdapter {
		public void mouseEntered (MouseEvent e){
			
			StatusBar.whatstatus.setText("This Panel Displays the Yelp Response Header Information");
		}
		public void mouseExited (MouseEvent e){
			StatusBar.whatstatus.setText("");
		}
	}
}
