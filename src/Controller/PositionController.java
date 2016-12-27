package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Position;
import Model.PositionModel;
import View.PositionAddView;
import View.PositionEditView;
import View.PositionListView;
import View.PositionSearchView;

public class PositionController 
{
	PositionModel model = new PositionModel();
	PositionAddView addView;
	PositionListView listView;
	PositionEditView editView;
	PositionSearchView searchView;
	
	PositionAddSubmitListener addSubmitListener;
	PositionSearchSubmitListener searchSubmitListener;
	
	public PositionController(PositionAddView addView, PositionListView listView, PositionEditView editView, PositionSearchView searchView)
	{
		this.addView = addView;
		this.listView = listView;
		this.editView = editView;
		this.searchView = searchView;
		
		this.addSubmitListener = new PositionAddSubmitListener();
		this.searchSubmitListener = new PositionSearchSubmitListener();
		
		this.listView.PositionEditListener(new PositionEditListener());
		this.listView.PositionRemoveListener(new PositionRemoveListener());
	}
	
	public void RefreshView()
	{
		//reload PositionTable data from DB
		listView.positionTableModel.ReloadPositionTable(model.list());
		listView.positionList.clearSelection();
		//update view
		listView.scrollablePositionList.repaint();
		listView.invalidate();
		listView.validate();
	}
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class PositionAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			Insert();
		}
	}
	
	class PositionEditListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Update();
		}
	}
	
	class PositionRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Delete();
		}
	}
	
	class PositionSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Find();
		}
	}
	
	public void Insert()
	{
		String title = addView.titleField.getText();
		int salary = 0;
		String salaryAsString = "0";
		try
		{
			salary = Integer.parseInt(addView.salaryField.getText());
			salaryAsString = addView.salaryField.getText();
		}
		catch(Exception e)
		{
			salary = 0;
			salaryAsString = "0";
		}
		
		//REGEX VALIDATION
		Pattern titlePattern = Pattern.compile("[A-Z][a-z]{1,19}");
		Pattern salaryPattern = Pattern.compile("[0-9]{1,10}");
		 
		Matcher titleMatcher = titlePattern.matcher(title);
		Matcher salaryMatcher = salaryPattern.matcher(salaryAsString);
		
		boolean titleMatches = titleMatcher.matches();
		boolean salaryMatches = salaryMatcher.matches();
		
		//Prepare error message list
		ArrayList<String> errorMessage = new ArrayList<String>();
		if(titleMatches == false) errorMessage.add("wrong format for title");
		if(salaryMatches == false) errorMessage.add("wrong format for salary");
		
		//true if every field matches its regex, false otherwise
		boolean dataIsValid = titleMatches && salaryMatches;
		
		if(dataIsValid == true)
		{
			int tempId = 0;
			Position Position = new Position(tempId, title, salary);
			
			model.insert(Position);
			
			addView.titleField.setText("");
			addView.salaryField.setText("");
			
			addView.DisplaySuccessPopup();
		}
		else
		{
			addView.DisplayErrorPopup(errorMessage);
		}	
	}
	
	public void Delete()
	{
		int selectedRow = listView.positionList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Position selectedPosition = listView.positionTableModel.getPosition(selectedRow);
		
		//CONFIRMATION POPUP
		if(listView.DisplayRemoveConfirmationPopup() == true)
		{
			model.delete(selectedPosition);
		}
		RefreshView();
	}
	
	public void Update()
	{
		int selectedRow = listView.positionList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Position selectedPosition = listView.positionTableModel.getPosition(selectedRow);
		
		// FILL POPUP FIELDS WITH SELECTED ITEM DATA
		editView.titleField.setText(selectedPosition.getTitle());
		editView.salaryField.setText(String.valueOf(selectedPosition.getSalary()));
		
		boolean itemModified = editView.DisplayPopup();
		
		if(itemModified == true)
		{
			//GET NEW DATA FROM POPUP
			String title = editView.titleField.getText();
			int salary = 0;
			String salaryAsString = "0";
			try
			{
				salary = Integer.parseInt(editView.salaryField.getText());
				salaryAsString = String.valueOf(salary);
			}
			catch(Exception e)
			{
				salary = 0;
				salaryAsString = "0";
			}
			
			//REGEX VALIDATION
			Pattern titlePattern = Pattern.compile("[A-Z][a-z]{1,19}");
			Pattern salaryPattern = Pattern.compile("[0-9]{1,10}");
			 
			Matcher titleMatcher = titlePattern.matcher(title);
			Matcher salaryMatcher = salaryPattern.matcher(salaryAsString);
			
			boolean titleMatches = titleMatcher.matches();
			boolean salaryMatches = salaryMatcher.matches();
			
			//Prepare error message list
			ArrayList<String> errorMessage = new ArrayList<String>();
			if(titleMatches == false) errorMessage.add("wrong format for title");
			if(salaryMatches == false) errorMessage.add("wrong format for salary");
			
			//true if every field matches its regex, false otherwise
			boolean dataIsValid = titleMatches && salaryMatches;
			
			if(dataIsValid == true)
			{
				selectedPosition.setTitle(title);
				selectedPosition.setSalary(salary);
				
				model.update(selectedPosition);
				
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
		
		Position foundPosition = model.find(rowName, rowValue);
		if(foundPosition != null)
		{
			searchView.DisplayResultPopup(foundPosition);
		}
		else
		{
			searchView.DisplayNoResultPopup();
		}	
	}
	
}
