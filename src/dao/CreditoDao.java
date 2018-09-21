package dao;

import java.math.BigDecimal;
import java.util.Date;

import jdbc.DaoBase;
import modelo.Credito;

public class CreditoDao extends DaoBase{
	
	public void insereRegistro(Credito credito) {

		
		String query= 		
				" 	insert into tb_credito	 "	 +
						" 	(ID_PLANO_EXECUCAO,	 "	 +
						" 	DT_RECEBIMENTO,	 "	 +
						" 	SETOR,	 "	 +
						" 	PROCESSO,	 "	 +
						" 	DT_DISTRIBUICAO,	 "	 +
						" 	NM_EXEQUENTE,	 "	 +
						" 	DT_ANTERIORIDADE,	 "	 +
						" 	IN_PRIORIDADE,	 "	 +
						" 	VL_PEDIDO,	 "	 +
						" 	LOCALIZACAO,	 "	 +
						" 	OBSERVACAO)	 "	 +
						" 	values(	 "	 +
						
					credito.getId_plano_execucao() + 
						concatenaVariavel(credito.getDataRecebimento()) + 
						concatenaVariavel(credito.getSetor())			+ 
						concatenaVariavel(credito.getProcesso()) +
						concatenaVariavel(credito.getDataDistribuicao()) +
						concatenaVariavel(credito.getNomeExequente()) +
						concatenaVariavel(credito.getDataAnterioridade())  +
						concatenaVariavel(credito.getIn_prioridadade()) +
						concatenaVariavel(credito.getValorDoPedido().replace(".", "")) +
						concatenaVariavel(credito.getLocalizacao()) +
						concatenaVariavel(credito.getObservacao()) +
						')'
						;

		executaDML(query);
	
	}
	
	public String concatenaVariavel(String variavel){
		if(variavel==null)
			return "null,";
		else
			return ",'"+variavel+"'";		
	}
	
	public String concatenaVariavelData(Date variavel){
		if(variavel==null)
			return "null,";
		else
			return ",'"+variavel+"'";		
	}
	
	public String concatenaVariavelBigDecimal(BigDecimal variavel){
		if(variavel==null)
			return "null,";
		else
			return ",'"+variavel+"'";		
	}
}
