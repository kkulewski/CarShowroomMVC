package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Position;
import Model.PositionModel;

public class PurchaseEditView
{
    JPanel panel = new JPanel(new GridLayout(0, 1));
	
    //CLIENT
    JLabel clientLabel = new JLabel("Client:");
    public JComboBox clientCombo;
    //WORKER
    JLabel workerLabel = new JLabel("Worker:");
    public JComboBox workerCombo;
    //CAR
    JLabel carLabel = new JLabel("Car:");
    public JComboBox carCombo;
	//DATE
	JLabel transaction_dateLabel = new JLabel("Date:");
    public JTextField transaction_dateField = new JTextField();
    
    public PurchaseEditView()
    {
    	
		//CLIENT
        panel.add(clientLabel);
	    clientCombo = new JComboBox<String>();
	    JScrollPane clientScrollPane = new JScrollPane(clientCombo);    
		panel.add(clientScrollPane);
		
		//WORKER
        panel.add(workerLabel);
	    workerCombo = new JComboBox<String>();
	    JScrollPane workerScrollPane = new JScrollPane(workerCombo);    
		panel.add(workerScrollPane);
		
		//CAR
        panel.add(carLabel);
	    carCombo = new JComboBox<String>();
	    JScrollPane carScrollPane = new JScrollPane(carCombo);    
		panel.add(carScrollPane);
		
		
        panel.add(transaction_dateLabel);
        panel.add(transaction_dateField);
    }
	
	
    public boolean DisplayPopup() 
    {
        int option = JOptionPane.showConfirmDialog(null, panel, "Edit purchase", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        //IF MODIFIED RETURN TRUE
        if (option == JOptionPane.OK_OPTION) 
        {
            return true;
        }
        return false;
    }
    
	public void DisplaySuccessPopup()
	{
		JPanel panel = new JPanel(new GridLayout(0, 1));
	    
		JLabel text = new JLabel("Purchase modified succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Purchase updated", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void DisplayErrorPopup(List<String> errorMessage)
	{
		JPanel panel = new JPanel(new GridLayout(0, 1));
	    
		JLabel text = new JLabel("Error: ");
		panel.add(text);
		for(String s : errorMessage)
		{
			panel.add(new JLabel(s));
		}
		
		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.PLAIN_MESSAGE);	
	}
	
}
