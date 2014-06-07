

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.border.*;

public class MainGUI extends JFrame{
	private String search;
	private Double latitude,longitude;
	
	public MainGUI (){
		super ("TamingCh02-Yelp");
		
		JMenuBar menubar = new MenuManager();
		JToolBar toolbar = new ToolBarManager();
		toolbar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
		toolbar.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		//this.add(toolbar,BorderLayout.PAGE_START);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		SearchPanel srchPanel = new SearchPanel ();
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(toolbar,BorderLayout.NORTH);
		p.add(srchPanel,BorderLayout.CENTER);
		container.add(p,BorderLayout.NORTH);
		setJMenuBar (menubar);
		MainWrap mainwrap =  new MainWrap();
		container.add(mainwrap,BorderLayout.CENTER);
		StatusBar sbar = new StatusBar ();
		
		container.add(sbar,BorderLayout.SOUTH);
		setSize (1024,700);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src"+File.separator+"images\\logo.png"));
		this.addWindowListener(
				new WindowAdapter (){
					public void windowClosing (WindowEvent e){
						System.exit(0);
					}
				}
				);
	}
}
