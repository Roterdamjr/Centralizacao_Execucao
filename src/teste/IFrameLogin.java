package teste;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import view.FramePrincipal;
 
public class IFrameLogin extends JInternalFrame {
	private JTextField txtUsuario;  
	private JPasswordField pswSenha;
	private FramePrincipal frameChamador;
	
    public IFrameLogin(FramePrincipal frameChamador) {
/*        super("Internal Frame Um",
              true, //resizable
               true, //closable
              true, //maximizable
               true);//iconifiable  
 */
         Container container = getContentPane();
         getContentPane().setLayout(new BorderLayout(0, 0));
         
         JPanel panel = new JPanel();
         panel.setBorder(null);
         getContentPane().add(panel);
         panel.setLayout(new GridLayout(3, 3, 0, 0));
         
         JPanel panel_4 = new JPanel();
         panel_4.setBorder(null);
         panel.add(panel_4);
         
         JPanel panel_5 = new JPanel();
         panel.add(panel_5);
         
         JPanel panel_3 = new JPanel();
         panel_3.setBorder(null);
         panel.add(panel_3);
         
         JPanel panel_1 = new JPanel();
         panel.add(panel_1);
         
         JPanel panel_2 = new JPanel();
         panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
         panel.add(panel_2);
         panel_2.setLayout(new GridLayout(3, 1, 0, 0));
         
         JPanel panel_8 = new JPanel();
         panel_2.add(panel_8);
         
         JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
         panel_8.add(lblNewLabel);
         
         txtUsuario = new JTextField();
         panel_8.add(txtUsuario);
         txtUsuario.setColumns(10);
         
         JPanel panel_10 = new JPanel();
         panel_2.add(panel_10);
         
         JLabel lblNewLabel_1 = new JLabel("Senha");
         panel_10.add(lblNewLabel_1);
         
         pswSenha = new JPasswordField();
         panel_10.add(pswSenha);
         pswSenha.setColumns(10);
         pswSenha.setEchoChar('*');
         
         JPanel panel_9 = new JPanel();
         panel_2.add(panel_9);
         
         JButton btnSair = new JButton("Sair");
         btnSair.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		sair();
         		
         	}
         });
         panel_9.add(btnSair);
         
         JButton btnLogar = new JButton("Logar");
         btnLogar.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		logar();
         	}
         });
         panel_9.add(btnLogar);
         
         JPanel panel_6 = new JPanel();
         panel.add(panel_6);
         
         JPanel panel_7 = new JPanel();
         panel_7.setBorder(null);
         panel.add(panel_7);       
         
 		/*=======================================================
			C´O D I G O  P E R S O N A L I Z A D O
		======================================================*/
         this.frameChamador=frameChamador;
         
     }
    
    private void logar(){
		if(txtUsuario.getText().equals("") || pswSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Login ou Senha inválido.", "Login!", JOptionPane.ERROR_MESSAGE);
        }
		else{
			String loginn="roterdam";
			String senhaa="abcd1234";
	
	        if(txtUsuario.getText().equals(loginn) && pswSenha.getText().equals(senhaa)){
	        	frameChamador.setUsuarioLogado(true);
	        	sair();	 
	        }
	        else{
        	JOptionPane.showMessageDialog(null,"Login ou Senha inválidos.","Login",JOptionPane.ERROR_MESSAGE);
	                        pswSenha.setText("");
	        }
                
        }//else do login e senha vazios
		txtUsuario.setText("");
        pswSenha.setText("");
    }
    
    private void sair(){    	
    	this.doDefaultCloseAction();    	    
    }
 }