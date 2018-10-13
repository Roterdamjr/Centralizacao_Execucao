package utilitarios;




public enum TipoPrioridade {
	
	A80 ("Acima 80 anos"),
	QDG ("Quadro Geral"),
	QDU ("Quadro Geral União"),
	IDS ("Idoso"),
	VBR ("Verbas rescisórias"),
	DGR ("Doença Grave"),
	S60 ("Até 60 S.M");
	
	private String descricao;

	private TipoPrioridade(String descricao){
		 this.descricao=descricao;
	 }
	
	 public String getDescricao(){
		 return this.descricao;
	 }
	 
	 public String toString() {
		 return descricao;
	 }
	 
	 public static String retornaChave(String descricao){
		 
		 String retorno=null;
		 switch( descricao )
		 {
		 case "Acima 80 anos":
			 retorno= "ARQ";
			 break;       

		 case "Quadro Geral":
			 retorno= "QDG";
			 break;

		 case "Quadro Geral União":
			 retorno= "QDU";
			 break;

		 case "Idoso":
			 retorno= "IDS";
			 break;

		 case "Verbas rescisórias":
			 retorno= "VBR";
			 break;
		 case "Doença Grave":
			 retorno= "DGR";
			 break;			 
		 case "Até 60 S.M":
			 retorno= "S60";
			 break;			 
		 default:
			 retorno= null;      
		 }
		 return retorno;

		 		 
	 }
	 
	 public static String[] valores(){
		String[]  values = new String[7]; //--> tamanho da enumeração
		int i=0;
		for(TipoPrioridade op : TipoPrioridade.values()){
			values[i]=op.getDescricao();
			i++;
		}
		return values;
	 }
}
