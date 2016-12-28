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
import Model.CarModel;
import Model.Client;
import Model.ClientModel;
import Model.Position;
import Model.PositionModel;
import Model.Purchase;
import Model.Worker;
import Model.WorkerModel;

public class PurchaseSearchView extends JPanel
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
	
	public PurchaseSearchView()
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
		
	    String[] rowNames = new String[]{"transaction_date" };
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
	
	public void DisplayResultPopup(Purchase foundPurchase)
	{
		panel = new JPanel(new GridLayout(0, 1));
		
		//CLIENT
		JLabel clientLabel = new JLabel("Client:");
		JTextField clientField = new JTextField();
		ClientModel clientModel = new ClientModel();
		Client purchaseClient = clientModel.find("id_client", String.valueOf(foundPurchase.getId_client()));
		clientField.setText(purchaseClient.getSurname());
		clientField.setEditable(false);
		panel.add(clientLabel);
		panel.add(clientField);
		
		//WORKER
		JLabel workerLabel = new JLabel("Worker:");
		JTextField workerField = new JTextField();
		WorkerModel workerModel = new WorkerModel();
		Worker purchaseWorker = workerModel.find("id_worker", String.valueOf(foundPurchase.getId_worker()));
		workerField.setText(purchaseWorker.getSurname());
		workerField.setEditable(false);
		panel.add(workerLabel);
		panel.add(workerField);
		
		//CAR
		JLabel carLabel = new JLabel("Car:");
		JTextField carField = new JTextField();
		CarModel carModel = new CarModel();
		Car purchaseCar = carModel.find("id_car", String.valueOf(foundPurchase.getId_car()));
		carField.setText(purchaseCar.getModel());
		carField.setEditable(false);
		panel.add(carLabel);
		panel.add(carField);
		
		//DATE
		JLabel transaction_dateLabel = new JLabel("Date:");
		JTextField transaction_dateField = new JTextField();
		transaction_dateField.setText(String.valueOf(foundPurchase.getTransaction_date()));
		transaction_dateField.setEditable(false);
		panel.add(transaction_dateLabel);
		panel.add(transaction_dateField);
		
		JOptionPane.showConfirmDialog(null, panel, "Result found", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void PurchaseSearchSubmitListener(ActionListener purchaseSearchSubmitListener) 
	{
		submitButton.addActionListener(purchaseSearchSubmitListener);
	}
}