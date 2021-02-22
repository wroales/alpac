package alpac;

import java.util.ArrayList;

import org.hibernate.Session;

public class mainQuote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    	Alpac ap = new Alpac();
        //Thread object = new Thread(new MultithreadingDemo()); 
        //object.start(); 
        
    	//AlpacJpa.updateCurrent("quote");
   	    //String qtes="GM,F,KO,PEP,AAPL,AMZN,PFE,ETN,WY";
   	    String qtes="WY";
        String[] arrOfTkr = qtes.split(","); 
  
        for (String tkr : arrOfTkr) { 
            System.out.println(tkr);
    	    //Quote qte = ap.getQuote(tkr);
            Quote qte = ap.getQuote(tkr);

    	
	        System.out.println("SYMBOL="+qte.getSymbol());
	        System.out.println("STATUS="+qte.getStatus());
	        System.out.println("ASK   ="+qte.getLast().getAskprice());
	        System.out.println("BID   ="+qte.getLast().getBidprice());
	        System.out.println("TS    ="+qte.getLast().getTimestamp());
	        //System.out.println("ASK   ="+qte.getAskprice());
	        //System.out.println("TIMES ="+qte.getTimestamp());
	        
	        // save Quote Obj to database
//	        Session sess = AlpacJpa.beginTransaction();
//			sess.persist(qte);
//			AlpacJpa.commitTransaction(sess);
        }
	}
           		
}