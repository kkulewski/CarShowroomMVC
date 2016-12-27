package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Client;

public class ClientModel extends Database 
{
	private String tableName = "client";
	
	public boolean insertClient(Client client)
	{
		
		try
		{
			String query = "INSERT INTO "+tableName+" VALUES (NULL, ?, ?, ?, ?, ?);";
			PreparedStatement insertStatement = conn.prepareStatement(query);
			
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getSurname());
			insertStatement.setLong(3, client.getPesel());
			insertStatement.setString(4, client.getCity());
			insertStatement.setString(5, client.getStreet());
			
			insertStatement.execute();
			insertStatement.close();
		}
		catch(SQLException e)
		{
			System.err.println("Cannot insert new "+tableName+".");
			return false;
		}
		return true;
	}
	
	public boolean deleteClient(Client client)
	{
		try
		{
			String query = "DELETE FROM "+tableName+" WHERE id_"+tableName+" = ?;";
			PreparedStatement deleteStatement = conn.prepareStatement(query);
			
			deleteStatement.setInt(1, client.getId());
			
			deleteStatement.execute();
			deleteStatement.close();
		}
		catch(SQLException e)
		{
			System.err.println("Cannot delete row from "+tableName+".");
			return false;
		}
		return true;
	}
	
	public boolean updateClient(Client client)
	{
		
		try
		{
			String query = "UPDATE "+tableName+" SET name = ?, surname = ?, pesel = ?, city = ?, street = ? WHERE id_"+tableName+" = ?;";
			PreparedStatement updateStatement = conn.prepareStatement(query);
			
			updateStatement.setString(1, client.getName());
			updateStatement.setString(2, client.getSurname());
			updateStatement.setLong(3, client.getPesel());
			updateStatement.setString(4, client.getCity());
			updateStatement.setString(5, client.getStreet());
			updateStatement.setInt(6, client.getId());
			
			updateStatement.execute();
			updateStatement.close();
		}
		catch(SQLException e)
		{
			System.err.println("Cannot update row in "+tableName+".");
			return false;
		}
		return true;
	}
	
	public ArrayList<Client> listClient()
	{
		ArrayList<Client> rowList = new ArrayList<Client>();
		
	    try 
	    {
	    	String query = "SELECT * FROM "+tableName+";";
	        ResultSet result = stat.executeQuery(query);
	        
	        while(result.next()) 
	        {
	        	Client client = new Client
	            		(
	            			result.getInt("id_client"), 
	            			result.getString("name"), 
	            			result.getString("surname"), 
	            			result.getLong("pesel"), 
	            			result.getString("city"), 
	            			result.getString("street")
	            		);
	            rowList.add(client);
	        }
	        result.close();
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return null;
	    }
	    return rowList;
	}
	
	public Client findClient(String columnName, String value)
	{
		try
		{
			String selectQuery = "SELECT * FROM "+tableName+" WHERE "+columnName+" = ?;";
			PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
			
			//row type is based on rowName
			switch(columnName)
			{
			case "name":
				selectStatement.setString(1, value);
				break;
			case "surname":
				selectStatement.setString(1, value);
				break;
			case "pesel":
				try 
				{
					selectStatement.setLong(1, Long.parseLong(value));	
				}
				catch(Exception e)
				{
					selectStatement.setLong(1, 0L);
				}
				break;
			case "city":
				selectStatement.setString(1, value);
				break;
			case "street":
				selectStatement.setString(1, value);
				break;
			default:
				selectStatement.setString(1, "name");
				break;
			}
			
			ResultSet result = selectStatement.executeQuery();

	        Client foundRow = null;
	        while(result.next()) 
	        {
	            foundRow = new Client
	            		(
	            			result.getInt("id_client"), 
	            			result.getString("name"), 
	            			result.getString("surname"), 
	            			result.getLong("pesel"), 
	            			result.getString("city"), 
	            			result.getString("street")
	            		);
	        }
	        return foundRow;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
