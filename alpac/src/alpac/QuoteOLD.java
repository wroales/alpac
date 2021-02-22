package alpac;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.*;

@Entity
@Table(name = "quote", schema = "trade")

public class QuoteOLD {
	 
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long quoteId;
	
	@Column(name = "is_current", columnDefinition = "boolean default true")
	private boolean is_current = true;
	
	 @Column(name = "status", nullable=true)
			    private String status;

			    private String symbol;
	 
			    private double askprice;
	 
			    private int asksize;
	 
			    private int askexchange;

			    private double bidprice;

			    private int bidsize;

			    private int bidexchange;

			    private long timestamp;
			    
			    private Date qDate;
			    

			    public void setStatus(String status){
			        this.status = status;
			    }
			    public String getStatus(){
			        return this.status;
			    }
			    public void setSymbol(String symbol){
			        this.symbol = symbol;
			    }
			    public String getSymbol(){
			        return this.symbol;
			    }
			    public void setAskprice(double askprice){
			        this.askprice = askprice;
			    }
			    public double getAskprice(){
			        return this.askprice;
			    }
			    public void setAsksize(int asksize){
			        this.asksize = asksize;
			    }
			    public int getAsksize(){
			        return this.asksize;
			    }
			    public void setAskexchange(int askexchange){
			        this.askexchange = askexchange;
			    }
			    public int getAskexchange(){
			        return this.askexchange;
			    }
			    public void setBidprice(double bidprice){
			        this.bidprice = bidprice;
			    }
			    public double getBidprice(){
			        return this.bidprice;
			    }
			    public void setBidsize(int bidsize){
			        this.bidsize = bidsize;
			    }
			    public int getBidsize(){
			        return this.bidsize;
			    }
			    public void setBidexchange(int bidexchange){
			        this.bidexchange = bidexchange;
			    }
			    public int getBidexchange(){
			        return this.bidexchange;
			    }
			    public void setTimestamp(long timestamp){
			        this.timestamp = timestamp;
			    }
			    public Date getTimestamp(){
			    	long millis = TimeUnit.MILLISECONDS.convert(timestamp, TimeUnit.NANOSECONDS);
			    	qDate = new Date(millis);
			    	//System.out.println("DATE===>"+qDate);
			        return this.qDate;
			    }
}
