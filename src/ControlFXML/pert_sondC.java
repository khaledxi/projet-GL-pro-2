package ControlFXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXListView;

import DataBase.SondageDB;
import Model.SondageM;
import Model.SondageM.SondageV;
import Model.SondageM.choixS_M;
import Model.SondageM.loadS;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import Model.SondageM.listChoixView;

public class pert_sondC implements Initializable{

	@FXML
	private Label Ltext;
	@FXML
	private JFXListView<choixS_M> LVchoix;
	
	
	private ToggleGroup tgg;
	public static int Lchoix=0,Nchoix=0;
	public static float sum_P=0f;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Ltext.setText(loadS.sondage.getQuestion());
		LVchoix.getItems().addAll(loadS.sondage.getChoix());
		System.out.println(loadS.sondage.getChoix().size());
		tgg=new ToggleGroup();
		LVchoix.setCellFactory(par->new listChoixView(tgg));
	}
	public void participer(Event event)
	{
		if(Lchoix !=Nchoix)
		{
			SondageDB.selectioner(forumC.IDUser, Lchoix,Nchoix);
			System.out.println("participer et ajouter "+ Lchoix +"   "+ Nchoix);
		}
		
		//sum_P=0;***********************************************************
		Node n=(Node)event.getSource();
		Stage win=(Stage)n.getScene().getWindow();
		win.close();
	}
	public void resultat(Event event)
	{
		//loadS.sondage
		//participer(event);
		//SondageDB.getchoix(loadS.sondage);
		Stage s=new Stage();
		try {
			s.setScene(new Scene(FXMLLoader.load(getClass().getResource("/ControlFXML/resultatS.fxml"))));
			s.show();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "erreur !!");
		}
	}
}
