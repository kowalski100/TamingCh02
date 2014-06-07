import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import javax.swing.*;
public class ResultPanel extends JPanel {
	static JButton processBtn;
	static JProgressBar pBar;
	public ResultPanel (){
		Box box = Box.createVerticalBox();
		JPanel p = new JPanel ();
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		processBtn =  new JButton ("Process");
		processBtn.setFont(new Font(Font.SERIF,Font.BOLD,16));
		processBtn.setToolTipText("Press to Process Data");
		processBtn.setForeground(new Color(255,30,50));
		processBtn.setBackground(new Color (0,0,0));
		processBtn.setEnabled(false);
		mouseHandler mHandler = new mouseHandler();
		buttonHandler bHandler = new buttonHandler();
		processBtn.addActionListener(bHandler);
		processBtn.addMouseListener(mHandler);
		p.add(processBtn);
		p.setBackground(new Color (255,255,255));
		box.add(p);
		pBar = new JProgressBar();
		pBar.setMaximum(3);
		pBar.setMinimum(0);
		pBar.setPreferredSize(new Dimension(240,25));
		box.add(pBar);
		box.add(Box.createVerticalStrut(10));
		ResponseResults result = new ResponseResults();
		box.add(result);
		box.setBackground(new Color (255,255,255));
		this.add(box);
		this.setBorder(new LineBorder(new Color (0,0,0),1,true));
		this.setPreferredSize(new Dimension(250,40));
		this.setBackground(new Color (255,255,255));
		
		
	}
	public Insets getInsets (){
		return new Insets (5,0,5,0);
	}
	
	
	private class mouseHandler extends MouseAdapter {
		public void mouseEntered (MouseEvent e){
			if (e.getSource()== processBtn){
				StatusBar.whatstatus.setText("Press this button to Extract the NLP information from the Response");				
			}
		}
		public void mouseExited (MouseEvent e){
			if (e.getSource()== processBtn){
				StatusBar.whatstatus.setText("");				
			}
			
		}
	}
	
	private class buttonHandler implements ActionListener {
		
		public void actionPerformed (ActionEvent e){
			if (e.getSource()==processBtn){
				new ProcessBarThread().start();
				new SentenceDetectThread ().start();
				new TokenizerThread().start();
				ResultPanel.processBtn.setEnabled(false);
			}
		}
	}
}
