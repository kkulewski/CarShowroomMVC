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
		
		mainView.AddClientListener(new addClientListener());
	}
	
	
	class addClientListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			mainView.ActionCommited();	
		}
	}
	
}


