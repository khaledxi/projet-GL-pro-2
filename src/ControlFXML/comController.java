package ControlFXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import DataBase.ForumDB;
import Model.ComentaireM;
import Model.ForumM;
import Views.ForumV;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class comController implements Initializable{
	
	private ForumM Qest;
	
	@FXML
	JFXListView<ComentaireM> listCom;
	@FXML
	Label question;
	@FXML
	JFXTextField com;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Qest=ForumV.F_selected;
		question.setText(Qest.getQuestion());
		initList();
		listCom.setCellFactory(parm -> new comV());
	}
	
	public void actionE(Event event)
	{
		System.out.println();
	}
	private void initList()
	{	
		//System.out.println("test 1" +question.getText()+"  "+Qest);
		ObservableList<ComentaireM> list=ForumDB.comentaireF(Qest.getId());
		listCom.getItems().clear();
		listCom.getItems().addAll(list);
		com.setText("");
	}
	
	public  void commenter()
	{
		if(!com.getText().equals(""))
		{
			ForumDB.reponderQ(forumC.IDUser,Qest.getId(),com.getText());
			initList();
		}
	}
	/********** cell list *******************/
	public class comV extends ListCell<ComentaireM>{

		Label rep;
		ImageView image;
		HBox box;
		public comV() {
			rep=new Label();
			rep.setMaxWidth(400);
			rep.setWrapText(true);
			
			Font font=new Font("Arial",14);
			rep.setFont(font);
			
			Image img=null;
			try {
				img=new Image(new FileInputStream(new File("./asset/user.PNG")));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "problem image");
			}
			image =new ImageView(img);
			image.setFitHeight(30);
			image.setFitWidth(30);
			
			box=new HBox();
			box.setMinHeight(30);
			box.setSpacing(15);
			box.getChildren().addAll(image,rep);
		}
		@Override
		protected void updateItem(ComentaireM com, boolean arg1) {
			// TODO Auto-generated method stub
			super.updateItem(com, arg1);
			
			if(com!=null)
			{
				rep.setText(com.getContenue());
				setGraphic(box);
			}
			else
				setGraphic(null);
		}
}
	
}