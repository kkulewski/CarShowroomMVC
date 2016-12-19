package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Model.ClientTableModel;

public class ClientListView extends JPanel
{
	public JTable clientList;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");
	
	//TODO: move to controller somehow
	public ClientTableModel clientTableModel;
	
	public ClientListView(ClientTableModel clientTableModel)
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//EDIT BUTTON
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		this.add(editButton, c);
		//REMOVE BUTTON
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		this.add(removeButton, c);
		
		//TABLE
		//c.fill = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		
		this.clientTableModel = clientTableModel;
		this.clientList = new JTable(this.clientTableModel);
		this.clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(clientList, c);
		
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
