package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;

import Model.Position;
import Model.PositionModel;
import Model.Worker;
import Model.WorkerModel;
import View.WorkerAddView;
import View.WorkerEditView;
import View.WorkerListView;
import View.WorkerSearchView;

public class WorkerController 
{
	WorkerModel model = new WorkerModel();
	WorkerAddView addView;
	WorkerListView listView;
	WorkerEditView editView;
	WorkerSearchView searchView;
	
	WorkerAddSubmitListener addSubmitListener;
	WorkerSearchSubmitListener searchSubmitListener;
	
	public WorkerController(WorkerAddView addView, WorkerListView listView, WorkerEditView editView, WorkerSearchView searchView)
	{
		this.addView = addView;
		this.listView = listView;
		this.editView = editView;
		this.searchView = searchView;
		
		this.addSubmitListener = new WorkerAddSubmitListener();
		this.searchSubmitListener = new WorkerSearchSubmitListener();
		
		this.listView.WorkerEditListener(new WorkerEditListener());
		this.listView.WorkerRemoveListener(new WorkerRemoveListener());
	}
	
	public void RefreshView()
	{
		//reload workerTable data from DB
		listView.workerTableModel.ReloadWorkerTable(model.list());
		listView.workerList.clearSelection();
		//update view
		listView.scrollableWorkerList.repaint();
		listView.invalidate();
		listView.validate();
	}
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class WorkerAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			Insert();
		}
	}
	
	class WorkerEditListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Update();
		}
	}
	
	class WorkerRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Delete();
		}
	}
	
	class WorkerSearchSubmitListener implements ActionListener
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
		//POSITION FROM COMBOBOX
		PositionModel positionModel = new PositionModel();
		String selectedPosition = (String)addView.positionCombo.getSelectedItem();
		Position foundPosition = positionModel.find("title", selectedPosition);
		int positionId = foundPosition.getId();
		
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
			Worker worker = new Worker(tempId, name, surname, pesel, city, street, positionId);
			
			model.insert(worker);
			
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
		int selectedRow = listView.workerList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Worker selectedWorker = listView.workerTableModel.getWorker(selectedRow);
		
		//CONFIRMATION POPUP
		if(listView.DisplayRemoveConfirmationPopup() == true)
		{
			model.delete(selectedWorker);
		}
		RefreshView();
	}
	
	public void Update()
	{
		int selectedRow = listView.workerList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Worker selectedWorker = listView.workerTableModel.getWorker(selectedRow);
		
		// FILL POPUP FIELDS WITH SELECTED ITEM DATA
		editView.nameField.setText(selectedWorker.getName());
		editView.surnameField.setText(selectedWorker.getSurname());
		editView.peselField.setText(String.valueOf(selectedWorker.getPesel()));
		editView.cityField.setText(selectedWorker.getCity());
		editView.streetField.setText(selectedWorker.getStreet());
		

		PositionModel positionModel = new PositionModel();
		ArrayList<Position> positionList = new ArrayList<Position>();
		positionList = positionModel.list();
		this.editView.positionCombo.removeAllItems();
	    for(Position p : positionList)
	    {
	    	this.editView.positionCombo.addItem(p.getTitle());
	    }
	    int worker_positionId = selectedWorker.getId_position();
	    int positionId = worker_positionId;
	    Position worker_position = positionModel.find("id_position", String.valueOf(worker_positionId));
	    String worker_positionTitle = worker_position.getTitle();
	    this.editView.positionCombo.setSelectedItem(worker_positionTitle);
		
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
			//POSITION FROM COMBOBOX
			String selectedPosition = (String)editView.positionCombo.getSelectedItem();
			Position foundPosition = positionModel.find("title", selectedPosition);
			positionId = foundPosition.getId();
			
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
				selectedWorker.setName(name);
				selectedWorker.setSurname(surname);
				selectedWorker.setPesel(pesel);
				selectedWorker.setCity(city);
				selectedWorker.setStreet(street);
				selectedWorker.setId_position(positionId);
				
				model.update(selectedWorker);
				
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
		
		Worker foundWorker = model.find(rowName, rowValue);
		if(foundWorker != null)
		{
			searchView.DisplayResultPopup(foundWorker);
		}
		else
		{
			searchView.DisplayNoResultPopup();
		}	
	}
	
}
