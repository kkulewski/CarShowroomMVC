package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Car;
import Model.CarModel;
import View.CarAddView;
import View.CarEditView;
import View.CarListView;
import View.CarSearchView;

public class CarController 
{
	CarModel model = new CarModel();
	CarAddView addView;
	CarListView listView;
	CarEditView editView;
	CarSearchView searchView;
	
	CarAddSubmitListener addSubmitListener;
	CarSearchSubmitListener searchSubmitListener;
	
	public CarController(CarAddView addView, CarListView listView, CarEditView editView, CarSearchView searchView)
	{
		this.addView = addView;
		this.listView = listView;
		this.editView = editView;
		this.searchView = searchView;
		
		this.addSubmitListener = new CarAddSubmitListener();
		this.searchSubmitListener = new CarSearchSubmitListener();
		
		this.listView.CarEditListener(new CarEditListener());
		this.listView.CarRemoveListener(new CarRemoveListener());
	}
	
	public void RefreshView()
	{
		//reload carTable data from DB
		listView.carTableModel.ReloadCarTable(model.list());
		listView.carList.clearSelection();
		//update view
		listView.scrollableCarList.repaint();
		listView.invalidate();
		listView.validate();
	}
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class CarAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			Insert();
		}
	}
	
	class CarEditListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Update();
		}
	}
	
	class CarRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Delete();
		}
	}
	
	class CarSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Find();
		}
	}
	
	public void Insert()
	{
		String brand = addView.brandField.getText();
		String car_model = addView.modelField.getText();
		double price = 0;
		String priceAsString = "0";
		boolean isNew = addView.isNewField.isSelected();
		try
		{
			price = Double.parseDouble(addView.priceField.getText());
			priceAsString = addView.priceField.getText();
		}
		catch(Exception e)
		{
			price = 0;
			priceAsString = "0";
		}
		
		//REGEX VALIDATION
		Pattern brandPattern = Pattern.compile("[a-zA-Z]{1,20}");
		Pattern car_modelPattern = Pattern.compile("[a-zA-Z1-9]{1,20}");
		Pattern pricePattern = Pattern.compile("[0-9]{1,10}[.]{0,1}[0-9]{0,2}");
		 
		Matcher brandMatcher = brandPattern.matcher(brand);
		Matcher car_modelMatcher = car_modelPattern.matcher(car_model);
		Matcher priceMatcher = pricePattern.matcher(priceAsString);
		
		boolean brandMatches = brandMatcher.matches();
		boolean car_modelMatches = car_modelMatcher.matches();
		boolean priceMatches = priceMatcher.matches();
		
		//Prepare error message list
		ArrayList<String> errorMessage = new ArrayList<String>();
		if(brandMatches == false) errorMessage.add("wrong format for brand");
		if(car_modelMatches == false) errorMessage.add("wrong format for model");
		if(priceMatches == false) errorMessage.add("wrong format for price");
		
		//true if every field matches its regex, false otherwise
		boolean dataIsValid = brandMatches && car_modelMatches && priceMatches;
		
		if(dataIsValid == true)
		{
			int tempId = 0;
			Car tcar = new Car(tempId, brand, car_model, price, isNew);
			
			model.insert(tcar);
			
			addView.brandField.setText("");
			addView.modelField.setText("");
			addView.priceField.setText("");
			addView.isNewField.setSelected(false);
			
			addView.DisplaySuccessPopup();
		}
		else
		{
			addView.DisplayErrorPopup(errorMessage);
		}	
	}
	
	public void Delete()
	{
		int selectedRow = listView.carList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Car selectedCar = listView.carTableModel.getCar(selectedRow);
		
		//CONFIRMATION POPUP
		if(listView.DisplayRemoveConfirmationPopup() == true)
		{
			model.delete(selectedCar);
		}
		RefreshView();
	}
	
	public void Update()
	{
		int selectedRow = listView.carList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Car selectedCar = listView.carTableModel.getCar(selectedRow);
		
		// FILL POPUP FIELDS WITH SELECTED ITEM DATA
		editView.brandField.setText(selectedCar.getBrand());
		editView.modelField.setText(selectedCar.getModel());
		editView.priceField.setText(String.valueOf(selectedCar.getPrice()));
		editView.isNewField.setSelected(selectedCar.getIsNew());
		
		boolean itemModified = editView.DisplayPopup();
		
		if(itemModified == true)
		{
			//GET NEW DATA FROM POPUP
			String brand = editView.brandField.getText();
			String car_model = editView.modelField.getText();
			double price = 0;
			String priceAsString = "0";
			boolean isNew = editView.isNewField.isSelected();
			try
			{
				price = Double.parseDouble(editView.priceField.getText());
				priceAsString = String.valueOf(price);
			}
			catch(Exception e)
			{
				price = 0;
				priceAsString = "0";
			}
			
			//REGEX VALIDATION
			Pattern brandPattern = Pattern.compile("[a-zA-Z]{1,20}");
			Pattern car_modelPattern = Pattern.compile("[a-zA-Z1-9]{1,20}");
			Pattern pricePattern = Pattern.compile("[0-9]{1,10}[.]{0,1}[0-9]{0,2}");
			 
			Matcher brandMatcher = brandPattern.matcher(brand);
			Matcher car_modelMatcher = car_modelPattern.matcher(car_model);
			Matcher priceMatcher = pricePattern.matcher(priceAsString);
			
			boolean brandMatches = brandMatcher.matches();
			boolean car_modelMatches = car_modelMatcher.matches();
			boolean priceMatches = priceMatcher.matches();
			
			//Prepare error message list
			ArrayList<String> errorMessage = new ArrayList<String>();
			if(brandMatches == false) errorMessage.add("wrong format for brand");
			if(car_modelMatches == false) errorMessage.add("wrong format for model");
			if(priceMatches == false) errorMessage.add("wrong format for price");
			
			//true if every field matches its regex, false otherwise
			boolean dataIsValid = brandMatches && car_modelMatches && priceMatches;
			
			if(dataIsValid == true)
			{
				selectedCar.setBrand(brand);
				selectedCar.setModel(car_model);
				selectedCar.setPrice(price);
				selectedCar.setIsNew(isNew);
				
				model.update(selectedCar);
				
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
		
		Car foundCar = model.find(rowName, rowValue);
		if(foundCar != null)
		{
			searchView.DisplayResultPopup(foundCar);
		}
		else
		{
			searchView.DisplayNoResultPopup();
		}	
	}
	
}
