package ControlFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import DataBase.SondageDB;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class new_SC implements Initializable{

	@FXML
	private JFXListView<String> LVChoix;
	@FXML
	private JFXTextField TFsondage,TFchoix;
	@FXML
	private JFXButton Bsupp;
	
	
	private int IDuser=1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void setVisible(Event event)
	{
		Bsupp.setVisible(true);
	}
	public void ajouterSondage(Event event)
	{
		if(TFsondage.getText().equals("")|| LVChoix.getItems().size()==0)
		{
			JOptionPane.showMessageDialog(null, "erreur  !!");
		}
		else 
		{
			SondageDB.ajouterSondage(IDuser, TFsondage.getText(), LVChoix.getItems());
			JOptionPane.showMessageDialog(null, "le sondage a été ajouté !!");
			Node node=(Node)event.getSource();
			Stage s=(Stage)node.getScene().getWindow();
			s.close();
		}
	}
	public void ajouterItem(Event event)
	{
		if(!TFchoix.getText().equals(""))
			LVChoix.getItems().add(TFchoix.getText());
	}
	public void suppItem(Event event)
	{
		LVChoix.getItems().remove(LVChoix.getSelectionModel().getSelectedIndex());
	}
}
