package teste;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import utilitarios.Utilitario;
import documento.GeradorDeDocumento;

public class TestaClasse {
	public static void main(String[] args) {
		//new TestaClasse().executa();


		System.out.println(Utilitario.converteStringParaBigDecimal("4.555.555,55"));
		System.out.println(Utilitario.converteStringParaBigDecimal("55,55"));
		System.out.println(Utilitario.converteStringParaBigDecimal("0,55"));
		System.out.println(Utilitario.converteStringParaBigDecimal("555,00"));
		
	}
	
	public void executa(){
		
		
		try {
			new SAEDao().insereCredito();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
/*	public boolean validaNumero(String textoDoNumero){
		
		boolean isValido=true;
		String textoFormatado= textoDoNumero.trim().replace(".","");
		textoFormatado= textoFormatado.replace(",",".");
		
		BigDecimal valor=null;
		try{
			valor =new BigDecimal(textoFormatado);
			
		}catch(Exception e){
			isValido=false;
		}
		
		return isValido;
	}*/
	

	
/*	private void banco() {
		List lista = new PlanoExecucaoDao().buscaTodos();
		
	}*/
	
	
	
	public void geraDocumento(){
		 Map<String, String> properties = new HashMap<String,String>();
		 properties.put("P1", "0162000-57.2009.5.01.0040");
		 properties.put("P2", "Wilson Maria dos Santos Filho");

		 GeradorDeDocumento cd=new GeradorDeDocumento();
		 try {
			cd.preencheTemplate( "c:/temp/Certidao_saida.docx",properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void geraData(){
		System.out.print(Utilitario.dataPorExtenso());
	}
	
}
