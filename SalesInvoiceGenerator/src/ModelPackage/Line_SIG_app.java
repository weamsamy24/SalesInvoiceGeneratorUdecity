
package ModelPackage;


public class Line_SIG_app {
    private double ItemPrice;
    private String ItemName;
    private int NumberOfItems;
    private Header_SIG_app invNumber;

    public Line_SIG_app() {
    }

    public Line_SIG_app(double ItemPrice, String ItemName, int NumberOfItems, Header_SIG_app invNumber) {
        this.ItemPrice = ItemPrice;
        this.ItemName = ItemName;
        this.NumberOfItems = NumberOfItems;
        this.invNumber = invNumber;
    }

   

    public Header_SIG_app getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(Header_SIG_app invNumber) {
        this.invNumber = invNumber;
    }

    public double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(double ItemPrice) {
        this.ItemPrice = ItemPrice;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public int getNumberOfItems() {
        return NumberOfItems;
    }

    public void setNumberOfItems(int NumberOfItems) {
        this.NumberOfItems = NumberOfItems;
    }
    
  
    public double getmultofLineTotal(){
        return ItemPrice * NumberOfItems;
    }
    
     @Override
    public String toString() {
        return "InvoiceLine{" + "ItemName=" + ItemName + ", ItemPrice=" + ItemPrice + ", NumberOfItems=" + NumberOfItems + ", invNumber=" + invNumber + '}';
    }
     public String getCSVfile(){
         return ""+getInvNumber().getCustomerName()+ "," + getItemName() +","+getItemPrice()+getNumberOfItems();}
     }
 