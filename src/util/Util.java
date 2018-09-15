package util;

import java.awt.Component;

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
}
