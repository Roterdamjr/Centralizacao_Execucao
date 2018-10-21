package service;

import java.util.ArrayList;

import modelo.Credito;
import modelo.Processo;
import dao.CreditoDao;
import dao.ProcessoPJe1GDao;
import dao.ProcessoSapwebDao;

public class RecebidoService {
	
	public ArrayList<Processo> buscaRemetidosENaorecebidos(){
		
		ArrayList<Processo> remetidosENaorecebidos=new ArrayList<Processo>();
		ArrayList<Processo> remetidos=null;
		try {

			/* @TESTE PRODU��O */
			remetidos = new ProcessoSapwebDao().buscaRemessas();
			/* @TESTE EM CASA */
			// rs=GeradorMassaTeste.remessasSapweb();

			//		Utilitario.populaModeloJTableComResultset(tabela, rs);

			//exclui dos remetidos o recebidos
			ArrayList<Credito> recebidos=null;		

			recebidos=new CreditoDao().buscaPorSituacao("RB");

			for(Processo processo :remetidos){
				String numCnj= processo.getNumCnj();
				//System.out.println("lendo "+ numCnj);

				boolean achou=false;
				for (Credito credito:recebidos){
					//System.out.println("procurando "+ credito.getNumCnj());
					if(credito.getNumCnj().equals(numCnj)){
						//System.out.println("achou "+ credito.getNumCnj());
						achou=true;
						break;
					}
				}
				if(!achou){
					remetidosENaorecebidos.add(processo);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return remetidosENaorecebidos;
	}
	
	public Processo consultaDadosProcessoNoBanco(String numCnj) {
		Processo processo = null;

		/* @TESTE PRODU��O */
		try {
			// busca dados no banco sapweb

			processo = new ProcessoSapwebDao().buscaDados(numCnj);
			// se n�o achou busca no PJe1G
			if (processo == null) {
				processo = new ProcessoPJe1GDao().buscaDados(numCnj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* @TESTE EM CASA */
		/*
		 * try { // busca dados no banco sapweb processo =
		 * GeradorMassaTeste.dadosDeProcesso(); // se n�o achou busca no PJe1G
		 * if (processo == null) { processo = new
		 * ProcessoPJe1GDao().buscaDados(numCnj); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		return processo;
	}
	
	
}
