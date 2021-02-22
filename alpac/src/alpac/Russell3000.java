package alpac;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "russell3000", schema = "trade")
//============================
public class Russell3000
{
//	  `id` int(11) NOT NULL AUTO_INCREMENT,
//	  `tkr` varchar(16) NOT NULL,
//	  `exchange` varchar(32) DEFAULT NULL,
//	  `name` varchar(128) NOT NULL,
//	  `sector` varchar(500) DEFAULT NULL,
//	  `industry` varchar(500) DEFAULT NULL,
	  
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "tkr", nullable=false)
	private String tkr;
	
	@Column(name = "exchange", nullable=true)
	private String exchange;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "sector", nullable=true)
	private String sector;
	
	@Column(name = "industry", nullable=true)
	private String industry;


	public Russell3000() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTkr() {
		return tkr;
	}

	public void setTkr(String tkr) {
		this.tkr = tkr;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
}
