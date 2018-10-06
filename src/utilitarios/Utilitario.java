package utilitarios;

import java.awt.Component;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

public class Utilitario {
	
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
	
    public static String escolherArquivo() {
    	
    	String arquivo=null;
    	
        try {

            //Configurando o meu JFileChooser
            JFileChooser jfc = new JFileChooser();
            
            jfc.setSelectedFile(new File("certidao.doc"));
            
            //Configura o filtro de sele��o
            jfc.setFileFilter(new FileNameExtensionFilter("Arquivo Word (*.doc)", "doc"));
            
            //Configura o Label do bot�o de sele��o
            jfc.setApproveButtonText("Selecionar");
            //Configura o T�tulo da caixa de Dialogo
            jfc.setDialogTitle("Selecionar");
            //Configura a poss�bilidade de selecionar v�rios arquivos
            jfc.setAcceptAllFileFilterUsed(false);

            int returnVal = jfc.showOpenDialog(null);
                        
            if (!(returnVal == JFileChooser.APPROVE_OPTION)) { // Teste o usu�rio seleionou algo
                return null;
            }
            
            arquivo = jfc.getSelectedFile().getAbsolutePath();
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return arquivo;
    }
    
    public static String dataPorExtenso(){
    	GregorianCalendar calendarioGregoriano = new GregorianCalendar();
    	int dia = calendarioGregoriano.get(GregorianCalendar.DAY_OF_MONTH);
    	String sDia =Integer.toString(dia);

    	MesDoAno sMes=MesDoAno.values()[GregorianCalendar.MONTH];

    	sMes.toString();
    	
    	//String sMes = String.valueOf(calendarioGregoriano.get(GregorianCalendar.MONTH));
    	return sDia+" " +sMes;
    }
    
    public static MaskFormatter buscaMascaraProcesso(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("#######-##.####.5.01.####");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("M�scara de processo inv�lida!");
		}
		return mascaraProcesso;		
	}    

    
    public static MaskFormatter buscaMascaraData(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("##/##/####");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("M�scara de data inv�lida!");
		}
		return mascaraProcesso;		
	}
    
    public static MaskFormatter buscaMascaraValor(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("###.###.###,##");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("M�scara de data inv�lida!");
		}
		return mascaraProcesso;		
	}
    
    public static boolean isDataValida(String dataTexto){
    	
    	boolean isValida=true;
    	
    	Date data = null;

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		format.setLenient(false);
    		data = format.parse(dataTexto);
    	} 
    	catch (ParseException e) {
    		isValida=false;
    	}
    	return isValida;
    	
    }
}
