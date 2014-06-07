import java.awt.Color;
import javax.swing.text.*;
import org.json.*;
import java.net.*;
public class SearchThread extends Thread{
	StyledDocument doc;
	SimpleAttributeSet heading,content;
	public SearchThread (){
		ControlHandles.isResponseReceived = false;
		doc = MainTextPanel.textpanel.getStyledDocument();
		
		heading = new SimpleAttributeSet();
		StyleConstants.setForeground(heading, Color.WHITE);
		StyleConstants.setBackground(heading, Color.black);
		StyleConstants.setBold(heading, true);
		StyleConstants.setFontSize(heading, 15);

		content = new SimpleAttributeSet();
		StyleConstants.setForeground(content, Color.black);
		StyleConstants.setBold(content, true);

	}
	
	public void run (){
		
		MainTextPanel.textpanel.setText("");
	    String consumerKey = "q9dj3xqkFxiZqNu8TRwcGQ";
	    String consumerSecret = "WCywpjIlgeW8mynBV8VXcyHoOtE";
	    String token = "-De520MiQz7-kaR_3254zwFoiLRmkUTT";
	    String tokenSecret = "URfBYQJppVUhys5InNPgmB_fCpc";
	    
	    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
	    ControlHandles.response = yelp.search(ControlHandles.srchString, ControlHandles.latitude_input, ControlHandles.longitude_input);
	    //ControlHandles.MainString= ControlHandles.response.toString();
	    
	    //System.out.println(ControlHandles.response);
	    
	    try {
	    JSONObject jObj  = new JSONObject(ControlHandles.response);
	    //inner region information
	    JSONObject regionObj = jObj.getJSONObject("region");
	    //span information
	    JSONObject spanObj = regionObj.getJSONObject("span");
	    Double D_Lat = spanObj.getDouble("latitude_delta");
	    Double D_Lon = spanObj.getDouble("longitude_delta");
	    ResponseResults.latitude_delta.setText(D_Lat.toString());
	    ResponseResults.longitude_delta.setText(D_Lon.toString());
	    //GPS Location
	    JSONObject centerObj = regionObj.getJSONObject("center");
	    Double Lat = centerObj.getDouble("latitude");
	    Double Lon = centerObj.getDouble("longitude");
	    ResponseResults.latitude.setText(Lat.toString());
	    ResponseResults.longitude.setText(Lon.toString());
	    //Total Results found
	    int totalresp = jObj.getInt("total");
	    ResponseResults.totalResults.setText(""+totalresp);
	 
	    JSONArray businesses = jObj.getJSONArray("businesses");
	    ControlHandles.names = new String[businesses.length()];
	    ControlHandles.statuses =  new String[businesses.length()];
	    int urlcount = 0;
		ControlHandles.mobileURLs = new String [businesses.length()];
	    for (int i = 0; i< businesses.length(); i++){
	    	JSONObject obj = businesses.getJSONObject(i);
	    		// get text data from url pages....
	    	    //System.err.println(obj.getString("mobile_url")+"\n");

	    	    String sName = "Name: "+obj.getString("name")+"\n";
	    	    String sStatus;
	    	    if (!obj.getBoolean("is_closed")){
	    	    	sStatus = "Status: OPEN\n";	
	    	    }else {
	    	    sStatus = "Status: CLOSE\n";
	    	    }
	    	    String sPhone = "Phone#: "+ obj.getString("display_phone")+"\n";
	    	    JSONObject loc = obj.getJSONObject("location");
	    	    String sCity = "City: "+loc.getString("city")+"\n";
	    	    JSONArray display_address = loc.getJSONArray("display_address");
	    	    String addresses[] =  new String[display_address.length()];
	    	    String sAdd = "Address : " ;
	    	    for(int k=0;k<addresses.length;k++) {
	    	    	sAdd+= display_address.getString(k)+" ";
	    	    }
	    	    
	    	    sAdd +="\n";
	    	    String sRating ="Rating: "+ obj.getDouble("rating")+"\n";
	    	    String sReview ="Reviews: "+ obj.getDouble("review_count")+"\n";
	    	    ControlHandles.saveURL(obj.getString("mobile_url"), i); 
	    	    String sUrl ="Yelp: " + obj.getString("mobile_url");
	    	    
	    	    try
	    	    {
	    	    	
	    	    	doc.insertString(doc.getLength(), "Result# "+(i+1)+"\n\n", heading);
	    	        doc.insertString(doc.getLength(), sName, content );
	    	        doc.insertString(doc.getLength(), sStatus, content );
	    	        doc.insertString(doc.getLength(), sPhone, content );
	    	        doc.insertString(doc.getLength(), sCity, content );
	    	        doc.insertString(doc.getLength(), sAdd, content );
	    	        doc.insertString(doc.getLength(), sReview, content );
	    	        doc.insertString(doc.getLength(), sRating, content );
	    	        doc.insertString(doc.getLength(), sUrl, content );
	    	        doc.insertString(doc.getLength(), "\n*********************************************************\n", content );
	    	        
	    	    }
	    	    catch(Exception e) {  }
	    	
	    }
	  //  JOptionPane.showMessageDialog(null, businesses.length());
	    
	    }catch (JSONException jsonexp){
	    	System.err.println("Problem parsing");
	    }
	    new DataExtractThread().start();
	}
	
}
