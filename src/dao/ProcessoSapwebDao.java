package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Processo;

import com.sun.org.apache.xalan.internal.xslt.Process;

import jdbc.DaoBaseSapweb;

public class ProcessoSapwebDao extends DaoBaseSapweb {
		
	public Processo buscaDados(String numeroCnj)throws Exception{
		//caso não ache o processo retorn null
			
		String numeroCnjDesformatado=numeroCnj.replace(".", "").replace("-", "");
		
		Processo processo=new Processo();
			
		String query = 	"select (select to_char(max(dt_distribuicao),'dd/mm/yyyy')"+  
					" from tb_distribuicao_1grau d1" +
					" join tb_distribuicao d on d.sq_distribuicao=d1.sq_distribuicao" +
					" where sq_processo=pr.sq_processo) dt_distribuicao ," +
					" s.sg_setor" +
					" from tb_processo pr" +
					" join tb_setor s on s.sq_setor=pr.sq_setor_julgador" +
					" where nr_processo_atual= '" + numeroCnjDesformatado + "'";  
				 							
		
		executaBusca(query);

		boolean achou=false;
		try {
			while (rs.next()) {	
				achou=true;
				processo.setDataDistribuicao(rs.getString(1));
				processo.setSiglaSetor(rs.getString(2));
				processo.setPartes(buscaPartes(numeroCnjDesformatado));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (achou){
			processo.setSistemaOrigem("Sapweb");
			return processo;
		}
		else {
			return null;
		}
	}
	
	private ArrayList<String> buscaPartes(String numeroCnjDesformatado) throws Exception{
				
		String query = "select nm_parte " +
				" from tb_processo pr " +
				" inner join tb_polo po on po.sq_processo=pr.sq_processo " +
				" inner join tb_polo_parte pp on pp.sq_polo=po.sq_polo "+
				" inner join tb_parte pt on pt.sq_parte=pp.sq_parte "+
				" left join tb_pessoa_fisica pf on pf.sq_pessoa_fisica=pt.sq_pessoa_fisica "+
				" left join tb_pessoa_juridica pj on pj.sq_pessoa_juridica=pt.sq_pessoa_juridica "+
				" where  nr_processo_atual= '" + numeroCnjDesformatado +"'";
		
		executaBusca(query);
		
		ArrayList<String> lista =new  ArrayList<String>();
		while (rs.next()) {
			lista.add(rs.getString(1));			
		}
		return lista;		
	}	
	
}
