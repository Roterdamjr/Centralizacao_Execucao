package modelo;

import java.util.Calendar;
import java.util.Date;

public class Credito {
	private String		setor ;
	private Date		dataRecebimento;
	private String 		processo ;
	private String		exequente;
	private Calendar	anterioridade;
	private String		in_classificacao;
	private String 		alvPgParcial; 
	private double		valorDoPedido;
	private double		valorPago;
	private String		localizacao; 
	private String		observacao;
	
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public Date getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	public String getProcesso() {
		return processo;
	}
	public void setProcesso(String processo) {
		this.processo = processo;
	}
	public String getExequente() {
		return exequente;
	}
	public void setExequente(String exequente) {
		this.exequente = exequente;
	}
	public Calendar getAnterioridade() {
		return anterioridade;
	}
	public void setAnterioridade(Calendar anterioridade) {
		this.anterioridade = anterioridade;
	}
	public String getIn_classificacao() {
		return in_classificacao;
	}
	public void setIn_classificacao(String in_classificacao) {
		this.in_classificacao = in_classificacao;
	}
	public String getAlvPgParcial() {
		return alvPgParcial;
	}
	public void setAlvPgParcial(String alvPgParcial) {
		this.alvPgParcial = alvPgParcial;
	}
	public double getValorDoPedido() {
		return valorDoPedido;
	}
	public void setValorDoPedido(double valorDoPedido) {
		this.valorDoPedido = valorDoPedido;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
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
