package Controller;

import java.awt.event.ActionListener;

import Model.Client;
import Model.ClientModel;
import Model.ClientTableModel;
import Model.PositionModel;
import Model.PositionTableModel;

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

	
	public MainController(MainView mainView)
	{
		this.mainView = mainView;
		
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
		
		
		//EXAMPLE WORKERS
		
		
		//EXAMPLE CARS
		
		
		//EXAMPLE PURHCASES
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
	
}


