import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
public class ToolBarManager extends JToolBar {
	static JButton newBtn,exitBtn,imgBtn,nlpBtn;
	public ToolBarManager (){
		this.setPreferredSize(new Dimension(100,35));
		
		//new icon on Toolbar
		newBtn = new JButton();
		ImageIcon newbtnicon = new ImageIcon ("src\\images\\new_t.png");
		newBtn.setIcon(newbtnicon);
		newBtn.setBorder(null);
		newBtn.setBorderPainted(false);
		newBtn.setFocusPainted(false);
		newBtn.setToolTipText("New");
		newBtn.setOpaque(false);
		buttonHandler Handle = new buttonHandler ();
		newBtn.addActionListener(Handle);
		add(newBtn);

		//NLP icon on Toolbar
		nlpBtn = new JButton();
		ImageIcon nlpbtnicon = new ImageIcon ("src\\images\\nlpicon.png");
		nlpBtn.setIcon(nlpbtnicon);
		nlpBtn.setBorder(null);
		nlpBtn.setBorderPainted(false);
		nlpBtn.setFocusPainted(false);
		nlpBtn.setToolTipText("Response Output");
		nlpBtn.setOpaque(false);
		nlpBtn.setEnabled(false);
		nlpBtn.addActionListener(Handle);
		add (nlpBtn);

		
		//image icon on Toolbar
		imgBtn = new JButton();
		ImageIcon imgbtnicon = new ImageIcon ("src\\images\\imageicon.png");
		imgBtn.setIcon(imgbtnicon);
		imgBtn.setBorder(null);
		imgBtn.setBorderPainted(false);
		imgBtn.setFocusPainted(false);
		imgBtn.setToolTipText("Browse Response Images");
		imgBtn.setOpaque(false);
		imgBtn.setEnabled(false);
		imgBtn.addActionListener(Handle);
		add (imgBtn);
								
		
		//exit icon on Toolbar
		exitBtn = new JButton();
		ImageIcon exitbtnicon = new ImageIcon ("src\\images\\exit_t.png");
		exitBtn.setIcon(exitbtnicon);
		exitBtn.setBorder(null);
		exitBtn.setBorderPainted(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setToolTipText("Exit");
		exitBtn.setOpaque(false);
		exitBtn.addActionListener(Handle);
		add (exitBtn);
		
		
	}
	
	protected void paintComponent(Graphics g){
	    // Create the 2D copy
	    Graphics2D g2 = (Graphics2D)g.create();

	    // Apply vertical gradient
	    g2.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), new Color(170	,198	,237)));
	    g2.fillRect(0, 0, getWidth(), getHeight());
	    
	    // Dispose of copy
	    g2.dispose();
	}
	
	private class buttonHandler implements ActionListener {
		
		public void actionPerformed (ActionEvent e){
			if (e.getSource() == newBtn){
				SearchPanel.initConfig();
				MainTextPanel.textpanel.setText("");
				ResponseResults.latitude.setText("");
				ResponseResults.latitude_delta.setText("");
				ResponseResults.longitude.setText("");
				ResponseResults.longitude_delta.setText("");
				ResponseResults.totalResults.setText("");
				ResultPanel.processBtn.setEnabled(false);
				ToolBarManager.nlpBtn.setEnabled(false);
				ToolBarManager.imgBtn.setEnabled(false);
				ResultPanel.pBar.setValue(0);
				SearchPanel.srchpbar.setValue(0);
			}
			if (e.getSource()==exitBtn){
				System.exit(0);
			}
			if (e.getSource()==imgBtn){
				JOptionPane.showMessageDialog(null, "This Feature is currently Disabled!");
			}
			if (e.getSource()==nlpBtn){
				
				ProcessBuilder pb1 = new ProcessBuilder("Notepad.exe", "src\\outputs\\sentences.txt");
				ProcessBuilder pb2 = new ProcessBuilder("Notepad.exe", "src\\outputs\\Tokens.txt");
				ProcessBuilder pb3 = new ProcessBuilder("Notepad.exe", "src\\outputs\\ExpectedNames.txt");
				try {
				pb1.start();
				pb2.start();
				pb3.start();
				}catch (IOException pbe){
					JOptionPane.showMessageDialog(null, pbe.toString(),"IO Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
