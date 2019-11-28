package ControlFXML;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import DataBase.ForumDB;
import Model.ForumM;
import Views.ForumV;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class forumC implements Initializable{
	
	@FXML
	private JFXListView<ForumM> forumL;
	@FXML
	private JFXTextField nouvFQ,rech;

	
	private File photoQ;
	
	public static int IDUser=1;
	
	public static HashMap<Integer, ForumM> forum=new HashMap<Integer, ForumM>();
	/**************************** variable partie commentaire ***********************************************/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		photoQ=null;
		initForum();
	}
	public void actionAddPhoto(Event event)
	{
		photoQ=null;
		FileChooser fc=new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("image", "*.jpg","*.png"));
		photoQ=fc.showOpenDialog(null);
	}
	public void initForum()
	{
		ObservableList<ForumM> listTmp=ForumDB.getAllForum();
		for(ForumM f:listTmp)
			forum.put(f.getId(), f);
		
		
		forumL.getItems().addAll(forum.values());
		forumL.setCellFactory(parms -> new ForumV(this));
		
	}
	public void ajouterQ(Event event)
	{
		if(!nouvFQ.getText().equals(""))
		{
			ForumDB.addQ(IDUser,nouvFQ.getText(), photoQ);
			nouvFQ.setText("");
			photoQ=null;
			JOptionPane.showMessageDialog(null, "votre question a été publier !!");
			

			forum.clear();
			ObservableList<ForumM> listTmp=ForumDB.getAllForum();
			for(ForumM f:listTmp)
				forum.put(f.getId(), f);
			
			forumL.getItems().clear();
			forumL.getItems().addAll(forum.values());
		}
		else
			JOptionPane.showMessageDialog(null, "entrer la question !!");
	}
	public void recherchF(Event event)
	{
		forumL.getItems().clear(); 
		String r=rech.getText();
		if(r.equals(""))
		{
			forumL.getItems().addAll(forum.values());
		}
		else
		{
			for(ForumM f:forum.values())
			{
				if(f.getQuestion().contains(r))
				{
					forumL.getItems().add(f);
				}
			}
		}
	}
	public void mesForums(Event event)
	{
		ObservableList<ForumM> mesF=ForumDB.getForumUser(IDUser);
		forumL.getItems().clear();
		forumL.getItems().addAll(mesF);
	}
	
	/*********** user set and get ******************/
	public int getIDUser() {
		return IDUser;
	}
	public void setIDUser(int iDUser) {
		IDUser = iDUser;
	}
	
}
