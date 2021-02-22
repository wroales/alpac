package alpac;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;


/*
 * Converting a java object into a JSON string:

	  String json_string = new Gson().toJson(an_object);
	  
Creating a java object from a JSON string:

	  MyObject obj = new Gson().fromJson(a_json_string, MyObject.class);
	  
Create an object from json

//https://www.freecodeformat.com/json2pojo.php
 * 
	  */


//"APCA-API-KEY-ID", "PK4HZQOC53YRQMQDBQ0V"
//"APCA-API-SECRET-KEY", "VIoITILjTHNuPprCoK0wONyS3zFfcYTBFjTe3vF3"


public class Alpac {
	
   String keyId="";
   String secretKey="";
	
   public void loadAlpacaProperties() {
	   
	   try (InputStream input = new FileInputStream("c:\\Users\\13862\\Desktop\\config.properties")) {

		    //System.out.println("INPUT="+input);
		    
	        Properties prop = new Properties();

	        // load a properties file
	        prop.load(input);

	        // get the property value and print it out
	        System.out.println(prop.getProperty("db.url"));
	        System.out.println(prop.getProperty("db.user"));
	        System.out.println(prop.getProperty("db.password"));
	        System.out.println(prop.getProperty("APCA-API-KEY-ID"));
	        System.out.println(prop.getProperty("APCA-API-SECRET-KEY"));
	        keyId=prop.getProperty("APCA-API-KEY-ID");
	        secretKey=prop.getProperty("APCA-API-SECRET-KEY");
	        

	    } catch (IOException ex) {
	    	System.out.println("Property File Load ERROR!!!!");
	        ex.printStackTrace();
	    }
   }

	public Alpac() {
		// TODO Auto-generated constructor stub
		loadAlpacaProperties();
		
	}

    public String alpacAPI(String func) {
    	
        CloseableHttpClient httpClient = null;
        HttpGet httget = null;
        CloseableHttpResponse response = null;
        StringBuilder returnStr = new StringBuilder();
        String url = "https://paper-api.alpaca.markets/v2/";
        
        try {

            httpClient = HttpClients.createDefault();
            httget = new HttpGet(url+func);        
            httget.addHeader("APCA-API-KEY-ID", keyId);
            httget.addHeader("APCA-API-SECRET-KEY",secretKey);

            response = httpClient.execute(httget);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));
        
            String buf;
            while ((buf = br.readLine()) != null) {
            	returnStr.append(buf);
            }
            
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try{
                response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }      
        return(returnStr.toString());
    }
    
    
    public Account getAcct() {
    	String buffa = alpacAPI("account");
    	System.out.println("ACCOUNT="+buffa);
    	Account acct = new Gson().fromJson(buffa, Account.class);
    	return acct;
    }
    
    public ArrayList<Position> getPositions() {  	
    	String buff = alpacAPI("positions"); 
    	System.out.println("POS="+buff);
       	Type listType = new TypeToken<ArrayList<Position>>() {}.getType();
       	ArrayList<Position> positions = new Gson().fromJson(buff , listType);
       	return positions;
    }
    
    public ArrayList<Trade> getTrades() {  	
    	String buff = alpacAPI("account/activities"); 
    	System.out.println("TRADES="+buff);
       	Type listType = new TypeToken<ArrayList<Trade>>() {}.getType();
       	ArrayList<Trade> trades = new Gson().fromJson(buff , listType);
       	return trades;
    }
    
    public ArrayList<OrderStatus> getOrderStatus() {  
    	String buff = alpacAPI("orders");
    	System.out.println("BUFF    =="+buff);    	
    	Type listType = new TypeToken<ArrayList<OrderStatus>>() {}.getType();
    	ArrayList<OrderStatus> os = new Gson().fromJson(buff , listType);
    	return os;
    }
    
    public void tradeEntry(MktOrder ord ) {
    	
    	String jsonOrderStr = new Gson().toJson(ord);
    	String result = "";
  
        CloseableHttpClient httpClient = null;
        HttpPost httpost = null;
        CloseableHttpResponse response = null;
        String url = "https://paper-api.alpaca.markets/v2/orders";
        
        JsonObject jsonObject = new JsonParser().parse(jsonOrderStr).getAsJsonObject();
       
        System.out.println(url+jsonOrderStr);
        
        try {

            httpClient = HttpClients.createDefault();
            httpost = new HttpPost(url);
            HttpEntity stringEntity = new StringEntity(jsonObject.toString(),ContentType.APPLICATION_JSON);
            httpost.setEntity(stringEntity);           
            httpost.addHeader("APCA-API-KEY-ID", keyId);
            httpost.addHeader("APCA-API-SECRET-KEY", secretKey);
            
            response = httpClient.execute(httpost);
            
            //System.out.println("$$$$$$$$$$$ ="+response);
                 
            result = EntityUtils.toString(response.getEntity()); 
            OrderStatus os = new Gson().fromJson(result, OrderStatus.class);
            System.out.println("TRADE  ID="+os.getId());
            System.out.println("CLIENTOID="+os.getClient_order_id());
            System.out.println("RESULT   ="+result);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Trade Entry Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
           

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try{
                response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }      
        
        return;
    }   
    
    //====================================================================================
    public void tradeCancel(String orderId) {
  
        CloseableHttpClient httpClient = null;
        HttpDelete httdelete = null;
        CloseableHttpResponse response = null;
        String url = "https://paper-api.alpaca.markets/v2/orders/";
        
        System.out.println(url);
        
        try {

            httpClient = HttpClients.createDefault();
            httdelete = new HttpDelete(url+orderId);  
            httdelete.addHeader("APCA-API-KEY-ID", keyId);
            httdelete.addHeader("APCA-API-SECRET-KEY", secretKey);
            
            response = httpClient.execute(httdelete);   

            // 204 is a good Http code for cancel of a pending order
            if (response.getStatusLine().getStatusCode() != 204) {
                System.out.println("Trade Cancel  : HTTP Return code : "
                        + response.getStatusLine().getStatusCode());
            }
           

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try{
                response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }      
        
        return;
    }
    
    //==========================================================================
    // Get market Data
    //==========================================================================
    
    //public Quote getQuote(String tkr) {
    public Quote getQuote(String tkr) {
       	
        CloseableHttpClient httpClient = null;
        HttpGet httget = null;
        CloseableHttpResponse response = null;
        StringBuilder returnStr = new StringBuilder();
        String url = "https://data.alpaca.markets/v1/last_quote/stocks/";
        
        try {

            httpClient = HttpClients.createDefault();
            httget = new HttpGet(url+tkr); 
            httget.addHeader("APCA-API-KEY-ID", keyId);
            httget.addHeader("APCA-API-SECRET-KEY",secretKey);
            httget.addHeader("accept", "application/json");
  
            response = httpClient.execute(httget);

            if (response.getStatusLine().getStatusCode() != 200) {
            	System.out.println("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));
        
            String buf;
            while ((buf = br.readLine()) != null) {
            	returnStr.append(buf);
            }
            
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try{
                response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }  
        
        System.out.println("QUOTE String="+returnStr.toString());
        // Hack to make a flat quote object. Merge the
        // Last object into quote obj and eliminate the json tags 
        String tmpStr = returnStr.toString().replace("\"last\":{", "");
        String tmpStr2 = tmpStr.replace("}}","}");
        System.out.println("tmpStr2     ="+tmpStr2);
        //Quote qte = new Gson().fromJson(tmpStr2, Quote.class);       
        //return(qte);
        
        
        Gson gson = new Gson();
        Type CollectionType = new TypeToken<Quote>() {
        }.getType();
        Quote myq = gson.fromJson(returnStr.toString(), CollectionType);
        
        return myq;
    }
    
    	
}