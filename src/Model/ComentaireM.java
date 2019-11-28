package Model;

import java.sql.Date;

public class ComentaireM {
	private int ID,question,comentateur;
	private String contenue;
	private Date date;
	
	
	public ComentaireM(int iD, int question, int comentateur, String contenue, Date date) {
		super();
		ID = iD;
		this.question = question;
		this.comentateur = comentateur;
		this.contenue = contenue;
		this.date = date;
	}
	/*******************************************/
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
		this.question = question;
	}
	public int getComentateur() {
		return comentateur;
	}
	public void setComentateur(int comentateur) {
		this.comentateur = comentateur;
	}
	public String getContenue() {
		return contenue;
	}
	public void setContenue(String contenue) {
		this.contenue = contenue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
