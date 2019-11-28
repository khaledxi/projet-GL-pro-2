package Views;

import ControlFXML.messagesC;
import Model.ContactM;
import Model.messageM;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class chatV extends ListCell<messageM>{

	private messagesC control;
	
	
	private HBox box;
	private Label contenue,date;
	private ImageView photo;
	private Image photoA,photoB;
	
	public chatV(messagesC control)
	{
		//save les parametres 
		this.control=control;
		// initializer le adapter
		
		photo=new ImageView();
		photo.setFitHeight(30);
		photo.setFitWidth(30);
		
		contenue=new Label();
		contenue.setWrapText(true);
		contenue.setMaxWidth(250);
		
		date=new Label();
		date.setWrapText(true);
		
		photoA=control.mapC.get(control.IDUser).getPhoto();
		//photoA=null;
		photoB=control.mapC.get(control.IDContact).getPhoto();
		
		box=new HBox();
		box.setSpacing(8);
		box.getChildren().addAll(photo,contenue,date);
	}
	
	@Override
	protected void updateItem(messageM msg, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(msg, empty);
		if(msg!=null)
		{
			//remplire le contenue de messages
			if(msg.getContenue()!=null)
				contenue.setText(msg.getContenue());
			else
				contenue.setText("    ");
			
			//remplire la date de message
			if(msg.getDate()!=null)
				date.setText("  '"+msg.getDate()+"'  ");
			else
				date.setText("            ");
			
			
			// oriontation de hbox + ajouter l'image
			if(msg.getEmeteur()==control.IDUser)
			{
				box.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				if(photoA!=null)
					photo.setImage(photoA);
				else
					photo.setImage(null);
			}else
			{
				box.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
				
				if(photoB!=null)
					photo.setImage(photoB);
				else
					photo.setImage(null);
			}
				
			
			// affichier le hbox
			setGraphic(box);
		}else
			setGraphic(null);
	}
	
}
