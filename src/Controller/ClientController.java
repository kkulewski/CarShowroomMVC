package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Client;
import Model.ClientModel;
import View.ClientAddView;
import View.ClientEditView;
import View.ClientListView;
import View.ClientSearchView;

public class ClientController 
{
	ClientModel clientModel = new ClientModel();
	ClientAddView clientAddView;
	ClientListView clientListView;
	ClientEditView clientEditView;
	ClientSearchView clientSearchView;
	
	ClientAddSubmitListener clientAddSubmitListener;
	ClientSearchSubmitListener clientSearchSubmitListener;
	
	public ClientController(ClientAddView clientAddView, ClientListView clientListView, ClientEditView clientEditView, ClientSearchView clientSearchView)
	{
		this.clientAddView = clientAddView;
		this.clientListView = clientListView;
		this.clientEditView = clientEditView;
		this.clientSearchView = clientSearchView;
		this.clientAddSubmitListener = new ClientAddSubmitListener();
		this.clientSearchSubmitListener = new ClientSearchSubmitListener();
		
		this.clientListView.ClientEditListener(new ClientEditListener());
		this.clientListView.ClientRemoveListener(new ClientRemoveListener());
	}
	
	public void RefreshView()
	{
		//reload clientTable data from DB
		clientListView.clientTableModel.ReloadClientTable(clientModel.listClient());
		clientListView.clientList.clearSelection();
		//update view
		clientListView.invalidate();
		clientListView.validate();
	}
	
	public void InsertClient(String name, String surname, long pesel, String city, String street)
	{
		clientModel.insertClient(name, surname, pesel, city, street);
	}
	
	public void DeleteClient(int id)
	{
		clientModel.deleteClient(id);
	}
	
	public void UpdateClient(int id, String name, String surname, long pesel, String city, String street)
	{
		clientModel.updateClient(id, name, surname, pesel, city, street);
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
			int selectedRow = clientListView.clientList.getSelectedRow();
			//RETURN IF NO ROW SELECTED
			if(selectedRow == -1)
				return;
			
			Client selectedClient = clientListView.clientTableModel.getClient(selectedRow);
			
			int clientId = selectedClient.getId();
			
			// FILL POPUP FIELDS WITH SELECTED ITEM DATA
			String clientName = selectedClient.getName();
			clientEditView.nameField.setText(clientName);
			
			String clientSurname = selectedClient.getSurname();
			clientEditView.surnameField.setText(clientSurname);
			
			String clientPesel = String.valueOf(selectedClient.getPesel());
			clientEditView.peselField.setText(clientPesel);
			
			String clientCity = selectedClient.getCity();
			clientEditView.cityField.setText(clientCity);
			
			String clientStreet = selectedClient.getStreet();
			clientEditView.streetField.setText(clientStreet);
			
			
			boolean itemModified = clientEditView.DisplayPopup();
			
			if(itemModified == true)
			{
				//GET NEW DATA FROM POPUP
				String clientNewName = clientEditView.name;
				String clientNewSurname = clientEditView.surname;
				long clientNewPesel = clientEditView.pesel;
				String clientNewCity = clientEditView.city;
				String clientNewStreet = clientEditView.street;
				
				//TODO: regex check new data
				
				UpdateClient(clientId, clientNewName, clientNewSurname, clientNewPesel, clientNewCity, clientNewStreet);
				
				RefreshView();
			}
		}
	}
	
	class ClientRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int selectedRow = clientListView.clientList.getSelectedRow();
			//RETURN IF NO ROW SELECTED
			if(selectedRow == -1)
				return;
			
			Client selectedClient = clientListView.clientTableModel.getClient(selectedRow);
			int selectedClientId = selectedClient.getId();
			
			//TODO: add confirmation popup
			
			DeleteClient(selectedClientId);
			
			RefreshView();
		}
	}
	
	class ClientSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.out.println("Searching for client with PESEL == ");
		}
	}
}
