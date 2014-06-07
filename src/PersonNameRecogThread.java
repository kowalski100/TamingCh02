import java.io.*;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;


public class PersonNameRecogThread extends Thread{
	InputStream modelIn;
	String[] s;
	public PersonNameRecogThread (){
		
	}
	
	public void run (){
        s = ControlHandles.getTokens();
		File f = new File ("src\\opennlp\\models\\en-ner-person.zip");
		try {
		modelIn = new FileInputStream (f);
		TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
		NameFinderME nameFinder = new NameFinderME(model);
		System.out.println("you entered the names finder");
		Span nameSpans[] = nameFinder.find(s);
		
		File namefile = new File ("src\\outputs\\ExpectedNames.txt");
		PrintWriter name_out = null ;
		try {
			name_out = new PrintWriter(new FileWriter(namefile, false));
			name_out.println("");
			name_out.close();
			name_out = new PrintWriter(new BufferedWriter(new FileWriter(namefile, true)));
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

		for(Span p: nameSpans){
			//System.out.println(p.toString());
			for (int m = p.getStart(); m <p.getEnd(); m++){
				name_out.println(ControlHandles.Tokens[m]);
				System.out.println(ControlHandles.Tokens[m]);
				
		   }
			
		}
		name_out.close();
		ControlHandles.incrementProcess();
		
		//for (int i =0; i< ControlHandles.sentences.length; i++){
			//System.out.println(ControlHandles.sentences[i]+"\n");
			
		//}
		
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
