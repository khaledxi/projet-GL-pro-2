package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDB {
	protected static Connection connect()
	{
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://Projet_GL.accdb");
			
			System.out.println("ok !!!");
			
			return con;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "problem connection !!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "problem connection !!");
		}
		return  null;
	}
}
