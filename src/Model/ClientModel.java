package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Client;

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
	
	public boolean deleteClient(int rowToDeleteId)
	{
		try
		{
			String query = "DELETE FROM client WHERE id_client = ?;";
			
			PreparedStatement deleteStatement = conn.prepareStatement(query);
			deleteStatement.setInt(1, rowToDeleteId);
			
			deleteStatement.execute();
			deleteStatement.close();
		}
		catch(SQLException e)
		{
			System.err.println("Cannot delete row");
			return false;
		}
		return true;
	}
	
	public boolean updateClient(int id, String name, String surname, long pesel, String city, String street)
	{
		try
		{
			String query = "UPDATE client SET name = ?, surname = ?, pesel = ?, city = ?, street = ? WHERE id_client = ?;";
			
			PreparedStatement updateStatement = conn.prepareStatement(query);
			updateStatement.setString(1, name);
			updateStatement.setString(2, surname);
			updateStatement.setLong(3, pesel);
			updateStatement.setString(4, city);
			updateStatement.setString(5, street);
			updateStatement.setInt(6, id);
			
			updateStatement.execute();
			updateStatement.close();
		}
		catch(SQLException e)
		{
			System.err.println("Cannot update row");
			return false;
		}
		return true;
	}
	
	public ArrayList<Client> listClient()
	{
		ArrayList<Client> clientList = new ArrayList<Client>();
		
	    try 
	    {
	        ResultSet result = stat.executeQuery("SELECT * FROM client");
	        int id;
	        long pesel;
	        String name, surname, city, street;
	        while(result.next()) 
	        {
	            id = result.getInt("id_client");
	            name = result.getString("name");
	            surname = result.getString("surname");
	            pesel = result.getLong("pesel");
	            city = result.getString("city");
	            street = result.getString("street");
	            clientList.add(new Client(id, name, surname, pesel, city, street));
	        }
	        result.close();
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return null;
	    }
	    return clientList;
	}
	
	public Client findClient(long searchedPesel)
	{
		try
		{
			String selectQuery = "SELECT * FROM client WHERE pesel = ?;";
			
			PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
			selectStatement.setLong(1, searchedPesel);
			
			ResultSet result = selectStatement.executeQuery();
			
			int id;
	        long pesel;
	        String name, surname, city, street;
	        Client foundClient = null;
	        while(result.next()) 
	        {
	            id = result.getInt("id_client");
	            name = result.getString("name");
	            surname = result.getString("surname");
	            pesel = result.getLong("pesel");
	            city = result.getString("city");
	            street = result.getString("street");
	            foundClient = new Client(id, name, surname, pesel, city, street);
	            
	        }
	        return foundClient;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
