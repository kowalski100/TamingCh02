import javax.swing.*;
import java.awt.*;
public class MainWrap extends JPanel {

	ResultPanel R_panel;
	MainTextPanel textpane;
	
	public MainWrap (){
		R_panel = new ResultPanel();
		textpane = new MainTextPanel();
		setLayout (new BorderLayout ());
		add(textpane,BorderLayout.CENTER);
		add(R_panel,BorderLayout.EAST);
	}
	
	public Insets getInsets (){
		return new Insets(5,5,3,5);
	}
	
}
