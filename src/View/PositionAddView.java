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

public class PositionAddView extends JPanel
{
	//TITLE
	public JTextField titleField = new JTextField(20);
	JLabel titleLabel = new JLabel("Title:");
	//SALARY
	public JTextField salaryField = new JTextField(20);
	JLabel salaryLabel = new JLabel("Salary:");
	//SUBMIT BUTTON
	public JButton submitButton = new JButton("Add");
	
	public PositionAddView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(3,1));
		
		//DATA
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0,1));
		//TITLE
		dataPanel.add(titleLabel);
		dataPanel.add(titleField);
		//SALARY
		dataPanel.add(salaryLabel);
		dataPanel.add(salaryField);
		//PADDING
		JLabel empty1 = new JLabel(" ");
		JLabel empty2 = new JLabel(" ");
		dataPanel.add(empty1);
		dataPanel.add(empty2);
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
	    
		JLabel text = new JLabel("Position added succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Position added", JOptionPane.PLAIN_MESSAGE);
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
	
	public void PositionAddSubmitListener(ActionListener positionAddSubmitListener) 
	{
		submitButton.addActionListener(positionAddSubmitListener);
	}
}
