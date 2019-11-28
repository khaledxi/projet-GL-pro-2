package DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.ForumM;
import Model.ComentaireM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class ForumDB extends ConnectionDB {

	public static void addQ(int IDcreateur, String question)//rahi temchi
	{

		try (Connection con = connect();
				PreparedStatement pr = con
						.prepareStatement("insert into questionF(createur,contenu,dateP) values(?,?,?)");) {

			pr.setInt(1, IDcreateur);
			pr.setString(2, question);
			pr.setDate(3, new Date(new java.util.Date().getTime()));
			/*
			 * if(photo!=null) pr.setBinaryStream(4,new FileInputStream(photo));
			 */

			pr.execute(); 
			System.out.println("ok !!c");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "erreur ajoute forum !!");
		}
	}
	public static void addQ(int IDcreateur, String question, File photo) // rahi temchi
	{

		if (photo == null)
			addQ(IDcreateur, question);
		else {
			try (
					Connection con = connect();
					PreparedStatement pr = con.prepareStatement("insert into questionF(createur,contenu,dateP,photo) values(?,?,?,?)");
				) {

				pr.setInt(1, IDcreateur);
				pr.setString(2, question);
				pr.setDate(3, new Date(new java.util.Date().getTime()));
				pr.setBinaryStream(4, new FileInputStream(photo));

				pr.execute();
				System.out.println("ok !!c");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "erreur ajoute forum !!");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "erreur ajoute photo forum !!");
			}
		}
	}
	public static void reponderQ(int IDUser ,int question,String contenue)//rahi temchi
	{
		
		try (
			Connection con=connect();
			PreparedStatement pr=con.prepareStatement("insert into comntF(comentateur,questionF,contenu,dateC) values (?,?,?,?)");
			){
			
			pr.setInt(1,IDUser);
			pr.setInt(2, question);
			pr.setString(3, contenue);
			pr.setDate(4,new Date(new java.util.Date().getTime()));
			
			pr.execute();
			System.out.println("ok !!!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "problem commentaire !!");
		}
	}
	public static ObservableList<ForumM> getForumUser(int IDUser)//rahi temchi
	{
		ObservableList<ForumM> arr=FXCollections.observableArrayList();
		
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("select ID,createur,contenu,dateP,photo from questionF where createur=?");
				){
			pr.setInt(1, IDUser);
			
			ResultSet rs=pr.executeQuery();
			
			while(rs.next())
				arr.add(new ForumM(rs.getInt("ID"),rs.getInt("createur"), rs.getString("contenu"), rs.getDate("dateP"), rs.getBinaryStream("photo")));
			
			System.out.println("ok !!! size="+arr.size());
			return arr;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "erreur load forum");
		}
		return null;
	}
	public static ObservableList<ForumM> getAllForum()//rahi temchi
	{
			ObservableList<ForumM> arr=FXCollections.observableArrayList();
		
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("select ID,createur,contenu,dateP,photo from questionF");
				){
			
			ResultSet rs=pr.executeQuery();
			
			while(rs.next())
				arr.add(new ForumM(rs.getInt("ID"),rs.getInt("createur"), rs.getString("contenu"), rs.getDate("dateP"), rs.getBinaryStream("photo")));
			
			System.out.println("ok !!! size="+arr.size());
			return arr;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "erreur load forum");
		}
		return null;
	}
	public static ObservableList<ComentaireM> comentaireF(int question)//rahi temchi
	{
		ObservableList<ComentaireM> arr=FXCollections.observableArrayList();
		
		try (
			Connection con=connect();
			PreparedStatement pr=con.prepareStatement("select ID,contenu,questionF,comentateur,dateC from comntF where questionF=?");
			){
			pr.setInt(1, question);
			
			ResultSet rs=pr.executeQuery();
			while(rs.next())
				arr.add(new ComentaireM(rs.getInt("ID"), rs.getInt("questionF"), rs.getInt("comentateur"), rs.getString("contenu"), rs.getDate("dateC")));
			
			System.out.println("ok !!! size ="+arr.size());
			return arr;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "problem load commentaires !!");
		}
		return null;
	}
}
