package alpac;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;


public class AlpacInterface {

	public AlpacInterface() {
		
	}

	public static void main(String[] args) {
		
		/*
		   Session sess = AlpacJpa.beginTransaction();
		   
		   List<Quote> myList = sess.createQuery("SELECT q FROM Quote q WHERE q.is_current=true ", Quote.class).getResultList();
		   
		   System.out.println("==============> 1. Quotes");
		   for (int i = 0; i < myList.size(); i++) {
			   System.out.println(myList.get(i).getSymbol()+ " bid="+myList.get(i).getBidprice());
		   }
		   
		 */
		  getPositions();

	}
	
	public static void getPositions() {
		Position pos = new Position();
		ArrayList pl = pos.loadYourself();
		
        for (int counter = 0; counter < pl.size(); counter++) { 	
      	   pos = (Position)pl.get(counter);
      	   System.out.println("ASSETID=="+pos.getAsset_id());
      	   System.out.println("SYMBOL =="+pos.getSymbol());
      	   System.out.println("POS    =="+pos.getQty());
         }   
	}	

}
