package modelo;

public class Setor {
	private String siglaSetor;
	/*
	 * VT16RJ,VT1BP,VT1MAC
	 */
	

	public String getParteNumerica(){
		/*
		Exemplo: sigla=VT16RJ, retorna 16
		*/
		
		String texto=siglaSetor.replace("VT","");
		String retorno="";

		for (int i=0;i<texto.length();i++){
			String caracter=texto.substring(i,i+1);

			if (isNumerico(caracter)) {
				retorno+= caracter;
			}
		}
		return retorno;
	}
	
	public String getParteNaoNumerica(){
		/*
		Exemplo: sigla=VT16RJ, retorna RJ
		*/
		String texto=siglaSetor.replace("VT","");
		String retorno="";

		for (int i=0;i<texto.length();i++){
			String caracter=texto.substring(i,i+1);

			if (!isNumerico(caracter)) {
				retorno+= caracter;
			}
		}
		return retorno;
	}
	
	private boolean isNumerico(String s) {
	    return java.util.regex.Pattern.matches("\\d+", s);
	}
	
	public String getSiglaSetor() {
		return siglaSetor;
	}

	public void setSiglaSetor(String siglaSetor) {
		this.siglaSetor = siglaSetor;
	}
	
	
}
