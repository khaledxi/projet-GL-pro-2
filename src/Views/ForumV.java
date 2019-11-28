package Views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import ControlFXML.forumC;
import Model.ForumM;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ForumV extends ListCell<ForumM> implements EventHandler<MouseEvent>{
	public forumC control;
	
	private Parent root;
	private HBox box;
	private Label question,date,numF;
	private ImageView photoV;
	private Image image;
	
	public static ForumM F_selected;
	
	/************ initialization *************/
	public ForumV(forumC control)
	{
		this.control=control;
		
		question=new Label();
		question.setMinHeight(50);
		question.setMinWidth(250);
		question.setMaxWidth(250);
		question.setWrapText(true);
		
		Font font=new Font("Arial",16);
		question.setFont(font);
		
		numF=new Label();
		//numF.setVisible(false);
		numF.setPrefWidth(100);
		
		date=new Label();
		
		photoV=new ImageView();
		photoV.setFitHeight(50);
		photoV.setFitWidth(50);
		
		try {
			image=new Image(new FileInputStream(new File("./asset/question.png")));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "erreur !!");
		}
		
		box=new HBox();
		//box.setMinHeight(80);
		box.setSpacing(15);
		box.setOnMouseClicked(this);
		
		box.getChildren().addAll(photoV,question,numF,date);
	}
	/****************** pratie affichage ***********************/
	@Override
	protected void updateItem(ForumM frm, boolean arg1) {
		// TODO Auto-generated method stub
		super.updateItem(frm, arg1);
		
		if(frm!=null)
		{
			if(frm.getPhoto()== null)
			{
				photoV.setImage(image);
			}else
			{
				photoV.setImage(frm.getPhoto());
			}
			
			//affiche date
			if(frm.getDate()==null)
			{
				date.setText("");
			}else
			{
				date.setText(frm.getDate().toString());
			}
			numF.setText(frm.getId()+"");
			question.setText(frm.getQuestion());
			setGraphic(box);
		}
		else
		{
			setGraphic(null);
		}
	}
	/******************** event handelar ta3 l hbox**********************************/
	// normalement y affichier une nouveul fenetre fiha les comnetaires ta3 al forum
	@Override
	public void handle(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		try {
			HBox box=(HBox) arg0.getSource();
			Label l=(Label)(box.getChildren().get(2)),q=(Label)(box.getChildren().get(1));
			ImageView img=(ImageView)(box.getChildren().get(0));
			F_selected=new ForumM(Integer.parseInt(l.getText()), 0, q.getText(), null, img.getImage());
			// affichage fenet
			root = FXMLLoader.load(getClass().getResource("/ControlFXML/comForum.fxml"));
			Scene scene=new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "problem affichage");
		}
		
	}
	
	
}
