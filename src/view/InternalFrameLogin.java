package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



@SuppressWarnings("serial")
public class InternalFrameLogin extends JInternalFrame {
/*	
	//passagem de prâmetros entre frames
	private InternalFrameTriagem frameChamador;
	//
	*/
	private JTextField txtUsuario;
	private JPasswordField pswSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameLogin frame = new InternalFrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InternalFrameLogin() {

		setTitle("Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_4);
		panel_4.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		panel_7.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		panel_7.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		panel_8.add(lblNewLabel_1);
		
		pswSenha = new JPasswordField();
		pswSenha.setColumns(10);
		panel_8.add(pswSenha);
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validarUsuario();
			}
		});
		panel_9.add(btnOK);
		
		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);
		
		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6);
		
		/*
		 * ======================================================= 
		 * C´O D I G O 		P E R S O N A L I Z A D O
		 * ======================================================
		 */
		
		//faz ENTER disparar botão "OK"		
		this.getRootPane().setDefaultButton(btnOK);
	
	}

	private void validarUsuario(){
		System.out.println("validando");
		
		//o getPassword retorn arry de caracter, por isso deve ser craida uma string 
		String senhaDigitada= new String(pswSenha.getPassword());
		
		if(txtUsuario.getText().equals("") || senhaDigitada.equals("")){
            JOptionPane.showMessageDialog(null, "Login ou Senha inválido.", "Login", JOptionPane.ERROR_MESSAGE);
        }
		else{
			String loginn="r";
			String senhaa="a";
				
	        if(txtUsuario.getText().equals(loginn) && senhaDigitada.equals(senhaa)){
	        	this.doDefaultCloseAction();
	        	dispose();  
	        }
	        else{
	        	JOptionPane.showMessageDialog(null,"Login ou Senha inválidos.","Login",JOptionPane.ERROR_MESSAGE);
	                        pswSenha.setText("");
	        }
                
        }//else do login e senha vazios
		txtUsuario.setText("");
        pswSenha.setText("");
	}
}
