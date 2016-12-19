package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public boolean removeClient(int rowToDeleteId)
	{
		try
		{
			String query = "DELETE FROM client WHERE ? = ?;";
			
			PreparedStatement deleteStatement = conn.prepareStatement(query);
			deleteStatement.setString(1, "id_client");
			deleteStatement.setInt(2, rowToDeleteId);
			
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
	
	
	//STUB - GET DATA FROM DB, PUT INTO ARRAYLIST AND RETURN
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
	
}
