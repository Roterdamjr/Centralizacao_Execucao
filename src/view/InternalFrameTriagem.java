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
import utilitarios.Util;
import dao.EmpresaDao;
import dao.TriagemDao;

import javax.swing.SwingConstants;

import modelo.Processo;

public class InternalFrameTriagem extends JInternalFrame {
	//variavel poder passar aos parâmetros a referencia a este Frame
	private InternalFrameTriagem esteFrame;
	Processo processoRecebido;
	
	
	private JPanel contentPane;
	private JTable tabela;
	private JButton btnSair;
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
	private JLabel lblNewLabel_3;
	private JTextField txtValorPedido;
	private JLabel lblNewLabel_5;
	private JTextField txtLocalizacao;
	private JTextField txtObservacao;
	private final JTextField textField_11 = new JTextField();
	private JLabel lblObservao;
	private JPanel pnlInclusao;
	private JTextField txtExequente;
	private JPanel panel_22;
	private JPanel panel_23;
	private JButton btnSelecionarPlano;
	private JLabel lblEmpresa;
	private JPanel pnlTabela;
	private JComboBox comboBox;
	private JPanel panel;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel_4;
	private JTextField txtDataDistribuicao;
	private JButton btnProcesso;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameTriagem frame = new InternalFrameTriagem();
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
	public InternalFrameTriagem() {
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
		panel_23.setLayout(new GridLayout(3, 1, 0, 0));

		panel_22 = new JPanel();
		panel_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_23.add(panel_22);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_22.add(panel_4, BorderLayout.WEST);

		lbl1 = new JLabel("Plano");
		panel_4.add(lbl1);

		lblEmpresa = new JLabel("New label");
		panel_4.add(lblEmpresa);
		lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmpresa.setForeground(Color.RED);
				
		panel = new JPanel();
		panel_22.add(panel, BorderLayout.EAST);

		btnSelecionarPlano = new JButton("Plano");
		panel.add(btnSelecionarPlano);
		btnSelecionarPlano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//selecionaProcesso(this);
			}
		});
		btnSelecionarPlano.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSelecionarPlano.setForeground(Color.BLUE);

		pnlInclusao = new JPanel();
		panel_23.add(pnlInclusao);
		pnlInclusao.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlInclusao.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		pnlInclusao.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblProcesso = new JLabel("Processo");
		panel_1.add(lblProcesso);
		lblProcesso.setHorizontalAlignment(SwingConstants.RIGHT);

		txtProcesso = new JTextField();
		panel_1.add(txtProcesso);
		txtProcesso.setColumns(10);

		lblSetor = new JLabel("Setor");
		panel_1.add(lblSetor);
		lblSetor.setHorizontalAlignment(SwingConstants.RIGHT);

		txtSetor = new JTextField();
		panel_1.add(txtSetor);
		txtSetor.setColumns(7);

		lblNewLabel = new JLabel("Exequente");
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		txtExequente = new JTextField();
		panel_1.add(txtExequente);
		txtExequente.setColumns(40);

		lblNewLabel_4 = new JLabel("Data Distribui\u00E7\u00E3o");
		panel_1.add(lblNewLabel_4);

		txtDataDistribuicao = new JTextField();
		panel_1.add(txtDataDistribuicao);
		txtDataDistribuicao.setColumns(7);

		panel_3 = new JPanel();
		pnlInclusao.add(panel_3, BorderLayout.EAST);

		btnProcesso = new JButton("Processo");
		btnProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaProcesso();
			}
		});
		btnProcesso.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnProcesso.setForeground(Color.BLUE);
		panel_3.add(btnProcesso);

		panel_5 = new JPanel();
		panel_23.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblDataRecebimento = new JLabel("Data Recebimento");
		panel_6.add(lblDataRecebimento);
		lblDataRecebimento.setHorizontalAlignment(SwingConstants.RIGHT);

		txtDataRecebimento = new JTextField();
		panel_6.add(txtDataRecebimento);
		txtDataRecebimento.setColumns(7);

		lblNewLabel_1 = new JLabel("Anterioridade");
		panel_6.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);

		txtAnterioridade = new JTextField();
		panel_6.add(txtAnterioridade);
		txtAnterioridade.setColumns(7);

		lblNewLabel_2 = new JLabel("Prioridade");
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBox = new JComboBox();
		panel_6.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"a", "b"}));

		lblNewLabel_3 = new JLabel("Valor Pedido");
		panel_6.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		txtValorPedido = new JTextField();
		panel_6.add(txtValorPedido);
		txtValorPedido.setColumns(9);

		lblNewLabel_5 = new JLabel("Localiza\u00E7\u00E3o");
		panel_6.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);

		txtLocalizacao = new JTextField();
		panel_6.add(txtLocalizacao);
		txtLocalizacao.setColumns(20);

		lblObservao = new JLabel("Observa\u00E7\u00E3o");
		panel_6.add(lblObservao);
		lblObservao.setHorizontalAlignment(SwingConstants.RIGHT);

		txtObservacao = new JTextField();
		panel_6.add(txtObservacao);
		txtObservacao.setColumns(20);

		pnlTabela = new JPanel();
		pnlTabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlTabela, BorderLayout.CENTER);
		pnlTabela.setLayout(new BorderLayout(10, 5));

		tabela = new JTable();
		tabela.setEnabled(false);
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Processo", "Setor", "Exequente", "Data Recebimento", "Prioridade", "Valor do Pedido:", "Valor Pedidos", "Localliza\u00E7\u00E3o", "Observa\u00E7\u00E3o"
			}
		));
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
		 * P E R S O N A L I Z A D O
		 * ======================================================
		 */
		setSize(1300, 800);
		esteFrame=this; 
		/* apos carregamento da janela */
		// populaComboDeClassificacoes();
	

	}


	private void selecionaProcesso() {	
		DialogLocalizarProcesso dialogo=new DialogLocalizarProcesso(esteFrame);
		dialogo.setVisible(true);
		System.out.println(processoRecebido.getNumCnj()+ processoRecebido.getExequente()+processoRecebido.getSetor()+processoRecebido.getDataDistribuicao());
		
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


	
	public void recebeProcessoDoDialog(Processo proc){
		processoRecebido=proc;
	}

	private void fechaFrame() {	
	  	this.doDefaultCloseAction();
	}

	private void validaDados() {

	}

}
