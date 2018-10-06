package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBaseSapweb;
import modelo.Advogado;
import modelo.Parte;
import modelo.Processo;
import modelo.Processoold;


public class ProcessoSapwebDao extends DaoBaseSapweb {
		
	public Processo buscaDados(String numeroCnjForamtado)throws Exception{
		//caso não ache o processo retorn null
			
		String numeroCnjDesformatado=numeroCnjForamtado.replace(".", "").replace("-", "");
			
		String query = 	"select (select to_char(max(dt_distribuicao),'dd/mm/yyyy')"+  
					" from tb_distribuicao_1grau d1" +
					" join tb_distribuicao d on d.sq_distribuicao=d1.sq_distribuicao" +
					" where sq_processo=pr.sq_processo) dt_distribuicao ," +
					" s.sg_setor" +
					" from tb_processo pr" +
					" join tb_setor s on s.sq_setor=pr.sq_setor_julgador" +
					" where nr_processo_atual= '" + numeroCnjDesformatado + "'";  
				 							
		
		executaBusca(query);

		Processo processo = new Processo();
		boolean achou=false;
		
		try {
			while (rs.next()) {	
				achou=true;				
				
				processo.setDataDistribuicao(rs.getString(1));
				processo.setSiglaSetor(rs.getString(2));				
				processo.setSistemaOrigem("Sapweb");
				
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
/*	private boolean isMigaradoCLE(String numeroCnj){
		
	}
*/

}
