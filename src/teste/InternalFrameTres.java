package teste;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
 
@SuppressWarnings("serial")
public class InternalFrameTres extends JInternalFrame {
	private JTextField textField;  
 
    public InternalFrameTres() {
        super("Internal Frame Um",
              true, //resizable
               true, //closable
              true, //maximizable
               true);//iconifiable  
 
         setSize(300,300);  
 
         setSize(300,300);  
 
        JPanel panel = new JPanel();
         panel.setBackground(Color.BLUE);  
 
         Container container = getContentPane();
         container.add(panel);
         
         JButton btnNewButton = new JButton("New button");
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		dispose();
         	}
         });
         panel.add(btnNewButton);
         
         JLabel lblNewLabel = new JLabel("New label");
         panel.add(lblNewLabel);
         
         textField = new JTextField();
         panel.add(textField);
         textField.setColumns(10);
         
         addInternalFrameListener(new InternalFrameAdapter() {	
             public void internalFrameClosing(InternalFrameEvent e) {
            	  JOptionPane.showMessageDialog(null,"fechar: ","Oi. Simples assim!",JOptionPane.INFORMATION_MESSAGE);	  
             }
         });
     }
    
 }