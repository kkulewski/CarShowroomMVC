package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientAddView extends JPanel
{
	//NAME
	JTextField nameField = new JTextField(20);
	JLabel nameLabel = new JLabel("Name:");
	
	//SURNAME
	JTextField surnameField = new JTextField(20);
	JLabel surnameLabel = new JLabel("Surname:");
	
	//PESEL
	JTextField peselField = new JTextField(20);
	JLabel peselLabel = new JLabel("PESEL:");
	
	//CITY
	JTextField cityField = new JTextField(20);
	JLabel cityLabel = new JLabel("City:");
	
	//STREET
	JTextField streetField = new JTextField(20);
	JLabel streetLabel = new JLabel("Street:");
	
	//SUBMIT BUTTON
	JButton submitButton = new JButton("Add");
	
	public ClientAddView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(225, 255, 205);
		this.setBackground(backgroundColor);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.insets = new Insets(10,40,0,40);
		//NAME LABEL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(nameLabel, c);
		//NAME FIELD
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 0;
		this.add(nameField, c);

		//SURNAME LABEL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.gridx = 0;
		c.gridy = 1;
		this.add(surnameLabel, c);
		//SURNAME FIELD
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 1;
		this.add(surnameField, c);
		
		//PESEL LABEL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.gridx = 0;
		c.gridy = 2;
		this.add(peselLabel, c);
		//PESEL FIELD
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 2;
		this.add(peselField, c);
		
		//CITY LABEL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.gridx = 0;
		c.gridy = 3;
		this.add(cityLabel, c);
		//CITY FIELD
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 3;
		this.add(cityField, c);
		
		//STREET LABEL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.gridx = 0;
		c.gridy = 4;
		this.add(streetLabel, c);
		//STREET FIELD
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 4;
		this.add(streetField, c);

		//SUBMIT
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 5;
		c.weighty = 0.5;
		c.gridwidth = 2;
		c.insets = new Insets(10,80,0,80);
		this.add(submitButton, c);
	}
}
