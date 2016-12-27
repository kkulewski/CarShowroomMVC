package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Car;

public class CarModel extends Database 
{
	private String tableName = "car";
	
	public boolean insert(Car row)
	{
		
		try
		{
			String query = "INSERT INTO "+tableName+" VALUES (NULL, ?, ?, ?);";
			PreparedStatement insertStatement = conn.prepareStatement(query);
			
			insertStatement.setString(1, row.getBrand());
			insertStatement.setString(2, row.getModel());
			insertStatement.setDouble(3, row.getPrice());
			
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
	
	public boolean delete(Car row)
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
	
	public boolean update(Car row)
	{
		
		try
		{
			String query = "UPDATE "+tableName+" SET brand = ?, model = ?, price = ? WHERE id_"+tableName+" = ?;";
			PreparedStatement updateStatement = conn.prepareStatement(query);
			
			updateStatement.setString(1, row.getBrand());
			updateStatement.setString(2, row.getModel());
			updateStatement.setDouble(3, row.getPrice());
			updateStatement.setInt(4, row.getId());
			
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
	
	public ArrayList<Car> list()
	{
		ArrayList<Car> rowList = new ArrayList<Car>();
		
	    try 
	    {
	    	String query = "SELECT * FROM "+tableName+";";
	        ResultSet result = stat.executeQuery(query);
	        
	        while(result.next()) 
	        {
	        	Car row = new Car
	            		(
	            			result.getInt("id_car"), 
	            			result.getString("brand"), 
	            			result.getString("model"), 
	            			result.getDouble("price")
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
	
	public Car find(String columnName, String value)
	{
		try
		{
			String query = "SELECT * FROM "+tableName+" WHERE "+columnName+" = ?;";
			PreparedStatement selectStatement = conn.prepareStatement(query);
			
			//row type is based on rowName
			switch(columnName)
			{
				case "brand":
					selectStatement.setString(1, value);
					break;
				case "model":
					selectStatement.setString(1, value);
					break;
				case "price":
					try 
					{
						selectStatement.setDouble(1, Double.parseDouble(value));	
					}
					catch(Exception e)
					{
						selectStatement.setLong(1, 0L);
					}
					break;
				default:
					selectStatement.setString(1, "brand");
					break;
			}
			
			ResultSet result = selectStatement.executeQuery();

	        Car foundRow = null;
	        while(result.next()) 
	        {
	            foundRow = new Car
	            		(
	            			result.getInt("id_car"), 
	            			result.getString("brand"), 
	            			result.getString("model"), 
	            			result.getDouble("price")
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
