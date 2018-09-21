package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBaseSapweb;

public class ProcessoSapwebDao extends DaoBaseSapweb {
		
	public String[] buscaDados(String proc)throws Exception{
		
		String processo=proc.replace(".", "").replace("-", "");
		
		String[] array = new String[6];
						
		String query = 	"select (select to_char(max(dt_distribuicao),'dd/mm/yyyy')"+  
					" from tb_distribuicao_1grau d1" +
					" join tb_distribuicao d on d.sq_distribuicao=d1.sq_distribuicao" +
					" where sq_processo=pr.sq_processo) dt_distribuicao ," +
					" s.sg_setor" +
					" from tb_processo pr" +
					" join tb_setor s on s.sq_setor=pr.sq_setor_julgador" +
					" where nr_processo_atual= '" + processo + "'";  
				 							
		
		executaBusca(query);

		try {
			while (rs.next()) {				
				array[0]=rs.getString(1);
				array[1]=rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public ArrayList<String> buscaPartes(String proc) throws Exception{
				
		String processo=proc.replace(".", "").replace("-", "");
		
		String query = "select nm_parte " +
				" from tb_processo pr " +
				" inner join tb_polo po on po.sq_processo=pr.sq_processo " +
				" inner join tb_polo_parte pp on pp.sq_polo=po.sq_polo "+
				" inner join tb_parte pt on pt.sq_parte=pp.sq_parte "+
				" left join tb_pessoa_fisica pf on pf.sq_pessoa_fisica=pt.sq_pessoa_fisica "+
				" left join tb_pessoa_juridica pj on pj.sq_pessoa_juridica=pt.sq_pessoa_juridica "+
				" where  nr_processo_atual= '" + processo +"'";
		
		executaBusca(query);
		
		ArrayList<String> lista =new  ArrayList();
		while (rs.next()) {
			lista.add(rs.getString(1));			
		}
		return lista;
		
	}
	

	
	
}
