package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CarAddView extends JPanel
{
	//BRAND
	public JTextField brandField = new JTextField(20);
	JLabel brandLabel = new JLabel("Brand:");
	//MODEL
	public JTextField modelField = new JTextField(20);
	JLabel modelLabel = new JLabel("Model:");
	//PRICE
	public JTextField priceField = new JTextField(20);
	JLabel priceLabel = new JLabel("Price:");
	//SUBMIT BUTTON
	public JButton submitButton = new JButton("Add");
	
	public CarAddView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(3,1));
		
		//DATA
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0,1));
		//BRAND
		dataPanel.add(brandLabel);
		dataPanel.add(brandField);
		//MODEL
		dataPanel.add(modelLabel);
		dataPanel.add(modelField);
		//PRICE
		dataPanel.add(priceLabel);
		dataPanel.add(priceField);
		this.add(dataPanel);
		
		//DATA2
		JPanel dataPanel2 = new JPanel();
		dataPanel2.setLayout(new GridLayout(0,1));
		this.add(dataPanel2);
		
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
	    
		JLabel text = new JLabel("Car added succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Car added", JOptionPane.PLAIN_MESSAGE);
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
	
	public void CarAddSubmitListener(ActionListener carAddSubmitListener) 
	{
		submitButton.addActionListener(carAddSubmitListener);
	}
}
