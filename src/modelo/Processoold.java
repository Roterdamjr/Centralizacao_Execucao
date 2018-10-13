package modelo;

import java.util.ArrayList;

public class Processoold {
	private String numCnj;
	private String setor;
	private String  exequente;
	private String dataDistribuicao;
	private ArrayList<String> partes;
	private String siglaSetor;
	private String sistemaOrigem;
	
	public String getSistemaOrigem() {
		return sistemaOrigem;
	}
	public void setSistemaOrigem(String sistemaOrigem) {
		this.sistemaOrigem = sistemaOrigem;
	}
	public String getNumCnj() {
		return numCnj;
	}
	public void setNumCnj(String numCnj) {
		this.numCnj = numCnj;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getExequente() {
		return exequente;
	}
	public void setExequente(String exequente) {
		this.exequente = exequente;
	}
	public String getDataDistribuicao() {
		return dataDistribuicao;
	}
	public void setDataDistribuicao(String dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}
	public ArrayList<String> getPartes() {
		return partes;
	}
	public void setPartes(ArrayList<String> partes) {
		this.partes = partes;
	}
	public String getSiglaSetor() {
		return siglaSetor;
	}
	public void setSiglaSetor(String siglaSetor) {
		this.siglaSetor = siglaSetor;
	}
	
	
}
