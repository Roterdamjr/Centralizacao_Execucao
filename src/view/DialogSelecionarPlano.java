package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Processo;
import dao.PlanoExecucaoDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogSelecionarPlano extends JDialog {

	//passagem de prâmetros entre frames
	private InternalFrameTriagem framePrincipal;
	
	private final JPanel contentPanel = new JPanel();
	private JTable tabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogSelecionarPlano dialog = new DialogSelecionarPlano(new InternalFrameTriagem());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogSelecionarPlano(InternalFrameTriagem framePrincipal) {
		setTitle("Selecionar Plano de Execu\u00E7\u00E3o");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(20, 20));


		tabela = new JTable();
		tabela.setModel(new DefaultTableModel(
				new Object[][] {
						{null},
				},
				new String[] {
						"Planos de Execu\u00E7\u00E3o"
				}
				));
		contentPanel.add(tabela);
		JScrollPane scrollPane = new JScrollPane(tabela);
		contentPanel.add(scrollPane);

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
		 * ======================================================= 
		 * C´O D I G O 		P E R S O N A L I Z A D O
		 * ======================================================
		 */
		//tabela.getModel().isCellEditable(false);
		populaTabelaComDadosDoBanco();
		
		//passagem de prâmetros entre frames
		this.framePrincipal=framePrincipal;

	}
	
	private void populaTabelaComDadosDoBanco() {

		ResultSet rs = new PlanoExecucaoDao().buscaTodosComResultset();

		try {
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] row = new Object[columns];
				for (int i = 1; i <= columns; i++) {
					row[i - 1] = rs.getObject(i);
				}
				((DefaultTableModel) tabela.getModel()).insertRow(
						rs.getRow() - 1, row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	private void retornarDados(){	
		String texto=(String)tabela.getModel().getValueAt(  tabela.getSelectedRow() ,0);
		//seta no frame chamador o processo
		framePrincipal.recebePlanoDoDialog(texto);
		
		System.out.println("selecao da dialog "+texto);
		dispose();
	}
}
