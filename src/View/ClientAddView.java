package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
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
		this.setLayout(new GridLayout(0,1));
		
		//NAME LABEL
		this.add(nameLabel);
		//NAME FIELD
		this.add(nameField);

		//SURNAME LABEL
		this.add(surnameLabel);
		//SURNAME FIELD
		this.add(surnameField);
		
		//PESEL LABEL
		this.add(peselLabel);
		//PESEL FIELD
		this.add(peselField);
		
		//CITY LABEL
		this.add(cityLabel);
		//CITY FIELD
		this.add(cityField);
		
		//STREET LABEL
		this.add(streetLabel);
		//STREET FIELD
		this.add(streetField);

		//SUBMIT
		this.add(submitButton);
	}
	
	public void ClientAddSubmitListener(ActionListener clientAddSubmitListener) 
	{
		submitButton.addActionListener(clientAddSubmitListener);
	}
}
