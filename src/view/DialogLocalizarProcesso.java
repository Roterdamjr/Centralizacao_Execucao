package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ProcessoSapwebDao;
import modelo.Processo;

public class DialogLocalizarProcesso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProcesso;
	private JLabel lblSetor,lblDataDistribuicao,lblExequente;
	private InternalFrameTriagem framePrincipal;
	private JComboBox<String> cboPartes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogLocalizarProcesso dialog = new DialogLocalizarProcesso(new InternalFrameTriagem());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogLocalizarProcesso(InternalFrameTriagem framePrincipal) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Selecionar Plano");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("Processo");
				panel.add(lblNewLabel);
			}
			{
				txtProcesso = new JTextField();
				panel.add(txtProcesso);
				txtProcesso.setColumns(20);
			}
			{
				JButton btnNewButton = new JButton("Selecionar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						exibeDadosDeProcesso();
					}
				});
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
				btnNewButton.setForeground(Color.BLUE);
				panel.add(btnNewButton);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				lblExequente = new JLabel("Exequente");
				panel.add(lblExequente);
			}
			{
				cboPartes = new JComboBox<String>();
				panel.add(cboPartes);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel_1 = new JLabel("Setor:");
				panel.add(lblNewLabel_1);
			}
			{
				lblSetor = new JLabel("Setor");
				panel.add(lblSetor);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel_2 = new JLabel("Data Distribui\u00E7\u00E3o:");
				panel.add(lblNewLabel_2);
			}
			{
				lblDataDistribuicao = new JLabel("DataDistribui\u00E7\u00E3o");
				panel.add(lblDataDistribuicao);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						retornarDados();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		/*
		 * ======================================================= C´O D I G O P
		 * P E R S O N A L I Z A D O
		 * ======================================================
		 */
		
		this.framePrincipal=framePrincipal;
		preencheCamposParaTeste();
	}
	
	private void preencheCamposParaTeste(){
		txtProcesso.setText("0162000-57.2009.5.01.0040");
		lblSetor.setText("40 VT/RJ");
		lblDataDistribuicao.setText("16/12/2009");
	}

	private void exibeDadosDeProcesso(){
		
		String[] dados;
		
		try {
			dados = new ProcessoSapwebDao().buscaDados(txtProcesso.getText());			
			lblSetor.setText(dados[0]);
			lblDataDistribuicao.setText(dados[1]);
		
			ArrayList<String> lista=new ProcessoSapwebDao().buscaPartes(txtProcesso.getText());
			for(String nome:lista){
				cboPartes.addItem((String)nome);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void retornarDados(){
		
		Processo obj =new Processo();
		obj.setNumCnj(txtProcesso.getText());
		obj.setExequente((String)cboPartes.getSelectedItem());
		obj.setSetor(lblSetor.getText());
		obj.setDataDistribuicao(lblDataDistribuicao.getText());
		
		//seta no frame chamador o processo
		framePrincipal.recebeProcessoDoDialog(obj);
		
		System.out.println("selecao "+cboPartes.getSelectedItem());
		dispose();
	}

}
