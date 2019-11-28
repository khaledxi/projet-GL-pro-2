package Model;

import java.io.InputStream;
import java.sql.Date;

import javafx.scene.image.Image;

public class ForumM {
	private int id,createur;
	private String question;
	private Date date;
	private Image photo;
	
	
	public ForumM(int id, int createur, String question, Date date, InputStream photo) {
		super();
		this.id = id;
		this.createur = createur;
		this.question = question;
		this.date = date;
		
		if(photo!=null)
			this.photo = new Image(photo);
		else
			this.photo=null;
	}
	public ForumM(int id, int createur, String question, Date date, Image photo) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.createur = createur;
		this.question = question;
		this.date = date;
		this.photo=photo;
	}
	/***********************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreateur() {
		return createur;
	}
	public void setCreateur(int createur) {
		this.createur = createur;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
	
}
