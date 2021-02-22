package alpac;

public class Quote {

    private String status;
    private String symbol;
    private Last last;
    

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
    public void setLast(Last last){
        this.last = last;
    }
    public Last getLast(){
        return this.last;
    }
			
}