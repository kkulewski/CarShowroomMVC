package View;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientEditView
{
    JPanel panel = new JPanel(new GridLayout(0, 1));
	
	//NAME
    public String name;
	JLabel nameLabel = new JLabel("Name:");
    public JTextField nameField = new JTextField();
    //SURNAME
    public String surname;
	JLabel surnameLabel = new JLabel("Surname:");
    public JTextField surnameField = new JTextField();
    //PESEL
    public Long pesel;
	JLabel peselLabel = new JLabel("PESEL:");
    public JTextField peselField = new JTextField();
    //CITY
    public String city;
	JLabel cityLabel = new JLabel("City:");
    public JTextField cityField = new JTextField();
    //STREET
    public String street;
	JLabel streetLabel = new JLabel("Street:");
    public JTextField streetField = new JTextField();
    
    public ClientEditView()
    {
        panel.add(nameLabel);
        panel.add(nameField);
        
        panel.add(surnameLabel);
        panel.add(surnameField);
        
        panel.add(peselLabel);
        panel.add(peselField);
        
        panel.add(cityLabel);
        panel.add(cityField);
        
        panel.add(streetLabel);
        panel.add(streetField);
    }
	
	
    public boolean DisplayPopup() 
    {
        int option = JOptionPane.showConfirmDialog(null, panel, "Edit client", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        //IF MODIFIED RETURN TRUE
        if (option == JOptionPane.OK_OPTION) 
        {
        	//SAVE NEW DATA
        	name = nameField.getText();
        	surname = surnameField.getText();
        	pesel = Long.valueOf(peselField.getText());
        	city = cityField.getText();
        	street = streetField.getText();
        	
            return true;
        }
        return false;
    }
	
}
