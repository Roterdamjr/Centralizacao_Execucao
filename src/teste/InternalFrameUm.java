package teste;

import javax.swing.JInternalFrame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
 
@SuppressWarnings("serial")
public class InternalFrameUm extends JInternalFrame {
	private JTextField textField;  
 
    public InternalFrameUm() {
        super("Internal Frame Um",
              true, //resizable
               true, //closable
              false, //maximizable
               true);//iconifiable  
 
         setSize(300,300);  
 
 
        JPanel panel = new JPanel();
         panel.setBackground(Color.GREEN);  
 
         Container container = getContentPane();
         container.add(panel);
         
         JLabel lblNewLabel = new JLabel("New label");
         panel.add(lblNewLabel);
         
         textField = new JTextField();
         panel.add(textField);
         textField.setColumns(10);
         
         JButton btnNewButton = new JButton("New button");
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		fechar();
         	}
         });
         panel.add(btnNewButton);
         
         
     }
    
    private void fechar(){
    	this.doDefaultCloseAction();
    	dispose();
    }
 }