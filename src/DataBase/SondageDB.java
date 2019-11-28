package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ControlFXML.forumC;
import Model.SondageM;
import Model.SondageM.choixS_M;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SondageDB extends ConnectionDB{

	public static void ajouterSondage(int createur,String question,ObservableList<String> choix)//rahi temchi
	{
		String sql1="insert into sondage(contenuS,createur) values(?,?)";
		String sql2="insert into choix (contenu,sondage) values(?,?)";
		int idSondage=0;
		
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement(sql1,PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement pr_choix=con.prepareStatement(sql2);
				){
			pr.setString(1, question);
			pr.setInt(2, createur);
			
			pr.execute();
			ResultSet rs=pr.getGeneratedKeys();
			
			if(rs.next())
				idSondage=rs.getInt(1);
			if(idSondage !=0)
			{
				// remplire les choix
				pr_choix.setInt(2, idSondage);
				for(String c:choix)
				{
					pr_choix.setString(1, c);
					pr_choix.addBatch();
				}
				pr_choix.executeBatch();
				
				System.out.println("ok!!!");
			}
			else
				System.out.println("erreur get key!!!");
				
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public static void selectioner(int user,int choix)
	{
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("insert into choixE(etudiant,choix) values(?,?)");
				){
			
			pr.setInt(1, user);
			pr.setInt(2, choix);
			
			pr.execute();
			System.out.println("ok !!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public static void selectioner(int user,int choixP,int choixN)
	{
		if(choixP!=choixN)
		{
			try (
					Connection con=connect();
					PreparedStatement pr_d=con.prepareStatement("delete from choixE where etudiant=? and choix=?");
					PreparedStatement pr_test=con.prepareStatement("select * from choixE where etudiant=? and choix=?");
					PreparedStatement pr_i=con.prepareStatement("insert into choixE(etudiant,choix) values(?,?)");
					){
				
				pr_d.setInt(1, user);
				pr_d.setInt(2, choixP);
				
				pr_d.execute();
				
				pr_test.setInt(1, user);
				pr_test.setInt(2, choixN);
				ResultSet rs=pr_test.executeQuery();
				if(! rs.next())
				{
					pr_i.setInt(1, user);
					pr_i.setInt(2, choixN);
					
					pr_i.execute();
				}
				
				
				System.out.println("delete");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		else
			System.out.println("rahoum kif kif !!!");
	}
	public static ObservableList<SondageM> getAllSondage()//rahi temchi
	{
		ObservableList<SondageM> arr=FXCollections.observableArrayList();
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("select ID,contenuS,resultatS,createur from sondage");
				){
			ResultSet rs=pr.executeQuery();
			while(rs.next())
			{
				arr.add(new SondageM(rs.getInt("ID"), rs.getInt("createur"), rs.getString("contenuS"),FXCollections.observableArrayList(),rs.getString("resultatS")));
			}
			
			System.out.println(arr.size());
			return arr;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
	public static void getchoix(SondageM sondage)// problem hnaya!!
	{
		ObservableList<choixS_M> arr=FXCollections.observableArrayList();
		try (
				Connection con=connect();
				PreparedStatement pr=con.prepareStatement("select choix.ID,choix.contenu from sondage,choix where sondage.ID=choix.sondage and sondage.ID=? ");                        
				PreparedStatement pr_nbr=con.prepareStatement("select count(*) from choixE where choixE.choix =?");
				PreparedStatement pr_selected=con.prepareStatement("select * from choixE where choix=? and etudiant=?");
				){
			
			pr.setInt(1, sondage.getId());
			ResultSet rs=pr.executeQuery();
			while(rs.next())
			{
				System.out.println("ok");
				arr.add(new SondageM.choixS_M(rs.getInt(1),rs.getString(2), 0,false));
			}
			//set nbr participation pour chaque choix
			for(int i=0;i<arr.size();i++)
			{
				pr_nbr.setInt(1, arr.get(i).getId());
				ResultSet rs_N=pr_nbr.executeQuery();
				if(rs_N.next())
					arr.get(i).setRst(rs_N.getInt(1));
			}
			// set la bon repance
			
			pr_selected.setInt(2, forumC.IDUser);
			for(int i=0;i<arr.size();i++)
			{
				pr_selected.setInt(1, arr.get(i).getId());
				ResultSet rs_S=pr_selected.executeQuery();
				arr.get(i).setSelect(rs_S.next());
			}
			sondage.getChoix().addAll(arr);
			//System.out.println(sondage.getChoix().size());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
