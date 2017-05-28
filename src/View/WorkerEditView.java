package View;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class WorkerEditView
{
    JPanel panel = new JPanel(new GridLayout(0, 1));
	
	//NAME
	JLabel nameLabel = new JLabel("Name:");
    public JTextField nameField = new JTextField();
    //SURNAME
	JLabel surnameLabel = new JLabel("Surname:");
    public JTextField surnameField = new JTextField();
    //CNUM
	JLabel cnumLabel = new JLabel("CNUM:");
    public JTextField cnumField = new JTextField();
    //CITY
	JLabel cityLabel = new JLabel("City:");
    public JTextField cityField = new JTextField();
    //STREET
	JLabel streetLabel = new JLabel("Street:");
    public JTextField streetField = new JTextField();
    //POSITION
    JLabel positionLabel = new JLabel("Position:");
    public JComboBox positionCombo;
    
    public WorkerEditView()
    {
        panel.add(nameLabel);
        panel.add(nameField);
        
        panel.add(surnameLabel);
        panel.add(surnameField);
        
        panel.add(cnumLabel);
        panel.add(cnumField);
        
        panel.add(cityLabel);
        panel.add(cityField);
        
        panel.add(streetLabel);
        panel.add(streetField);
        
		//POSITION
        panel.add(positionLabel);
	    positionCombo = new JComboBox<String>();
	    JScrollPane positionScrollPane = new JScrollPane(positionCombo);    
		panel.add(positionScrollPane);
    }
	
	
    public boolean DisplayPopup() 
    {
        int option = JOptionPane.showConfirmDialog(null, panel, "Edit worker", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
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
	    
		JLabel text = new JLabel("Worker modified succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Worker updated", JOptionPane.PLAIN_MESSAGE);
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
