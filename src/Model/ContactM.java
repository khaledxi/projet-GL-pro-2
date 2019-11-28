package Model;

import java.io.InputStream;
import java.io.OutputStream;

import javafx.scene.image.Image;

public class ContactM {
	private int ID;
	private String nom,prenom;
	private Image photo;
	
	
	public ContactM(int iD, String nom, String prenom, InputStream photo) {
		super();
		ID = iD;
		this.nom = nom;
		this.prenom = prenom;
		if(photo != null)
			this.photo = new Image(photo);
		else
			this.photo=null;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Image getPhoto() {
		return photo;
	}


	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
	
}
