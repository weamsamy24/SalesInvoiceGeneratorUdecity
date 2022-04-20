
package ModelPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author weam1
 */
public class Header_SIG_app {
    private int NumberOfInvoice;
    private String CustomerName;
    private Date InvoiceDate;
    private ArrayList<Line_SIG_app>linesOfInv;   //create arraylist to store numbers and add lines to header and line.

    public Header_SIG_app() {
    }

    public Header_SIG_app(int NumberOfInvoice, String CustomerName, Date InvoiceDate) {
        this.NumberOfInvoice = NumberOfInvoice;
        this.CustomerName = CustomerName;
        this.InvoiceDate = InvoiceDate;
    }

  
    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public int getNumberOfInvoice() {
        return NumberOfInvoice;
    }

    public void setNumberOfInvoice(int NumberOfInvoice) {
        this.NumberOfInvoice = NumberOfInvoice;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public ArrayList<Line_SIG_app> getLinesOfInv() {
         if (linesOfInv == null) {
            linesOfInv= new ArrayList<>();
        }
        return linesOfInv;
    }


    public void setLinesOfInv(ArrayList<Line_SIG_app> linesOfInv) {
        this.linesOfInv = linesOfInv;
    }
    
    
    public double getInvoiceTotal(){
        double total= 0.0;
        for(int w=0;w<getLinesOfInv().size();w++){
            total+= getLinesOfInv().get(w).getmultofLineTotal();
        }
        return total;
        
        
    }
    @Override
    public String toString() {
        return "InvoiceHeader{" + "num=" + NumberOfInvoice+ ", customer=" + CustomerName + ", invDate=" + InvoiceDate + '}';
    }

     public String getCSVfile(){
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

         return ""+getNumberOfInvoice()+","+dateFormat.format(getInvoiceDate())+","+getCustomerName();
     }
    
    
}
