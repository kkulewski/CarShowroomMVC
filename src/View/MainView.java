package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainView extends JFrame
{
	JMenuBar menuBar;
	JMenu menuMain, menuClient, menuWorker, menuPosition, menuCar, menuPurchase;
	JMenuItem clientAdd, clientList, clientSearch, 
	workerAdd, workerList, workerSearch,
	positionAdd, positionList, positionSearch,
	carAdd, carList, carSearch,
	purchaseAdd, purchaseList, purchaseSearch;
	
	public MainView()
	{
		
		this.setSize(new Dimension(600,400));
		this.setLayout(new GridLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Car Showroom");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuMain = new JMenu("Main");
		menuBar.add(menuMain);
		
		//CLIENT
		menuClient = new JMenu("Client");
		menuBar.add(menuClient);
		
		clientAdd = new JMenuItem("Add new client");
		menuClient.add(clientAdd);
		clientList = new JMenuItem("List clients");
		menuClient.add(clientList);
		clientSearch = new JMenuItem("Search client...");
		menuClient.add(clientSearch);
		
		//WORKER
		menuWorker = new JMenu("Worker");
		menuBar.add(menuWorker);
		
		workerAdd = new JMenuItem("Add new worker");
		menuWorker.add(workerAdd);
		workerList = new JMenuItem("List workers");
		menuWorker.add(workerList);
		workerSearch = new JMenuItem("Search worker...");
		menuWorker.add(workerSearch);
		
		//POSITION
		menuPosition = new JMenu("Position");
		menuBar.add(menuPosition);
		
		positionAdd = new JMenuItem("Add new position");
		menuPosition.add(positionAdd);
		positionList = new JMenuItem("List positions");
		menuPosition.add(positionList);
		positionSearch = new JMenuItem("Search position...");
		menuPosition.add(positionSearch);
		
		//CAR
		menuCar = new JMenu("Car");
		menuBar.add(menuCar);
		
		carAdd = new JMenuItem("Add new car");
		menuCar.add(carAdd);
		carList = new JMenuItem("List cars");
		menuCar.add(carList);
		carSearch = new JMenuItem("Search car...");
		menuCar.add(carSearch);
		
		//PURCHASE
		menuPurchase = new JMenu("Purchase");
		menuBar.add(menuPurchase);
		
		purchaseAdd = new JMenuItem("Add new purchase");
		menuPurchase.add(purchaseAdd);
		purchaseList = new JMenuItem("List purchases");
		menuPurchase.add(purchaseList);
		purchaseSearch = new JMenuItem("Search purchase...");
		menuPurchase.add(purchaseSearch);
	}
	
	//CLIENT
	
	public void ClientAddListener(ActionListener clientAddListener) 
	{
		clientAdd.addActionListener(clientAddListener);
	}
	
	public void ClientListListener(ActionListener clientListListener) 
	{
		clientList.addActionListener(clientListListener);
	}
	
	public void ClientSearchListener(ActionListener clientSearchListener) 
	{
		clientSearch.addActionListener(clientSearchListener);
	}
	
	// POSITION
	
	public void PositionAddListener(ActionListener positionAddListener) 
	{
		positionAdd.addActionListener(positionAddListener);
	}
	
	public void PositionListListener(ActionListener positionListListener) 
	{
		positionList.addActionListener(positionListListener);
	}
	
	public void PositionSearchListener(ActionListener positionSearchListener) 
	{
		positionSearch.addActionListener(positionSearchListener);
	}
	
	// CAR
	
	public void CarAddListener(ActionListener carAddListener) 
	{
		carAdd.addActionListener(carAddListener);
	}
	
	public void CarListListener(ActionListener carListListener) 
	{
		carList.addActionListener(carListListener);
	}
	
	public void CarSearchListener(ActionListener carSearchListener) 
	{
		carSearch.addActionListener(carSearchListener);
	}
	
	// WORKER
	
	public void WorkerAddListener(ActionListener workerAddListener) 
	{
		workerAdd.addActionListener(workerAddListener);
	}
	
	public void WorkerListListener(ActionListener workerListListener) 
	{
		workerList.addActionListener(workerListListener);
	}
	
	public void WorkerSearchListener(ActionListener workerSearchListener) 
	{
		workerSearch.addActionListener(workerSearchListener);
	}
	
	// PURCHASE
	
	public void PurchaseAddListener(ActionListener purchaseAddListener) 
	{
		purchaseAdd.addActionListener(purchaseAddListener);
	}
	
	public void PurchaseListListener(ActionListener purchaseListListener) 
	{
		purchaseList.addActionListener(purchaseListListener);
	}
	
	public void PurchaseSearchListener(ActionListener purchaseSearchListener) 
	{
		purchaseSearch.addActionListener(purchaseSearchListener);
	}
	
	public void ActionCommited()
	{
		JOptionPane.showMessageDialog(null, "Button clicked");
	}
}
