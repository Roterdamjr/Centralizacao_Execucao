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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Processo;
import utilitarios.TipoPrioridade;
import utilitarios.Utilitario;
import dao.TriagemDao;
import documento.GeradorDeDocumento;

public class InternalFrameTriagem extends JInternalFrame {
	//passagem de prâmetros entre frames 
	private InternalFrameTriagem esteFrame;
	private Processo processoRecebido;
	private String planoRecebido;
	
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
	private JTextField txtDataAnterioridade;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtValorDivida;
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
	private JLabel lblPlano;
	private JPanel pnlTabela;
	private JComboBox<String> cboPrioridade;
	private JPanel panel;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel_4;
	private JTextField txtDataDistribuicao;
	private JButton btnProcesso;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_6;
	private JButton btnGravar;

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

		lblPlano = new JLabel("New label");
		panel_4.add(lblPlano);
		lblPlano.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPlano.setForeground(Color.RED);
				
		panel = new JPanel();
		panel_22.add(panel, BorderLayout.EAST);

		btnSelecionarPlano = new JButton("Selecionar Plano     ");
		panel.add(btnSelecionarPlano);
		btnSelecionarPlano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaPlano();
			}
		});
		btnSelecionarPlano.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelecionarPlano.setForeground(Color.BLACK);

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
		txtProcesso.setColumns(15);

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

		btnProcesso = new JButton("SelecionarProcesso");
		btnProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaProcesso();
			}
		});
		btnProcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnProcesso.setForeground(Color.BLACK);
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

		txtDataAnterioridade = new JTextField();
		panel_6.add(txtDataAnterioridade);
		txtDataAnterioridade.setColumns(7);

		lblNewLabel_2 = new JLabel("Prioridade");
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);

		cboPrioridade = new JComboBox();
		panel_6.add(cboPrioridade);

		lblNewLabel_3 = new JLabel("Valor da D\u00EDvida");
		panel_6.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		txtValorDivida = new JTextField();
		panel_6.add(txtValorDivida);
		txtValorDivida.setColumns(9);

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
		
		btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravarCredito();
			}
		});
		btnGravar.setForeground(Color.BLUE);
		btnGravar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_2.add(btnGravar);
		/*
		 * ======================================================= 
		 * C´O D I G O P		 * P E R S O N A L I Z A D O
		 * ======================================================
		 */
		setSize(1400, 800);//larguraXaltura
		esteFrame=this; 
		populaComboPrioridade();
		/* apos carregamento da janela */


		preencheCamposParaTeste();

	}

	
	private void populaComboPrioridade() {	
		for(TipoPrioridade prioridade : TipoPrioridade.values()){
			cboPrioridade.addItem((String)prioridade.getDescricao());
		}
	}

	private void selecionaPlano() {
		//passagem de prâmetros entre frames 
		DialogSelecionarPlano dialogo=new DialogSelecionarPlano(esteFrame);
		dialogo.setVisible(true);
		
		//carrega dados na tela
		System.out.println("no retorno:"+planoRecebido);
		lblPlano.setText(planoRecebido);
	}
	
	private void selecionaProcesso() {	
		//passagem de prâmetros entre frames 
		DialogSelecionarProcesso dialogo=new DialogSelecionarProcesso(esteFrame);
		dialogo.setVisible(true);
		
		//carrega dados na tela
		txtProcesso.setText(processoRecebido.getNumCnj());
		txtSetor.setText(processoRecebido.getSetor());	
		txtExequente.setText(processoRecebido.getExequente());
		txtDataDistribuicao.setText(processoRecebido.getDataDistribuicao());
		
		System.out.println("proc:"+processoRecebido.getNumCnj() +  "  exeq:"+processoRecebido.getExequente()+  
				"  setor:"+ processoRecebido.getSetor()+ " data:"+processoRecebido.getDataDistribuicao());		
	}
	
	private void gravarCredito() {
		//popup de emissao de certidão
		 if(JOptionPane.showConfirmDialog(null,"Imprimir Certidão", null,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			 String arquivoDestino=Utilitario.escolherArquivo();
			 
			 //preenche as propriedades
			 Map<String, String> properties = new HashMap<String,String>();
			 properties.put("#001#", txtProcesso.getText());
			 properties.put("P2", txtExequente.getText());
			 properties.put("P3", lblPlano.getText());
			 properties.put("P4", txtSetor.getText());
			 properties.put("P5", txtDataDistribuicao.getText());
			 properties.put("P6", txtDataRecebimento.getText());
			 properties.put("P7", txtDataAnterioridade.getText());
			 properties.put("P8", txtValorDivida.getText());			 
			 properties.put("P9", cboPrioridade.getSelectedItem().toString());
			 //properties.put("P10", new GregorianCalendar().getTime().toString());
			 
			 //salva
			 GeradorDeDocumento gerador=new GeradorDeDocumento();
			 try {
				 gerador.preencheTemplate(arquivoDestino,properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }

		 
		//grava no banco
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


	public void recebePlanoDoDialog(String plano){
		//passagem de prâmetros entre frames
		planoRecebido=plano;
		lblPlano.setText(plano);
		System.out.println("retorno " +plano);
	}
	
	public void recebeProcessoDoDialog(Processo proc){
		//passagem de prâmetros entre frames
		processoRecebido=proc;
	}

	
	private void fechaFrame() {	
	  	this.doDefaultCloseAction();
	}

	private void validaDados() {

	}

	private void preencheCamposParaTeste(){
		txtDataRecebimento.setText("30/05/2018");
		txtDataAnterioridade.setText("18/01/2012");
		cboPrioridade.setSelectedItem("Verbas rescisórias");
		txtValorDivida.setText("69.033,65");
		txtLocalizacao.setText("Gaveta A");
		txtObservacao.setText("Pagar logo");
	
	}
}
