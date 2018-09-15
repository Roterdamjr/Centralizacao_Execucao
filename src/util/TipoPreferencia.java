package util;

import java.lang.Thread.State;

public enum TipoPreferencia {
	
	A80 ("Acima 80 anos"),
	QDG ("Quadro Geral"),
	QDU ("Quadro Geral União"),
	IDS ("Idoso"),
	VBR ("Verbas rescisórias"),
	DGR ("Doença Grave"),
	S60 ("Até 60 S.M");
	
	private String tipoPreferenciaExtenso;

	private TipoPreferencia(String tipoPreferenciaExtenso){
		 this.tipoPreferenciaExtenso=tipoPreferenciaExtenso;
	 }
	
	 public String getTipoPreferenciaExtenso(){
		 return this.tipoPreferenciaExtenso;
	 }
	 
	 public String toString() {
		 return tipoPreferenciaExtenso;
	 }
	 
	 public static String[] valores(){
		String[]  values = new String[7]; //--> tamanho da enumeração
		int i=0;
		for(TipoPreferencia op : TipoPreferencia.values()){
			values[i]=op.getTipoPreferenciaExtenso();
			i++;
		}
		return values;
	 }
}
