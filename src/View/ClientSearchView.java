package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Client;

public class ClientSearchView extends JPanel
{
	//POPUP
    JPanel panel;
    
	//PESEL
	public JTextField peselField = new JTextField(20);
	JLabel peselLabel = new JLabel("PESEL:");
	
	//SEARCH BUTTON
	public JButton submitButton = new JButton("Search");
	
	public ClientSearchView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(225, 255, 205);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(0, 1));
		
		//PESEL LABEL
		this.add(peselLabel);
		//PESEL FIELD
		this.add(peselField);
		
		//SUBMIT
		this.add(submitButton);
	}
	
	public void DisplayNoResultPopup()
	{
	    panel = new JPanel(new GridLayout(0, 1));
	    
		JLabel text = new JLabel("No result found");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "No result found", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void DisplayResultPopup(Client foundClient)
	{
		panel = new JPanel(new GridLayout(0, 1));
		
		//NAME
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField();
		nameField.setText(foundClient.getName());
		nameField.setEditable(false);
		panel.add(nameLabel);
		panel.add(nameField);
		
		//SURNAME
		JLabel surnameLabel = new JLabel("Surname:");
		JTextField surnameField = new JTextField();
		surnameField.setText(foundClient.getSurname());
		surnameField.setEditable(false);
		panel.add(surnameLabel);
		panel.add(surnameField);
		
		//PESEL
		JLabel peselLabel = new JLabel("PESEL:");
		JTextField peselField = new JTextField();
		peselField.setText(String.valueOf(foundClient.getPesel()));
		peselField.setEditable(false);
		panel.add(peselLabel);
		panel.add(peselField);
		
		//NAME
		JLabel cityLabel = new JLabel("City:");
		JTextField cityField = new JTextField();
		cityField.setText(foundClient.getCity());
		cityField.setEditable(false);
		panel.add(cityLabel);
		panel.add(cityField);
		
		//NAME
		JLabel streetLabel = new JLabel("Street:");
		JTextField streetField = new JTextField();
		streetField.setText(foundClient.getStreet());
		streetField.setEditable(false);
		panel.add(streetLabel);
		panel.add(streetField);
		
		JOptionPane.showConfirmDialog(null, panel, "Result found", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void ClientSearchSubmitListener(ActionListener clientSearchSubmitListener) 
	{
		submitButton.addActionListener(clientSearchSubmitListener);
	}
}