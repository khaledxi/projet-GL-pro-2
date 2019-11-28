package Views;

import ControlFXML.messagesC;
import Model.ContactM;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ContactV extends ListCell<ContactM>implements EventHandler<MouseEvent> {

	private HBox box;
	private Label nom,id;
	private ImageView photo;
	private messagesC control;
	
	public ContactV(messagesC control) {
		// TODO Auto-generated constructor stub
		this.control=control;
		
		nom =new Label();
		
		id=new Label();
		id.setVisible(false);
		
		photo =new ImageView();
		photo.setFitHeight(25);
		photo.setFitWidth(25);
		
		box =new HBox();
		box.getChildren().addAll(photo,nom,id);
		box.setOnMouseClicked(this);
	}
	@Override
	protected void updateItem(ContactM cont, boolean arg1) {
		// TODO Auto-generated method stub
		super.updateItem(cont, arg1);
		
		
		
		if(cont !=null)
		{
			nom.setText(cont.getNom()+" "+cont.getPrenom());
			id.setText(""+cont.getID());
			
			if(cont.getPhoto() !=null)
				photo.setImage(cont.getPhoto());
			else
				photo.setImage(null);
			
			setGraphic(box);
		}else
			setGraphic(null);
	}
	
	
	/*************************** on mouse clicked **************/
	@Override
	public void handle(MouseEvent hbox) {
		// TODO Auto-generated method stub
		HBox box=(HBox)hbox.getSource();
		Label contactID=(Label)box.getChildren().get(2);
		//System.out.println(contactID.getText());
		control.setIDContact(Integer.parseInt(contactID.getText()));
	}

	
}
