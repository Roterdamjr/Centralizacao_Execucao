package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Credito;
import modelo.HistoricoSituacaoCredito;
import modelo.Parte;
import modelo.PlanoDeExecucao;
import modelo.Processo;
import modelo.Usuario;
import service.RecebidoService;
import utilitarios.EscopoGlobal;
import utilitarios.JMoneyField;
import utilitarios.TipoPrioridade;
import utilitarios.Utilitario;
import dao.CreditoDao;
import dao.PlanoExecucaoDao;
import dao.ProcessoPJe1GDao;
import dao.ProcessoSapwebDao;
import documento.GeradorDeDocumento;
import javax.swing.border.BevelBorder;

public class InternalFrameRecebimento extends JInternalFrame {
	// passagem de prâmetros entre frames
	
	private InternalFrameRecebimento esteFrame;
	//
	String numCnjSelecionado;
	Processo processoSelecionado;
	
	
	private JPanel contentPane;
	private JTextField txtSetor;
	private JTextField txtLocalizacao;
	private JTextField txtObservacao;
	private JTable tabela;
	private ButtonGroup grupo;
	private JRadioButton rdbLista;	
	private JFormattedTextField fmtDataDistribuicao;
	private JFormattedTextField fmtDataRecebimento;
	private JFormattedTextField fmtDataAnterioridade;
	private JFormattedTextField fmtProcessoAvulso;
	private JComboBox<String> cboPrioridade;
	private JComboBox<PlanoDeExecucao> cboPlano;
	private JComboBox<String> cboExequente;
	private JLabel lblProcesso;
	private JMoneyField fmtValorDivida;
	private JButton btnSalvar;	
	
	
	/**
	 * Create the frame.
	 */
	public InternalFrameRecebimento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 5));
		setContentPane(contentPane);
		
		JPanel panel_0 = new JPanel();
		contentPane.add(panel_0, BorderLayout.CENTER);
		panel_0.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_0.add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSelecao = new JPanel();
		pnlSelecao.setBorder(null);
		pnlPrincipal.add(pnlSelecao, BorderLayout.WEST);
		pnlSelecao.setLayout(new BorderLayout(10, 5));
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlSelecao.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlLista = new JPanel();
		pnlLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_16.add(pnlLista, BorderLayout.NORTH);
		pnlLista.setLayout(new BorderLayout(10, 10));
		
		rdbLista = new JRadioButton("Lista");
		rdbLista.setSelected(true);
		pnlLista.add(rdbLista, BorderLayout.NORTH);

		
		tabela = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		pnlLista.add(scrollPane, BorderLayout.SOUTH);

		JPanel pnlProcesso = new JPanel();
		pnlProcesso.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_16.add(pnlProcesso, BorderLayout.SOUTH);
		pnlProcesso.setLayout(new BorderLayout(10, 10));
		
		JRadioButton rdbProcesso = new JRadioButton("Avulso");
		pnlProcesso.add(rdbProcesso, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlProcesso.add(panel, BorderLayout.SOUTH);
		
		JLabel label_12 = new JLabel("Processo");
		panel.add(label_12);
		
		fmtProcessoAvulso = new JFormattedTextField(Utilitario.buscaMascaraProcesso());
		fmtProcessoAvulso.setEnabled(false);
		fmtProcessoAvulso.setColumns(15);
		panel.add(fmtProcessoAvulso);
		
		JPanel panel_17 = new JPanel();
		pnlSelecao.add(panel_17, BorderLayout.EAST);
		panel_17.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSelecionaProcesso = new JButton("-->");
		btnSelecionaProcesso.setForeground(Color.BLUE);
		btnSelecionaProcesso.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_17.add(btnSelecionaProcesso);
		
		JPanel pnlInclusaoCredito = new JPanel();
		pnlInclusaoCredito.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlPrincipal.add(pnlInclusaoCredito);
		pnlInclusaoCredito.setLayout(new GridLayout(12, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_4);
		
		JLabel label = new JLabel("Processo");
		panel_4.add(label);
		
		lblProcesso = new JLabel("");
		lblProcesso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcesso.setForeground(Color.RED);
		panel_4.add(lblProcesso);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_5);
		
		JLabel lblPlano = new JLabel("Plano *");
		panel_5.add(lblPlano);
		
		cboPlano = new JComboBox<PlanoDeExecucao>();
		cboPlano.setSelectedIndex(-1);
		panel_5.add(cboPlano);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_6);
		
		JLabel lblDataRecebimento = new JLabel("Data Recebimento *");
		panel_6.add(lblDataRecebimento);
		lblDataRecebimento.setHorizontalAlignment(SwingConstants.RIGHT);
		
		fmtDataRecebimento = new JFormattedTextField(Utilitario.buscaMascaraData());
		panel_6.add(fmtDataRecebimento);
		fmtDataRecebimento.setText("");
		fmtDataRecebimento.setColumns(7);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_7.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_7);
		
		JLabel label_3 = new JLabel("Exequente");
		panel_7.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cboExequente = new JComboBox<String>();
		panel_7.add(cboExequente);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_8.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_8);
		
		JLabel label_4 = new JLabel("Setor");
		panel_8.add(label_4);
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtSetor = new JTextField();
		panel_8.add(txtSetor);
		txtSetor.setText("");
		txtSetor.setColumns(7);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_9.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_9);
		
		JLabel label_6 = new JLabel("Data Distribui\u00E7\u00E3o");
		panel_9.add(label_6);
		
		fmtDataDistribuicao = new JFormattedTextField(Utilitario.buscaMascaraData());
		fmtDataDistribuicao.setText("");
		fmtDataDistribuicao.setColumns(7);
		panel_9.add(fmtDataDistribuicao);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_10.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_10);
		
		JLabel label_7 = new JLabel("Anterioridade");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_10.add(label_7);
		
		fmtDataAnterioridade = new JFormattedTextField((AbstractFormatter) null);
		fmtDataAnterioridade.setText("");
		fmtDataAnterioridade.setColumns(7);
		panel_10.add(fmtDataAnterioridade);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_11.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_11);
		
		JLabel label_8 = new JLabel("Prioridade");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(label_8);
		
		cboPrioridade = new JComboBox();
		cboPrioridade.setSelectedIndex(-1);
		panel_11.add(cboPrioridade);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_12.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_12);
		
		JLabel label_9 = new JLabel("Valor da D\u00EDvida");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_12.add(label_9);
		
		fmtValorDivida = new JMoneyField();
		fmtValorDivida.setText("");
		fmtValorDivida.setColumns(10);
		panel_12.add(fmtValorDivida);
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_13.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_13);
		
		JLabel label_10 = new JLabel("Localiza\u00E7\u00E3o");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_13.add(label_10);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setText("");
		txtLocalizacao.setColumns(20);
		panel_13.add(txtLocalizacao);
		
		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_14.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		pnlInclusaoCredito.add(panel_14);
		
		JLabel label_11 = new JLabel("Observa\u00E7\u00E3o");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_14.add(label_11);
		
		txtObservacao = new JTextField();
		txtObservacao.setText("");
		txtObservacao.setColumns(10);
		panel_14.add(txtObservacao);
		
		JPanel panel_15 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_15.getLayout();
		flowLayout_13.setAlignment(FlowLayout.RIGHT);
		pnlInclusaoCredito.add(panel_15);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_15.add(btnSalvar);
		
		JPanel panel_1 = new JPanel();
		panel_0.add(panel_1, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSair.setForeground(Color.BLUE);
		panel_1.add(btnSair);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Aviso");
		panel_2.add(lblNewLabel);

		/*
		 * ======================================================= 
		 * C´O D I G O 	P E R S O N A L I Z A D O
		 * ======================================================
		 */
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] { { null }, }, new String[] { "Processo" })
		// impede que jtable seja editável
		{
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tabela.setModel(modelo);
		
		btnSelecionaProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaDadosProcessoNoBancoSegundoPlano().execute();
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SalvaDadosNoBancoSegundoPlano().execute();
			}
		});
		
		
		/*@TESTE*/
		Usuario u =new Usuario();
		u.setNome("roterdam.junior");
		u.setId(1);
		EscopoGlobal.setUsuarioLogado(u);
		/*@FIM TESTE*/
		setTitle(getTitle() + " - " + EscopoGlobal.getUsuarioLogado().getNome());	
		
		grupo = new ButtonGroup();
		grupo.add(rdbLista);
		grupo.add(rdbProcesso);

		rdbLista.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (rdbLista.isSelected()) {
					tabela.setEnabled(true);
					fmtProcessoAvulso.setEnabled(false);
					System.out.println("Parabéns, você é o cara");
				} else {
					tabela.setEnabled(false);
					fmtProcessoAvulso.setEnabled(true);
				}
			}
		});
		rdbLista.setSelected(true);

		setSize(1125, 726);// larguraXaltura
		esteFrame = this;
		populaComboPrioridade();
		populaComboPlano();
		fmtProcessoAvulso.setEnabled(false);
		
		populaListaDeProcessos();
		limpaPainelDeProcesso();
	}
	
	
	private void populaComboPlano() {
		ArrayList<PlanoDeExecucao> lista = new PlanoExecucaoDao().buscaTodos();

		for (PlanoDeExecucao obj : lista) {
			cboPlano.addItem(obj);
		}
	}

	private void populaComboPrioridade() {
		for (TipoPrioridade prioridade : TipoPrioridade.values()) {
			cboPrioridade.addItem((String) prioridade.getDescricao());
		}
	}

	private void populaListaDeProcessos() {

		ArrayList<Processo> lista=new RecebidoService().buscaRemetidosENaorecebidos();
		DefaultTableModel modelo=(DefaultTableModel) tabela.getModel();
		
		//limpa tabela
		modelo.setRowCount(0);;
		
		int i=0;
		for(Processo processo :lista){
			Object[] row = new Object[1];
			row[0]=processo.getNumCnj();
			modelo.insertRow(i,row);
			i++;
		}				
	}

	private class ConsultaDadosProcessoNoBancoSegundoPlano extends SwingWorker {

		protected Object doInBackground() throws Exception {
			limpaPainelDeProcesso();
			habilitaComponentes(false);

			if (rdbLista.isSelected()) {
				numCnjSelecionado = (String) tabela.getValueAt(
						tabela.getSelectedRow(), 0);
			} else {
				numCnjSelecionado = fmtProcessoAvulso.getText();
			}

			/*
			 * try {Thread.sleep(500); } catch (InterruptedException ex) { }
			 */

			processoSelecionado = new RecebidoService().consultaDadosProcessoNoBanco(numCnjSelecionado);
			populaListaDeProcessos();
			return null;
		}

		@Override
		public void done() {
			habilitaComponentes(true);
			populaPainelComDadosDeProcesso();
			/* @TESTE */
			preencheCamposParaTeste();
		}
	}



	private void populaPainelComDadosDeProcesso() {

		lblProcesso.setText(numCnjSelecionado);
		txtSetor.setText(processoSelecionado.getSiglaSetor());

		for (Parte pte : processoSelecionado.getPartes()) {
			cboExequente.addItem(pte.getNome());
		}
		cboExequente.setSelectedItem(-1);

		fmtDataDistribuicao.setText(processoSelecionado.getDataDistribuicao());
		// txtSistema.setText(processo.getSistemaOrigem());

	}

	private class SalvaDadosNoBancoSegundoPlano extends SwingWorker {
		private boolean GravacaoComSucesso = false;

		protected Object doInBackground() throws Exception {
			habilitaComponentes(false);

			if (isDadosValidos()) {
				GravacaoComSucesso = salvarDadosNoBanco();
			}
			return null;
		}

		@Override
		public void done() {
			if (GravacaoComSucesso) {
				limpaPainelDeProcesso();
			}
			
			habilitaComponentes(true);
			fmtProcessoAvulso.setText("");
			//recarrega lista
			populaListaDeProcessos();
		}
	}

	private boolean salvarDadosNoBanco() {
		/* @TESTE */
		try {
			Thread.sleep(200);
		} catch (InterruptedException ex) {
		}

		boolean retorno = false;

		Credito credito = new Credito();

		credito.setDataAnterioridade(fmtDataAnterioridade.getText());
		credito.setDataDistribuicao(fmtDataDistribuicao.getText());
		credito.setDataRecebimento(fmtDataRecebimento.getText());
		PlanoDeExecucao pl = (PlanoDeExecucao) cboPlano.getSelectedItem();
		System.out.println(pl.getIdPlano());
		credito.setId_plano_execucao(pl.getIdPlano());
		String in_prioridadade = TipoPrioridade
				.retornaChave((String) cboPrioridade.getSelectedItem());
		credito.setIn_prioridadade(in_prioridadade);
		credito.setLocalizacao(txtLocalizacao.getText());
		credito.setNomeExequente(cboExequente.getSelectedItem().toString());
		credito.setObservacao(txtObservacao.getText());
		credito.setNumCnj(lblProcesso.getText());
		credito.setSetor(txtSetor.getText());
		credito.setValorDoPedido(fmtValorDivida.getText());
		credito.setIndicadorSituacao("RB");

		HistoricoSituacaoCredito historico = new HistoricoSituacaoCredito();
		historico.setDataSituacao(Utilitario.buscaDataAtual());
		historico.setIdUsuario(EscopoGlobal.getUsuarioLogado().getId());	
		historico.setIndicadorSituacao("RB");
		//historico.setIdCredito(idCredito);
		
		try {
			new CreditoDao().insereCredito(credito,historico);
			
			JOptionPane.showMessageDialog(null, "Dados gravados!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			retorno = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar registro!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return retorno;
	}

	private boolean isDadosValidos() {

		if ((PlanoDeExecucao) cboPlano.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null,
					"Plano de execução não selecionado!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!Utilitario.isDataValida(fmtDataRecebimento.getText())) {
			JOptionPane.showMessageDialog(null,
					"Data do recebimento inválida!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		/*if (cboPrioridade.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Prioridade não selecionado!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cboExequente.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Exequente não selecionado!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}*/

		/*if (txtSetor.getText() == null || txtSetor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Setor não preenchido!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!Utilitario.isDataValida(fmtDataDistribuicao.getText())) {
			JOptionPane.showMessageDialog(null,
					"Data da Distribuição inválida!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}



		if (!Utilitario.isDataValida(fmtDataAnterioridade.getText())) {
			System.out.println("foramto --->" + fmtDataAnterioridade.getText());
			JOptionPane.showMessageDialog(null,
					"Data da Anterioridadade inválida!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!Utilitario.isNumeroValido(fmtValorDivida.getText())) {
			System.out.println("valida" + fmtValorDivida.getText());
			JOptionPane.showMessageDialog(null, "Valor da dívida inválido!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}*/

		return true;
	}

	private void limpaPainelDeProcesso() {
		lblProcesso.setText("");
		cboPlano.setSelectedIndex(-1);
		cboExequente.removeAllItems();
		txtSetor.setText("");
		fmtDataRecebimento.setText("");
		fmtDataDistribuicao.setText("");
		fmtDataAnterioridade.setText("");
		fmtValorDivida.setText("");
		cboPrioridade.setSelectedIndex(-1);
		txtLocalizacao.setText("");
		txtObservacao.setText("");

	}

	private void habilitaComponentes(boolean acao) {
		tabela.setEnabled(acao);
		cboPlano.setEnabled(acao);
		cboExequente.setEnabled(acao);
		txtSetor.setEnabled(acao);
		fmtDataRecebimento.setEnabled(acao);
		fmtDataDistribuicao.setEnabled(acao);
		fmtDataAnterioridade.setEnabled(acao);
		cboPrioridade.setEnabled(acao);
		fmtValorDivida.setEnabled(acao);
		txtLocalizacao.setEnabled(acao);
		txtObservacao.setEnabled(acao);
		btnSalvar.setEnabled(acao);
	}

	private void facultarEmissaoCertidao() {
		// popup de emissao de certidão
		if (JOptionPane.showConfirmDialog(null, "Imprimir Certidão", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String arquivoDestino = Utilitario.escolherArquivo();

			// preenche as propriedades
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("#001#", lblProcesso.getText());
			properties.put("P2", cboExequente.getSelectedItem().toString());
			properties.put("P3", cboPlano.getSelectedItem().toString());
			properties.put("P4", txtSetor.getText());
			properties.put("P5", fmtDataDistribuicao.getText());
			properties.put("P6", fmtDataRecebimento.getText());
			properties.put("P7", fmtDataAnterioridade.getText());
			properties.put("P8", fmtValorDivida.getText());
			properties.put("P9", cboPrioridade.getSelectedItem().toString());
			// properties.put("P10", new
			// GregorianCalendar().getTime().toString());

			// salva
			GeradorDeDocumento gerador = new GeradorDeDocumento();
			try {
				gerador.preencheTemplate(arquivoDestino, properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	
	
	private void preencheCamposParaTeste() {
		fmtDataRecebimento.setText("30/05/2018");
		fmtDataAnterioridade.setText("18/01/2012");
		cboPrioridade.setSelectedItem("Verbas rescisórias");
		// fmtValorDivida.setText("069.033,65");
		txtLocalizacao.setText("Gaveta A");
		txtObservacao.setText("Pagar logo");
		cboPlano.setSelectedIndex(0);

	}

}
