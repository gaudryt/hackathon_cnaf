package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceBd {
	
	private ResultSet envoiRequete(String requete) throws ClassNotFoundException, SQLException{
		ResultSet resultats = null;
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		   "jdbc:postgresql://172.0.0.1:5432/dbname","hackathon", "sopra");
		try {
			
			Statement stmt = connection.createStatement();
			resultats = stmt.executeQuery(requete);
		} catch (SQLException e) {
			//traitement de l'exception
		}
		connection.close();
		return resultats;
	}

}
