
public class ResponseProgressThread extends Thread {

	
	public ResponseProgressThread (){
		
	}
	public void run (){
		SearchPanel.srchpbar.setValue(0);
	    while (!ControlHandles.isResponseReceived){
	    	
	    	try {
	    		this.sleep(400);
	    		SearchPanel.srchpbar.setValue(1);
	    		this.sleep(800);
	    		SearchPanel.srchpbar.setValue(2);
	    		this.sleep(800);
	    		SearchPanel.srchpbar.setValue(3);
	    		while (!ControlHandles.isResponseReceived);
	    		SearchPanel.srchpbar.setValue(4);
	    		ResultPanel.processBtn.setEnabled(true);
	    	}catch (InterruptedException exc){
	    		
	    	}
	    	
	    	
	    }
	}
}
