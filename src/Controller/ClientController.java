package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Client;
import Model.ClientModel;
import View.ClientAddView;
import View.ClientListView;

public class ClientController 
{
	ClientModel clientModel = new ClientModel();
	ClientAddView clientAddView;
	ClientListView clientListView;
	
	ClientAddSubmitListener clientAddSubmitListener;
	
	public ClientController(ClientAddView clientAddView, ClientListView clientListView)
	{
		this.clientAddView = clientAddView;
		this.clientListView = clientListView;
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
	
	class ClientEditListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
		}
	}
	
	class ClientRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int selectedRow = clientListView.clientList.getSelectedRow();
			Client selectedClient = clientListView.clientTableModel.getClient(selectedRow);
			
			System.out.println("Removing "+selectedClient.getId()+" from db");
		}
	}
}
