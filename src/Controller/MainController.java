package Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import View.MainView;

public class MainController 
{
	private MainView mainView;
	
	public MainController(MainView mainView)
	{
		this.mainView = mainView;
		
		mainView.ClientAddListener(new ClientAddListener());
		mainView.ClientListListener(new ClientListListener());
		mainView.ClientSearchListener(new ClientSearchListener());
	}
	
	
	class ClientAddListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.ActionCommited();	
		}
	}
	
	class ClientListListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.ActionCommited();	
		}
	}
	
	class ClientSearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.ActionCommited();	
		}
	}
	
}


