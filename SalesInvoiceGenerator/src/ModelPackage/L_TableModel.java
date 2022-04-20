/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelPackage;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author weam1
 */
public class L_TableModel extends AbstractTableModel{
    
    private ArrayList<Line_SIG_app> LineInvsarray;
    private String[] columns = { "Item Name", "Item Price","count","Item Total"};

    public L_TableModel(ArrayList<Line_SIG_app> LineInvsarray) {
        this.LineInvsarray = LineInvsarray;
    }

    
    @Override
    public int getRowCount() {
        return  LineInvsarray== null ?0: LineInvsarray.size();   
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Line_SIG_app INF = LineInvsarray.get(rowIndex);
         if(LineInvsarray==null)   {return"";}
         else{
        switch (columnIndex) {
            case 0: return INF.getItemName();
            case 1: return INF.getItemPrice();
            case 2: return INF.getNumberOfItems();
            case 3: return INF.getmultofLineTotal();
            default: return "";
        }
         }
    }
      @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}


