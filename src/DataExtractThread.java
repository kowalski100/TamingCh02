import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class DataExtractThread extends Thread{
	Document doc;
	public DataExtractThread (){
		
	}
	
	public void run (){
			ControlHandles.MainString = "";
			for (int i = 0; i < ControlHandles.mobileURLs.length; i++ ){
		   try {
			   if (ControlHandles.mobileURLs[i]!=null){
			      String currenturl = ControlHandles.mobileURLs[i];
			      Document doc = Jsoup.connect(currenturl).get();
			      Elements paragraphs = doc.select("p");
			      for(Element p : paragraphs)
			      ControlHandles.MainString += (p.text()+"\n");
			      ControlHandles.isResponseReceived =  true;
			   }
			    } 
			    catch (IOException ex) {
			    }		
			}
		//	System.out.println(ControlHandles.MainString);
	}
}
