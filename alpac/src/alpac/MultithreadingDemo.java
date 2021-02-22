package alpac;

import org.hibernate.Session;

public class MultithreadingDemo implements Runnable {

	   public void run() 
	    { 
	        try
	        { 
	            // Displaying the thread that is running 
	            System.out.println ("Spin Thread " + 
	                                Thread.currentThread().getId() + 
	                                " is running"); 
                Alpac ap = new Alpac();
	            for(int i=0; i < 5; i++) {
	            	
	                System.out.println ("Spin Thread to get Quotes "+i);

	            	AlpacJpa.updateCurrent("quote");
	           	    String qtes="GM,F,KO,PEP,AAPL,AMZN,PFE,ETN,WY";
	                String[] arrOfTkr = qtes.split(","); 
	          
	                for (String tkr : arrOfTkr) { 
	                    System.out.println(tkr);
	            	    Quote qte = ap.getQuote(tkr);
	            	
//	        	        System.out.println("SYMBOL="+qte.getSymbol());
//	        	        System.out.println("STATUS="+qte.getStatus());
//	        	        System.out.println("ASK   ="+qte.getAskprice());
//	        	        System.out.println("TIMES ="+qte.getTimestamp());
	        	        
	        	        // save Quote Obj to database
	        	        Session sess = AlpacJpa.beginTransaction();
	        			sess.persist(qte);
	        			AlpacJpa.commitTransaction(sess);
	                }
	                Thread.sleep(15000);
	            }
	            
	        } 
	        catch (Exception e) 
	        { 
	            // Throwing an exception 
	            System.out.println ("Exception is caught"); 
	        } 
	    } 

}
