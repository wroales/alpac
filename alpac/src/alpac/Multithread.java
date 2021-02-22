package alpac;

public class Multithread {

	  public static void main(String[] args) 
	    { 
	        int n = 1; // Number of threads 
	        for (int i=0; i<n; i++) 
	        { 
	        	System.out.println ("HELLO from main");
	            Thread object = new Thread(new MultithreadingDemo()); 
	            object.start(); 
	            try {
					object.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println ("BYE from main");
	        } 
	    } 

}
