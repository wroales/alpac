package alpac;

import java.util.ArrayList;

import org.hibernate.Session;

public class mainAlpac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    	Alpac ap = new Alpac();
   	
     	ArrayList<Trade> trades; 
    	trades = ap.getTrades();
    	
      	Session sess = AlpacJpa.beginTransaction();
      	
        for (int counter = 0; counter < trades.size(); counter++) { 	
     	   Trade trd = trades.get(counter); 
     	   sess.persist(trd);
     	   System.out.println("===================================");
     	   System.out.println("TIME    =="+trd.getTransaction_time());
     	   System.out.println("ACT TYPE=="+trd.getActivity_type());
     	   System.out.println("ID      =="+trd.getId());
     	   System.out.println("ORDID   =="+trd.getOrder_id());
     	   System.out.println("SYM     =="+trd.getSymbol());
     	   System.out.println("PX      =="+trd.getPrice());
     	   System.out.println("QTY     =="+trd.getQty());
     	   System.out.println("LEAVES  =="+trd.getLeaves_qty());
     	   System.out.println("SIDE    =="+trd.getSide());
     	   
        }   
        AlpacJpa.commitTransaction(sess);
   	
    	Account acct;
    	acct = ap.getAcct();
    	System.out.println("ACCTNUM="+acct.getAccount_number());
    	System.out.println("BUY PWR="+acct.getBuying_power());
    	System.out.println("ACCT ID="+acct.getId());
    	
        // save Acct Obj to database   	
    	AlpacJpa.updateCurrent("account");
    	   	
        sess = AlpacJpa.beginTransaction();
  		sess.persist(acct);
  		AlpacJpa.commitTransaction(sess);
  		
 //=================================================================================
  		
    	AlpacJpa.updateCurrent("position");
    	ArrayList<Position> positions; 
    	positions = ap.getPositions();
    	sess = AlpacJpa.beginTransaction();
  
        for (int counter = 0; counter < positions.size(); counter++) { 	
     	   Position pos = positions.get(counter); 
     	   sess.persist(pos);
     	   System.out.println("ASSETID=="+pos.getAsset_id());
     	   System.out.println("SYMBOL =="+pos.getSymbol());
     	   System.out.println("POS    =="+pos.getQty());
        }   
        AlpacJpa.commitTransaction(sess);
         
 //============================================================================  	
        //ap.tradeEntry("KMB","buy","5","market", "");
        //ap.tradeEntry("KMB","buy","3","limit", "133.00");
        //ap.tradeEntry("PEP","buy","3","limit", "142.00");
   
  
    	AlpacJpa.updateCurrent("order");
        
      	MktOrder ord = new MktOrder();
    	ord.setSide("buy");
    	ord.setQty("2");
    	ord.setSymbol("GME");
    	ord.setType("limit");
    	//ord.setType("market");
        ord.setLimitPrice("1.00");
    	ord.setTime_in_force("gtc");
    	
        sess = AlpacJpa.beginTransaction();    	
    	ap.tradeEntry(ord);  
		sess.persist(ord);
		AlpacJpa.commitTransaction(sess);
 /*
    	//ap.tradeCancel("37f6ad0c-60ed-4d37-ac8f-213aa7cdcaa0");
		
//==============================================================================
	
        //Thread object = new Thread(new MultithreadingDemo()); 
        //object.start(); 
        
/*    	AlpacJpa.updateCurrent("quote");
   	
    	Quote qte = ap.getQuote("BMY");
        System.out.println("SYMBOL="+qte.getSymbol());
        System.out.println("STATUS="+qte.getStatus());
        System.out.println("ASK   ="+qte.getAskprice());
        System.out.println("TIMES ="+qte.getTimestamp());
        
        // save Quote Obj to database
        Session sess = AlpacJpa.beginTransaction();
		sess.persist(qte);
		AlpacJpa.commitTransaction(sess);
*/	
//===========================================================================
		
    	AlpacJpa.updateCurrent("orderstatus");

	    sess = AlpacJpa.beginTransaction();
        ArrayList<OrderStatus> ordStats;
    try {
        	ordStats = ap.getOrderStatus();

        	System.out.println("Orders On Tap="+ordStats.size());
    	
        	for (int counter = 0; counter < ordStats.size(); counter++) { 	
        		OrderStatus os = ordStats.get(counter);
        		sess.persist(os);
            	System.out.println("ID  ="+os.getId()); 
            	System.out.println("SYM ="+os.getSymbol());
            	System.out.println("FILL="+os.getFilled_at());
            	System.out.println("TYPE="+os.getOrder_type());
            	System.out.println("LMT ="+os.getLimit_price());
            	System.out.println("STAT="+os.getStatus());           	
        	}
        
    	}catch(Exception ex) {
    		System.out.println("ERROR creating OrderStatus");
    		//ex.printStackTrace();
    	}   
    	AlpacJpa.commitTransaction(sess);
   	
	}
           		
}