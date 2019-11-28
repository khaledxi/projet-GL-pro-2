package DataBase;

import java.io.File;

import Model.SondageM;
import Model.SondageM.choixS_M;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestDB {

	public static void main(String[] args) {//C:\\Users\\Khaled\\Documents\\project.jpg
		
		/*ObservableList<String> choix=FXCollections.observableArrayList();
		choix.addAll("test","khaled","moussa","rachid");
		SondageDB.ajouterSondage(0, "rahi temchi", choix);*/
		
		SondageM s=SondageDB.getAllSondage().get(2);
		SondageDB.getchoix(s);
		System.out.println(s.getId());
		for(choixS_M c:s.getChoix())
			System.out.println(c.getChoix()+"  "+c.getRst()+"  "+c.isSelect());
		//SondageDB.selectioner(1, 33);
	}

}
