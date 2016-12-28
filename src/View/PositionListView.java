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

import Model.PositionTableModel;

public class PositionListView extends JPanel
{
	public JTable positionList;
	public JScrollPane scrollablePositionList;
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");
	
	//TODO: move to controller somehow
	public PositionTableModel positionTableModel;
	
	public PositionListView(PositionTableModel positionTableModel)
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(3,1));
		
		
		//LIST		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(1,1));
		
		this.positionTableModel = positionTableModel;
		this.positionList = new JTable(this.positionTableModel);
		this.positionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.scrollablePositionList = new JScrollPane(positionList);
		this.scrollablePositionList.setBackground(backgroundColor);
		
		listPanel.add(scrollablePositionList);
		this.add(listPanel);
		
		//DATA2
		JPanel dataPanel2 = new JPanel();
		dataPanel2.setLayout(new GridLayout(0,1));
		this.add(dataPanel2);
		
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
        int option = JOptionPane.showConfirmDialog(null, confirmationPanel, "Position removal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option == 0)
        {
        	return true;
        }
        return false;
	}
	
	public void PositionEditListener(ActionListener positionEditListener) 
	{
		editButton.addActionListener(positionEditListener);
	}
	
	public void PositionRemoveListener(ActionListener positionRemoveListener) 
	{
		removeButton.addActionListener(positionRemoveListener);
	}
}
