package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.ContactM;
import Model.messageM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import oracle.sql.DATE;

public class ContactDB extends ConnectionDB{
	
	public static ObservableList<ContactM> getContacts(int idUser)//rahi temchi
	{
		ObservableList<ContactM> arr=FXCollections.observableArrayList();
		
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("select ID,nom,prenom,photo from utilisateur where not ID=?");
			){
			pr.setInt(1, idUser);
			ResultSet rs=pr.executeQuery();
			
			while(rs.next())
			{
				arr.add(new ContactM(rs.getInt("ID"), rs.getString("nom"), rs.getString("prenom"),rs.getBinaryStream("photo")));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "problem contact !!");
		}
		
		return arr;
	}
	public static ObservableList<messageM> getDescution(int IDUser ,int IDContact)//rahi temchi
	{
		ObservableList<messageM> list=FXCollections.observableArrayList();
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("select emetteur,dateE,contenu,recepteur from msg where (emetteur =? and recepteur=?)or(emetteur =? and recepteur=?)");
			){
			
			pr.setInt(1, IDUser);
			pr.setInt(2, IDContact);
			pr.setInt(3, IDContact);
			pr.setInt(4, IDUser);
			
			ResultSet rs=pr.executeQuery();
			while(rs.next())
				list.add(new messageM(rs.getInt("emetteur"), rs.getInt("recepteur"), rs.getDate("dateE"), rs.getString("contenu")));
			
			//System.out.println("ok !!! size =" +list.size());
			return list;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "erreur load messages");
		}
		return null;
	}
	public static void envoiMSG(int IDUser,int IDcontact,String contenue)//rahi temchi
	{
		Connection con=connect();
		try {
			PreparedStatement pr=con.prepareStatement("insert into msg(emetteur,dateE,contenu,recepteur)values(?,?,?,?)");
			
			pr.setInt(1, IDUser);
			pr.setDate(2,new Date(new java.util.Date().getTime()));
			pr.setString(3, contenue);
			pr.setInt(4, IDcontact);
			
			pr.execute();
			
			System.out.println("ok !!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "erreur envoi !!!");
		}
	}
}
