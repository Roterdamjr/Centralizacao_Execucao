package modelo;

public class PlanoDeExecucao {
	
	private int idPlano;
	private String nomeEmpresa;
	private String cnpj;
	private String depositoMensal;
	private String contaCorrente;
	private String dataDeferimento;
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDepositoMensal() {
		return depositoMensal;
	}
	public void setDepositoMensal(String depositoMensal) {
		this.depositoMensal = depositoMensal;
	}
	public String getContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public String getDataDeferimento() {
		return dataDeferimento;
	}
	public void setDataDeferimento(String dataDeferimento) {
		this.dataDeferimento = dataDeferimento;
	}
	public int getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(int idPlano) {
		this.idPlano = idPlano;
	}
	
	
}
