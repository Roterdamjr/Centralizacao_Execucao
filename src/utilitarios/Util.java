package utilitarios;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

public class Util {
	
	public static void bloquearLiberarCampos(JPanel panel, boolean acao){
	    Component[] componentes = panel.getComponents(); // altere para o nome da variavel do seu painel
	    
	    for (Component componente : componentes) {
    		componente.setEnabled(acao);
	    }
	}
	
	public static void bloquearLiberarCampos2Niveis(JPanel panel, boolean acao){
	    Component[] componentes = panel.getComponents(); // altere para o nome da variavel do seu painel
	    
	    for (Component componente : componentes) {
	    	JPanel neto=(JPanel)componente;
	    	bloquearLiberarCampos(neto,acao);
    		//componente.setEnabled(acao);
	    }
	}
	
	public static void exibirEsconderCampos(JPanel panel, boolean acao){
	    Component[] componentes = panel.getComponents(); // altere para o nome da variavel do seu painel
	    
	    for (Component componente : componentes) {
    		componente.setVisible(acao);
	    }
	}
	
	public static Date converteStringParaDate(String texto){
		Date ret=null;
		try {
			ret= new SimpleDateFormat("dd/MM/yyyy").parse(texto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;

	}
	
}
