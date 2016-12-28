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

import Model.Position;
import Model.PositionModel;
import Model.Worker;

public class WorkerSearchView extends JPanel
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
	
	public WorkerSearchView()
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
		
	    String[] rowNames = new String[]{"name", "surname", "pesel", "city", "street" };
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
	
	public void DisplayResultPopup(Worker foundWorker)
	{
		panel = new JPanel(new GridLayout(0, 1));
		
		//NAME
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField();
		nameField.setText(foundWorker.getName());
		nameField.setEditable(false);
		panel.add(nameLabel);
		panel.add(nameField);
		
		//SURNAME
		JLabel surnameLabel = new JLabel("Surname:");
		JTextField surnameField = new JTextField();
		surnameField.setText(foundWorker.getSurname());
		surnameField.setEditable(false);
		panel.add(surnameLabel);
		panel.add(surnameField);
		
		//PESEL
		JLabel peselLabel = new JLabel("PESEL:");
		JTextField peselField = new JTextField();
		peselField.setText(String.valueOf(foundWorker.getPesel()));
		peselField.setEditable(false);
		panel.add(peselLabel);
		panel.add(peselField);
		
		//NAME
		JLabel cityLabel = new JLabel("City:");
		JTextField cityField = new JTextField();
		cityField.setText(foundWorker.getCity());
		cityField.setEditable(false);
		panel.add(cityLabel);
		panel.add(cityField);
		
		//NAME
		JLabel streetLabel = new JLabel("Street:");
		JTextField streetField = new JTextField();
		streetField.setText(foundWorker.getStreet());
		streetField.setEditable(false);
		panel.add(streetLabel);
		panel.add(streetField);
		
		//POSITION
		JLabel positionLabel = new JLabel("Position:");
		JTextField positionField = new JTextField();
		PositionModel positionModel = new PositionModel();
		Position workerPosition = positionModel.find("id_position", String.valueOf(foundWorker.getId_position()));
		positionField.setText(workerPosition.getTitle());
		positionField.setEditable(false);
		panel.add(positionLabel);
		panel.add(positionField);
		
		JOptionPane.showConfirmDialog(null, panel, "Result found", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void WorkerSearchSubmitListener(ActionListener workerSearchSubmitListener) 
	{
		submitButton.addActionListener(workerSearchSubmitListener);
	}
}