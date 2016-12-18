package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ClientModel;
import View.ClientAddView;

public class ClientController 
{
	ClientModel clientModel = new ClientModel();
	ClientAddView clientAddView;
	
	ClientAddSubmitListener clientAddSubmitListener;
	
	public ClientController(ClientAddView clientAddView)
	{
		this.clientAddView = clientAddView;
		this.clientAddSubmitListener = new ClientAddSubmitListener();
	}
	
	public void InsertClient(String name, String surname, long pesel, String city, String street)
	{
		clientModel.insertClient(name, surname, pesel, city, street);
	}
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class ClientAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			String name = clientAddView.nameField.getText();
			String surname = clientAddView.surnameField.getText();
			long pesel = Long.parseLong(clientAddView.peselField.getText());
			String city = clientAddView.cityField.getText();
			String street = clientAddView.streetField.getText();
			
			//TODO: VALIDATION WITH REGEX
			
			InsertClient(name, surname, pesel, city, street);
			
			clientAddView.nameField.setText("");
			clientAddView.surnameField.setText("");
			clientAddView.peselField.setText("");
			clientAddView.cityField.setText("");
			clientAddView.streetField.setText("");
			
			System.out.println("Added record: [" + name + "] [" + surname + "] [" + pesel + "] [" + city + "] [" + street + "] to db");
		}
	}
}
