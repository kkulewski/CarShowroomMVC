package View;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import Model.ClientTableModel;

public class ClientListView extends JPanel
{
	public JTable clientList;
	
	public ClientListView(ClientTableModel clientTableModel)
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridBagLayout());
		
		this.clientList = new JTable(clientTableModel);
		this.add(clientList);
		
	}
}
