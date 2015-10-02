package entites;

import java.util.Date;

public class Evenement {

	private Long id;
	
	private String infos;
	
	private String infosCachees;
	
	private String type;
	
	private Date date;

	public Evenement(Long id, String infos, String infosCachees, String type, Date date) {
		super();
		this.id = id;
		this.infos = infos;
		this.infosCachees = infosCachees;
		this.type = type;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public String getInfosCachees() {
		return infosCachees;
	}

	public void setInfosCachees(String infosCachees) {
		this.infosCachees = infosCachees;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString(){
		return this.getId()+";"+this.getType()+";"+this.getInfos()+";"+this.getInfosCachees()+";"+this.getDate().getDay()+"/"+this.getDate().getMonth()+"/"+this.getDate().getYear();
	}
}
