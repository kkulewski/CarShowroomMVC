package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Client;

public class ClientModel extends Database 
{
	private String tableName = "client";
	
	public boolean insert(Client row)
	{
		
		try
		{
			String query = "INSERT INTO "+tableName+" VALUES (NULL, ?, ?, ?, ?, ?);";
			PreparedStatement insertStatement = conn.prepareStatement(query);
			
			insertStatement.setString(1, row.getName());
			insertStatement.setString(2, row.getSurname());
			insertStatement.setLong(3, row.getCnum());
			insertStatement.setString(4, row.getCity());
			insertStatement.setString(5, row.getStreet());
			
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
	
	public boolean delete(Client row)
	{
		try
		{
			String query = "DELETE FROM "+tableName+" WHERE id_"+tableName+" = ?;";
			PreparedStatement deleteStatement = conn.prepareStatement(query);
			
			deleteStatement.setInt(1, row.getId());
			
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
	
	public boolean update(Client row)
	{
		
		try
		{
			String query = "UPDATE "+tableName+" SET name = ?, surname = ?, cnum = ?, city = ?, street = ? WHERE id_"+tableName+" = ?;";
			PreparedStatement updateStatement = conn.prepareStatement(query);
			
			updateStatement.setString(1, row.getName());
			updateStatement.setString(2, row.getSurname());
			updateStatement.setLong(3, row.getCnum());
			updateStatement.setString(4, row.getCity());
			updateStatement.setString(5, row.getStreet());
			updateStatement.setInt(6, row.getId());
			
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
	
	public ArrayList<Client> list()
	{
		ArrayList<Client> rowList = new ArrayList<Client>();
		
	    try 
	    {
	    	String query = "SELECT * FROM "+tableName+";";
	        ResultSet result = stat.executeQuery(query);
	        
	        while(result.next()) 
	        {
	        	Client row = new Client
	            		(
	            			result.getInt("id_client"), 
	            			result.getString("name"), 
	            			result.getString("surname"), 
	            			result.getLong("cnum"), 
	            			result.getString("city"), 
	            			result.getString("street")
	            		);
	            rowList.add(row);
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
	
	public Client find(String columnName, String value)
	{
		try
		{
			String query = "SELECT * FROM "+tableName+" WHERE "+columnName+" = ?;";
			PreparedStatement selectStatement = conn.prepareStatement(query);
			
			//row type is based on rowName
			switch(columnName)
			{
				case "name":
					selectStatement.setString(1, value);
					break;
				case "surname":
					selectStatement.setString(1, value);
					break;
				case "cnum":
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
				case "id_client":
					selectStatement.setInt(1, Integer.parseInt(value));
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
	            			result.getLong("cnum"), 
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
