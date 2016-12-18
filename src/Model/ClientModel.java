package Model;

import java.sql.PreparedStatement;
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
	
	
	//STUB - GET DATA FROM DB, PUT INTO ARRAYLIST AND RETURN
	public ArrayList<Client> listClient()
	{
		Client client1 = new Client(7, "Jan", "Kowalski", 99999999999L, "Warszawa", "Dluga 2");
		Client client2 = new Client(7, "Tomasz", "Nowak", 12345678901L, "Gdansk", "Krotka 1");
		Client client3 = new Client(7, "Mateusz", "Wisniewski", 55555555555L, "Poznan", "Srednia 3");
		ArrayList<Client> clientList = new ArrayList<Client>();
		clientList.add(client1);
		clientList.add(client2);
		clientList.add(client3);
		
		return clientList;
	}
	
}
