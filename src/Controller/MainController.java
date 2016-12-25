package Controller;

import java.awt.event.ActionListener;
import Model.ClientModel;
import Model.ClientTableModel;

import java.awt.event.ActionEvent;

import View.ClientAddView;
import View.ClientEditView;
import View.ClientListView;
import View.ClientSearchView;
import View.MainView;

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

	
	public MainController(MainView mainView)
	{
		this.mainView = mainView;
		
		this.clientModel = new ClientModel();
		this.clientAddView = new ClientAddView();
		this.clientTableModel = new ClientTableModel(clientModel.listClient());
		this.clientListView = new ClientListView(clientTableModel);
		this.clientEditView = new ClientEditView();
		this.clientSearchView = new ClientSearchView();
		
		this.clientController = new ClientController(clientAddView, clientListView, clientEditView, clientSearchView);
		clientAddView.ClientAddSubmitListener(clientController.clientAddSubmitListener);
		clientSearchView.ClientSearchSubmitListener(clientController.clientSearchSubmitListener);
		
		mainView.ClientAddListener(new ClientAddListener());
		mainView.ClientListListener(new ClientListListener());
		mainView.ClientSearchListener(new ClientSearchListener());
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
			clientListView.clientTableModel.ReloadClientTable(clientModel.listClient());
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
	
}


