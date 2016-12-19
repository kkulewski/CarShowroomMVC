package Model;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ClientTableModel extends AbstractTableModel
{
    private String[] columnNames =
    {
        "Name",
        "Surname",
        "PESEL",
        "City",
        "Street"
    };
 
    private List<Client> clients;
 
    public ClientTableModel()
    {
        clients = new ArrayList<Client>();
    }
 
    public ClientTableModel(List<Client> clients)
    {
        this.clients = clients;
    }
    
    public void ReloadClientTable(List<Client> clients)
    {
    	this.clients = clients;
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
        return clients.size();
    }

    @Override
    public Class getColumnClass(int column)
    {
        switch (column)
        {
            case 2: return long.class;
            default: return String.class;
        }
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
        Client client = getClient(row);
     
        switch (column)
        {
            case 0: return client.getName();
            case 1: return client.getSurname();
            case 2: return client.getPesel();
            case 3: return client.getCity();
            case 4: return client.getStreet();
            default: return null;
        }
    }
     
    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Client client = getClient(row);
     
        switch (column)
        {
            case 0: client.setName((String)value); break;
            case 1: client.setSurname((String)value); break;
            case 2: client.setPesel((long)value); break;
            case 3: client.setCity((String)value); break;
            case 4: client.setStreet((String)value); break;
        }
     
        fireTableCellUpdated(row, column);
    }
     
    public Client getClient(int row)
    {
        return clients.get( row );
    }
 
}