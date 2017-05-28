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
		long cnum = 0;
		String cnumAsString = "0";
		try
		{
			cnum = Long.parseLong(addView.cnumField.getText());
			cnumAsString = addView.cnumField.getText();
		}
		catch(Exception e)
		{
			cnum = 0;
			cnumAsString = "0";
		}
		String city = addView.cityField.getText();
		String street = addView.streetField.getText();
		
		//REGEX VALIDATION
		Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,19}");
		Pattern surnamePattern = Pattern.compile("[A-Z][a-z]{1,49}");
		Pattern cnumPattern = Pattern.compile("[0-9]{11,11}");
		Pattern cityPattern = Pattern.compile("[A-Z][a-z]{1,29}");
		Pattern streetPattern = Pattern.compile("[A-Z].{1,69}");
		 
		Matcher nameMatcher = namePattern.matcher(name);
		Matcher surnameMatcher = surnamePattern.matcher(surname);
		Matcher cnumMatcher = cnumPattern.matcher(cnumAsString);
		Matcher cityMatcher = cityPattern.matcher(city);
		Matcher streetMatcher = streetPattern.matcher(street);
		
		boolean nameMatches = nameMatcher.matches();
		boolean surnameMatches = surnameMatcher.matches();
		boolean cnumMatches = cnumMatcher.matches();
		boolean cityMatches = cityMatcher.matches();
		boolean streetMatches = streetMatcher.matches();
		
		//Prepare error message list
		ArrayList<String> errorMessage = new ArrayList<String>();
		if(nameMatches == false) errorMessage.add("wrong format for name");
		if(surnameMatches == false) errorMessage.add("wrong format for surname");
		if(cnumMatches == false) errorMessage.add("wrong format for cnum");
		if(cityMatches == false) errorMessage.add("wrong format for city");
		if(streetMatches == false) errorMessage.add("wrong format for street");
		
		//true if every field matches its regex, false otherwise
		boolean dataIsValid = nameMatches && surnameMatches && cnumMatches && cityMatches && streetMatches;
		
		if(dataIsValid == true)
		{
			int tempId = 0;
			Client client = new Client(tempId, name, surname, cnum, city, street);
			
			model.insert(client);
			
			addView.nameField.setText("");
			addView.surnameField.setText("");
			addView.cnumField.setText("");
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
		editView.cnumField.setText(String.valueOf(selectedClient.getCnum()));
		editView.cityField.setText(selectedClient.getCity());
		editView.streetField.setText(selectedClient.getStreet());
		
		boolean itemModified = editView.DisplayPopup();
		
		if(itemModified == true)
		{
			//GET NEW DATA FROM POPUP
			String name = editView.nameField.getText();
			String surname = editView.surnameField.getText();
			long cnum = 0;
			String cnumAsString = "0";
			try
			{
				cnum = Long.parseLong(editView.cnumField.getText());
				cnumAsString = String.valueOf(cnum);
			}
			catch(Exception e)
			{
				cnum = 0;
				cnumAsString = "0";
			}
			String city = editView.cityField.getText();
			String street = editView.streetField.getText();
			
			//REGEX VALIDATION
			Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,19}");
			Pattern surnamePattern = Pattern.compile("[A-Z][a-z]{1,49}");
			Pattern cnumPattern = Pattern.compile("[0-9]{11,11}");
			Pattern cityPattern = Pattern.compile("[A-Z][a-z]{1,29}");
			Pattern streetPattern = Pattern.compile("[A-Z].{1,69}");
			 
			Matcher nameMatcher = namePattern.matcher(name);
			Matcher surnameMatcher = surnamePattern.matcher(surname);
			Matcher cnumMatcher = cnumPattern.matcher(cnumAsString);
			Matcher cityMatcher = cityPattern.matcher(city);
			Matcher streetMatcher = streetPattern.matcher(street);
			
			boolean nameMatches = nameMatcher.matches();
			boolean surnameMatches = surnameMatcher.matches();
			boolean cnumMatches = cnumMatcher.matches();
			boolean cityMatches = cityMatcher.matches();
			boolean streetMatches = streetMatcher.matches();
			
			//Prepare error message list
			ArrayList<String> errorMessage = new ArrayList<String>();
			if(nameMatches == false) errorMessage.add("wrong format for name");
			if(surnameMatches == false) errorMessage.add("wrong format for surname");
			if(cnumMatches == false) errorMessage.add("wrong format for cnum");
			if(cityMatches == false) errorMessage.add("wrong format for city");
			if(streetMatches == false) errorMessage.add("wrong format for street");
			
			//true if every field matches its regex, false otherwise
			boolean dataIsValid = nameMatches && surnameMatches && cnumMatches && cityMatches && streetMatches;
			
			if(dataIsValid == true)
			{
				selectedClient.setName(name);
				selectedClient.setSurname(surname);
				selectedClient.setCnum(cnum);
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
