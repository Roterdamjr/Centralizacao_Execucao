package teste;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilitarios.Utilitario;
import view.DialogSelecionarPlano;
import dao.PlanoExecucaoDao;
import documento.GeradorDeDocumento;

public class TestaClasse {
	public static void main(String[] args) {
		new TestaClasse().banco();
//		new TestaClasse().geraData();
	}
	
/*	public void executa(){
		String id_plano_execucao="4";
		String		dataRecebimento="30/05/2018";
		String		setor ="40 VT/RJ" ;	
		 String 	processo ="0162000-57.2009.5.01.0040";
		 String		dataDistribuicao="16/12/2009";
		 String		nomeExequente="Wilson Maria dos Santos Filho";
		 String		dataAnterioridade="18/01/2012";
		 String		in_prioridadade="QDG";
		 String		valorDoPedido="68.033,65";
		 String		localizacao="Gaveta"; 
		 String		observacao="Medina na agua";
				
		Credito credito= new Credito();	
		credito.setId_plano_execucao(Integer.parseInt(id_plano_execucao)) ;		
		credito.setDataRecebimento(dataRecebimento);
		credito.setSetor(setor)			;
		credito.setProcesso(processo) 		;
		credito.setDataDistribuicao(dataDistribuicao);
		credito.setNomeExequente(nomeExequente)	;
		credito.setDataAnterioridade(dataAnterioridade);
		credito.setIn_prioridadade(in_prioridadade);
		credito.setValorDoPedido(valorDoPedido);		
		credito.setLocalizacao(localizacao);
		credito.setObservacao(observacao) ;
		
		new CreditoDao().insereRegistro(credito);			
	}*/
	
	private void banco() {
		List lista = new PlanoExecucaoDao().buscaTodos();
		
	}
	
	private void executa() {
		//passagem de prâmetros entre frames 
		DialogSelecionarPlano dialogo=new DialogSelecionarPlano(null);
		dialogo.setVisible(true);
		
		//carrega dados na tela
/*		System.out.println("no retorno:"+planoRecebido);
		lblPlano.setText(planoRecebido);*/
	}
	
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
