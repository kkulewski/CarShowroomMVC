package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientSearchView extends JPanel
{
	
	//PESEL
	public JTextField peselField = new JTextField(20);
	JLabel peselLabel = new JLabel("PESEL:");
	
	//SEARCH BUTTON
	public JButton searchButton = new JButton("Search");
	
	public ClientSearchView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(200, 0, 200);
		this.setBackground(backgroundColor);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.insets = new Insets(10,40,0,40);
		//PESEL LABEL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(peselLabel, c);
		//PESEL FIELD
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 0;
		this.add(peselField, c);
		
		//SUBMIT
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 5;
		c.weighty = 0.5;
		c.gridwidth = 2;
		c.insets = new Insets(10,80,0,80);
		this.add(searchButton, c);
	}
}