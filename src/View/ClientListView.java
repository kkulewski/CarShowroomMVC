package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Model.ClientTableModel;

public class ClientListView extends JPanel
{
	public JTable clientList;
	public JScrollPane scrollableClientList;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");
	
	//TODO: move to controller somehow
	public ClientTableModel clientTableModel;
	
	public ClientListView(ClientTableModel clientTableModel)
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(2,1));
		
		
		//LIST		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(1,1));
		
		this.clientTableModel = clientTableModel;
		this.clientList = new JTable(this.clientTableModel);
		this.clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.scrollableClientList = new JScrollPane(clientList);
		this.scrollableClientList.setBackground(backgroundColor);
		
		listPanel.add(scrollableClientList);
		this.add(listPanel);
		
		
		//BUTTONS
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(editButton);
		buttonPanel.add(removeButton);
		this.add(buttonPanel);
		
	}
	
	public void ClientEditListener(ActionListener clientEditListener) 
	{
		editButton.addActionListener(clientEditListener);
	}
	
	public void ClientRemoveListener(ActionListener clientRemoveListener) 
	{
		removeButton.addActionListener(clientRemoveListener);
	}
}
