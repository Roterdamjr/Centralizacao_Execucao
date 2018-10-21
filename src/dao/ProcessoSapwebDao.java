package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBaseSapweb;
import modelo.Advogado;
import modelo.Parte;
import modelo.Processo;



public class ProcessoSapwebDao extends DaoBaseSapweb {
		
	public Processo buscaDados(String numeroCnjForamtado)throws Exception{
		//caso não ache o processo retorn null
			
		String numeroCnjDesformatado=numeroCnjForamtado.replace(".", "").replace("-", "");
		
		//busca query em arquivo
		FileReader fr = new FileReader("C:/Eclipse projects kepler/Centralizacao_Execucao/queries/BuscaDadosProcesso.sql");		
	    BufferedReader buffReader = new BufferedReader(fr);
	    
	    String linha;
	    String query="";
	    
	    while ((linha = buffReader.readLine()) != null)	    {    	
	    	query+=linha;
	    }
	    fr.close();
	    	    
	    PreparedStatement stmt= getStatmentParam(query);
	    stmt.setString(1,numeroCnjDesformatado);		 									
	    rs=stmt.executeQuery ();

	    //carrega dados
		Processo processo = new Processo();
		boolean achou=false;
		
		try {
			while (rs.next()) {	
				achou=true;				
				
				processo.setDataDistribuicao(rs.getString(1));
				processo.setSiglaSetor(rs.getString(2));				
				if(rs.getInt(3)>0){
					processo.setSistemaOrigem("Sapweb");
				}else{
					processo.setSistemaOrigem("Migrado");					
				}
				
				for (Advogado adv : buscaAdvogados(numeroCnjForamtado)) {
					processo.addAdvogado(adv);
				}
				for (Parte pt : buscaPartes(numeroCnjForamtado)) {
					processo.addParte(pt);
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (achou){			
			return processo;
		}else {
			return null;
		}
	}
	
	
	private ArrayList<Parte> buscaPartes(String numeroCnjFormatado) throws Exception{
		
		ArrayList<Parte> lista = new ArrayList<Parte>();				
		String numeroCnjDesformatado = numeroCnjFormatado.replace(".", "").replace("-", "");
		
		String query = "	select upper(pt.nm_parte) nome_parte,"
				+ "     coalesce(pf.nr_cpf, pt.nm_cnpj, pj.nr_cnpj) cpf_cnpj"
				+ "	  from tb_processo pr" + "	 inner join tb_polo po"
				+ "	    on po.sq_processo = pr.sq_processo"
				+ "	 inner join tb_polo_parte pp"
				+ "	    on pp.sq_polo = po.sq_polo"
				+ "	 inner join tb_parte pt"
				+ "	    on pt.sq_parte = pp.sq_parte"
				+ "	  left join tb_pessoa_fisica pf"
				+ "	    on pf.sq_pessoa_fisica = pt.sq_pessoa_fisica"
				+ "	  left join tb_pessoa_juridica pj"
				+ "	    on pj.sq_pessoa_juridica = pt.sq_pessoa_juridica"
				+ "	 where pr.nr_processo_atual = " + numeroCnjDesformatado
				+ "order by po.tp_polo, pp.nr_ordem";
		
		executaBusca(query);
		
		try {
			while (rs.next()) {
				Parte parte = new Parte();
				parte.setNome(rs.getString(1));
				parte.setCPF(rs.getString(2));
				lista.add(parte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;		
	}	

	public ArrayList<Advogado> buscaAdvogados(String numeroCnjFormatado)
			throws Exception {

		String numeroCnjDesformatado = numeroCnjFormatado.replace(".", "")
				.replace("-", "");

		ArrayList<Advogado> lista = new ArrayList<Advogado>();

		// advogados

		String query = "select distinct nome_advogado, oab from (" +
				"select  distinct upper(adv.nm_advogado) nome_advogado,"
				+ "       uf.cd_uf || adv.nr_registro_oab || adv.cd_letra_carteira_oab oab"
				+ "  from tb_processo pr" + " inner join tb_polo po"
				+ "    on po.sq_processo = pr.sq_processo"
				+ " inner join tb_polo_parte pp"
				+ "    on pp.sq_polo = po.sq_polo" + " inner join tb_parte pt"
				+ "    on pt.sq_parte = pp.sq_parte"
				+ "  inner join tb_patrono pat"
				+ "    on pat.sq_patrono = pt.sq_patrono"
				+ "  inner join tb_pessoa_fisica pf2"
				+ "    on pf2.sq_pessoa_fisica = pat.sq_pessoa_fisica"
				+ "  left join tb_advogado adv"
				+ "    on adv.sq_pessoa_fisica = pf2.sq_pessoa_fisica"
				+ "  left join tb_uf uf" + "    on uf.sq_uf = adv.sq_uf"
				+ " where pr.nr_processo_atual ='" + numeroCnjDesformatado +"'" 
				+ " order by po.tp_polo, pp.nr_ordem) t";

		executaBusca(query);

		try {
			while (rs.next()) {
				Advogado advogado = new Advogado();
				advogado.setNome(rs.getString(1));
				advogado.setOAB(rs.getString(2));
				lista.add(advogado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
/*	public ResultSet buscaRemessas() throws Exception{
		
		String query =  " 	select (substr(p.nr_processo_atual, 1, 7) || '-' ||	 " +
				" 	                substr(p.nr_processo_atual, 8, 2) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 10, 4) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 14, 1) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 15, 2) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 17)) processo,	 " +
				" 	                p.sq_processo	 " +
				" 	                  from tb_processo p	 " +
				" 	 inner join tb_item_lote_remessa ir    on p.sq_processo = ir.sq_processo	 " +
				" 	 inner join tb_lote_remessa lr    on ir.sq_lote_remessa = lr.sq_lote_remessa	 " +
				" 	 inner join tb_itinerario i    on lr.sq_itinerario = i.sq_itinerario	 " +
				" 	 inner join TB_ROTA_LOTE_REMESSA r    on r.sq_lote_remessa = lr.sq_lote_remessa	 " +
				" 	 where " +
				"	trunc(lr.dt_envio) > '12/10/2018' 	 " +
				" 	and    i.sq_setor_origem = p.sq_setor_julgador 	 " +
				" 	   and i.sq_setor_destino = 5709  " + 
				//"  and p.sq_processo in(  9989212,11824089,10454202)  " +
				" 	 order by processo";	 
		
		
		
		executaBusca(query);

		return rs;
	}*/
	
	public ArrayList<Processo> buscaRemessas() throws Exception{
		ArrayList<Processo> lista= new ArrayList<Processo>();
		String query =  " 	select (substr(p.nr_processo_atual, 1, 7) || '-' ||	 " +
				" 	                substr(p.nr_processo_atual, 8, 2) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 10, 4) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 14, 1) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 15, 2) || '.' ||	 " +
				" 	                substr(p.nr_processo_atual, 17)) processo,	 " +
				" 	                p.sq_processo	 " +
				" 	                  from tb_processo p	 " +
				" 	 inner join tb_item_lote_remessa ir    on p.sq_processo = ir.sq_processo	 " +
				" 	 inner join tb_lote_remessa lr    on ir.sq_lote_remessa = lr.sq_lote_remessa	 " +
				" 	 inner join tb_itinerario i    on lr.sq_itinerario = i.sq_itinerario	 " +
				" 	 inner join TB_ROTA_LOTE_REMESSA r    on r.sq_lote_remessa = lr.sq_lote_remessa	 " +
				" 	 where " +
				"	trunc(lr.dt_envio) > '12/10/2018' 	 " +
				" 	and    i.sq_setor_origem = p.sq_setor_julgador 	 " +
				" 	   and i.sq_setor_destino = 5709  " + 
				//"  and p.sq_processo in(  9989212,11824089,10454202)  " +
				" 	 order by processo";	 
		
		executaBusca(query);

		try {
			while (rs.next()) {
				Processo obj = new Processo();
				obj.setNumCnj(rs.getString(1));
				obj.setSqProcesso(rs.getInt(2));
				lista.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
/*	private boolean isMigaradoCLE(String numeroCnj){
		
	}
*/

}
