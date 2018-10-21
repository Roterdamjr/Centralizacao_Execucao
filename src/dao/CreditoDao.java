package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import utilitarios.Utilitario;
import jdbc.DaoBase;
import modelo.Advogado;
import modelo.Credito;
import modelo.HistoricoSituacaoCredito;
import modelo.Parte;
import modelo.Processo;

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
	
	/*public void insereCredito(Credito credito) throws Exception{
		
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
	
	}*/
	
	public void insereCredito(Credito credito,HistoricoSituacaoCredito historico) throws Exception{
		
		
		FileReader fr = new FileReader("C:/Eclipse projects kepler/Centralizacao_Execucao/queries/InsereCredito.sql");		
	    BufferedReader buffReader = new BufferedReader(fr);
		
	    String linha;
	    String query="";
	    
	    while ((linha = buffReader.readLine()) != null)	    {    	
	    	query+=linha;
	    }
	    fr.close();
	    
	    PreparedStatement stmt= getStatmentParam(query);
	    stmt.setInt(1,credito.getId_plano_execucao());	    		
	    stmt.setDate(2,Utilitario.converteStringParaSQLData(credito.getDataRecebimento()));	    	    
	    stmt.setString(3,credito.getSetor());	    
	    stmt.setString(4,credito.getNumCnj());
	    stmt.setDate(5,Utilitario.converteStringParaSQLData(credito.getDataDistribuicao()));	   
	    stmt.setString(6,credito.getNomeExequente());
	    stmt.setDate(7,Utilitario.converteStringParaSQLData(credito.getDataAnterioridade()));
	    stmt.setString(8,credito.getIn_prioridadade() );
	    stmt.setBigDecimal(9,Utilitario.converteStringParaBigDecimal(credito.getValorDoPedido()));
	    stmt.setString(10,credito.getLocalizacao());
	    stmt.setString(11,credito.getObservacao());
	    stmt.setString(12,credito.getIndicadorSituacao());
	    		
	    System.out.println(query);			
	    stmt.execute();		
	  
	    insereHistorico(historico);
	    
	}
	
	private void insereHistorico(HistoricoSituacaoCredito historico) throws Exception{
		
		/*seq para o hitorico
		*/
		int sequencia=0;
		executaBusca("select max(id_credito) from tb_credito");
		try {
			while (rs.next()) {	
				sequencia=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		historico.setIdCredito(sequencia);
		/*seq para o hitorico
		*/
		
		//busca query em arquivo
		FileReader fr = new FileReader("C:/Eclipse projects kepler/Centralizacao_Execucao/queries/InsereHistorico.sql");		
	    BufferedReader buffReader = new BufferedReader(fr);
		
	    String linha;
	    String query="";
	    
	    while ((linha = buffReader.readLine()) != null)	    {    	
	    	query+=linha;
	    }
	    fr.close();
	    
	    PreparedStatement stmt= getStatmentParam(query);
	    stmt.setString(1,historico.getIndicadorSituacao());
	    stmt.setString(2,historico.getDataSituacao());
	    stmt.setInt(3,historico.getIdUsuario());
	    stmt.setInt(4,	historico.getIdCredito());   		
	    
	    
	    System.out.println(query);			
	    stmt.execute();
	    
	}
	
/*	private void insereHistorico(HistoricoSituacaoCredito historico) throws Exception{
		
		String query="	insert into tb_hist_sit_credito( " +
						"IN_SITUACAO,DT_SITUACAO,ID_USUARIO) values('" +
						historico.getIndicadorSituacao() + "',TO_DATE('" + 
						historico.getDataSituacao()+ "', 'DD/MM/YYYY HH24:MI:SS')," + 
						historico.getIdUsuario()+")"	;
		
		executaDML(query);
	}*/

	public ArrayList<Credito> buscaPorSituacao(String tipo) throws Exception{
		
		ArrayList<Credito> lista= new ArrayList<Credito>();
		
		String query =  " select processo from tb_credito where in_situacao='"+ tipo+"'";	 
		
		executaBusca(query);

		try {
			while (rs.next()) {
				Credito obj = new Credito();
				obj.setNumCnj(rs.getString(1));
				lista.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
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
