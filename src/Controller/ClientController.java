package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	ClientModel model = new ClientModel();
	ClientAddView addView;
	ClientListView listView;
	ClientEditView editView;
	ClientSearchView searchView;
	
	ClientAddSubmitListener addSubmitListener;
	ClientSearchSubmitListener searchSubmitListener;
	
	public ClientController(ClientAddView addView, ClientListView listView, ClientEditView editView, ClientSearchView searchView)
	{
		this.addView = addView;
		this.listView = listView;
		this.editView = editView;
		this.searchView = searchView;
		
		this.addSubmitListener = new ClientAddSubmitListener();
		this.searchSubmitListener = new ClientSearchSubmitListener();
		
		this.listView.ClientEditListener(new ClientEditListener());
		this.listView.ClientRemoveListener(new ClientRemoveListener());
	}
	
	public void RefreshView()
	{
		//reload clientTable data from DB
		listView.clientTableModel.ReloadClientTable(model.list());
		listView.clientList.clearSelection();
		//update view
		listView.scrollableClientList.repaint();
		listView.invalidate();
		listView.validate();
	}
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class ClientAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			Insert();
		}
	}
	
	class ClientEditListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Update();
		}
	}
	
	class ClientRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Delete();
		}
	}
	
	class ClientSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Find();
		}
	}
	
	public void Insert()
	{
		String name = addView.nameField.getText();
		String surname = addView.surnameField.getText();
		long pesel = 0;
		String peselAsString = "0";
		try
		{
			pesel = Long.parseLong(addView.peselField.getText());
			peselAsString = addView.peselField.getText();
		}
		catch(Exception e)
		{
			pesel = 0;
			peselAsString = "0";
		}
		String city = addView.cityField.getText();
		String street = addView.streetField.getText();
		
		//REGEX VALIDATION
		Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,19}");
		Pattern surnamePattern = Pattern.compile("[A-Z][a-z]{1,49}");
		Pattern peselPattern = Pattern.compile("[0-9]{11,11}");
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
		
		//Prepare error message list
		ArrayList<String> errorMessage = new ArrayList<String>();
		if(nameMatches == false) errorMessage.add("wrong format for name");
		if(surnameMatches == false) errorMessage.add("wrong format for surname");
		if(peselMatches == false) errorMessage.add("wrong format for pesel");
		if(cityMatches == false) errorMessage.add("wrong format for city");
		if(streetMatches == false) errorMessage.add("wrong format for street");
		
		//true if every field matches its regex, false otherwise
		boolean dataIsValid = nameMatches && surnameMatches && peselMatches && cityMatches && streetMatches;
		
		if(dataIsValid == true)
		{
			int tempId = 0;
			Client client = new Client(tempId, name, surname, pesel, city, street);
			
			model.insert(client);
			
			addView.nameField.setText("");
			addView.surnameField.setText("");
			addView.peselField.setText("");
			addView.cityField.setText("");
			addView.streetField.setText("");
			
			addView.DisplaySuccessPopup();
		}
		else
		{
			addView.DisplayErrorPopup(errorMessage);
		}	
	}
	
	public void Delete()
	{
		int selectedRow = listView.clientList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Client selectedClient = listView.clientTableModel.getClient(selectedRow);
		
		//CONFIRMATION POPUP
		if(listView.DisplayRemoveConfirmationPopup() == true)
		{
			model.delete(selectedClient);
		}
		RefreshView();
	}
	
	public void Update()
	{
		int selectedRow = listView.clientList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Client selectedClient = listView.clientTableModel.getClient(selectedRow);
		
		// FILL POPUP FIELDS WITH SELECTED ITEM DATA
		editView.nameField.setText(selectedClient.getName());
		editView.surnameField.setText(selectedClient.getSurname());
		editView.peselField.setText(String.valueOf(selectedClient.getPesel()));
		editView.cityField.setText(selectedClient.getCity());
		editView.streetField.setText(selectedClient.getStreet());
		
		boolean itemModified = editView.DisplayPopup();
		
		if(itemModified == true)
		{
			//GET NEW DATA FROM POPUP
			String name = editView.nameField.getText();
			String surname = editView.surnameField.getText();
			long pesel = 0;
			String peselAsString = "0";
			try
			{
				pesel = Long.parseLong(editView.peselField.getText());
				peselAsString = String.valueOf(pesel);
			}
			catch(Exception e)
			{
				pesel = 0;
				peselAsString = "0";
			}
			String city = editView.cityField.getText();
			String street = editView.streetField.getText();
			
			//REGEX VALIDATION
			Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,19}");
			Pattern surnamePattern = Pattern.compile("[A-Z][a-z]{1,49}");
			Pattern peselPattern = Pattern.compile("[0-9]{11,11}");
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
			
			//Prepare error message list
			ArrayList<String> errorMessage = new ArrayList<String>();
			if(nameMatches == false) errorMessage.add("wrong format for name");
			if(surnameMatches == false) errorMessage.add("wrong format for surname");
			if(peselMatches == false) errorMessage.add("wrong format for pesel");
			if(cityMatches == false) errorMessage.add("wrong format for city");
			if(streetMatches == false) errorMessage.add("wrong format for street");
			
			//true if every field matches its regex, false otherwise
			boolean dataIsValid = nameMatches && surnameMatches && peselMatches && cityMatches && streetMatches;
			
			if(dataIsValid == true)
			{
				selectedClient.setName(name);
				selectedClient.setSurname(surname);
				selectedClient.setPesel(pesel);
				selectedClient.setCity(city);
				selectedClient.setStreet(street);
				
				model.update(selectedClient);
				
				editView.DisplaySuccessPopup();
			}
			else
			{
				editView.DisplayErrorPopup(errorMessage);
			}	
			RefreshView();
		}
	}
	
	public void Find()
	{
		String rowName = (String) searchView.searchRowCombo.getSelectedItem();
		String rowValue = searchView.searchValueField.getText();
		
		Client foundClient = model.find(rowName, rowValue);
		if(foundClient != null)
		{
			searchView.DisplayResultPopup(foundClient);
		}
		else
		{
			searchView.DisplayNoResultPopup();
		}	
	}
	
}
