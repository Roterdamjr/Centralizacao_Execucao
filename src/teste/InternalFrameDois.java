package teste;

import javax.swing.JInternalFrame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
 
public class InternalFrameDois extends JInternalFrame {
	private JTextField textField;  
 
     public InternalFrameDois() {
         super("Internal Frame Dois",
               true, //resizable
               true, //closable
                true, //maximizable
               true);//iconifiable  
 
         setSize(300,300);  
 
        JPanel panel = new JPanel();
         panel.setBackground(Color.YELLOW);  
 
         Container container = getContentPane();
         container.add(panel);
         
         JLabel lblNewLabel = new JLabel("New label");
         panel.add(lblNewLabel);
         
         textField = new JTextField();
         panel.add(textField);
         textField.setColumns(10);
 
     }
}