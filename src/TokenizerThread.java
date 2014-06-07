import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerThread extends Thread{
	String s;
	InputStream modelIn;

	public TokenizerThread (){
		
	}
	
	public void run (){
		
		this.s = ControlHandles.getResponseString();
		File f = new File ("src\\opennlp\\models\\en-token.zip");
		try {
		modelIn = new FileInputStream (f);
		  TokenizerModel model = new TokenizerModel(modelIn);
		  Tokenizer tokenizer = new TokenizerME(model);
		  ControlHandles.Tokens = tokenizer.tokenize(ControlHandles.getResponseString());
		  ControlHandles.incrementProcess();
		  try {
		  sleep(500);
		  }catch (InterruptedException e){
			  
		  }
		  new PersonNameRecogThread().start();
		//for (int i =0; i< ControlHandles.Tokens.length; i++){
			//System.out.println(ControlHandles.Tokens[i]+"\n");
			
		//}
		  
			File tokenfile = new File ("src\\outputs\\Tokens.txt");

			try {
			    PrintWriter token_out = new PrintWriter(new BufferedWriter(new FileWriter(tokenfile, false)));
			    token_out.println("");
			    token_out.close();
			    token_out = new PrintWriter(new BufferedWriter(new FileWriter(tokenfile, true)));
				for (int i =0; i< ControlHandles.Tokens.length; i++){
					token_out.println(ControlHandles.Tokens[i]);
			     }
				token_out.close();
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
		}catch (FileNotFoundException e){
			System.err.println("Tokenizer Model Not Found!");
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
