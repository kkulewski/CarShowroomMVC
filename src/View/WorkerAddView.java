package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Position;
import Model.PositionModel;

public class WorkerAddView extends JPanel
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
	//POSITION
	public JComboBox<String> positionCombo;
	JLabel positionLabel = new JLabel("Position:");
	//SUBMIT BUTTON
	public JButton submitButton = new JButton("Add");
	
	public WorkerAddView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(200, 200, 200);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(3,1));
		
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
		this.add(dataPanel);
		
		
		//DATA2
		JPanel dataPanel2 = new JPanel();
		dataPanel2.setLayout(new GridLayout(0,1));
		//CITY
		dataPanel2.add(cityLabel);
		dataPanel2.add(cityField);
		//STREET
		dataPanel2.add(streetLabel);
		dataPanel2.add(streetField);
		
		//POSITION
		dataPanel2.add(positionLabel);
		PositionModel positionModel = new PositionModel();
		ArrayList<Position> positionList = new ArrayList<Position>();
		positionList = positionModel.list();
		
	    positionCombo = new JComboBox<String>();
	    for(Position p : positionList)
	    {
	    	positionCombo.addItem(p.getTitle());
	    }
	    JScrollPane positionScrollPane = new JScrollPane(positionCombo);    
		dataPanel2.add(positionScrollPane);
		
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
	    
		JLabel text = new JLabel("Worker added succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Worker added", JOptionPane.PLAIN_MESSAGE);
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
	
	public void WorkerAddSubmitListener(ActionListener workerAddSubmitListener) 
	{
		submitButton.addActionListener(workerAddSubmitListener);
	}
}
