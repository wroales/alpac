package alpac;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

//https://www.freecodeformat.com/json2pojo.php

@Entity
@Table(name = "position", schema = "trade")
public class Position implements AlpacI {


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long positionId;

	@Column(name = "is_current", columnDefinition = "boolean default true")
	private boolean is_current = true;
	
    private String asset_id;

    private String symbol;

    private String exchange;

    private String asset_class;

    private String qty;

    private String avg_entry_price;

    private String side;

    private String market_value;

    private String cost_basis;

    private String unrealized_pl;

    private String unrealized_plpc;

    private String unrealized_intraday_pl;

    private String unrealized_intraday_plpc;

    private String current_price;

    private String lastday_price;

    private String change_today;

    public void setAsset_id(String asset_id){
        this.asset_id = asset_id;
    }
    public String getAsset_id(){
        return this.asset_id;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return this.symbol;
    }
    public void setExchange(String exchange){
        this.exchange = exchange;
    }
    public String getExchange(){
        return this.exchange;
    }
    public void setAsset_class(String asset_class){
        this.asset_class = asset_class;
    }
    public String getAsset_class(){
        return this.asset_class;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public String getQty(){
        return this.qty;
    }
    public void setAvg_entry_price(String avg_entry_price){
        this.avg_entry_price = avg_entry_price;
    }
    public String getAvg_entry_price(){
        return this.avg_entry_price;
    }
    public void setSide(String side){
        this.side = side;
    }
    public String getSide(){
        return this.side;
    }
    public void setMarket_value(String market_value){
        this.market_value = market_value;
    }
    public String getMarket_value(){
        return this.market_value;
    }
    public void setCost_basis(String cost_basis){
        this.cost_basis = cost_basis;
    }
    public String getCost_basis(){
        return this.cost_basis;
    }
    public void setUnrealized_pl(String unrealized_pl){
        this.unrealized_pl = unrealized_pl;
    }
    public String getUnrealized_pl(){
        return this.unrealized_pl;
    }
    public void setUnrealized_plpc(String unrealized_plpc){
        this.unrealized_plpc = unrealized_plpc;
    }
    public String getUnrealized_plpc(){
        return this.unrealized_plpc;
    }
    public void setUnrealized_intraday_pl(String unrealized_intraday_pl){
        this.unrealized_intraday_pl = unrealized_intraday_pl;
    }
    public String getUnrealized_intraday_pl(){
        return this.unrealized_intraday_pl;
    }
    public void setUnrealized_intraday_plpc(String unrealized_intraday_plpc){
        this.unrealized_intraday_plpc = unrealized_intraday_plpc;
    }
    public String getUnrealized_intraday_plpc(){
        return this.unrealized_intraday_plpc;
    }
    public void setCurrent_price(String current_price){
        this.current_price = current_price;
    }
    public String getCurrent_price(){
        return this.current_price;
    }
    public void setLastday_price(String lastday_price){
        this.lastday_price = lastday_price;
    }
    public String getLastday_price(){
        return this.lastday_price;
    }
    public void setChange_today(String change_today){
        this.change_today = change_today;
    }
    public String getChange_today(){
        return this.change_today;
    }
    	
	@Override
	public ArrayList loadYourself() {
		
	   	Alpac ap = new Alpac();
	   	AlpacJpa.updateCurrent("position");
    	ArrayList<Position> positions; 
    	positions = ap.getPositions();

    	Session sess = AlpacJpa.beginTransaction();

        for (int counter = 0; counter < positions.size(); counter++) { 	
     	   Position pos = positions.get(counter); 
     	   sess.persist(pos);
     	   //System.out.println("ASSETID=="+pos.getAsset_id());
     	   //System.out.println("SYMBOL =="+pos.getSymbol());
     	   //System.out.println("POS    =="+pos.getQty());
        }   
        AlpacJpa.commitTransaction(sess);
        return positions;
	}
	
}