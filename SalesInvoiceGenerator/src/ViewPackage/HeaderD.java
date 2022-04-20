/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewPackage;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author weam1
 */
public class HeaderD extends JDialog{

    private JTextField customerName;
    private JTextField invDateText;
    private JLabel customerNameText;
    private JLabel invoiceDate;
    private JButton OKbutton;
    private JButton cancelButton;

    public HeaderD(SIG_Application_Frame frame) {
        customerNameText = new JLabel("Customer Name:");
        customerName = new JTextField(20);
        invoiceDate = new JLabel("Invoice Date:");
        invDateText = new JTextField(20);
        cancelButton = new JButton("Cancel");
         OKbutton = new JButton("OK");
        
        
        OKbutton.setActionCommand("OK");
        cancelButton.setActionCommand("InvoiceCancel");
        
         OKbutton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDate);
        add(invDateText);
        add(customerNameText);
        add(customerName);
        add( OKbutton );
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JTextField getInvDateText() {
        return invDateText;
    }
    

    
    
}

    

