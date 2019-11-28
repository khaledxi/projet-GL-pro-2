package ControlFXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import DataBase.SondageDB;
import Model.SondageM;
import Model.SondageM.SondageV;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SondageC implements Initializable{

	@FXML
	JFXListView<SondageM> LVsondages;
	@FXML
	JFXTextField TFrecherche;
	
	ObservableList<SondageM> sondages;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		sondages=SondageDB.getAllSondage();
		LVsondages.getItems().addAll(sondages);
		LVsondages.setCellFactory(par->new SondageV());
	}
	
	
	public void ajouterS(Event event)
	{
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ControlFXML/new_S.fxml"));
			Scene scene=new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void recherch(Event event)
	{
		String t=TFrecherche.getText();
		LVsondages.getItems().clear();
		if(t.equals(""))
		{
			LVsondages.getItems().addAll(sondages);
		}else
		{
			for(SondageM s:sondages)
			{
				if(s.getQuestion().contains(t))
					LVsondages.getItems().add(s);
			}
		}
	}
}
