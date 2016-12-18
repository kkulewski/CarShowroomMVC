package Model;

import java.sql.*;

public class Database 
{
	public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:database.db";
    
    protected static Connection conn;
    protected static Statement stat;
    
    public Database() 
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e) 
        {
            System.err.println("JDBC driver not found");
            e.printStackTrace();
        }
        
        try 
        {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            stat = conn.createStatement();
        }
        catch (SQLException e) 
        {
            System.err.println("Cannot establish connection");
            e.printStackTrace();
        }
        
    }
    
    //TODO: ADD UNIQUE, NOT NULL ETC
    public boolean CreateTables() 
    {
        String createClientQuery = ""
        		+ "CREATE TABLE IF NOT EXISTS client"
        		+ "( "
        		+ "id_client INTEGER PRIMARY KEY AUTOINCREMENT, "
        		+ "name varchar(20), "
        		+ "surname varchar(50), "
        		+ "pesel bigint, "
        		+ "city varchar(30), "
        		+ "street varchar(70)"
        		+ ")";
        
        try 
        {
            stat.execute(createClientQuery);
        }
        catch (SQLException e) 
        {
            System.err.println("Cannot create tables");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void CloseConnection() 
    {
        try 
        {
            conn.close();
        }
        catch (SQLException e) 
        {
            System.err.println("Cannot close the connection");
            e.printStackTrace();
        }
    }
}