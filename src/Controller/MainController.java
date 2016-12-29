package Controller;

import java.awt.event.ActionListener;
import java.sql.Date;

import Model.Client;
import Model.ClientModel;
import Model.ClientTableModel;
import Model.Position;
import Model.PositionModel;
import Model.PositionTableModel;
import Model.Purchase;
import Model.PurchaseModel;
import Model.PurchaseTableModel;
import Model.Car;
import Model.CarModel;
import Model.CarTableModel;
import Model.Worker;
import Model.WorkerModel;
import Model.WorkerTableModel;

import java.awt.event.ActionEvent;

import View.ClientAddView;
import View.ClientEditView;
import View.ClientListView;
import View.ClientSearchView;
import View.MainView;
import View.PositionAddView;
import View.PositionEditView;
import View.PositionListView;
import View.PositionSearchView;
import View.PurchaseAddView;
import View.PurchaseEditView;
import View.PurchaseListView;
import View.PurchaseSearchView;
import View.CarAddView;
import View.CarEditView;
import View.CarListView;
import View.CarSearchView;
import View.WorkerAddView;
import View.WorkerEditView;
import View.WorkerListView;
import View.WorkerSearchView;

public class MainController 
{
	private MainView mainView;
	
	private ClientController clientController;
	private ClientAddView clientAddView;
	private ClientListView clientListView;
	private ClientEditView clientEditView;
	private ClientSearchView clientSearchView;

	public ClientModel clientModel;
	private ClientTableModel clientTableModel;
	
	private PositionController positionController;
	private PositionAddView positionAddView;
	private PositionListView positionListView;
	private PositionEditView positionEditView;
	private PositionSearchView positionSearchView;
	
	public PositionModel positionModel;
	private PositionTableModel positionTableModel;
	
	private CarController carController;
	private CarAddView carAddView;
	private CarListView carListView;
	private CarEditView carEditView;
	private CarSearchView carSearchView;
	
	public CarModel carModel;
	private CarTableModel carTableModel;
	
	private WorkerController workerController;
	private WorkerAddView workerAddView;
	private WorkerListView workerListView;
	private WorkerEditView workerEditView;
	private WorkerSearchView workerSearchView;
	
	public WorkerModel workerModel;
	private WorkerTableModel workerTableModel;

	private PurchaseController purchaseController;
	private PurchaseAddView purchaseAddView;
	private PurchaseListView purchaseListView;
	private PurchaseEditView purchaseEditView;
	private PurchaseSearchView purchaseSearchView;
	
	public PurchaseModel purchaseModel;
	private PurchaseTableModel purchaseTableModel;
	
	public MainController(MainView mainView)
	{
		this.mainView = mainView;
		mainView.TestDataListener(new TestDataListener());
		
		this.clientModel = new ClientModel();
		this.clientAddView = new ClientAddView();
		this.clientTableModel = new ClientTableModel(clientModel.list());
		this.clientListView = new ClientListView(clientTableModel);
		this.clientEditView = new ClientEditView();
		this.clientSearchView = new ClientSearchView();
		
		this.clientController = new ClientController(clientAddView, clientListView, clientEditView, clientSearchView);
		clientAddView.ClientAddSubmitListener(clientController.addSubmitListener);
		clientSearchView.ClientSearchSubmitListener(clientController.searchSubmitListener);
		
		mainView.ClientAddListener(new ClientAddListener());
		mainView.ClientListListener(new ClientListListener());
		mainView.ClientSearchListener(new ClientSearchListener());
		
		this.positionModel = new PositionModel();
		this.positionAddView = new PositionAddView();
		this.positionTableModel = new PositionTableModel(positionModel.list());
		this.positionListView = new PositionListView(positionTableModel);
		this.positionEditView = new PositionEditView();
		this.positionSearchView = new PositionSearchView();
		
		this.positionController = new PositionController(positionAddView, positionListView, positionEditView, positionSearchView);
		positionAddView.PositionAddSubmitListener(positionController.addSubmitListener);
		positionSearchView.PositionSearchSubmitListener(positionController.searchSubmitListener);
		
		mainView.PositionAddListener(new PositionAddListener());
		mainView.PositionListListener(new PositionListListener());
		mainView.PositionSearchListener(new PositionSearchListener());
		
		this.carModel = new CarModel();
		this.carAddView = new CarAddView();
		this.carTableModel = new CarTableModel(carModel.list());
		this.carListView = new CarListView(carTableModel);
		this.carEditView = new CarEditView();
		this.carSearchView = new CarSearchView();
		
		this.carController = new CarController(carAddView, carListView, carEditView, carSearchView);
		carAddView.CarAddSubmitListener(carController.addSubmitListener);
		carSearchView.CarSearchSubmitListener(carController.searchSubmitListener);
		
		mainView.CarAddListener(new CarAddListener());
		mainView.CarListListener(new CarListListener());
		mainView.CarSearchListener(new CarSearchListener());
		
		this.workerModel = new WorkerModel();
		this.workerAddView = new WorkerAddView();
		this.workerTableModel = new WorkerTableModel(workerModel.list());
		this.workerListView = new WorkerListView(workerTableModel);
		this.workerEditView = new WorkerEditView();
		this.workerSearchView = new WorkerSearchView();
		
		this.workerController = new WorkerController(workerAddView, workerListView, workerEditView, workerSearchView);
		workerAddView.WorkerAddSubmitListener(workerController.addSubmitListener);
		workerSearchView.WorkerSearchSubmitListener(workerController.searchSubmitListener);
		
		mainView.WorkerAddListener(new WorkerAddListener());
		mainView.WorkerListListener(new WorkerListListener());
		mainView.WorkerSearchListener(new WorkerSearchListener());

		this.purchaseModel = new PurchaseModel();
		this.purchaseAddView = new PurchaseAddView();
		this.purchaseTableModel = new PurchaseTableModel(purchaseModel.list());
		this.purchaseListView = new PurchaseListView(purchaseTableModel);
		this.purchaseEditView = new PurchaseEditView();
		this.purchaseSearchView = new PurchaseSearchView();
		
		this.purchaseController = new PurchaseController(purchaseAddView, purchaseListView, purchaseEditView, purchaseSearchView);
		purchaseAddView.PurchaseAddSubmitListener(purchaseController.addSubmitListener);
		purchaseSearchView.PurchaseSearchSubmitListener(purchaseController.searchSubmitListener);
		
		mainView.PurchaseAddListener(new PurchaseAddListener());
		mainView.PurchaseListListener(new PurchaseListListener());
		mainView.PurchaseSearchListener(new PurchaseSearchListener());
	
	}
	
	public void Run()
	{
		//
	}
	
	public void FillWithTestData()
	{
		//EXAMPLE CLIENTS
		clientModel.insert(new Client(1, "Jan", "Kowalski", 12233455666L, "Warszawa", "Dluga 3"));
		clientModel.insert(new Client(2, "Janusz", "Nowak", 87213452266L, "Gdansk", "Krotka 4a"));
		clientModel.insert(new Client(3, "Michal", "Malinowski", 77733755116L, "Gdynia", "Srednia 70"));
		clientModel.insert(new Client(4, "Dawid", "Wisniewski", 65413415261L, "Sopot", "Szeroka 1A"));
		clientModel.insert(new Client(5, "Mateusz", "Grucha", 55131477335L, "Poznan", "Waska 11"));
		
		//EXAMPLE POSITIONS
		positionModel.insert(new Position(1, "Sprzedawca", 2500));
		positionModel.insert(new Position(2, "Ksiegowy", 3500));
		positionModel.insert(new Position(3, "Manager", 8000));
		positionModel.insert(new Position(4, "Stazysta", 1200));
		
		//EXAMPLE WORKERS
		workerModel.insert(new Worker(1, "Dawid", "Jablonski", 67231155411L, "Gdansk", "Diamentowa 1", 1));
		workerModel.insert(new Worker(2, "Mateusz", "Sosnowski", 55513455565L, "Warszawa", "Opalowa 7b", 2));
		workerModel.insert(new Worker(3, "Lukasz", "Swierk", 32131755221L, "Katowice", "Brylantowa 12", 3));
		
		
		//EXAMPLE CARS
		carModel.insert(new Car(1, "Audi", "A7", 650000));
		carModel.insert(new Car(2, "VW", "Golf", 90000));
		carModel.insert(new Car(3, "VW", "Passat", 160000));
		carModel.insert(new Car(4, "Audi", "A3", 125000));
		
		//EXAMPLE PURCHASES
		purchaseModel.insert(new Purchase(1, 1, 1, 1, Date.valueOf("2000-01-01")));
		purchaseModel.insert(new Purchase(2, 2, 1, 2, Date.valueOf("2010-10-10")));
		purchaseModel.insert(new Purchase(3, 3, 3, 3, Date.valueOf("2005-05-05")));
	}
	
	
	//TODO: BETTER NAMING FOR EVENT HANDLERS
	class ClientAddListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			mainView.setContentPane(clientAddView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class ClientListListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//TODO: make TableModel update itself
			//HACK - update data in TableModel (flush and load from db)
			clientListView.clientTableModel.ReloadClientTable(clientModel.list());
			//HACK
			
			mainView.setContentPane(clientListView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class ClientSearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.setContentPane(clientSearchView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class PositionAddListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			mainView.setContentPane(positionAddView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class PositionListListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//TODO: make TableModel update itself
			//HACK - update data in TableModel (flush and load from db)
			positionListView.positionTableModel.ReloadPositionTable(positionModel.list());
			//HACK
			
			mainView.setContentPane(positionListView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class PositionSearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.setContentPane(positionSearchView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class CarAddListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			mainView.setContentPane(carAddView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class CarListListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//TODO: make TableModel update itself
			//HACK - update data in TableModel (flush and load from db)
			carListView.carTableModel.ReloadCarTable(carModel.list());
			//HACK
			
			mainView.setContentPane(carListView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class CarSearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.setContentPane(carSearchView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class WorkerAddListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			mainView.setContentPane(workerAddView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class WorkerListListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//TODO: make TableModel update itself
			//HACK - update data in TableModel (flush and load from db)
			workerListView.workerTableModel.ReloadWorkerTable(workerModel.list());
			//HACK
			
			mainView.setContentPane(workerListView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class WorkerSearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.setContentPane(workerSearchView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class PurchaseAddListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			mainView.setContentPane(purchaseAddView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class PurchaseListListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//TODO: make TableModel update itself
			//HACK - update data in TableModel (flush and load from db)
			purchaseListView.purchaseTableModel.ReloadPurchaseTable(purchaseModel.list());
			//HACK
			
			mainView.setContentPane(purchaseListView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class PurchaseSearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.setContentPane(purchaseSearchView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
	class TestDataListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			FillWithTestData();
		}
	}
	
}
