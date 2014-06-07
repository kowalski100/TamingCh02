import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.event.*;
import javax.swing.border.*;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.oauth.OAuthService;



public class SearchPanel extends JPanel {
	static JTextField srch ;
	static JTextField latitude;
	static JTextField longitude;
	JButton srchBtn;
	static JLabel refreshLabel;
	static JProgressBar srchpbar;
	public SearchPanel (){

		srch = new JTextField(30);
		MouseHandler mhandler =  new MouseHandler();
		srch.addMouseListener(mhandler);
		srch.setPreferredSize(new Dimension(3,30));
		srch.setFont(new Font(Font.SERIF,Font.BOLD,20));
		srchBtn = new JButton("Search");
		ButtonHandler handle = new ButtonHandler ();
		srchBtn.addActionListener(handle);
		JPanel p1 = new JPanel ();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(srch);

		latitude = new JTextField(10);
		longitude = new JTextField(10);
		latitude.addMouseListener(mhandler);
		longitude.addMouseListener(mhandler);
		longitude.setFont(new Font(Font.SERIF,Font.BOLD,13));
		latitude.setFont(new Font(Font.SERIF,Font.BOLD,13));
		srchpbar = new JProgressBar();
		srchpbar.setMinimum(0);
		srchpbar.setMaximum(4);
		srchpbar.setPreferredSize(new Dimension (70,15));
		p1.add(latitude);
		p1.add(longitude);
		p1.add(srchBtn);
		p1.add(srchpbar);
		p1.setOpaque(false);
		p1.setBorder(new LineBorder(new Color (255,255,255), 1, true));
		this.setLayout(new BorderLayout());
		add(p1,BorderLayout.CENTER);
		
		initConfig ();
	}
	
    public static void initConfig (){
		srch.setForeground(new Color (180,180,180));
		srch.setText("Enter Keyword");
		latitude.setForeground(new Color (180,180,180));
		latitude.setText("Latitude");
		longitude.setForeground(new Color (180,180,180));
		longitude.setText("Longitude"); 	
    }
	public Insets getInsets (){
		return new Insets(5,10,5,10);
	}
	public void paintComponent (Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setPaint(new GradientPaint (0,0, new Color(132,132,132),0,getHeight(),new Color(30,30,30)));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.dispose();
		
	}
	
	private class MouseHandler extends MouseAdapter {
		
		public void mouseClicked (MouseEvent e){
			if (e.getSource()==srch){
			if (srch.getText().equalsIgnoreCase("Enter Keyword")){
				srch.setForeground(new Color (0,0,0));
				srch.setText("");
				
			}else {
				srch.setForeground(new Color (0,0,0));				
			}
			}
			if (e.getSource()==latitude){
				if (latitude.getText().equalsIgnoreCase("Latitude")){
					latitude.setForeground(new Color (0,0,0));				
					latitude.setText("");
				}else {
					latitude.setForeground(new Color (0,0,0));				
				}	
			}
			if (e.getSource()==longitude){
				if (longitude.getText().equalsIgnoreCase("Longitude")){
					longitude.setForeground(new Color (0,0,0));
					longitude.setText("");
				}else {
					longitude.setForeground(new Color (0,0,0));				
				}
			}
		}
		public void mouseEntered (MouseEvent e){
		if (e.getSource()==srch){
			if (srch.getText().equalsIgnoreCase("")){
				srch.setForeground(new Color (180,180,180));
				srch.setText("Enter Keyword");
				
			}
			StatusBar.whatstatus.setText("Enter Search Keyword");
		}
		if (e.getSource()==latitude){
			if (latitude.getText().equalsIgnoreCase("")){
				latitude.setForeground(new Color (180,180,180));
				latitude.setText("Latitude");
			}
			StatusBar.whatstatus.setText("Enter Location Latitude");
		}
		if (e.getSource()==longitude){
			if (longitude.getText().equalsIgnoreCase("")){
				longitude.setForeground(new Color (180,180,180));
				longitude.setText("Longitude");
			}
			StatusBar.whatstatus.setText("Enter Location Longitude");
		}
		}
		public void mouseExited (MouseEvent e){
			StatusBar.whatstatus.setText("");
			if (e.getSource()==srch){
			if (srch.getText().equalsIgnoreCase("")){
				srch.setForeground(new Color (180,180,180));
				srch.setText("Enter Keyword");
			}
		}
		if (e.getSource()==latitude){
			if (latitude.getText().equalsIgnoreCase("")){
				latitude.setForeground(new Color (180,180,180));
				latitude.setText("Latitude");
			}
		}
		if (e.getSource()==longitude){
			if (longitude.getText().equalsIgnoreCase("")){
				longitude.setForeground(new Color (180,180,180));
				longitude.setText("Longitude");
			}
		}
		}
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e){
			if (e.getSource()== srchBtn){
				if  (   !srch.getText().equalsIgnoreCase("")
						&& !srch.getText().equalsIgnoreCase("Enter Keyword")
						&& !latitude.getText().equalsIgnoreCase("")
						&& !latitude.getText().equalsIgnoreCase("latitude")
						&& !longitude.getText().equalsIgnoreCase("")
						&& !longitude.getText().equalsIgnoreCase("longitude")){
					try {
						ControlHandles.latitude_input =  Double.parseDouble(latitude.getText());	
						ControlHandles.longitude_input = Double.parseDouble(longitude.getText());
						ControlHandles.srchString = srch.getText();
						
						srchpbar.setValue(0);
						
					    new ResponseProgressThread().start();
					    new SearchThread().start();
					    
					}catch (NumberFormatException exp){
						JOptionPane.showMessageDialog(null, "Bad GPS Location","Error",JOptionPane.ERROR_MESSAGE);
					}

				}else {
					JOptionPane.showMessageDialog(null, "All fields Required","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				//JOptionPane.showMessageDialog(null, "searched");
			}
		}
	}
}
