package modelo;

public class HistoricoSituacaoCredito {
	private String indicadorSituacao;
	private String dataSituacao;
	private int idUsuario;
	private int idCredito;
	
	
	public int getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(int idCredito) {
		this.idCredito = idCredito;
	}
	public String getIndicadorSituacao() {
		return indicadorSituacao;
	}
	public void setIndicadorSituacao(String indicadorSituacao) {
		this.indicadorSituacao = indicadorSituacao;
	}
	public String getDataSituacao() {
		return dataSituacao;
	}
	public void setDataSituacao(String dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
