package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLogin extends JDialog {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pswSenha;
	private FramePrincipal frameChamador;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			DialogLogin dialog = new DialogLogin(JFrame framePai);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public DialogLogin(FramePrincipal frameChamador) {
		setModal(true);
		setBounds(100, 100, 254, 172);
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Senha");
		panel_1.add(lblNewLabel);
		
		pswSenha = new JPasswordField();
		panel_1.add(pswSenha);
		pswSenha.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		panel.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clicarOK();
			}
		});
		panel_2.add(btnOK);
 		/*=======================================================
			C´O D I G O  P E R S O N A L I Z A D O
		======================================================*/
         this.frameChamador=frameChamador;
         frameChamador.setUsuarioLogado(false);
	}

	private void clicarOK(){
		if(txtUsuario.getText().equals("") || pswSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Login ou Senha inválido.", "Login", JOptionPane.ERROR_MESSAGE);
        }
		else{
			String loginn="r";
			String senhaa="a";
	
	        if(txtUsuario.getText().equals(loginn) && pswSenha.getText().equals(senhaa)){
	        	frameChamador.setUsuarioLogado(true);
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
