package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		//POSITION FROM COMBOBOX
		PositionModel positionModel = new PositionModel();
		String selectedPosition = (String)addView.positionCombo.getSelectedItem();
		Position foundPosition = positionModel.find("title", selectedPosition);
		int positionId = foundPosition.getId();
		
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
			Worker worker = new Worker(tempId, name, surname, cnum, city, street, positionId);
			
			model.insert(worker);
			
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
		editView.cnumField.setText(String.valueOf(selectedWorker.getCnum()));
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
			//POSITION FROM COMBOBOX
			String selectedPosition = (String)editView.positionCombo.getSelectedItem();
			Position foundPosition = positionModel.find("title", selectedPosition);
			positionId = foundPosition.getId();
			
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
				selectedWorker.setName(name);
				selectedWorker.setSurname(surname);
				selectedWorker.setCnum(cnum);
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
