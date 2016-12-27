package Model;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CarTableModel extends AbstractTableModel
{
    private String[] columnNames =
    {
        "Brand",
        "Model",
        "Price"
    };
 
    private List<Car> cars;
 
    public CarTableModel()
    {
        cars = new ArrayList<Car>();
    }
 
    public CarTableModel(List<Car> cars)
    {
        this.cars = cars;
    }
    
    public void ReloadCarTable(List<Car> cars)
    {
    	this.cars = cars;
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
        return cars.size();
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
        Car car = getCar(row);
     
        switch (column)
        {
            case 0: return car.getBrand();
            case 1: return car.getModel();
            case 2: return car.getPrice();
            default: return null;
        }
    }
     
    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Car car = getCar(row);
     
        switch (column)
        {
            case 0: car.setBrand((String)value); break;
            case 1: car.setModel((String)value); break;
            case 2: car.setPrice((double)value); break;
        }
     
        fireTableCellUpdated(row, column);
    }
     
    public Car getCar(int row)
    {
        return cars.get( row );
    }
 
}