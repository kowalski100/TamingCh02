import javax.swing.ImageIcon;

public class ControlHandles {

	static Double  latitude_input;
	static Double  longitude_input;
	static String  srchString;
	static ImageIcon refreshicon;
	static boolean  isResponseReceived = false;
	static String response;
	static String[] names;
	static String[] statuses;
	static String MainString;
	static int count = 0;
	static String[] sentences;
	static String[] Tokens;
	static String[] Names;
	static String[] mobileURLs;
	static int nlp_process_complete = 0;
	
	public static synchronized void incrementProcess(){
		nlp_process_complete ++;
	}
	
	public static synchronized String getResponseString (){
		return MainString;
	}
	public synchronized void getProcessComplete (){
		count++;
	}
	public static void saveURL (String s,int index){
		mobileURLs[index] = s;
		//System.err.println(s);
	}
	public static synchronized String[] getTokens(){
		return Tokens;
	}
	
}
