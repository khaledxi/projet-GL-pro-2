package Model;

import java.sql.Date;

public class messageM {
	private int emeteur,recepteur;
	private Date date;
	private String contenue;
	public messageM(int emeteur, int recepteur, Date date, String contenue) {
		super();
		this.emeteur = emeteur;
		this.recepteur = recepteur;
		this.date = date;
		this.contenue = contenue;
	}
	
	/**************************************************/
	public int getEmeteur() {
		return emeteur;
	}
	public void setEmeteur(int emeteur) {
		this.emeteur = emeteur;
	}
	public int getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(int recepteur) {
		this.recepteur = recepteur;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContenue() {
		return contenue;
	}
	public void setContenue(String contenue) {
		this.contenue = contenue;
	}
	
	
}
