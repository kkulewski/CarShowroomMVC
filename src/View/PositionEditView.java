package View;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PositionEditView
{
    JPanel panel = new JPanel(new GridLayout(0, 1));
	
	//TITLE
	JLabel titleLabel = new JLabel("Title:");
    public JTextField titleField = new JTextField();
    //SALARY
	JLabel salaryLabel = new JLabel("Salary:");
    public JTextField salaryField = new JTextField();
    
    public PositionEditView()
    {
        panel.add(titleLabel);
        panel.add(titleField);
        
        panel.add(salaryLabel);
        panel.add(salaryField);
        
    }
	
	
    public boolean DisplayPopup() 
    {
        int option = JOptionPane.showConfirmDialog(null, panel, "Edit position", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
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
	    
		JLabel text = new JLabel("Position modified succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Position updated", JOptionPane.PLAIN_MESSAGE);
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
