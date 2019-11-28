package ControlFXML;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import Model.SondageM;
import Model.SondageM.choixS_M;
import Model.SondageM.loadS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ResultatSC implements Initializable{

	@FXML
	JFXListView<choixS_M> LVresult;
	@FXML
	Label titre,choixP,nbrPr;
	
	public static float sum,idmax;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		calculeStat();
		System.out.println("sum          "+sum);
		System.out.println("resultat choix "+loadS.sondage.getChoix().size());
		LVresult.getItems().addAll(loadS.sondage.getChoix());
		LVresult.setCellFactory(parm ->new resultatV());
		
		initAutre();
	}
	public void calculeStat()
	{
		sum=0;
		idmax=0;
		int resM=0;
		for(choixS_M ch:loadS.sondage.getChoix())
		{
			sum+=ch.getRst();
			if(ch.getRst()>resM)
			{
				resM=ch.getRst();
				idmax=ch.getId();
			}
		}
		//calcule
		int x=loadS.sondage.getChoix().size();
		for(int i=0;i<x;i++)
		{
			choixS_M s=loadS.sondage.getChoix().get(i);
			s.setPorsuntage((s.getRst()/sum)*100);
		}
	}
	public void initAutre()
	{
		titre.setText(loadS.sondage.getQuestion());
		nbrPr.setText((int)sum+"");
		for(choixS_M ch:loadS.sondage.getChoix())
		{
			if(ch.getId()==idmax)
			{
				choixP.setText(ch.getChoix()+"     "+ch.getPorsuntage()+"%");
			}
		}
	}
	/**************** class view resultat **********************/
	public static class resultatV extends ListCell<choixS_M> {

		Label Lch,Lporsunatge;
		HBox box;
		
		public resultatV() {
			
			Font f=new Font("Consolas",16);
			
			Lch=new Label();
			Lch.setMinWidth(250);
			Lch.setMinHeight(40);
			Lch.setAlignment(Pos.CENTER_LEFT);
			Lch.setFont(f);
			
			Lporsunatge =new Label();
			Lporsunatge.setMinHeight(40);
			Lporsunatge.setMinWidth(150);
			Lporsunatge.setAlignment(Pos.CENTER_RIGHT);
			Lporsunatge.setFont(f);
			
			box=new HBox();
			box.setSpacing(15);
			box.setPadding(new Insets(15));
			box.setAlignment(Pos.CENTER_LEFT);
			
			box.getChildren().addAll(Lch,Lporsunatge);
		}
		
		@Override
		protected void updateItem(choixS_M ch, boolean arg1) {
			// TODO Auto-generated method stub
			super.updateItem(ch, arg1);
			if(ch!=null)
			{
				System.out.println("ok !! view ");
				Lch.setText(ch.getChoix());
				Lporsunatge.setText((int)ch.getPorsuntage()+"%");
				setGraphic(box);
			}else
				setGraphic(null);
		}
		
	}
}
