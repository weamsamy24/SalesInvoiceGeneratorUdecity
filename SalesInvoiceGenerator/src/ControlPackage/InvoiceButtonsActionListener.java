/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import ModelPackage.H_TableModel;
import ModelPackage.Header_SIG_app;
import ModelPackage.L_TableModel;
import ModelPackage.Line_SIG_app;
import ViewPackage.HeaderD;
import ViewPackage.LineD;
import ViewPackage.SIG_Application_Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author weam1
 */
public class InvoiceButtonsActionListener implements ActionListener {
    private SIG_Application_Frame frame;
    private HeaderD headerdialog;
    private LineD linedialog;

    
    public InvoiceButtonsActionListener(SIG_Application_Frame frame){
        this.frame = frame; }

    @Override
    public void actionPerformed(ActionEvent ae) { 
       switch(ae.getActionCommand()) {
           
           
               case"Load File":
       {
           try {
               loadfile();
           } catch (IOException ex) {
               Logger.getLogger(InvoiceButtonsActionListener.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
               break;
               case "Save File":
                  savefile(); 
               break;
               case "Create New Invoice":
                   createnewinvoice();
               break;
               case "Delete Invoice":
                   deleteinvoice();
               break;
               case "Save":
                   save();
               break;
               
               case "Cancel":
                   cancel();
               break;
               case"lineOK":
                makeThelineOKDialog();
                break;
                case"LineCancel":
                closeThelineCancelDialog();
                break;
               
               case"InvoiceCancel":
                   closeTheInvCancelDialog();
                   break;
                   
               case"OK":
                   makeTheInvoiceOKDialog();
                   break;
                   
                   
               
       }
     
    }

    private void savefile()    {
        String headerFile = "";
        String lineFile = "";
        for (Header_SIG_app Header : frame.getInvsarray()) {
            headerFile+=Header.getCSVfile();
            headerFile+="\n";
            for (Line_SIG_app Line : frame.getLineInvsarray()) {
                lineFile+=Line.getCSVfile();
                lineFile+="\n";}

        }
        JOptionPane.showMessageDialog(frame, "Select Header File", "attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser choosefile = new JFileChooser();
        int saveData =choosefile.showSaveDialog(frame);
        if (saveData == JFileChooser.APPROVE_OPTION) {
            File headersfile = choosefile.getSelectedFile();
            try{            
                FileWriter filewrite = new FileWriter(headersfile);
                filewrite.write(headerFile);
                filewrite.flush();
                filewrite.close();
            JOptionPane.showMessageDialog(frame, "Select Line File", "attension", JOptionPane.WARNING_MESSAGE);
            saveData=choosefile.showSaveDialog(frame);
            if (saveData == JFileChooser.APPROVE_OPTION) {
            File linesfile = choosefile.getSelectedFile();
            FileWriter linefilewrite = new FileWriter(linesfile);
            linefilewrite.write(lineFile);
            linefilewrite.flush();
            linefilewrite.close();

            }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "error: " + ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);}
            

        }

        
        

    }
    
         
   
    private void createnewinvoice() {
        headerdialog=new HeaderD(frame);
        headerdialog.setVisible(true);
    }

    private void deleteinvoice() {
        int linetableselection = frame.getHeaderTable().getSelectedRow();
        if (linetableselection != -1) {
            frame.getInvsarray().remove(linetableselection);
            frame.getHeadertablemodel().fireTableDataChanged();

            frame.getLineTable().setModel(new L_TableModel(null));
            frame.setLineInvsarray(null);
            frame.getInvoicenumberlabel().setText("");
            frame.getCustomernamefield().setText("");
            frame.getInvoicedatefield().setText("");
            frame.getInvoicetotallabel().setText("");
        
        }
    }

    private void save() {
         linedialog=new LineD(frame);
         linedialog.setVisible(true);
        
    }

    private void cancel() {
        int line=frame.getLineTable().getSelectedRow();
        int removeINNtotal = frame.getLineTable().getSelectedRow();

        if(line!= -1){
            frame.getLineInvsarray().remove(line);
            L_TableModel lineTable =(L_TableModel)frame.getLineTable().getModel();
            lineTable.fireTableDataChanged();
            frame.getInvoiceTotal().setText(""+frame.getInvsarray().get(removeINNtotal).getInvoiceTotal());
            frame.getHeadertablemodel().fireTableDataChanged();
            frame.getHeaderTable().setRowSelectionInterval(removeINNtotal, removeINNtotal);

        }
       
    }

    private void loadfile () throws IOException{
           JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<Header_SIG_app> invoiceHeaders = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date invoiceDate = SIG_Application_Frame.dateFormat.parse(str2);
                    Header_SIG_app header = new Header_SIG_app(code, str3, invoiceDate);
                    invoiceHeaders.add(header);  }
                
                frame.setInvsarray(invoiceHeaders);

                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<Line_SIG_app> invoiceLines = new ArrayList<>();
                    for (String lineLine : lineLines) {
                        String[] arr = lineLine.split(",");
                        String str1 = arr[0];    
                        String str2 = arr[1];       
                        String str3 = arr[2];          
                        String str4 = arr[3];           
                        int invCode = Integer.parseInt(str1);
                        double ItemPrice = Double.parseDouble(str3);
                        int NumberOfItems = Integer.parseInt(str4);
                        Header_SIG_app inv =frame.getInvobject(invCode);
                        Line_SIG_app line = new Line_SIG_app(ItemPrice, str2, NumberOfItems, inv);
                        inv.getLinesOfInv().add(line);}
                   
                
                }
            
                H_TableModel headertablemodel = new H_TableModel(invoiceHeaders);
                frame.setHeadertablemodel(headertablemodel);
                frame.getHeaderTable().setModel(headertablemodel);
                System.out.println("checkk");
            }            
         } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeTheInvCancelDialog() {
        headerdialog.setVisible(false);
        headerdialog.dispose();
        headerdialog=null;
    }

    private void makeTheInvoiceOKDialog() {
         headerdialog.setVisible(false);
         String customername=headerdialog.getCustomerName().getText();
         String invoicedate=headerdialog.getInvDateText().getText();
         Date date=new Date();
        try {
            date = SIG_Application_Frame.dateFormat.parse(invoicedate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot get the current date.", "Invalid date", JOptionPane.ERROR_MESSAGE);
        }
        int num = 0;
        for (Header_SIG_app inv : frame.getInvsarray()) {
            if (inv.getNumberOfInvoice() > num) {
                num = inv.getNumberOfInvoice();}
        }
        num++;
        Header_SIG_app in = new Header_SIG_app(num, customername, date);
        frame.getInvsarray().add(in);
        frame.getHeadertablemodel().fireTableDataChanged();
        headerdialog.dispose();
        headerdialog=null;
    }

    private void makeThelineOKDialog() {
        linedialog.setVisible(false);
        String numbercount=linedialog.getCountField().getText();
        String price=linedialog.getPriceField().getText();
        String name = linedialog.getNameField().getText();

        int countnum = 1;
        double pricenum = 1;
        
        try {
            pricenum = Double.parseDouble(price);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot show the price", "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }

         try {
            countnum = Integer.parseInt(numbercount);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot show the number", "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }
        int invoiceH = frame.getHeaderTable().getSelectedRow();
        if (invoiceH != -1) {
            Header_SIG_app header=frame.getInvsarray().get(invoiceH);
            Line_SIG_app Lineframe=new Line_SIG_app(pricenum, name,countnum,header);
            frame.getLineInvsarray().add(Lineframe);
            L_TableModel lineTable =(L_TableModel)frame.getLineTable().getModel();
            lineTable.fireTableDataChanged();
            frame.getHeadertablemodel().fireTableDataChanged();

        }
        
        frame.getHeaderTable().setRowSelectionInterval(invoiceH, invoiceH);

        linedialog.dispose();
        linedialog=null;
        
    }

    private void closeThelineCancelDialog() {
        linedialog.setVisible(false);
        linedialog.dispose();
        linedialog=null;
    }

   

    
}

    