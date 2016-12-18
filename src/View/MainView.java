package View;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainView extends JFrame
{
	JMenuBar menuBar;
	JMenu menuMain, menuClient, menuWorker;
	JMenuItem clientAdd, clientList, clientSearch, workerAdd, workerList, workerSearch;
	
	public MainView()
	{
		
		this.setSize(new Dimension(600,400));
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Car Showroom");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuMain = new JMenu("Main");
		menuBar.add(menuMain);
		
		//CLIENT
		menuClient = new JMenu("Client");
		menuBar.add(menuClient);
		
		clientAdd = new JMenuItem("Add new client");
		menuClient.add(clientAdd);
		clientList = new JMenuItem("List clients");
		menuClient.add(clientList);
		clientSearch = new JMenuItem("Search client...");
		menuClient.add(clientSearch);
		
		//WORKER
		menuWorker = new JMenu("Worker");
		menuBar.add(menuWorker);
		
		workerAdd = new JMenuItem("Add new worker");
		menuWorker.add(workerAdd);
		workerList = new JMenuItem("List workers");
		menuWorker.add(workerList);
		workerSearch = new JMenuItem("Search worker...");
		menuWorker.add(workerSearch);
	}
	
	public void ClientAddListener(ActionListener clientAddListener) 
	{
		clientAdd.addActionListener(clientAddListener);
	}
	
	public void ClientListListener(ActionListener clientListListener) 
	{
		clientList.addActionListener(clientListListener);
	}
	
	public void ClientSearchListener(ActionListener clientSearchListener) 
	{
		clientSearch.addActionListener(clientSearchListener);
	}
	
	public void ActionCommited()
	{
		JOptionPane.showMessageDialog(null, "Button clicked");
	}
}
