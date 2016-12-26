package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientAddView extends JPanel
{
	//NAME
	public JTextField nameField = new JTextField(20);
	JLabel nameLabel = new JLabel("Name:");
	
	//SURNAME
	public JTextField surnameField = new JTextField(20);
	JLabel surnameLabel = new JLabel("Surname:");
	
	//PESEL
	public JTextField peselField = new JTextField(20);
	JLabel peselLabel = new JLabel("PESEL:");
	
	//CITY
	public JTextField cityField = new JTextField(20);
	JLabel cityLabel = new JLabel("City:");
	
	//STREET
	public JTextField streetField = new JTextField(20);
	JLabel streetLabel = new JLabel("Street:");
	
	//SUBMIT BUTTON
	public JButton submitButton = new JButton("Add");
	
	public ClientAddView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(2,1));
		
		//DATA
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0,1));
		//NAME
		dataPanel.add(nameLabel);
		dataPanel.add(nameField);
		//SURNAME
		dataPanel.add(surnameLabel);
		dataPanel.add(surnameField);
		//PESEL
		dataPanel.add(peselLabel);
		dataPanel.add(peselField);
		//CITY
		dataPanel.add(cityLabel);
		dataPanel.add(cityField);
		//STREET
		dataPanel.add(streetLabel);
		dataPanel.add(streetField);
		this.add(dataPanel);
		
		
		//BUTTONS
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,1));
		//SUBMIT
		buttonPanel.add(submitButton);
		this.add(buttonPanel);
	}
	
	public void DisplaySuccessPopup()
	{
		JPanel panel = new JPanel(new GridLayout(0, 1));
	    
		JLabel text = new JLabel("Client added succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Client added", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void DisplayErrorPopup(String errorMessage)
	{
		JPanel panel = new JPanel(new GridLayout(0, 1));
	    
		JLabel text = new JLabel("Error: ");
		JLabel error = new JLabel(errorMessage);
		panel.add(text);
		panel.add(error);
		
		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.PLAIN_MESSAGE);	
	}
	
	public void ClientAddSubmitListener(ActionListener clientAddSubmitListener) 
	{
		submitButton.addActionListener(clientAddSubmitListener);
	}
}
