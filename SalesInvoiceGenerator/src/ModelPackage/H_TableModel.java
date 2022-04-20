
package ModelPackage;



import ViewPackage.SIG_Application_Frame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author weam1
 */
public class H_TableModel extends AbstractTableModel {

    private ArrayList<Header_SIG_app> Invsarray;
    private String[] columns = {"No.", "Date", "Customer","InvoiceTotal"};
    
    public H_TableModel(ArrayList<Header_SIG_app> Invsarray) {
        this.Invsarray = Invsarray;
    }

    @Override
    public int getRowCount() {
        return Invsarray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Header_SIG_app inv = Invsarray.get(rowIndex);
        switch (columnIndex) {
            case 0: return inv.getNumberOfInvoice();
            case 1: return SIG_Application_Frame.dateFormat.format(inv.getInvoiceDate());
            case 2: return inv.getCustomerName();
            case 3: return inv.getInvoiceTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

    

