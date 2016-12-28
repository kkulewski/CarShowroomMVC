package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;

import Model.Car;
import Model.CarModel;
import Model.Client;
import Model.ClientModel;
import Model.Position;
import Model.PositionModel;
import Model.Purchase;
import Model.PurchaseModel;
import Model.Worker;
import Model.WorkerModel;
import View.PurchaseAddView;
import View.PurchaseEditView;
import View.PurchaseListView;
import View.PurchaseSearchView;

public class PurchaseController 
{
	PurchaseModel model = new PurchaseModel();
	PurchaseAddView addView;
	PurchaseListView listView;
	PurchaseEditView editView;
	PurchaseSearchView searchView;
	
	PurchaseAddSubmitListener addSubmitListener;
	PurchaseSearchSubmitListener searchSubmitListener;
	
	public PurchaseController(PurchaseAddView addView, PurchaseListView listView, PurchaseEditView editView, PurchaseSearchView searchView)
	{
		this.addView = addView;
		this.listView = listView;
		this.editView = editView;
		this.searchView = searchView;
		
		this.addSubmitListener = new PurchaseAddSubmitListener();
		this.searchSubmitListener = new PurchaseSearchSubmitListener();
		
		this.listView.PurchaseEditListener(new PurchaseEditListener());
		this.listView.PurchaseRemoveListener(new PurchaseRemoveListener());
	}
	
	public void RefreshView()
	{
		//reload purchaseTable data from DB
		listView.purchaseTableModel.ReloadPurchaseTable(model.list());
		listView.purchaseList.clearSelection();
		//update view
		listView.scrollablePurchaseList.repaint();
		listView.invalidate();
		listView.validate();
	}
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class PurchaseAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			Insert();
		}
	}
	
	class PurchaseEditListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Update();
		}
	}
	
	class PurchaseRemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Delete();
		}
	}
	
	class PurchaseSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Find();
		}
	}
	
	public void Insert()
	{
		//CLIENT FROM COMBOBOX
		ClientModel clientModel = new ClientModel();
		String selectedClient = (String)addView.clientCombo.getSelectedItem();
		Client foundClient = clientModel.find("surname", selectedClient);
		int clientId = foundClient.getId();
		//WORKER FROM COMBOBOX
		WorkerModel workerModel = new WorkerModel();
		String selectedWorker = (String)addView.workerCombo.getSelectedItem();
		Worker foundWorker = workerModel.find("surname", selectedWorker);
		int workerId = foundWorker.getId();
		//CAR FROM COMBOBOX
		CarModel carModel = new CarModel();
		String selectedCar = (String)addView.carCombo.getSelectedItem();
		Car foundCar = carModel.find("model", selectedCar);
		int carId = foundCar.getId();
		//DATE
		String transaction_date = addView.transaction_dateField.getText();
		
		//REGEX VALIDATION
		Pattern transaction_datePattern = Pattern.compile("[1-2][1-9][1-9][1-9][-][0-1][0-9][-][0-3][0-9]");
		 
		Matcher transaction_dateMatcher = transaction_datePattern.matcher(transaction_date);
		
		boolean transaction_dateMatches = transaction_dateMatcher.matches();
		
		//Prepare error message list
		ArrayList<String> errorMessage = new ArrayList<String>();
		if(transaction_dateMatches == false) errorMessage.add("wrong format for date");
		
		//true if every field matches its regex, false otherwise
		boolean dataIsValid = transaction_dateMatches;
		
		if(dataIsValid == true)
		{
			int tempId = 0;
			Purchase purchase = new Purchase(tempId, clientId, workerId, carId, Date.valueOf(transaction_date));
			
			model.insert(purchase);
			
			addView.transaction_dateField.setText("");
			
			addView.DisplaySuccessPopup();
		}
		else
		{
			addView.DisplayErrorPopup(errorMessage);
		}	
	}
	
	public void Delete()
	{
		int selectedRow = listView.purchaseList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Purchase selectedPurchase = listView.purchaseTableModel.getPurchase(selectedRow);
		
		//CONFIRMATION POPUP
		if(listView.DisplayRemoveConfirmationPopup() == true)
		{
			model.delete(selectedPurchase);
		}
		RefreshView();
	}
	
	public void Update()
	{
		int selectedRow = listView.purchaseList.getSelectedRow();
		//RETURN IF NO ROW SELECTED
		if(selectedRow == -1)
			return;
		
		Purchase selectedPurchase = listView.purchaseTableModel.getPurchase(selectedRow);
		
		// FILL POPUP FIELDS WITH SELECTED ITEM DATA
		ClientModel clientModel = new ClientModel();
		ArrayList<Client> clientList = new ArrayList<Client>();
		clientList = clientModel.list();
		this.editView.clientCombo.removeAllItems();
	    for(Client c : clientList)
	    {
	    	this.editView.clientCombo.addItem(c.getSurname());
	    }
	    int purchase_clientId = selectedPurchase.getId_client();
	    int clientId = purchase_clientId;
	    Client purchase_client = clientModel.find("id_client", String.valueOf(purchase_clientId));
	    String purchase_clientSurname = purchase_client.getSurname();
	    this.editView.clientCombo.setSelectedItem(purchase_clientSurname);
	    
		WorkerModel workerModel = new WorkerModel();
		ArrayList<Worker> workerList = new ArrayList<Worker>();
		workerList = workerModel.list();
		this.editView.workerCombo.removeAllItems();
	    for(Worker w : workerList)
	    {
	    	this.editView.workerCombo.addItem(w.getSurname());
	    }
	    int purchase_workerId = selectedPurchase.getId_worker();
	    int workerId = purchase_workerId;
	    Worker purchase_worker = workerModel.find("id_worker", String.valueOf(purchase_workerId));
	    String purchase_workerSurname = purchase_worker.getSurname();
	    this.editView.workerCombo.setSelectedItem(purchase_workerSurname);
	    
		CarModel carModel = new CarModel();
		ArrayList<Car> carList = new ArrayList<Car>();
		carList = carModel.list();
		this.editView.carCombo.removeAllItems();
	    for(Car c : carList)
	    {
	    	this.editView.carCombo.addItem(c.getModel());
	    }
	    int purchase_carId = selectedPurchase.getId_car();
	    int carId = purchase_carId;
	    Car purchase_car = carModel.find("id_car", String.valueOf(purchase_carId));
	    String purchase_carModel = purchase_car.getModel();
	    this.editView.carCombo.setSelectedItem(purchase_carModel);
		
		
		
		editView.transaction_dateField.setText(String.valueOf(selectedPurchase.getTransaction_date()));
		
		boolean itemModified = editView.DisplayPopup();
		
		if(itemModified == true)
		{
			//GET NEW DATA FROM POPUP
			Date transaction_date = new Date(2000, 1, 1);
			String transaction_dateAsString = "2000-01-01";
			try
			{
				transaction_date = Date.valueOf((editView.transaction_dateField.getText()));
				transaction_dateAsString = String.valueOf(transaction_date);
			}
			catch(Exception e)
			{
				transaction_date = new Date(2000, 1, 1);
				transaction_dateAsString = "2000-01-01";
			}
			//CLIENT FROM COMBOBOX
			String selectedClient = (String)editView.clientCombo.getSelectedItem();
			Client foundClient = clientModel.find("surname", selectedClient);
			clientId = foundClient.getId();
			//WORKER FROM COMBOBOX
			String selectedWorker = (String)editView.workerCombo.getSelectedItem();
			Worker foundWorker = workerModel.find("surname", selectedWorker);
			workerId = foundWorker.getId();
			//CAR FROM COMBOBOX
			String selectedCar = (String)editView.carCombo.getSelectedItem();
			Car foundCar = carModel.find("model", selectedCar);
			carId = foundCar.getId();
			
			//REGEX VALIDATION
			Pattern transaction_datePattern = Pattern.compile("[1-2][1-9][1-9][1-9][-][0-1][0-9][-][0-3][0-9]");
			 
			Matcher transaction_dateMatcher = transaction_datePattern.matcher(transaction_dateAsString);
			
			boolean transaction_dateMatches = transaction_dateMatcher.matches();
			
			//Prepare error message list
			ArrayList<String> errorMessage = new ArrayList<String>();
			if(transaction_dateMatches == false) errorMessage.add("wrong format for date");
			
			//true if every field matches its regex, false otherwise
			boolean dataIsValid = transaction_dateMatches;
			
			if(dataIsValid == true)
			{
				selectedPurchase.setId_client(clientId);
				selectedPurchase.setId_worker(workerId);
				selectedPurchase.setId_car(carId);
				selectedPurchase.setTransaction_date(transaction_date);	
				
				model.update(selectedPurchase);
				
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
		
		Purchase foundPurchase = model.find(rowName, rowValue);
		if(foundPurchase != null)
		{
			searchView.DisplayResultPopup(foundPurchase);
		}
		else
		{
			searchView.DisplayNoResultPopup();
		}	
	}
	
}
