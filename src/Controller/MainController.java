package Controller;

import java.awt.event.ActionListener;
import Model.ClientModel;
import Model.ClientTableModel;

import java.awt.event.ActionEvent;

import View.ClientAddView;
import View.ClientListView;
import View.ClientSearchView;
import View.MainView;

public class MainController 
{
	private MainView mainView;
	
	private ClientController clientController;
	private ClientAddView clientAddView;
	private ClientListView clientListView;
	private ClientSearchView clientSearchView;
	
	private ClientModel clientModel;

	
	public MainController(MainView mainView)
	{
		this.mainView = mainView;
		
		this.clientModel = new ClientModel();
		this.clientAddView = new ClientAddView();
		this.clientListView = new ClientListView(new ClientTableModel(clientModel.listClient()));
		this.clientSearchView = new ClientSearchView();
		
		this.clientController = new ClientController(clientAddView);
		clientAddView.ClientAddSubmitListener(clientController.clientAddSubmitListener);
		
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
			//mainView.ActionCommited();	
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
			//mainView.ActionCommited();	
			
			//HACK - provide fresh model to make sure data is recent and correct
			clientListView = new ClientListView(new ClientTableModel(clientModel.listClient()));
			
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
			//mainView.ActionCommited();	
			mainView.setContentPane(clientSearchView);
			mainView.invalidate();
			mainView.validate();
		}
	}
	
}


