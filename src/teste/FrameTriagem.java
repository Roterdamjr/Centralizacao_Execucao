package teste;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import utilitarios.MesDoAno;
import utilitarios.TipoPrioridade;
import utilitarios.Util;
import dao.EmpresaDao;
import dao.TriagemDao;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class FrameTriagem extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	private JButton btnIncluir, btnSair;
	private JComboBox cboEmpresa;
	private JPanel panel_4;
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
	private JTextField txtClassificacao;
	private JLabel lblAlvPagParcial;
	private JTextField txtAlvPagParcial;
	private JLabel lblNewLabel_3;
	private JTextField txtValorPedido;
	private JLabel lblNewLabel_4;
	private JTextField txtValorPago;
	private JPanel panel_5;
	private JLabel lblNewLabel_5;
	private JTextField txtLocalizacao;
	private JTextField txtObservacao;
	/**
	 * @wbp.nonvisual location=252,-31
	 */
	private final JTextField textField_11 = new JTextField();
	private JLabel lblObservao;
	private JPanel pnlInclusao;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JPanel panel_21;
	private JTextField txtExequente;
	private JPanel panel_3;
	private JPanel panel_22;
	private JPanel panel_23;
	private JButton btnSelecionarEmpresa;
	private JButton btnTabela;
	private JButton btnInclusao;
	private JPanel panel_24;
	private JLabel lblEmpresa;
	JPanel pnlTabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTriagem frame = new FrameTriagem();
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
	public FrameTriagem() {
		textField_11.setColumns(10);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("Triagem");

		/*
		 *  ********************* ajusta as propriedades do frsme
		 * ***********************
		 */

		/* *********************** */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);

		panel_23 = new JPanel();
		contentPane.add(panel_23, BorderLayout.NORTH);
		panel_23.setLayout(new BorderLayout(10, 5));

		panel_24 = new JPanel();
		panel_24.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_5 = (FlowLayout) panel_24.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_23.add(panel_24, BorderLayout.NORTH);

		btnTabela = new JButton("Tabela");
		btnTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preparaParaTabela();
			}
		});
		btnTabela.setEnabled(false);
		panel_24.add(btnTabela);

		btnInclusao = new JButton("Inclus\u00E3o");
		btnInclusao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preparaParaInclusao();;
			}
		});
		btnInclusao.setEnabled(false);
		panel_24.add(btnInclusao);

		panel_22 = new JPanel();
		panel_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel_22.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_23.add(panel_22, BorderLayout.CENTER);

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
		pnlInclusao.setLayout(new GridLayout(4, 1, 0, 0));

		panel_7 = new JPanel();
		pnlInclusao.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 4, 0, 0));

		panel_12 = new JPanel();
		panel_7.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panel_10 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_10.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_10);

		lblSetor = new JLabel("Setor");
		panel_10.add(lblSetor);

		txtSetor = new JTextField();
		txtSetor.setEnabled(false);
		panel_10.add(txtSetor);
		txtSetor.setColumns(20);

		panel_11 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_11.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_11);

		lblDataRecebimento = new JLabel("Data Recebimento");
		panel_11.add(lblDataRecebimento);

		txtDataRecebimento = new JTextField();
		txtDataRecebimento.setEnabled(false);
		panel_11.add(txtDataRecebimento);
		txtDataRecebimento.setColumns(7);

		panel_16 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_16.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_16);

		lblProcesso = new JLabel("Processo");
		panel_16.add(lblProcesso);

		txtProcesso = new JTextField();
		txtProcesso.setEnabled(false);
		panel_16.add(txtProcesso);
		txtProcesso.setColumns(10);

		panel_8 = new JPanel();
		pnlInclusao.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 4, 0, 0));

		panel_14 = new JPanel();
		panel_8.add(panel_14);
		panel_14.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblNewLabel_3 = new JLabel("Valor Pedido");
		panel_14.add(lblNewLabel_3);

		txtValorPedido = new JTextField();
		txtValorPedido.setEnabled(false);
		panel_14.add(txtValorPedido);
		txtValorPedido.setColumns(5);

		panel_17 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_17.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_17);

		lblNewLabel_1 = new JLabel("Anterioridade");
		panel_17.add(lblNewLabel_1);

		txtAnterioridade = new JTextField();
		txtAnterioridade.setEnabled(false);
		panel_17.add(txtAnterioridade);
		txtAnterioridade.setColumns(10);

		panel_18 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_18.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_18);

		lblNewLabel_2 = new JLabel("Classifica\u00E7\u00E3o");
		panel_18.add(lblNewLabel_2);

		txtClassificacao = new JTextField();
		txtClassificacao.setEnabled(false);
		panel_18.add(txtClassificacao);
		txtClassificacao.setColumns(10);

		panel_19 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_19.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_19);

		lblAlvPagParcial = new JLabel("Alv. Pag Parcial");
		panel_19.add(lblAlvPagParcial);

		txtAlvPagParcial = new JTextField();
		txtAlvPagParcial.setEnabled(false);
		panel_19.add(txtAlvPagParcial);
		txtAlvPagParcial.setColumns(10);

		panel_9 = new JPanel();
		pnlInclusao.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 4, 0, 0));

		panel_15 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_15.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_15);

		lblNewLabel_4 = new JLabel("Valor Pago");
		panel_15.add(lblNewLabel_4);

		txtValorPago = new JTextField();
		txtValorPago.setEnabled(false);
		panel_15.add(txtValorPago);
		txtValorPago.setColumns(10);

		panel_13 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_13.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_13);

		lblNewLabel = new JLabel("Exequente");
		panel_13.add(lblNewLabel);

		txtExequente = new JTextField();
		txtExequente.setEnabled(false);
		panel_13.add(txtExequente);
		txtExequente.setColumns(20);

		panel_20 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_20.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_20);

		lblNewLabel_5 = new JLabel("Localiza\u00E7\u00E3o");
		panel_20.add(lblNewLabel_5);

		txtLocalizacao = new JTextField();
		txtLocalizacao.setEnabled(false);
		panel_20.add(txtLocalizacao);
		txtLocalizacao.setColumns(15);

		panel_21 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_21.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_21);

		lblObservao = new JLabel("Observa\u00E7\u00E3o      ");
		panel_21.add(lblObservao);

		txtObservacao = new JTextField();
		txtObservacao.setEnabled(false);
		panel_21.add(txtObservacao);
		txtObservacao.setColumns(15);

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

		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);

		panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		/*
		 * ======================================================= C´O D I G O P
		 * E R S O N A L I Z A D O
		 * ======================================================
		 */
		setSize(1300, 800);
		this.setLocationRelativeTo(null);
		/* apos carregamento da janela */

		populaTabelaComDadosDoBanco();
		// populaComboDeClassificacoes();
		populaComboDeEmpresas();
		// preparaFrameEmBusca();
		DefaultComboBoxModel<MesDoAno> cModel = new DefaultComboBoxModel<MesDoAno>(
				MesDoAno.values());
	}

	/******************************************************************************************************/

	private void populaComboDeEmpresas() {
		EmpresaDao dao = new EmpresaDao();
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

	private void preparaParaCadastro(){
		System.out.println("cadastrando");
		
/*		Util.bloquearLiberarCampos(pnlInclusao,false);
		Util.bloquearLiberarCampos(pnlTabela,false);*/
		habiltaDesabilitaInclusao(true);
		btnTabela.enable(true);
		btnInclusao.enable(false);			

	}

	
	private void preparaParaTabela(){
		tabela.setEnabled(false);
	}
	
	private void preparaParaInclusao(){
		
	}

	private void habiltaDesabilitaInclusao(boolean acao){
		txtSetor.setEnabled(acao);		
		txtDataRecebimento.setEnabled(acao);		 
		txtProcesso.setEnabled(acao);		  
		txtAnterioridade.setEnabled(acao);		
		txtClassificacao.setEnabled(acao);		  
		txtAlvPagParcial.setEnabled(acao);		 
		txtValorPedido.setEnabled(acao);		 
		txtValorPago.setEnabled(acao);				
		txtLocalizacao.setEnabled(acao);
		txtObservacao.setEnabled(acao);

	}



	private void fechaFrame() {

	}

	private void validaDados() {

	}

}
