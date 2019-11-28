package Model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import ControlFXML.ResultatSC;
import ControlFXML.SondageC;
import ControlFXML.forumC;
import ControlFXML.pert_sondC;
import DataBase.SondageDB;
import Model.SondageM.choixS_M;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SondageM {
	private int id,createur;
	private String question,resultat; 
	private ObservableList<choixS_M> choix;
	
	
	/********* constructeur  ***************/ 
	public SondageM(int id, int createur, String question, ObservableList<choixS_M> choix,String resultat) {
		super();
		this.id = id;
		this.createur = createur;
		this.question = question;
		if(choix!=null)
			this.choix = choix;
		else
			this.choix=FXCollections.observableArrayList();
		
		this.setResultat(resultat);
	}
 
	/*              set and get                   */
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
	public ObservableList<choixS_M> getChoix() {
		return choix;
	}
	public void setChoix(ObservableList<choixS_M> choix) {
		this.choix = choix;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	/*********************************************************************************************************/
	
	/*************************** class model d'un choix ****************************/
	public static class choixS_M {// oumba3d nzidlou nbr des choix vote w ida vota 3lih ssiyed wela lala 
		private String choix;
		private int resultat,id;
		private boolean select;
		private float porsuntage;
		/*************** constructeur *****************/
		public choixS_M(int id,String choix,int rst,boolean select) {
			// TODO Auto-generated constructor stub
			this.choix=choix;
			this.resultat=rst;
			this.id=id;
			this.select=select;
		}
		/********** set and get ********************/ 
		public String getChoix() {
			return choix;
		}
		public void setChoix(String choix) {
			this.choix = choix;
		}
		public int getRst() {
			return resultat;
		}
		public void setRst(int rst) {
			this.resultat = rst;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public boolean isSelect() {
			return select;
		}
		public void setSelect(boolean select) {
			this.select = select;
		}
		
		public float getPorsuntage() {
			return porsuntage;
		}
		public void setPorsuntage(float porsuntage) {
			this.porsuntage = porsuntage;
		}
		
	}
	/******************** view d'un row de sondage *************************/
	public static class SondageV extends ListCell<SondageM>{
		HBox box;
		Label text,id;
		ImageView image;
		public SondageV() {
			id=new Label();
			id.setVisible(false);
			
			image=new ImageView();
			image.setFitHeight(50);
			image.setFitWidth(50);
			try {
				image.setImage(new Image(new FileInputStream("./asset/question2.PNG")));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "problem image");
			}
			
			text=new Label();
			Font font=new Font("Arial",18);
			text.setFont(font);
			text.setMinHeight(50);
			text.setPadding(new Insets(0, 0, 0, 40));
			
			box=new HBox();
			box.setMinHeight(50);
			box.setPadding(new Insets(5, 5, 5, 40));
			
			box.getChildren().addAll(image,text,id);
			box.setOnMouseClicked(new loadS());
		}
		@Override
		protected void updateItem(SondageM sondage, boolean arg1) {
			// TODO Auto-generated method stub
			super.updateItem(sondage, arg1);
			if(sondage!=null)
			{
				id.setText(sondage.getId()+"");
				text.setText(sondage.getQuestion());
				setGraphic(box);
			}
			else
				setGraphic(null);
		}
	}
	/*************** event de load les sondages ************************/
	public static class loadS implements EventHandler<MouseEvent>{

		public static SondageM sondage;
		@Override
		public void handle(MouseEvent event) {
			try {
				initSondage(event);
				Parent test =FXMLLoader.load(getClass().getResource("/ControlFXML/part_sondage.fxml"));
				Scene scene=new Scene(test);
				Stage stage=new Stage();
				System.out.println("load");
				stage.setScene(scene);
				stage.show();
				stage.setResizable(false);
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "problem load feneter !!!");
			}
		}
		
		private void initSondage(MouseEvent event) {
			HBox b=(HBox)event.getSource();
			Label id=(Label)b.getChildren().get(2),tx=(Label)b.getChildren().get(1);
			sondage=new SondageM(Integer.parseInt(id.getText()),0,tx.getText(),null,null);
			SondageDB.getchoix(sondage);
		}
	}
	/******************* view list des choix ****************************/
	public static class listChoixView extends ListCell<choixS_M> {

		Label choix,id;
		HBox box;
		RadioButton rb;
		Font font;
		// group button lazem ndekhlou comm parametre !!
		public listChoixView(ToggleGroup tgg) {
			id=new Label();
			id.setVisible(false);
			
			rb=new RadioButton();
			rb.setOnAction(new participer());
			rb.setToggleGroup(tgg);
			//tgl.getChildrenUnmodifiable().add(rb);
			choix=new Label();
			choix.setPrefSize(300,50);
			
			font=new Font("Gill Sans MT",16);
			choix.setFont(font);
			
			box=new HBox();
			box.setAlignment(Pos.CENTER_LEFT);
			box.setPrefWidth(50);
			box.setSpacing(20);
			box.getChildren().addAll(choix,rb,id);
		}
		@Override
		protected void updateItem(choixS_M ch, boolean arg1) {
			// TODO Auto-generated method stub
			super.updateItem(ch, arg1);
			if(ch!=null)
			{
				pert_sondC.sum_P+=ch.getRst();
				
				if(ch.isSelect())
				{
					pert_sondC.Lchoix=ch.getId();
					rb.setSelected(true);
				}
				if(ch.isSelect())
					pert_sondC.Lchoix=ch.getId();
				
				id.setText(ch.getId()+"");
				choix.setText(ch.choix);
				setGraphic(box);
			}else
				setGraphic(null);
		}
	}
	/*****************event de participation ******************************/
	public static class participer implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Node n=(Node)event.getSource();
			HBox box=(HBox)n.getParent();
			
			Label l=(Label)box.getChildren().get(2);
			System.out.println(l.getText());
			pert_sondC.Nchoix=Integer.parseInt(l.getText());
			//SondageDB.selectioner(user, choix);
		}
		
	}
}
