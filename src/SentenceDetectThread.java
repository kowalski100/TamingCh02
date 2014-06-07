import java.io.*;
import opennlp.tools.sentdetect.*;

public class SentenceDetectThread extends Thread{
	String s;
	InputStream modelIn;
	BufferedWriter write;
	
	public SentenceDetectThread(){
		
	}
	public void run (){
		this.s = ControlHandles.getResponseString();
		File f = new File ("src\\opennlp\\models\\en-sent.zip");
		try {
		modelIn = new FileInputStream (f);
		SentenceModel model = new SentenceModel (modelIn);
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		ControlHandles.sentences = sentenceDetector.sentDetect(s);
		
		ControlHandles.incrementProcess();
		
		//for (int i =0; i< ControlHandles.sentences.length; i++){
			//System.out.println(ControlHandles.sentences[i]+"\n");
			
		//}
		
		File sentfile = new File ("src\\outputs\\sentences.txt");

		try {
		    PrintWriter sent_out = new PrintWriter(new BufferedWriter(new FileWriter(sentfile, false)));
		    sent_out.println("");
		    sent_out.close();
		    sent_out = new PrintWriter(new BufferedWriter(new FileWriter(sentfile, true)));
			for (int i =0; i< ControlHandles.sentences.length; i++){
				sent_out.println(ControlHandles.sentences[i]);
		     }
			sent_out.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		
		}catch (FileNotFoundException e){
			System.err.println("Sentence Model Not Found!");
		}catch (IOException ioexp){
			System.err.println("IOException!");
		}finally {
			try {
				if (modelIn !=null){
					modelIn.close();
				}
			}catch (IOException fioexp){
				
			}
		}
	}

}
