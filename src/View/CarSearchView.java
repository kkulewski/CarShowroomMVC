package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Car;

public class CarSearchView extends JPanel
{
	//POPUP
    JPanel panel;
    
	//SEARCH ROW AND VALUE
    public JComboBox<String> searchRowCombo;
	public JTextField searchValueField;
	JLabel searchValueLabel;
	JLabel searchRowLabel;
	
	//SEARCH BUTTON
	public JButton submitButton = new JButton("Search");
	
	public CarSearchView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(2, 1));
		  
	      
		//DATA
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0,1));

	    searchRowLabel = new JLabel("Select column to search:");
	    dataPanel.add(searchRowLabel);
		
	    String[] rowNames = new String[]{"brand", "model", "price" };
	    searchRowCombo = new JComboBox<String>(rowNames);    
	    searchRowCombo.setSelectedIndex(0);
	    JScrollPane searchRowScrollPane = new JScrollPane(searchRowCombo);    
		dataPanel.add(searchRowScrollPane);
		
		searchValueLabel = new JLabel("Set searched value:");
		dataPanel.add(searchValueLabel);
		
		searchValueField = new JTextField(70);
		dataPanel.add(searchValueField);
		
		this.add(dataPanel);
		
		//BUTTONS
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,1));
		//SUBMIT
		buttonPanel.add(submitButton);
		this.add(buttonPanel);
		
	}
	
	public void DisplayNoResultPopup()
	{
	    panel = new JPanel(new GridLayout(0, 1));
	    
		JLabel text = new JLabel("No result found");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "No result found", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void DisplayResultPopup(Car foundCar)
	{
		panel = new JPanel(new GridLayout(0, 1));
		
		//BRAND
		JLabel brandLabel = new JLabel("Brand:");
		JTextField brandField = new JTextField();
		brandField.setText(foundCar.getBrand());
		brandField.setEditable(false);
		panel.add(brandLabel);
		panel.add(brandField);
		
		//MODEL
		JLabel modelLabel = new JLabel("Model:");
		JTextField modelField = new JTextField();
		modelField.setText(foundCar.getModel());
		modelField.setEditable(false);
		panel.add(modelLabel);
		panel.add(modelField);
		
		//PRICE
		JLabel priceLabel = new JLabel("Price:");
		JTextField priceField = new JTextField();
		priceField.setText(String.valueOf(foundCar.getPrice()));
		priceField.setEditable(false);
		panel.add(priceLabel);
		panel.add(priceField);
		
		JOptionPane.showConfirmDialog(null, panel, "Result found", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void CarSearchSubmitListener(ActionListener carSearchSubmitListener) 
	{
		submitButton.addActionListener(carSearchSubmitListener);
	}
}