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
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import utilitarios.MesDoAno;
import utilitarios.Utilitario;
import dao.PlanoExecucaoDao;
import dao.TriagemDao;

import javax.swing.SwingConstants;

public class Old_InternalFrameTriagem extends JInternalFrame {
	private JPanel contentPane;
	private JTable tabela;
	private JButton btnIncluir, btnSair;
	private JComboBox cboEmpresa;
	JLabel lbl1;
	private JLabel lblSetor;
	private JTextField txtSetor;
	private JLabel lblDataRecebimento;
	private JTextField txtDataRecebimento;
	private JLabel lblProcesso;
	private JTextField txtProcesso;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtAnterioridade;
	private JLabel lblNewLabel_2;
	private JLabel lblAlvPagParcial;
	private JTextField txtAlvPagParcial;
	private JLabel lblNewLabel_3;
	private JTextField txtValorPedido;
	private JLabel lblNewLabel_4;
	private JTextField txtValorPago;
	private JLabel lblNewLabel_5;
	private JTextField txtLocalizacao;
	private JTextField txtObservacao;
	private final JTextField textField_11 = new JTextField();
	private JLabel lblObservao;
	private JPanel pnlInclusao;
	private JTextField txtExequente;
	private JPanel panel_3;
	private JPanel panel_22;
	private JPanel panel_23;
	private JButton btnSelecionarEmpresa;
	private JLabel lblEmpresa;
	private JPanel pnlTabela;
	private JPanel panel_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Old_InternalFrameTriagem frame = new Old_InternalFrameTriagem();
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
	public Old_InternalFrameTriagem() {
		setClosable(true);

		textField_11.setColumns(10);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("Triagem");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);

		panel_23 = new JPanel();
		contentPane.add(panel_23, BorderLayout.NORTH);
		panel_23.setLayout(new BorderLayout(10, 5));

		panel_22 = new JPanel();
		panel_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel_22.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_23.add(panel_22, BorderLayout.NORTH);

		lbl1 = new JLabel("Empresa");
		panel_22.add(lbl1);

		cboEmpresa = new JComboBox();
		panel_22.add(cboEmpresa);

		btnSelecionarEmpresa = new JButton("Ok");
		btnSelecionarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preparaParaCadastro();
			}
		});
		btnSelecionarEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSelecionarEmpresa.setForeground(Color.BLUE);
		panel_22.add(btnSelecionarEmpresa);

		lblEmpresa = new JLabel("New label");
		lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmpresa.setForeground(Color.RED);
		panel_22.add(lblEmpresa);

		pnlInclusao = new JPanel();
		panel_23.add(pnlInclusao, BorderLayout.SOUTH);
		pnlInclusao.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlInclusao.setLayout(new GridLayout(3, 8, 5, 5));

		lblSetor = new JLabel("Setor");
		lblSetor.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblSetor);

		txtSetor = new JTextField();
		pnlInclusao.add(txtSetor);
		txtSetor.setEnabled(false);
		txtSetor.setColumns(10);

		lblDataRecebimento = new JLabel("Data Recebimento");
		lblDataRecebimento.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblDataRecebimento);

		txtDataRecebimento = new JTextField();
		pnlInclusao.add(txtDataRecebimento);
		txtDataRecebimento.setEnabled(false);
		txtDataRecebimento.setColumns(7);

		lblProcesso = new JLabel("Processo");
		lblProcesso.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblProcesso);

		txtProcesso = new JTextField();
		pnlInclusao.add(txtProcesso);
		txtProcesso.setEnabled(false);
		txtProcesso.setColumns(10);

		lblNewLabel_3 = new JLabel("Valor Pedido");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblNewLabel_3);

		txtValorPedido = new JTextField();
		pnlInclusao.add(txtValorPedido);
		txtValorPedido.setEnabled(false);
		txtValorPedido.setColumns(5);

		lblNewLabel_1 = new JLabel("Anterioridade");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblNewLabel_1);

		txtAnterioridade = new JTextField();
		pnlInclusao.add(txtAnterioridade);
		txtAnterioridade.setEnabled(false);
		txtAnterioridade.setColumns(10);

		lblNewLabel_2 = new JLabel("Classifica\u00E7\u00E3o");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"a", "b"}));
		pnlInclusao.add(comboBox);

		lblAlvPagParcial = new JLabel("Alv. Pag Parcial");
		lblAlvPagParcial.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblAlvPagParcial);

		txtAlvPagParcial = new JTextField();
		pnlInclusao.add(txtAlvPagParcial);
		txtAlvPagParcial.setEnabled(false);
		txtAlvPagParcial.setColumns(10);

		lblNewLabel_4 = new JLabel("Valor Pago");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblNewLabel_4);

		txtValorPago = new JTextField();
		pnlInclusao.add(txtValorPago);
		txtValorPago.setEnabled(false);
		txtValorPago.setColumns(10);

		lblNewLabel = new JLabel("Exequente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblNewLabel);

		txtExequente = new JTextField();
		pnlInclusao.add(txtExequente);
		txtExequente.setEnabled(false);
		txtExequente.setColumns(20);

		lblNewLabel_5 = new JLabel("Localiza\u00E7\u00E3o");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblNewLabel_5);

		txtLocalizacao = new JTextField();
		pnlInclusao.add(txtLocalizacao);
		txtLocalizacao.setEnabled(false);
		txtLocalizacao.setColumns(15);

		lblObservao = new JLabel("Observa\u00E7\u00E3o");
		lblObservao.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInclusao.add(lblObservao);

		txtObservacao = new JTextField();
		pnlInclusao.add(txtObservacao);
		txtObservacao.setEnabled(false);
		txtObservacao.setColumns(15);

		panel_1 = new JPanel();
		pnlInclusao.add(panel_1);

		panel_3 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_3.getLayout();
		flowLayout_13.setHgap(30);
		flowLayout_13.setAlignment(FlowLayout.RIGHT);
		pnlInclusao.add(panel_3);

		btnIncluir = new JButton("Incluir");
		panel_3.add(btnIncluir);
		btnIncluir.setForeground(Color.BLUE);
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				incluirRegistro();
			}
		});

		pnlTabela = new JPanel();
		pnlTabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlTabela, BorderLayout.CENTER);
		pnlTabela.setLayout(new BorderLayout(10, 5));

		tabela = new JTable();
		tabela.setEnabled(false);
		tabela.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null, null, null, null, null, null, null, null }

		}, new String[] { "Setor", "Data Recebimento", "Processo", "Exequente",
				"Anterioridade", "Classifica\u00E7\u00E3o", "Alv. Pg Parcial",
				"Valor do Pedido:", "Valor Pago", "Localiza\u00E7\u00E3o",
				"Observa\u00E7\u00E3o" }));
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
		setSize(1300, 800);
		
		/* apos carregamento da janela */
		// populaComboDeClassificacoes();

		/* populaComboDeEmpresas(); */
	
		pnlInclusao.setVisible(false);
		pnlTabela.setVisible(false);
	}

	private void populaComboDeEmpresas() {
		PlanoExecucaoDao dao = new PlanoExecucaoDao();
		List<String> lista = dao.buscaTodos();

		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			String obj = (String) iterator.next();
			cboEmpresa.addItem(obj);
		}
		cboEmpresa.setSelectedIndex(-1); // limpa combo
	}

	private void incluirRegistro() {
		validaDados();
		// salva em BD

		for (int i = 0; i < tabela.getRowCount(); i++) {
			String[] valores = new String[10];
			String valorColuna;

			valorColuna = (String) tabela.getModel().getValueAt(i, 0); // 3=coluna;
			valores[0] = valorColuna;

			valorColuna = (String) tabela.getModel().getValueAt(i, 1); // 3=coluna;
			valores[1] = valorColuna;

			new TriagemDao().insereLinha(valores);
		}

		populaTabelaComDadosDoBanco();
	}

	private void populaTabelaComDadosDoBanco() {

		ResultSet rs = new TriagemDao().buscaRsPorEmpresa(1);

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

	private void preparaParaCadastro() {	
		pnlInclusao.setVisible(true);
		pnlTabela.setVisible(true);
		Utilitario.bloquearLiberarCampos(pnlInclusao,true);
		Utilitario.bloquearLiberarCampos(pnlTabela,false);
		 


	}

	private void preparaParaInclusao() {

	}

	private void fechaFrame() {	
	  	this.doDefaultCloseAction();
	}

	private void validaDados() {

	}

}
