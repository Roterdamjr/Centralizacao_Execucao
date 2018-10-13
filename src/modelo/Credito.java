package modelo;


public class Credito {
	private int			id_plano_execucao;
	private String		dataRecebimento;
	private String		setor ;	
	private String 		processo ;
	private String		dataDistribuicao;
	private String		nomeExequente;
	private String		dataAnterioridade;
	private String		in_prioridadade;
	private String	valorDoPedido;
	private String		localizacao; 
	private String		observacao;
/*	
 * http://blog.caelum.com.br/arredondamento-no-java-do-double-ao-bigdecimal/
	sempre usar o construtor que trabalha com Strings, assim o BigDecimal vai internamente fazer o parsing 
	desses números sem que eles sejam armazenados em um double, evitando os problemas de precisão:

		// atencao! usando String no construtor:
		BigDecimal big1 = new BigDecimal("0.1");
		BigDecimal big2 = new BigDecimal("0.2");
		 
		System.out.println(big1.add(big2));
		Finalmente obtendo o resultado esperado. Há ainda importantes observações sobre o BigDecimal: por padrão ele não fará 
		nenhum tipo de arredondamento, o que o obriga a lançar java.lang.ArithmeticException no caso de uma dízima decimal 
		(tentar dividir 1/3 por exemplo). Nesses casos é necessário delimitar a quantidade de bits a serem usados ou escolher o modo de arredondamento:

		BigDecimal big1 = new BigDecimal("1");
		BigDecimal big2 = new BigDecimal("3");
		 
		System.out.println(big1.divide(big2, 3, RoundingMode.UP));
	*/
	public int getId_plano_execucao() {
		return id_plano_execucao;
	}
	public void setId_plano_execucao(int id_plano_execucao) {
		this.id_plano_execucao = id_plano_execucao;
	}
	public String getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getProcesso() {
		return processo;
	}
	public void setProcesso(String processo) {
		this.processo = processo;
	}
	public String getDataDistribuicao() {
		return dataDistribuicao;
	}
	public void setDataDistribuicao(String dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}
	public String getNomeExequente() {
		return nomeExequente;
	}
	public void setNomeExequente(String nomeExequente) {
		this.nomeExequente = nomeExequente;
	}
	public String getDataAnterioridade() {
		return dataAnterioridade;
	}
	public void setDataAnterioridade(String dataAnterioridade) {
		this.dataAnterioridade = dataAnterioridade;
	}
	public String getIn_prioridadade() {
		return in_prioridadade;
	}
	public void setIn_prioridadade(String in_prioridadade) {
		this.in_prioridadade = in_prioridadade;
	}
	public String getValorDoPedido() {
		return valorDoPedido;
	}
	public void setValorDoPedido(String valorDoPedido) {
		this.valorDoPedido = valorDoPedido;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	}
