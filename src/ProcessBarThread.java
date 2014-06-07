import javax.swing.JOptionPane;


public class ProcessBarThread extends Thread{

	public ProcessBarThread (){
		
	}
	public void run (){
		while (ControlHandles.nlp_process_complete<3){
			
			ResultPanel.pBar.setValue(ControlHandles.nlp_process_complete);
			try {
			sleep(500);
			}catch (InterruptedException e){
				
			}
		}
		ResultPanel.pBar.setValue(3);	
		ToolBarManager.nlpBtn.setEnabled(true);
		ToolBarManager.imgBtn.setEnabled(true);
		JOptionPane.showMessageDialog(null, "Process Completed!","Process Message",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
