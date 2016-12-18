package Controller;

import Model.ClientModel;

public class ClientController 
{
	ClientModel clientModel = new ClientModel();
	
	public void InsertClient(String name, String surname, long pesel, String city, String street)
	{
		clientModel.insertClient(name, surname, pesel, city, street);
	}
}
