package utilitarios;

public enum MesDoAno {
	JANEIRO 	("Janeiro"),
	FEVEREIRO	("Fevereiro"),
	MARÇO		("Março"),
	ABRIL		("Abril"),
	MAIO		("Maio"),
	JUNHO		("Junho"),
	JULHO		("Julho"),
	AGOSTO		("Agosto"),
	SETEMBRO	("setembro"),
	OUTUBRO		("Outubro"),
	NOVEMBRO	("Novembro"),
	DEZEMBRO	("Dezembro");
	
	
	 private String mesExtenso;
	 
	 private MesDoAno(String mesExtenso){
		 this.mesExtenso=mesExtenso;
	 }
	 
	 public String getMesExtenso(){
		 return this.mesExtenso;
	 }
	 
	 public String toString() {
		 return mesExtenso;
	 }
	 
}
