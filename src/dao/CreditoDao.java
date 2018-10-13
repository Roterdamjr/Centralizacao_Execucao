package dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;

import jdbc.DaoBase;
import modelo.Credito;

public class CreditoDao extends DaoBase{
	
	public ResultSet buscaRsPorPlano(int idPlanoExecucao){
/*		String query = "select setor,  to_char(dt_recebimento,'dd/mm/yyyy'), processo,exequente,"
				+ " to_char(anterioridade,'dd/mm/yyyy') ," 
				+"in_classificacao,PAG_PARC_ALV,VALOR_PEDIDO,VALOR_PAGO,localizacao,observacao"
				+ " from tb_triagem_credito_fev t "
				+ "	where fk_empresa=" +  idEmpresa; */
					
		String query =" 	select setor,	 " +
				" 	       to_char(dt_recebimento, 'dd/mm/yyyy') dt_recebimento,	 " +
				" 	       processo,	 " +
				" 	       nm_exequente,	 " +
				" 	       to_char(dt_anterioridade, 'dd/mm/yyyy') dt_anterioridade,	 " +
				" 	       case in_prioridade	 " +
				" 	         when 'A80' then	 " +
				" 	          'Acima 80 anos'	 " +
				" 	         when 'QDG' then	 " +
				" 	          'Quadro Geral'	 " +
				" 	         when 'QDU' then	 " +
				" 	          'Quadro Geral União'	 " +
				" 	         when 'IDS' then	 " +
				" 	          'Idoso'	 " +
				" 	         when 'VBR' then	 " +
				" 	          'Verbas rescisórias'	 " +
				" 	         when 'DGR' then	 " +
				" 	          'Doença Grave'	 " +
				" 	         when 'S60' then	 " +
				" 	          'Até 60 S.M'	 " +
				" 	       end classif,	 " +
				" 	       t.vl_divida,	 " +
				" 	       t.localizacao,	 " +
				" 	       t.observacao	 " +
				" 	  from tb_credito t	 " +
				" 	 where id_plano_execucao=" +  idPlanoExecucao;
		
		System.out.println(query);
	
		executaBusca(query);
		return rs;
	}
	
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
						" 	VL_DIVIDA,	 "	 +
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
	
/*	public List<Credito> buscaPorEmpresa(int idEmpresa){
	
	List<Credito> lista = new ArrayList<Credito>();
		
	String query = "select * from tb_triagem_credito_fev t "
				+ "	where fk_empresa=" +  idEmpresa; 						
	
	executaBusca(query);

	try {
		while (rs.next()) {
			Credito obj=new Credito();
			 
			obj.setSetor(rs.getString("setor"));
			Date dt = rs.getDate("DT_RECEBIMENTO");
			String dtString =  rs.getDate("DT_RECEBIMENTO").toString();
			obj.setProcesso(rs.getString("processo"));
			obj.setExequente(rs.getString("exequente"));
			//obj.setAnterioridade(anterioridade);
			obj.setIn_classificacao(rs.getString("in_classificacao"));
			obj.setAlvPgParcial(rs.getString("PAG_PARC_ALV"));
			obj.setValorDoPedido(rs.getDouble("VALOR_PEDIDO"));
			obj.setValorPago(rs.getDouble("VALOR_PAGO"));
			obj.setLocalizacao(rs.getString("localizacao"));
			obj.setObservacao(rs.getString("observacao"));
			
			lista.add(obj);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lista;
}*/
}
