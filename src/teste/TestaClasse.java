package teste;

import modelo.Credito;
import dao.CreditoDao;

public class TestaClasse {
	public static void main(String[] args) {
		new TestaClasse().executa();		
	}
	
	public void executa(){
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
	}
}
