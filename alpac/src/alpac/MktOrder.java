package alpac;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mktorder", schema = "trade")

public class MktOrder {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long mktordId;
	
	@Column(name = "is_current", columnDefinition = "boolean default true")
	private boolean is_current = true;

    private String side;

    private String symbol;

    private String type;

    private String qty;

    private String time_in_force;
    
    private String limit_price;

    public void setSide(String side){
        this.side = side;
    }
    public String getSide(){
        return this.side;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return this.symbol;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public String getQty(){
        return this.qty;
    }
    public void setTime_in_force(String time_in_force){
        this.time_in_force = time_in_force;
    }
    public String getTime_in_force(){
        return this.time_in_force;
    }
    public void setLimitPrice(String lp){
        this.limit_price = lp;
    }
    public String getLimitPrice(){
        return this.limit_price;
    }
}