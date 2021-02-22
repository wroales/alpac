package alpac;

//============================

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;


@Entity
@Table(name = "account", schema = "trade")
//============================
public class Account  {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long accountId;
	
	@Column(name = "is_current", columnDefinition = "boolean default true")
	private boolean is_current = true;

    private String id;
   
    private String account_number;

    private String status;

    private String currency;

    private String buying_power;

    private String regt_buying_power;

    private String daytrading_buying_power;

    private String cash;

    private String portfolio_value;

    private boolean pattern_day_trader;

    private boolean trading_blocked;

    private boolean transfers_blocked;

    private boolean account_blocked;

    private String created_at;

    private boolean trade_suspended_by_user;

    private String multiplier;

    private boolean shorting_enabled;

    private String equity;

    private String last_equity;

    private String long_market_value;

    private String short_market_value;

    private String initial_margin;

    private String maintenance_margin;

    private String last_maintenance_margin;

    private String sma;

    private int daytrade_count;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    
    public void setAccount_number(String account_number){
        this.account_number = account_number;
    }
    public String getAccount_number(){
        return this.account_number;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }
    public void setBuying_power(String buying_power){
        this.buying_power = buying_power;
    }
    public String getBuying_power(){
        return this.buying_power;
    }
    public void setRegt_buying_power(String regt_buying_power){
        this.regt_buying_power = regt_buying_power;
    }
    public String getRegt_buying_power(){
        return this.regt_buying_power;
    }
    public void setDaytrading_buying_power(String daytrading_buying_power){
        this.daytrading_buying_power = daytrading_buying_power;
    }
    public String getDaytrading_buying_power(){
        return this.daytrading_buying_power;
    }
    public void setCash(String cash){
        this.cash = cash;
    }
    public String getCash(){
        return this.cash;
    }
    public void setPortfolio_value(String portfolio_value){
        this.portfolio_value = portfolio_value;
    }
    public String getPortfolio_value(){
        return this.portfolio_value;
    }
    public void setPattern_day_trader(boolean pattern_day_trader){
        this.pattern_day_trader = pattern_day_trader;
    }
    public boolean getPattern_day_trader(){
        return this.pattern_day_trader;
    }
    public void setTrading_blocked(boolean trading_blocked){
        this.trading_blocked = trading_blocked;
    }
    public boolean getTrading_blocked(){
        return this.trading_blocked;
    }
    public void setTransfers_blocked(boolean transfers_blocked){
        this.transfers_blocked = transfers_blocked;
    }
    public boolean getTransfers_blocked(){
        return this.transfers_blocked;
    }
    public void setAccount_blocked(boolean account_blocked){
        this.account_blocked = account_blocked;
    }
    public boolean getAccount_blocked(){
        return this.account_blocked;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setTrade_suspended_by_user(boolean trade_suspended_by_user){
        this.trade_suspended_by_user = trade_suspended_by_user;
    }
    public boolean getTrade_suspended_by_user(){
        return this.trade_suspended_by_user;
    }
    public void setMultiplier(String multiplier){
        this.multiplier = multiplier;
    }
    public String getMultiplier(){
        return this.multiplier;
    }
    public void setShorting_enabled(boolean shorting_enabled){
        this.shorting_enabled = shorting_enabled;
    }
    public boolean getShorting_enabled(){
        return this.shorting_enabled;
    }
    public void setEquity(String equity){
        this.equity = equity;
    }
    public String getEquity(){
        return this.equity;
    }
    public void setLast_equity(String last_equity){
        this.last_equity = last_equity;
    }
    public String getLast_equity(){
        return this.last_equity;
    }
    public void setLong_market_value(String long_market_value){
        this.long_market_value = long_market_value;
    }
    public String getLong_market_value(){
        return this.long_market_value;
    }
    public void setShort_market_value(String short_market_value){
        this.short_market_value = short_market_value;
    }
    public String getShort_market_value(){
        return this.short_market_value;
    }
    public void setInitial_margin(String initial_margin){
        this.initial_margin = initial_margin;
    }
    public String getInitial_margin(){
        return this.initial_margin;
    }
    public void setMaintenance_margin(String maintenance_margin){
        this.maintenance_margin = maintenance_margin;
    }
    public String getMaintenance_margin(){
        return this.maintenance_margin;
    }
    public void setLast_maintenance_margin(String last_maintenance_margin){
        this.last_maintenance_margin = last_maintenance_margin;
    }
    public String getLast_maintenance_margin(){
        return this.last_maintenance_margin;
    }
    public void setSma(String sma){
        this.sma = sma;
    }
    public String getSma(){
        return this.sma;
    }
    public void setDaytrade_count(int daytrade_count){
        this.daytrade_count = daytrade_count;
    }
    public int getDaytrade_count(){
        return this.daytrade_count;
    }
    
    //======================
    
    public void doAPICall(){
    	Alpac ap = new Alpac();
       	Account acct;
    	acct = ap.getAcct();
    	System.out.println("ACCTNUM="+acct.getAccount_number());
    	System.out.println("BUY PWR="+acct.getBuying_power());
    	System.out.println("ACCT ID="+acct.getId());
    	
        // save Acct Obj to database   	
    	AlpacJpa.updateCurrent("account");    	   	
    	Session sess = AlpacJpa.beginTransaction();
  		sess.persist(acct);
  		AlpacJpa.commitTransaction(sess);
    	
    }
    
}
