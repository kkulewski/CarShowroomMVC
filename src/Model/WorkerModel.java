package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Worker;

public class WorkerModel extends Database 
{
	private String tableName = "worker";
	
	public boolean insert(Worker row)
	{
		
		try
		{
			String query = "INSERT INTO "+tableName+" VALUES (NULL, ?, ?, ?, ?, ?, ?);";
			PreparedStatement insertStatement = conn.prepareStatement(query);
			
			insertStatement.setString(1, row.getName());
			insertStatement.setString(2, row.getSurname());
			insertStatement.setLong(3, row.getCnum());
			insertStatement.setString(4, row.getCity());
			insertStatement.setString(5, row.getStreet());
			insertStatement.setInt(6, row.getId_position());
			
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
	
	public boolean delete(Worker row)
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
	
	public boolean update(Worker row)
	{
		
		try
		{
			String query = "UPDATE "+tableName+" SET name = ?, surname = ?, cnum = ?, city = ?, street = ?, id_position = ? WHERE id_"+tableName+" = ?;";
			PreparedStatement updateStatement = conn.prepareStatement(query);
			
			updateStatement.setString(1, row.getName());
			updateStatement.setString(2, row.getSurname());
			updateStatement.setLong(3, row.getCnum());
			updateStatement.setString(4, row.getCity());
			updateStatement.setString(5, row.getStreet());
			updateStatement.setInt(6, row.getId_position());
			updateStatement.setInt(7, row.getId());
			
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
	
	public ArrayList<Worker> list()
	{
		ArrayList<Worker> rowList = new ArrayList<Worker>();
		
	    try 
	    {
	    	String query = "SELECT * FROM "+tableName+";";
	        ResultSet result = stat.executeQuery(query);
	        
	        while(result.next()) 
	        {
	        	Worker row = new Worker
	            		(
	            			result.getInt("id_worker"), 
	            			result.getString("name"), 
	            			result.getString("surname"), 
	            			result.getLong("cnum"), 
	            			result.getString("city"), 
	            			result.getString("street"),
	            			result.getInt("id_position")
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
	
	public Worker find(String columnName, String value)
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
				case "id_worker":
					selectStatement.setInt(1, Integer.parseInt(value));
					break;
				case "id_position":
					try 
					{
						selectStatement.setInt(1, Integer.parseInt(value));	
					}
					catch(Exception e)
					{
						selectStatement.setInt(1, 0);
					}
					break;
				default:
					selectStatement.setString(1, "name");
					break;
			}
			
			ResultSet result = selectStatement.executeQuery();

	        Worker foundRow = null;
	        while(result.next()) 
	        {
	            foundRow = new Worker
	            		(
	            			result.getInt("id_worker"), 
	            			result.getString("name"), 
	            			result.getString("surname"), 
	            			result.getLong("cnum"), 
	            			result.getString("city"), 
	            			result.getString("street"),
	            			result.getInt("id_position")
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
