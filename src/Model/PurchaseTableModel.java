package Model;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PurchaseTableModel extends AbstractTableModel
{
    private String[] columnNames =
    {
        "Client",
        "Worker",
        "Car",
        "Date"
    };
 
    private List<Purchase> purchases;
 
    public PurchaseTableModel()
    {
        purchases = new ArrayList<Purchase>();
    }
 
    public PurchaseTableModel(List<Purchase> purchases)
    {
        this.purchases = purchases;
    }
    
    public void ReloadPurchaseTable(List<Purchase> purchases)
    {
    	this.purchases = purchases;
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public int getRowCount()
    {
        return purchases.size();
    }
     
    @Override
    public boolean isCellEditable(int row, int column)
    {
        switch (column)
        {
            default: return false;
        }
    }
     
    @Override
    public Object getValueAt(int row, int column)
    {
        Purchase purchase = getPurchase(row);
     
        switch (column)
        {
            case 0: 
            	ClientModel clientModel = new ClientModel();
            	int clientId = purchase.getId_client();
            	Client foundClient = clientModel.find("id_client", String.valueOf(clientId));
            	if(foundClient == null)
            	{
            		PurchaseModel purchaseModel = new PurchaseModel();
            		purchaseModel.delete(purchase);
            		return "#OUTDATED!#";
            	}
            	return foundClient.getSurname();
            case 1:
            	WorkerModel workerModel = new WorkerModel();
            	int workerId = purchase.getId_worker();
            	Worker foundWorker = workerModel.find("id_worker", String.valueOf(workerId));
            	if(foundWorker == null)
            	{
            		PurchaseModel purchaseModel = new PurchaseModel();
            		purchaseModel.delete(purchase);
            		return "#OUTDATED!#";
            	}
            	return foundWorker.getSurname();
            case 2: 
            	CarModel carModel = new CarModel();
            	int carId = purchase.getId_car();
            	Car foundCar = carModel.find("id_car", String.valueOf(carId));
            	if(foundCar == null)
            	{
            		PurchaseModel purchaseModel = new PurchaseModel();
            		purchaseModel.delete(purchase);
            		return "#OUTDATED!#";
            	}
            	return foundCar.getModel();
            case 3: 
            	return purchase.getTransaction_date();
            default: 
            	return null;
        }
    }
     
    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Purchase purchase = getPurchase(row);
     
        switch (column)
        {
            case 0: purchase.setId_client((int)value); break;
            case 1: purchase.setId_worker((int)value); break;
            case 2: purchase.setId_car((int)value); break;
            case 3: purchase.setTransaction_date(Date.valueOf((String)value)); break;
        }
     
        fireTableCellUpdated(row, column);
    }
     
    public Purchase getPurchase(int row)
    {
        return purchases.get( row );
    }
 
}