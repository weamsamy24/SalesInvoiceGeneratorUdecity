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
public class LineD extends JDialog{
    private JTextField NameField;
    private JTextField CountField;
    private JTextField PriceField;
    private JLabel NameLabel;
    private JLabel CountLabel;
    private JLabel PriceLabel;
    private JButton cancelButton;
    private JButton OKbutton;

    
    public LineD(SIG_Application_Frame frame) {
        NameField = new JTextField(20);
        NameLabel = new JLabel("Item Name");
        
        CountField = new JTextField(20);
        CountLabel = new JLabel("Item Count");
        
        PriceField = new JTextField(20);
        PriceLabel = new JLabel("Item Price");
        
        OKbutton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        OKbutton.setActionCommand("lineOK");
        cancelButton.setActionCommand("LineCancel");
        
        OKbutton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(4, 2));
        
        add(NameLabel);
        add(NameField);
        add(CountLabel);
        add(CountField);
        add(PriceLabel);
        add(PriceField);
        add(OKbutton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getNameField() {
        return NameField;
    }

    public JTextField getCountField() {
        return CountField;
    }

    public JTextField getPriceField() {
        return PriceField;
    }
}

