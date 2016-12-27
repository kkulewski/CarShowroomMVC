package View;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CarEditView
{
    JPanel panel = new JPanel(new GridLayout(0, 1));
	
	//BRAND
	JLabel brandLabel = new JLabel("Brand:");
    public JTextField brandField = new JTextField();
    //MODEL
	JLabel modelLabel = new JLabel("Model:");
    public JTextField modelField = new JTextField();
    //PRICE
	JLabel priceLabel = new JLabel("Price:");
    public JTextField priceField = new JTextField();
    
    public CarEditView()
    {
        panel.add(brandLabel);
        panel.add(brandField);
        
        panel.add(modelLabel);
        panel.add(modelField);
        
        panel.add(priceLabel);
        panel.add(priceField);
    }
	
	
    public boolean DisplayPopup() 
    {
        int option = JOptionPane.showConfirmDialog(null, panel, "Edit car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
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
	    
		JLabel text = new JLabel("Car modified succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Car updated", JOptionPane.PLAIN_MESSAGE);
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
