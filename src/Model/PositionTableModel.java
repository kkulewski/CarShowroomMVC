package Model;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PositionTableModel extends AbstractTableModel
{
    private String[] columnNames =
    {
        "Title",
        "Salary"
    };
 
    private List<Position> positions;
 
    public PositionTableModel()
    {
        positions = new ArrayList<Position>();
    }
 
    public PositionTableModel(List<Position> positions)
    {
        this.positions = positions;
    }
    
    public void ReloadPositionTable(List<Position> positions)
    {
    	this.positions = positions;
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
        return positions.size();
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
        Position position = getPosition(row);
     
        switch (column)
        {
            case 0: return position.getTitle();
            case 1: return position.getSalary();
            default: return null;
        }
    }
     
    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Position position = getPosition(row);
     
        switch (column)
        {
            case 0: position.setTitle((String)value); break;
            case 1: position.setSalary((int)value); break;
        }
     
        fireTableCellUpdated(row, column);
    }
     
    public Position getPosition(int row)
    {
        return positions.get( row );
    }
 
}