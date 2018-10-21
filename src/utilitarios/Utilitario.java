package utilitarios;

import java.awt.Component;
import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
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
	
	public static void limparCampos(JPanel panel){
		 Component[] componentes = panel.getComponents(); 
		 
		 for (Component componente : componentes) {
			 Class componentType = componente.getClass().getComponentType();
			 System.out.println(componentType.toString());
	    		//componente.setEnabled();
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
	
	public static java.sql.Date converteDataParaSQLData(Date data){
		return new java.sql.Date(data.getTime());
			
	}
	
	public static java.sql.Date converteStringParaSQLData(String data){
		return converteDataParaSQLData(converteStringParaDate(data));
			
	}
	
	public static BigDecimal converteStringParaBigDecimal(String texto){
		/*
		para os formatos "123.567,89"
		*/

		texto=texto.replace(".", "");
		texto=texto.replace(",", ".");
		return new BigDecimal(texto);
	}
	
     public static String escolherArquivo() {
    	
    	String arquivo=null;
    	
        try {

            //Configurando o meu JFileChooser
            JFileChooser jfc = new JFileChooser();
            
            jfc.setSelectedFile(new File("certidao.doc"));
            
            //Configura o filtro de seleção
            jfc.setFileFilter(new FileNameExtensionFilter("Arquivo Word (*.doc)", "doc"));
            
            //Configura o Label do botão de seleção
            jfc.setApproveButtonText("Selecionar");
            //Configura o Título da caixa de Dialogo
            jfc.setDialogTitle("Selecionar");
            //Configura a possíbilidade de selecionar vários arquivos
            jfc.setAcceptAllFileFilterUsed(false);

            int returnVal = jfc.showOpenDialog(null);
                        
            if (!(returnVal == JFileChooser.APPROVE_OPTION)) { // Teste o usuário seleionou algo
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
    	
    	//String sMes = String.valueOf(calendarioGregoriano.get(GregorianCalendar.MONTH-> invalido
    	return sDia+" " +sMes;
    }
    
    public static MaskFormatter buscaMascaraProcesso(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("#######-##.####.5.01.####");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("Máscara de processo inválida!");
		}
		return mascaraProcesso;		
	}    

    
    public static MaskFormatter buscaMascaraData(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("##/##/####");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("Máscara de data inválida!");
		}
		return mascaraProcesso;		
	}
    
    public static MaskFormatter buscaMascaraValor(){
		MaskFormatter mascara=null;
		
		try{
			mascara = new MaskFormatter("###.###.###,##");
			mascara.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("Máscara  inválida!");
		}
		return mascara;		
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

	public static boolean isNumeroValido(String textoDoNumero){
		
		/*Verfica se o número em foramto texto é válido
		 3.  1.245,66" -> invalido
		 845.497,6"-> valido
		 845.497, 6"-> invalido
		 045.497,6 "-> valido		
		*/
		
		boolean isValido=true;
		
		String temp= textoDoNumero.trim().replace(".","");
		String textoFormatado= temp.replace(",",".");
				
		try{
			BigDecimal valor=null;
			System.out.println(textoFormatado);
			valor =new BigDecimal(textoFormatado);			
		}catch(Exception e){
			isValido=false;
		}
		
		return isValido;
	}

	public static void populaModeloJTableComResultset(JTable tabela,ResultSet rs) {		

		try {
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] row = new Object[columns];
				for (int i = 1; i <= columns; i++) {
					row[i - 1] = rs.getObject(i);
					 //System.out.println("inserindo "+ row.toString());
				}
				((DefaultTableModel) tabela.getModel()).insertRow(
						rs.getRow() - 1, row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	public static String buscaDataAtual(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		return dateFormat.format(new Date());
		
		 
	}
}
