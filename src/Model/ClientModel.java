package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Client;

public class ClientModel extends Database 
{
	
	public boolean insertClient(Client client)
	{
		String name = client.getName();
		String surname = client.getSurname();
		long pesel = client.getPesel();
		String city = client.getCity();
		String street = client.getStreet();
		
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
	
	public boolean deleteClient(Client client)
	{
		try
		{
			String query = "DELETE FROM client WHERE id_client = ?;";
			
			PreparedStatement deleteStatement = conn.prepareStatement(query);
			int clientId = client.getId();
			deleteStatement.setInt(1, clientId);
			
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
	
	public boolean updateClient(Client client)
	{
		int id = client.getId();
		String name = client.getName();
		String surname = client.getSurname();
		long pesel = client.getPesel();
		String city = client.getCity();
		String street = client.getStreet();
		
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
	
	public Client findClient(String rowName, String rowValue)
	{
		try
		{
			String selectQuery = "SELECT * FROM client WHERE "+rowName+" = ?;";
			
			PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
			//row type is based on rowName
			switch(rowName)
			{
			case "name":
				selectStatement.setString(1, rowValue);
				break;
			case "surname":
				selectStatement.setString(1, rowValue);
				break;
			case "pesel":
				try 
				{
					selectStatement.setLong(1, Long.parseLong(rowValue));	
				}
				catch(Exception e)
				{
					selectStatement.setLong(1, 0L);
				}
				break;
			case "city":
				selectStatement.setString(1, rowValue);
				break;
			case "street":
				selectStatement.setString(1, rowValue);
				break;
			}
			
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
