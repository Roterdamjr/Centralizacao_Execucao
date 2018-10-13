package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class FrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pswSenha;
	private JButton btnNewButton;
	private JPanel panel_1;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
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
	public FrameLogin() {
		
		
		setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 16));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		panel_2.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		panel_2.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		panel.add(lblNewLabel_1);
		
		pswSenha = new JPasswordField();
		pswSenha.setEchoChar('*');
		pswSenha.setColumns(5);
		panel.add(pswSenha);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logar();
			}
		});
		panel_1.add(btnNewButton);
	}
	
	private void logar(){
		if(txtUsuario.getText().equals("") || pswSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Login ou Senha inválido.", "Oi. Simples assim!", JOptionPane.ERROR_MESSAGE);
        }
		else{
			String loginn="roterdam";
			String senhaa="abcd1234";
	
	        if(txtUsuario.getText().equals(loginn) && pswSenha.getText().equals(senhaa)){
	                        JOptionPane.showMessageDialog(null,"Seja bem vindo: " + loginn,"Oi. Simples assim!",JOptionPane.INFORMATION_MESSAGE);	 
	        }
	        else{
	        	JOptionPane.showMessageDialog(null,"Login ou Senha inválidos.","Oi. Simples assim!",JOptionPane.ERROR_MESSAGE);
	                        pswSenha.setText("");
	        }
                
        }//else do login e senha vazios
		txtUsuario.setText("");
        pswSenha.setText("");
	}

}
