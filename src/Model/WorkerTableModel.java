package Model;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class WorkerTableModel extends AbstractTableModel
{
    private String[] columnNames =
    {
        "Name",
        "Surname",
        "PESEL",
        "City",
        "Street",
        "Position"
    };
 
    private List<Worker> workers;
 
    public WorkerTableModel()
    {
        workers = new ArrayList<Worker>();
    }
 
    public WorkerTableModel(List<Worker> workers)
    {
        this.workers = workers;
    }
    
    public void ReloadWorkerTable(List<Worker> workers)
    {
    	this.workers = workers;
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
        return workers.size();
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
        Worker worker = getWorker(row);
     
        switch (column)
        {
            case 0: return worker.getName();
            case 1: return worker.getSurname();
            case 2: return worker.getPesel();
            case 3: return worker.getCity();
            case 4: return worker.getStreet();
            case 5:
        		PositionModel positionModel = new PositionModel();
        		int positionId = worker.getId_position();
        		Position foundPosition = positionModel.find("id_position", String.valueOf(positionId));
            	if(foundPosition == null)
            	{
            		WorkerModel workerModel = new WorkerModel();
            		workerModel.delete(worker);
            		return "#OUTDATED!#";
            	}
            	return foundPosition.getTitle();
            default: return null;
        }
    }
     
    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Worker worker = getWorker(row);
     
        switch (column)
        {
            case 0: worker.setName((String)value); break;
            case 1: worker.setSurname((String)value); break;
            case 2: worker.setPesel((long)value); break;
            case 3: worker.setCity((String)value); break;
            case 4: worker.setStreet((String)value); break;
            case 5: worker.setId_position((int)value); break;
        }
     
        fireTableCellUpdated(row, column);
    }
     
    public Worker getWorker(int row)
    {
        return workers.get( row );
    }
 
}