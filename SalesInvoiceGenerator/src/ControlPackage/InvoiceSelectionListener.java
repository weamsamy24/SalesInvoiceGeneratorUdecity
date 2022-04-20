/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import ModelPackage.Header_SIG_app;
import ModelPackage.L_TableModel;
import ModelPackage.Line_SIG_app;
import ViewPackage.SIG_Application_Frame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author weam1
 */
public class InvoiceSelectionListener implements ListSelectionListener {
    private SIG_Application_Frame frame;

    public InvoiceSelectionListener(SIG_Application_Frame frame) {
        this.frame = frame;
    }
    

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int selectionInvoices=frame.getHeaderTable().getSelectedRow();
        System.out.println("Invoice is selected"+ selectionInvoices);
        if (selectionInvoices != -1) {
        Header_SIG_app selInvoices = frame.getInvsarray().get(selectionInvoices);
        ArrayList<Line_SIG_app>linesOfInv = selInvoices.getLinesOfInv();
        L_TableModel linemodel= new L_TableModel(linesOfInv);
        frame.setLineInvsarray(linesOfInv);
        frame.getLineTable().setModel(linemodel);
        frame.getInvoicenumberlabel().setText(""+selInvoices.getNumberOfInvoice());
        frame.getCustomernamefield().setText(selInvoices.getCustomerName());
        frame.getInvoicedatefield().setText(SIG_Application_Frame.dateFormat.format(selInvoices.getInvoiceDate()));
        frame.getInvoicetotallabel().setText(""+selInvoices.getInvoiceTotal());}
         
    }
    
    
}
