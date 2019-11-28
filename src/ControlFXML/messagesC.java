package ControlFXML;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import DataBase.ContactDB;
import Model.ContactM;
import Model.messageM;
import Views.ContactV;
import Views.chatV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class messagesC implements Initializable{

	@FXML
	JFXListView<ContactM> cont;
	@FXML
	JFXListView<messageM> chat;
	@FXML
	Label Ncont;
	@FXML
	JFXTextField msg,rech;
	
	public int IDUser=2,IDContact=-1;
	public HashMap<Integer, ContactM> mapC;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<ContactM> contacts=ContactDB.getContacts(-1);
		mapC=new HashMap<Integer, ContactM>();
		
		for(ContactM c:contacts)
			mapC.put(c.getID(), c);
		
		contacts.clear();
		
		initContacts();
		loadChat();
	}
	
	public void actionA(Event event)
	{
		
	}

	public void initContacts()
	{
		// ymedlou le conntenu mel base de donnee
		cont.getItems().addAll(mapC.values());
		
		// ykhiyerlou cell ta3 l view ta3 al conntact 
		cont.setCellFactory(parms -> new ContactV(this));
	}
	
	public void loadChat()//rahi temchi
	{
		chat.setCellFactory(par -> new chatV(this));
		System.out.println("load chat");
	}

	public void setIDContact(int idc) {
		System.out.println("message C");
		IDContact = idc;
		Ncont.setText(mapC.get(idc).getNom());
		
		ObservableList<messageM> listM=ContactDB.getDescution(IDUser, IDContact);
		
		chat.setItems(listM);
		loadChat();
	}
	
	
	public int getIDContact() {
		return IDContact;
	}
	/***************************************************************/
	public void envoiMSG(Event evnet)
	{
		if(msg.getText()!=""&&msg.getText()!=null)
		{
			ContactDB.envoiMSG(IDUser, IDContact, msg.getText());
			chat.getItems().add(new messageM(IDUser, IDContact,new java.sql.Date(new Date().getTime()), msg.getText()));
		}else
			JOptionPane.showMessageDialog(null, "erreur !!");
	}
	/*********************************************************************/
	public void recherchC(Event event)
	{
		
		String text=rech.getText();
		System.out.println("recherch !!."+text+".");
		cont.getItems().clear();
		if(text !=null && !text.equals(""))
		{
			System.out.println("remplire");
			cont.getItems().clear();//???
			for(ContactM c:mapC.values())
			{
				if(c.getNom().contains(text)|| c.getPrenom().contains(text))
					cont.getItems().add(c);
			}
		}else
		{
			System.out.println("init");
			cont.getItems().addAll(mapC.values());
		}
	}
}
