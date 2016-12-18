package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientModel extends Database 
{
	
	public boolean insertClient(String name, String surname, long pesel, String city, String street)
	{
		try
		{
			String query = "INSERT INTO client VALUES (NULL, ?, ?, ?, ?, ?);";
			
			PreparedStatement insertStatement = conn.prepareStatement(query);
			insertStatement.setString(1, name);
			insertStatement.setString(2, surname);
			insertStatement.setLong(3, pesel);
			insertStatement.setString(4, city);
			insertStatement.setString(5, street);
			
			insertStatement.execute();
			insertStatement.close();
		}
		catch(SQLException e)
		{
			System.err.println("Cannot insert new client");
			return false;
		}
		return true;
	}
}
