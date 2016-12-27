package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Model.WorkerTableModel;

public class WorkerListView extends JPanel
{
	public JTable workerList;
	public JScrollPane scrollableWorkerList;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");
	
	//TODO: move to controller somehow
	public WorkerTableModel workerTableModel;
	
	public WorkerListView(WorkerTableModel workerTableModel)
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(2,1));
		
		
		//LIST		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(1,1));
		
		this.workerTableModel = workerTableModel;
		this.workerList = new JTable(this.workerTableModel);
		this.workerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.scrollableWorkerList = new JScrollPane(workerList);
		this.scrollableWorkerList.setBackground(backgroundColor);
		
		listPanel.add(scrollableWorkerList);
		this.add(listPanel);
		
		
		//BUTTONS
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(editButton);
		buttonPanel.add(removeButton);
		this.add(buttonPanel);
		
	}
	
	public boolean DisplayRemoveConfirmationPopup()
	{
		JPanel confirmationPanel = new JPanel();
		JLabel confirmationText = new JLabel("Do you really want to delete selected row?");
		confirmationPanel.add(confirmationText);
        int option = JOptionPane.showConfirmDialog(null, confirmationPanel, "Worker removal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option == 0)
        {
        	return true;
        }
        return false;
	}
	
	public void WorkerEditListener(ActionListener workerEditListener) 
	{
		editButton.addActionListener(workerEditListener);
	}
	
	public void WorkerRemoveListener(ActionListener workerRemoveListener) 
	{
		removeButton.addActionListener(workerRemoveListener);
	}
}
