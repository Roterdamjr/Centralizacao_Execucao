package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import utilitarios.Utilitario;
import dao.PlanoExecucaoDao;
import dao.TriagemDao;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.border.BevelBorder;

public class InternalFrameEmpresa extends JInternalFrame {
	private JPanel contentPane;
	private JTable tabela;
	private JButton btnSair;
	private JLabel lblSetor;
	private JTextField txtNome;
	private JLabel lblProcesso;
	private JTextField txtProcesso;
	private JLabel lblNewLabel_3;
	private JTextField txtDepositoMensal;
	private final JTextField textField_11 = new JTextField();
	private JPanel pnlInclusao;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel pnlTabela;
	private JPanel panel;
	private JPanel panel_4;
	private JButton btnExcluir;
	private JRadioButton rdbInclusao;
	private JRadioButton rdbSelecao;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JPanel panel_7;
	private JButton btnIncluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameEmpresa frame = new InternalFrameEmpresa();
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
	public InternalFrameEmpresa() {
		setClosable(true);

		textField_11.setColumns(10);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("Empresa");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);

		panel_23 = new JPanel();
		panel_23.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_23, BorderLayout.NORTH);
		panel_23.setLayout(new BorderLayout(10, 20));

		panel_24 = new JPanel();
		panel_24.setBorder(null);
		panel_23.add(panel_24, BorderLayout.CENTER);

		rdbInclusao = new JRadioButton("Inclus\u00E3o");
		rdbInclusao.setSelected(true);
		rdbInclusao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					habilitaInclusao();
					// System.out.println("habilitaInclusao");
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					habilitaSelecao();
					// System.out.println("habilitaSelecao");
				}
			}
		});
		panel_24.setLayout(new BorderLayout(0, 0));
		panel_24.add(rdbInclusao, BorderLayout.WEST);

		pnlInclusao = new JPanel();
		panel_23.add(pnlInclusao, BorderLayout.SOUTH);
		pnlInclusao.setBorder(null);
		pnlInclusao.setLayout(new GridLayout(2, 3, 5, 5));

		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlInclusao.add(panel_1);

		lblSetor = new JLabel("Nome");
		panel_1.add(lblSetor);
		lblSetor.setHorizontalAlignment(SwingConstants.RIGHT);

		txtNome = new JTextField();
		panel_1.add(txtNome);
		txtNome.setColumns(10);

		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlInclusao.add(panel_3);

		lblProcesso = new JLabel("Processo");
		panel_3.add(lblProcesso);
		lblProcesso.setHorizontalAlignment(SwingConstants.RIGHT);

		txtProcesso = new JTextField();
		panel_3.add(txtProcesso);
		txtProcesso.setColumns(10);

		panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlInclusao.add(panel_5);

		lblNewLabel_3 = new JLabel("Dep\u00F3sito Mensal");
		panel_5.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		txtDepositoMensal = new JTextField();
		panel_5.add(txtDepositoMensal);
		txtDepositoMensal.setColumns(5);

		panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnlInclusao.add(panel_6);

		lblNewLabel = new JLabel("CNPJ");
		panel_6.add(lblNewLabel);

		textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(10);

		panel_7 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnlInclusao.add(panel_7);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 20));

		panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		rdbSelecao = new JRadioButton("Sele\u00E7\u00E3o");
		panel_4.add(rdbSelecao);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(Color.BLUE);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExcluir.setHorizontalAlignment(SwingConstants.LEFT);
		btnExcluir.setEnabled(false);
		panel_4.add(btnExcluir, BorderLayout.EAST);

		pnlTabela = new JPanel();
		panel.add(pnlTabela);
		pnlTabela.setBorder(null);
		pnlTabela.setLayout(new BorderLayout(10, 5));

		tabela = new JTable();
		tabela.setEnabled(false);
		tabela.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "Nome", "Processo",
				"Dep\u00F3sito Mensal" }));
		JScrollPane scrollPane = new JScrollPane(tabela);
		pnlTabela.add(scrollPane);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		btnSair = new JButton("Sair");
		btnSair.setForeground(Color.BLUE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechaFrame();
			}
		});
		panel_2.add(btnSair);
		/*
		 * ======================================================= C´O D I G O P
		 * E R S O N A L I Z A D O
		 * ======================================================
		 */
		setSize(600, 600);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbInclusao);
		
		btnIncluir = new JButton("Incluir");
		panel_24.add(btnIncluir, BorderLayout.EAST);
		grupo.add(rdbSelecao);

		populaTabelaComDadosDoBanco();
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

	}

	private void habilitaInclusao() {
		Utilitario.bloquearLiberarCampos2Niveis(pnlInclusao, true);

		tabela.setEnabled(false);
		btnIncluir.setEnabled(true);
		btnExcluir.setEnabled(false);
	}

	private void habilitaSelecao() {
		Utilitario.bloquearLiberarCampos2Niveis(pnlInclusao, false);
		tabela.setEnabled(true);
		btnIncluir.setEnabled(false);
		btnExcluir.setEnabled(true);
	}

	private void fechaFrame() {
		this.doDefaultCloseAction();
	}

	private void validaDados() {

	}

}
