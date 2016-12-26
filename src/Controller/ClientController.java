package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		clientListView.scrollableClientList.repaint();
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
			String peselAsString = clientAddView.peselField.getText();
			String city = clientAddView.cityField.getText();
			String street = clientAddView.streetField.getText();
			
			//REGEX VALIDATION
			Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,19}");
			Pattern surnamePattern = Pattern.compile("[A-Z][a-z]{1,49}");
			Pattern peselPattern = Pattern.compile("[0-9]{11}");
			Pattern cityPattern = Pattern.compile("[A-Z][a-z]{1,29}");
			Pattern streetPattern = Pattern.compile("[A-Z].{1,69}");
			 
			Matcher nameMatcher = namePattern.matcher(name);
			Matcher surnameMatcher = surnamePattern.matcher(surname);
			Matcher peselMatcher = peselPattern.matcher(peselAsString);
			Matcher cityMatcher = cityPattern.matcher(city);
			Matcher streetMatcher = streetPattern.matcher(street);
			
			boolean nameMatches = nameMatcher.matches();
			boolean surnameMatches = surnameMatcher.matches();
			boolean peselMatches = peselMatcher.matches();
			boolean cityMatches = cityMatcher.matches();
			boolean streetMatches = streetMatcher.matches();
			
			//Prepare error message
			String errorMessage = "";
			if(nameMatches == false) errorMessage += "\nwrong name format";
			if(surnameMatches == false) errorMessage += "\nwrong surname format";
			if(peselMatches == false) errorMessage += "\nwrong pesel format";
			if(cityMatches == false) errorMessage += "\nwrong city format";
			if(streetMatches == false) errorMessage += "\nwrong street format";
			
			
			//true is every field matches its regex, false otherwise
			boolean dataIsValid = nameMatches && surnameMatches && peselMatches && cityMatches && streetMatches;
			
			if(dataIsValid == true)
			{
				InsertClient(name, surname, pesel, city, street);
				
				clientAddView.nameField.setText("");
				clientAddView.surnameField.setText("");
				clientAddView.peselField.setText("");
				clientAddView.cityField.setText("");
				clientAddView.streetField.setText("");
				
				clientAddView.DisplaySuccessPopup();
			}
			else
			{
				clientAddView.DisplayErrorPopup(errorMessage);
			}
			
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
				String errorMessage = "";
				//true if every field matches its regex, false otherwise
				boolean dataIsValid = true;
				
				if(dataIsValid == true)
				{
					UpdateClient(clientId, clientNewName, clientNewSurname, clientNewPesel, clientNewCity, clientNewStreet);
					clientEditView.DisplaySuccessPopup();
				}
				else
				{
					clientEditView.DisplayErrorPopup(errorMessage);
				}
				
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
			
			//CONFIRMATION POPUP
			if(clientListView.DisplayRemoveConfirmationPopup() == true)
			{
				DeleteClient(selectedClientId);
			}
			
			RefreshView();
		}
	}
	
	class ClientSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Long pesel = Long.parseLong(clientSearchView.peselField.getText());
			
			Client foundClient = clientModel.findClient(pesel);
			if(foundClient != null)
			{
				clientSearchView.DisplayResultPopup(foundClient);
			}
			else
			{
				clientSearchView.DisplayNoResultPopup();
			}
			
		}
	}
}
