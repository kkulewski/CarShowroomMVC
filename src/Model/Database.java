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
        		+ "name varchar(20) NOT NULL, "
        		+ "surname varchar(50) NOT NULL, "
        		+ "pesel bigint UNIQUE, "
        		+ "city varchar(30) NOT NULL, "
        		+ "street varchar(70) NOT NULL"
        		+ ")";
        
        String createPositionQuery = ""
        		+ "CREATE TABLE IF NOT EXISTS position"
        		+ "( "
        		+ "id_position INTEGER PRIMARY KEY AUTOINCREMENT, "
        		+ "title varchar(20) NOT NULL,"
        		+ "salary REAL NOT NULL,"
        		+ "isFullTime BOOL NOT NULL,"
        		+ "isContract BOOL NOT NULL"
        		+ ")";
        
        String createWorkerQuery = ""
        		+ "CREATE TABLE IF NOT EXISTS worker"
        		+ "( "
        		+ "id_worker INTEGER PRIMARY KEY AUTOINCREMENT, "
        		+ "name varchar(20) NOT NULL, "
        		+ "surname varchar(50) NOT NULL, "
        		+ "pesel bigint UNIQUE, "
        		+ "city varchar(30) NOT NULL, "
        		+ "street varchar(70) NOT NULL, "
        		+ "id_position INTEGER, "
        		+ "FOREIGN KEY(id_position) REFERENCES position(id_position)"
        		+ ")";
        
        String createCarQuery = ""
        		+ "CREATE TABLE IF NOT EXISTS car"
        		+ "( "
        		+ "id_car INTEGER PRIMARY KEY AUTOINCREMENT, "
        		+ "brand varchar(20) NOT NULL, "
        		+ "model varchar(20) NOT NULL, "
        		+ "price REAL NOT NULL, "
        		+ "isNew BOOL NOT NULL"
        		+ ")";
        
        String createPurchaseQuery = ""
        		+ "CREATE TABLE IF NOT EXISTS purchase"
        		+ "( "
        		+ "id_purchase INTEGER PRIMARY KEY AUTOINCREMENT, "
        		+ "id_client INTEGER, "
        		+ "id_worker INTEGER, "
        		+ "id_car INTEGER, "
        		+ "transaction_date DATE NOT NULL, "
        		+ "FOREIGN KEY(id_client) REFERENCES client(id_client), "
        		+ "FOREIGN KEY(id_worker) REFERENCES worker(id_worker), "
        		+ "FOREIGN KEY(id_car) REFERENCES car(id_car)"
        		+ ")";
        
        try 
        {
            stat.execute(createClientQuery);
            stat.execute(createPositionQuery);
            stat.execute(createWorkerQuery);
            stat.execute(createCarQuery);
            stat.execute(createPurchaseQuery);
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