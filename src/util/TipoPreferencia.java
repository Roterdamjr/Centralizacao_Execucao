package util;

public enum TipoPreferencia {
	
	A80 ("Acima 80 anos"),
	QDG ("Quadro Geral"),
	QDU ("Quadro Geral União"),
	IDS ("Idoso"),
	VBR ("Verbas rescisórias"),
	DGR ("Doença Grave"),
	S60 ("Até 60 S.M");
	
	private String tipoPreferenciaExtenso;
	//contrutor privado obrigatório
	private TipoPreferencia(String tipoPreferenciaExtenso){
		 this.tipoPreferenciaExtenso=tipoPreferenciaExtenso;
	 }
	
	 public String getTipoPreferenciaExtenso(){
		 return this.tipoPreferenciaExtenso;
	 }
	 
	 public static TipoPreferencia getNomeConstante(String descricao){
		 TipoPreferencia ret=null;

		 for(TipoPreferencia obj:TipoPreferencia.values()){
			 if(descricao.equals(obj.getTipoPreferenciaExtenso()))
				 ret=obj;		
		 }
		 return ret;
	 }
	 
/*	 public static String[] valores(){
		String[]  values = new String[7]; //--> tamanho da enumeração
		int i=0;
		for(TipoPreferencia op : TipoPreferencia.values()){
			values[i]=op.getTipoPreferenciaExtenso();
			i++;
		}
		return values;
	 }*/
}
