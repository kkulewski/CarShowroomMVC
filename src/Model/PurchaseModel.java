package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Purchase;

public class PurchaseModel extends Database 
{
	private String tableName = "purchase";
	
	public boolean insert(Purchase row)
	{
		
		try
		{
			String query = "INSERT INTO "+tableName+" VALUES (NULL, ?, ?, ?, ?);";
			PreparedStatement insertStatement = conn.prepareStatement(query);
			
			insertStatement.setInt(1, row.getId_client());
			insertStatement.setInt(2, row.getId_worker());
			insertStatement.setInt(3, row.getId_car());
			insertStatement.setDate(4, row.getTransaction_date());
			
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
	
	public boolean delete(Purchase row)
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
	
	public boolean update(Purchase row)
	{
		
		try
		{
			String query = "UPDATE "+tableName+" SET id_client = ?, id_worker = ?, id_car = ?, transaction_date = ? WHERE id_"+tableName+" = ?;";
			PreparedStatement updateStatement = conn.prepareStatement(query);
			
			updateStatement.setInt(1, row.getId_client());
			updateStatement.setInt(2, row.getId_worker());
			updateStatement.setInt(3, row.getId_car());
			updateStatement.setDate(4, row.getTransaction_date());
			updateStatement.setInt(5, row.getId());
			
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
	
	public ArrayList<Purchase> list()
	{
		ArrayList<Purchase> rowList = new ArrayList<Purchase>();
		
	    try 
	    {
	    	String query = "SELECT * FROM "+tableName+";";
	        ResultSet result = stat.executeQuery(query);
	        
	        while(result.next()) 
	        {
	        	Purchase row = new Purchase
	            		(
	            			result.getInt("id_purchase"), 
	            			result.getInt("id_client"), 
	            			result.getInt("id_worker"), 
	            			result.getInt("id_car"), 
	            			result.getDate("transaction_date")
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
	
	public Purchase find(String columnName, String value)
	{
		try
		{
			String query = "SELECT * FROM "+tableName+" WHERE "+columnName+" = ?;";
			PreparedStatement selectStatement = conn.prepareStatement(query);
			
			//row type is based on rowName
			switch(columnName)
			{
				case "id_client":
					try 
					{
						selectStatement.setInt(1, Integer.valueOf(value));	
					}
					catch(Exception e)
					{
						selectStatement.setInt(1, 0);
					}					
					break;
				case "id_worker":
					try 
					{
						selectStatement.setInt(1, Integer.valueOf(value));	
					}
					catch(Exception e)
					{
						selectStatement.setInt(1, 0);
					}
					break;
				case "id_car":
					try 
					{
						selectStatement.setInt(1, Integer.valueOf(value));	
					}
					catch(Exception e)
					{
						selectStatement.setInt(1, 0);
					}
					break;
				case "transaction_date":
					selectStatement.setDate(1, Date.valueOf(value));
					break;
				default:
					selectStatement.setInt(1, 0);
					break;
			}
			
			ResultSet result = selectStatement.executeQuery();

	        Purchase foundRow = null;
	        while(result.next()) 
	        {
	            foundRow = new Purchase
	            		(
	            			result.getInt("id_purchase"), 
	            			result.getInt("id_client"), 
	            			result.getInt("id_worker"), 
	            			result.getInt("id_car"), 
	            			result.getDate("transaction_date")
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
