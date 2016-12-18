package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainView extends JFrame
{
	JMenuBar menuBar;
	JMenu menuMain, menuClient;
	JMenuItem clientAdd, clientList, clientSearch;
	
	public MainView()
	{
		
		this.setSize(new Dimension(600,400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new CardLayout());
		this.setTitle("Car Showroom");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuMain = new JMenu("Main");
		menuBar.add(menuMain);
		
		
		menuClient = new JMenu("Client");
		menuBar.add(menuClient);
		
		clientAdd = new JMenuItem("Add new client");
		menuClient.add(clientAdd);
		clientList = new JMenuItem("List clients");
		menuClient.add(clientList);
		clientSearch = new JMenuItem("Search client...");
		menuClient.add(clientSearch);
		
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
