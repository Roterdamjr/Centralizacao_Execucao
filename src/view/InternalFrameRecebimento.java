
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Credito;
import modelo.Parte;
import modelo.PlanoDeExecucao;
import modelo.Processo;
import utilitarios.TipoPrioridade;
import utilitarios.Utilitario;
import dao.CreditoDao;
import dao.PlanoExecucaoDao;
import dao.ProcessoPJe1GDao;
import dao.ProcessoSapwebDao;
import documento.GeradorDeDocumento;

public class InternalFrameRecebimento extends JInternalFrame {
	// passagem de prâmetros entre frames
	private InternalFrameRecebimento esteFrame;
	
	//
	String numCnjSelecionado;
	Processo processoSelecionado;

	private JPanel contentPane;
	private JLabel lblSetor;
	private JTextField txtSetor;
	private JLabel lblDataRecebimento;
	private JLabel lblProcesso;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JTextField txtLocalizacao;
	private JTextField txtObservacao;
	private final JTextField textField_11 = new JTextField();
	private JLabel lblObservao;
	private JPanel pnlInclusaoCredito;
	private JPanel pnlLista;
	private JComboBox<String> cboPrioridade;
	private JComboBox<PlanoDeExecucao> cboPlano;
	private JComboBox<String> cboExequente;
	private JLabel lblNewLabel_4;
	private JFormattedTextField fmtDataDistribuicao;
	private JFormattedTextField fmtDataRecebimento;
	private JFormattedTextField fmtDataAnterioridade;
	private JPanel panel;
	private JButton btnSelecionaProcesso;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel_6;
	private JScrollPane scrollPane_1;
	private JTable tabela;
	private JFormattedTextField fmtProcessoAvulso;
	private JPanel panel_2;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_15;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JButton btnSalvar;
	private JFormattedTextField fmtValorDivida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameRecebimento frame = new InternalFrameRecebimento();
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
	public InternalFrameRecebimento() {
		setClosable(true);

		textField_11.setColumns(10);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("Recebimento");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);

		pnlLista = new JPanel();
		pnlLista.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlLista, BorderLayout.WEST);
		pnlLista.setLayout(new BorderLayout(10, 5));

		panel_1 = new JPanel();
		pnlLista.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);

		tabela = new JTable();
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

		panel_4.add(tabela);

		scrollPane_1 = new JScrollPane(tabela);
		panel_4.add(scrollPane_1);

		panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_3, BorderLayout.SOUTH);

		lblNewLabel_6 = new JLabel("Processo");
		panel_3.add(lblNewLabel_6);

		fmtProcessoAvulso = new JFormattedTextField(
				Utilitario.buscaMascaraProcesso());
		fmtProcessoAvulso.setColumns(15);
		panel_3.add(fmtProcessoAvulso);

		panel = new JPanel();
		pnlLista.add(panel, BorderLayout.EAST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnSelecionaProcesso = new JButton("-->");
		btnSelecionaProcesso.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSelecionaProcesso.setForeground(Color.BLUE);
		btnSelecionaProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaDadosNoBancoSegundoPlano().execute();
			}
		});
		panel.add(btnSelecionaProcesso);

		pnlInclusaoCredito = new JPanel();
		pnlInclusaoCredito.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlInclusaoCredito, BorderLayout.CENTER);
		pnlInclusaoCredito.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_10 = new JPanel();
		pnlInclusaoCredito.add(panel_10);

		lblNewLabel_8 = new JLabel("Processo");
		panel_10.add(lblNewLabel_8);

		lblProcesso = new JLabel("  ");
		lblProcesso.setForeground(Color.RED);
		panel_10.add(lblProcesso);
		lblProcesso.setHorizontalAlignment(SwingConstants.RIGHT);

		panel_15 = new JPanel();
		pnlInclusaoCredito.add(panel_15);

		lblNewLabel_7 = new JLabel("Plano");
		panel_15.add(lblNewLabel_7);

		cboPlano = new JComboBox<PlanoDeExecucao>();
		panel_15.add(cboPlano);

		panel_12 = new JPanel();
		pnlInclusaoCredito.add(panel_12);

		lblNewLabel = new JLabel("Exequente");
		panel_12.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		cboExequente = new JComboBox<String>();
		panel_12.add(cboExequente);

		panel_11 = new JPanel();
		pnlInclusaoCredito.add(panel_11);

		lblSetor = new JLabel("Setor");
		panel_11.add(lblSetor);
		lblSetor.setHorizontalAlignment(SwingConstants.RIGHT);

		txtSetor = new JTextField();
		panel_11.add(txtSetor);
		txtSetor.setColumns(7);

		panel_2 = new JPanel();
		pnlInclusaoCredito.add(panel_2);

		lblDataRecebimento = new JLabel("Data Recebimento");
		panel_2.add(lblDataRecebimento);
		lblDataRecebimento.setHorizontalAlignment(SwingConstants.RIGHT);

		fmtDataRecebimento = new JFormattedTextField(
				Utilitario.buscaMascaraData());
		panel_2.add(fmtDataRecebimento);
		fmtDataRecebimento.setColumns(7);

		panel_13 = new JPanel();
		pnlInclusaoCredito.add(panel_13);

		lblNewLabel_4 = new JLabel("Data Distribui\u00E7\u00E3o");
		panel_13.add(lblNewLabel_4);

		fmtDataDistribuicao = new JFormattedTextField(
				Utilitario.buscaMascaraData());
		panel_13.add(fmtDataDistribuicao);
		fmtDataDistribuicao.setColumns(7);

		panel_5 = new JPanel();
		pnlInclusaoCredito.add(panel_5);

		lblNewLabel_1 = new JLabel("Anterioridade");
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);

		fmtDataAnterioridade = new JFormattedTextField(
				Utilitario.buscaMascaraData());
		panel_5.add(fmtDataAnterioridade);
		fmtDataAnterioridade.setColumns(7);

		panel_6 = new JPanel();
		pnlInclusaoCredito.add(panel_6);

		lblNewLabel_2 = new JLabel("Prioridade");
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);

		cboPrioridade = new JComboBox();
		panel_6.add(cboPrioridade);

		panel_7 = new JPanel();
		pnlInclusaoCredito.add(panel_7);

		lblNewLabel_3 = new JLabel("Valor da D\u00EDvida");
		panel_7.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		fmtValorDivida = new JFormattedTextField(new JFormattedTextField(Utilitario.buscaMascaraValor()));
		fmtValorDivida.setText("");
		panel_7.add(fmtValorDivida);
		fmtValorDivida.setColumns(10);

		panel_8 = new JPanel();
		pnlInclusaoCredito.add(panel_8);

		lblNewLabel_5 = new JLabel("Localiza\u00E7\u00E3o");
		panel_8.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);

		txtLocalizacao = new JTextField();
		panel_8.add(txtLocalizacao);
		txtLocalizacao.setColumns(20);

		panel_9 = new JPanel();
		pnlInclusaoCredito.add(panel_9);

		lblObservao = new JLabel("Observa\u00E7\u00E3o");
		panel_9.add(lblObservao);
		lblObservao.setHorizontalAlignment(SwingConstants.RIGHT);

		txtObservacao = new JTextField();
		panel_9.add(txtObservacao);
		txtObservacao.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarDadosNoBanco();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalvar.setForeground(Color.BLUE);
		pnlInclusaoCredito.add(btnSalvar);

		/*
		 * ======================================================= C´O D I G O P
		 * E R S O N A L I Z A D O
		 * ======================================================
		 */

		setSize(1125, 726);// larguraXaltura
		esteFrame = this;
		populaComboPrioridade();
		populaComboPlano();
		populaListaDeProcessos();
		limpaPainelDeProcesso();
		// preencheCamposParaTeste();
	}

	private void populaListaDeProcessos() {

		ResultSet rs = null;
		try {
			rs = new ProcessoSapwebDao().buscaRemessas();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] row = new Object[columns];
				for (int i = 1; i <= columns; i++) {
					row[i - 1] = rs.getObject(i);
					// System.out.println("inserindo "+ row.toString());
				}
				((DefaultTableModel) tabela.getModel()).insertRow(
						rs.getRow() - 1, row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class ConsultaDadosNoBancoSegundoPlano extends SwingWorker {

		protected Object doInBackground() throws Exception {
			limpaPainelDeProcesso();
			habilitaDesabilitaComponentes(false);
			numCnjSelecionado = (String) tabela.getValueAt(
					tabela.getSelectedRow(), 0);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
			processoSelecionado = consultaDadosNoBanco(numCnjSelecionado);
			return null;
		}

		@Override
		public void done() {
			habilitaDesabilitaComponentes(true);
			preencheTelaComDadosDoBanco();
		}
	}

	private void preencheTelaComDadosDoBanco() {

		lblProcesso.setText(numCnjSelecionado);		
		txtSetor.setText(processoSelecionado.getSiglaSetor());

		for (Parte pte : processoSelecionado.getPartes()) {
			cboExequente.addItem(pte.getNome());
		}
		cboExequente.setSelectedItem(-1);
		
		fmtDataDistribuicao.setText(processoSelecionado.getDataDistribuicao());
		// txtSistema.setText(processo.getSistemaOrigem());

	}

	private void limpaPainelDeProcesso() {
		cboPlano.setSelectedIndex(-1);
		cboExequente.removeAllItems();
		txtSetor.setText("");
		fmtDataRecebimento.setText("");
		fmtDataDistribuicao.setText("");
		fmtDataAnterioridade.setText("");
		cboPrioridade.setSelectedIndex(-1);
		txtLocalizacao.setText("");
		txtObservacao.setText("");
		
		preencheCamposParaTeste();
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

	private Processo consultaDadosNoBanco(String numCnj) {
		Processo processo = null;
		try {
			// busca dados no banco sapweb
			processo = new ProcessoSapwebDao().buscaDados(numCnj);
			// se não achou busca no PJe1G
			if (processo == null) {
				processo = new ProcessoPJe1GDao().buscaDados(numCnj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processo;
	}

	private void salvarDadosNoBanco() {
		if (isDadosValidos()) {
			// grava no banco
			Credito credito = new Credito();
			credito.setDataAnterioridade(fmtDataAnterioridade.getText());
			credito.setDataDistribuicao(fmtDataDistribuicao.getText());
			credito.setDataRecebimento(fmtDataRecebimento.getText());
			PlanoDeExecucao pl= (PlanoDeExecucao)cboPlano.getSelectedItem();
			System.out.println(pl.getIdPlano());
			credito.setId_plano_execucao(pl.getIdPlano());
			String in_prioridadade = TipoPrioridade.retornaChave((String) cboPrioridade.getSelectedItem());
			credito.setIn_prioridadade(in_prioridadade);

			credito.setLocalizacao(txtLocalizacao.getText());
			credito.setNomeExequente(cboExequente.getSelectedItem().toString());
			credito.setObservacao(txtObservacao.getText());
			credito.setProcesso(lblProcesso.getText());
			credito.setSetor(txtSetor.getText());
			credito.setValorDoPedido(fmtValorDivida.getText());

			new CreditoDao().insereRegistro(credito);
		}
	}

	private boolean isDadosValidos() {
		
		//System.out.println(pl.getIdPlano());
		
		if ((PlanoDeExecucao)cboPlano.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null,
					"Plano de execução não selecionado!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cboPrioridade.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null,
					"Prioridade não selecionado!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cboExequente.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Exequente não selecionado!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtSetor.getText() == null || txtSetor.getText().isEmpty()) {
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

		if (!Utilitario.isDataValida(fmtDataRecebimento.getText())) {
			JOptionPane.showMessageDialog(null,
					"Data do recebimento inválida!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!Utilitario.isDataValida(fmtDataAnterioridade.getText())) {
			JOptionPane.showMessageDialog(null,
					"Data da Anterioridadade inválida!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!Utilitario.isNumeroValido(fmtValorDivida.getText())) {
			JOptionPane.showMessageDialog(null, "Valor da dívida inválido!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

/*	public void recebePlanoDoDialog(PlanoDeExecucao plano) {
		// passagem de prâmetros entre frames
		planoDeExecuoao = plano;
	}*/

	private void habilitaDesabilitaComponentes(boolean acao) {
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
	}

	private void preencheCamposParaTeste() {
		fmtDataRecebimento.setText("30/05/2018");
		fmtDataAnterioridade.setText("18/01/2012");
		cboPrioridade.setSelectedItem("Verbas rescisórias");
		//fmtValorDivida.setText("069.033,65");
		txtLocalizacao.setText("Gaveta A");
		txtObservacao.setText("Pagar logo");
		cboPlano.setSelectedIndex(0);

	}
}
