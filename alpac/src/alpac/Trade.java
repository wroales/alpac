package alpac;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

@Entity
@Table(name = "trade", schema = "trade")
//============================
public class Trade  {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long tradeId;
	
	@Column(name = "is_current", columnDefinition = "boolean default true")
	private boolean is_current = true;

    private String id;

    private String activity_type;

    private String transaction_time;

    private String type;

    private String price;

    private String qty;

    private String side;

    private String symbol;

    private String leaves_qty;

    private String order_id;

    private String cum_qty;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setActivity_type(String activity_type){
        this.activity_type = activity_type;
    }
    public String getActivity_type(){
        return this.activity_type;
    }
    public void setTransaction_time(String transaction_time){
        this.transaction_time = transaction_time;
    }
    public String getTransaction_time(){
        return this.transaction_time;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public String getQty(){
        return this.qty;
    }
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
    public void setLeaves_qty(String leaves_qty){
        this.leaves_qty = leaves_qty;
    }
    public String getLeaves_qty(){
        return this.leaves_qty;
    }
    public void setOrder_id(String order_id){
        this.order_id = order_id;
    }
    public String getOrder_id(){
        return this.order_id;
    }
    public void setCum_qty(String cum_qty){
        this.cum_qty = cum_qty;
    }
    public String getCum_qty(){
        return this.cum_qty;
    }
    
 
}
