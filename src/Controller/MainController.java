package Controller;

import java.awt.event.ActionListener;
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

	
	public MainController(MainView mainView)
	{
		this.mainView = mainView;
		
		this.clientAddView = new ClientAddView();
		this.clientListView = new ClientListView();
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


