import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MenuManager extends JMenuBar{
	JMenu file,edit;
	JMenuItem f_new,f_exit;
	public MenuManager (){
		file = new JMenu("File");
		file.setMnemonic('F');
		
		//new Item declariona and handler
		f_new = new JMenuItem("New            ");
		f_new.setMnemonic('N');
		f_new.setAccelerator(KeyStroke.getKeyStroke('N',KeyEvent.CTRL_DOWN_MASK));
		file.add(f_new);
		menuHandler handler = new menuHandler();
		f_new.addActionListener(handler);
		ImageIcon newicon =  new ImageIcon("src\\images\\new.png");
		f_new.setIcon(newicon);
		file.addSeparator();
		
		//exit Item declaration and handler
		f_exit = new JMenuItem("Exit");
		f_exit.setMnemonic('x');
		f_exit.setAccelerator(KeyStroke.getKeyStroke('X',KeyEvent.CTRL_DOWN_MASK));
		file.add(f_exit);
		f_exit.addActionListener(handler);
		ImageIcon exiticon = new ImageIcon ("src\\images\\exit.png");
		f_exit.setIcon(exiticon);
		
		
		edit = new JMenu ("Edit");
		
		add(file);
		
	}
	
	private class menuHandler implements ActionListener{
		
		public void actionPerformed (ActionEvent e){
			if (e.getSource()== f_new){
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
			if (e.getSource()== f_exit){
				System.exit(0);
			}
			
		}
	}
	
}
