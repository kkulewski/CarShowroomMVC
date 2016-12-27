package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Position;

public class PositionModel extends Database 
{
	private String tableName = "position";
	
	public boolean insert(Position row)
	{
		
		try
		{
			String query = "INSERT INTO "+tableName+" VALUES (NULL, ?, ?);";
			PreparedStatement insertStatement = conn.prepareStatement(query);
			
			insertStatement.setString(1, row.getTitle());
			insertStatement.setInt(2, row.getSalary());
			
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
	
	public boolean delete(Position row)
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
	
	public boolean update(Position row)
	{
		
		try
		{
			String query = "UPDATE "+tableName+" SET title = ?, salary = ? WHERE id_"+tableName+" = ?;";
			PreparedStatement updateStatement = conn.prepareStatement(query);
			
			updateStatement.setString(1, row.getTitle());
			updateStatement.setInt(2, row.getSalary());
			updateStatement.setInt(3, row.getId());
			
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
	
	public ArrayList<Position> list()
	{
		ArrayList<Position> rowList = new ArrayList<Position>();
		
	    try 
	    {
	    	String query = "SELECT * FROM "+tableName+";";
	        ResultSet result = stat.executeQuery(query);
	        
	        while(result.next()) 
	        {
	        	Position row = new Position
	            		(
	            			result.getInt("id_position"), 
	            			result.getString("title"), 
	            			result.getInt("salary")
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
	
	public Position find(String columnName, String value)
	{
		try
		{
			String query = "SELECT * FROM "+tableName+" WHERE "+columnName+" = ?;";
			PreparedStatement selectStatement = conn.prepareStatement(query);
			
			//row type is based on rowName
			switch(columnName)
			{
				case "title":
					selectStatement.setString(1, value);
					break;
				case "id_position":
					selectStatement.setInt(1, Integer.parseInt(value));
					break;
				default:
					selectStatement.setString(1, "title");
					break;
			}
			
			ResultSet result = selectStatement.executeQuery();

	        Position foundRow = null;
	        while(result.next()) 
	        {
	            foundRow = new Position
	            		(
	            			result.getInt("id_position"), 
	            			result.getString("title"), 
	            			result.getInt("salary")
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
