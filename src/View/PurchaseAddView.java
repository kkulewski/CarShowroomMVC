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

import Model.Car;
import Model.CarModel;
import Model.Client;
import Model.ClientModel;
import Model.Worker;
import Model.WorkerModel;

public class PurchaseAddView extends JPanel
{
	//CLIENT
	public JComboBox<String> clientCombo;
	JLabel clientLabel = new JLabel("Client:");
	//WORKER
	public JComboBox<String> workerCombo;
	JLabel workerLabel = new JLabel("Worker:");
	//CAR
	public JComboBox<String> carCombo;
	JLabel carLabel = new JLabel("Car:");
	//DATE
	public JTextField transaction_dateField = new JTextField(20);
	JLabel transaction_dateLabel = new JLabel("Date:");
	//SUBMIT BUTTON
	public JButton submitButton = new JButton("Add");
	
	public PurchaseAddView()
	{
		this.setVisible(true);
		Color backgroundColor = new Color(100, 200, 0);
		this.setBackground(backgroundColor);
		this.setLayout(new GridLayout(3,1));
		
		//DATA
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0,1));
		
		//CLIENT
		dataPanel.add(clientLabel);
		ClientModel clientModel = new ClientModel();
		ArrayList<Client> clientList = new ArrayList<Client>();
		clientList = clientModel.list();
		
	    clientCombo = new JComboBox<String>();
	    for(Client c : clientList)
	    {
	    	clientCombo.addItem(c.getSurname());
	    }
	    JScrollPane clientScrollPane = new JScrollPane(clientCombo);    
		dataPanel.add(clientScrollPane);
		
		//WORKER
		dataPanel.add(workerLabel);
		WorkerModel workerModel = new WorkerModel();
		ArrayList<Worker> workerList = new ArrayList<Worker>();
		workerList = workerModel.list();
				
	    workerCombo = new JComboBox<String>();
	    for(Worker w : workerList)
	    {
	    	workerCombo.addItem(w.getSurname());
	    }
	    JScrollPane workerScrollPane = new JScrollPane(workerCombo);    
		dataPanel.add(workerScrollPane);

		//POSITION
		dataPanel.add(carLabel);
		CarModel carModel = new CarModel();
		ArrayList<Car> carList = new ArrayList<Car>();
		carList = carModel.list();
		
	    carCombo = new JComboBox<String>();
	    for(Car c : carList)
	    {
	    	carCombo.addItem(c.getModel());
	    }
	    JScrollPane carScrollPane = new JScrollPane(carCombo);    
		dataPanel.add(carScrollPane);
		this.add(dataPanel);		
		
		//DATA2
		JPanel dataPanel2 = new JPanel();
		dataPanel2.setLayout(new GridLayout(0,1));
		dataPanel2.add(transaction_dateLabel);
		dataPanel2.add(transaction_dateField);
		//PADDING
		JLabel empty1 = new JLabel(" ");
		JLabel empty2 = new JLabel(" ");
		JLabel empty3 = new JLabel(" ");
		JLabel empty4 = new JLabel(" ");
		dataPanel2.add(empty1);
		dataPanel2.add(empty2);
		dataPanel2.add(empty3);
		dataPanel2.add(empty4);
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
	    
		JLabel text = new JLabel("Purchase added succesfully.");
		panel.add(text);
		
		JOptionPane.showConfirmDialog(null, panel, "Purchase added", JOptionPane.PLAIN_MESSAGE);
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
	
	public void PurchaseAddSubmitListener(ActionListener purchaseAddSubmitListener) 
	{
		submitButton.addActionListener(purchaseAddSubmitListener);
	}
}
