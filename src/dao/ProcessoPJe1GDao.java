package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Processo;
import jdbc.DaoBasePJe1G;

public class ProcessoPJe1GDao extends DaoBasePJe1G{

	/*
	 * 0101921-48.2017.5.01.0003
select dt_autuacao, ds_sigla
 from pje.tb_processo_trf pr
join pje.tb_orgao_julgador oj on oj.id_orgao_julgador=pr.id_orgao_julgador
where nr_sequencia= 0101921
and nr_digito_verificador=48
and nr_ano=2017
and nr_origem_processo =0003
	*/
	
	public Processo buscaDados(String numeroCnj) throws Exception{

		Processo processo=new Processo();
		
		String numeroCnjDesformatado=numeroCnj.replace(".", "").replace("-", "");
	
		int nr_sequencia = Integer.parseInt(numeroCnjDesformatado.substring(0,7));
		int nr_digito_verificador = Integer.parseInt(numeroCnjDesformatado.substring(7,9));
		int nr_ano =Integer.parseInt(numeroCnjDesformatado.substring(9,13));
		int nr_origem_processo =Integer.parseInt(numeroCnjDesformatado.substring(16));
		
		String query = "select to_char(dt_autuacao,'dd/mm/yyyy'), ds_sigla " + 
						" from pje.tb_processo_trf pr " +
						" join pje.tb_orgao_julgador oj on oj.id_orgao_julgador=pr.id_orgao_julgador " +
						" where nr_sequencia=" +  nr_sequencia +
						" and nr_digito_verificador="+nr_digito_verificador +
						" and nr_ano="  + nr_ano +
						" and nr_origem_processo =" +nr_origem_processo	;		
		
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
			processo.setSistemaOrigem("PJe1G");
			return processo;
		}
		else{ 
			return null;
		}
	}
	
	private ArrayList<String> buscaPartes(String numeroCnjDesformatado) throws Exception{
		
		/*select ul.ds_nome 
		from pje.tb_processo_parte pp 
		join pje.tb_usuario_login ul  on ul.id_usuario = pp.id_pessoa
		join pje.tb_processo_trf pr on pr.id_processo_trf=pp.id_processo_trf
		 where nr_sequencia= 0101921
		and nr_digito_verificador=48
		and nr_ano=2017
		and nr_origem_processo =0003
		*/
		
		
		int nr_sequencia = Integer.parseInt(numeroCnjDesformatado.substring(0,7));
		int nr_digito_verificador = Integer.parseInt(numeroCnjDesformatado.substring(7,9));
		int nr_ano =Integer.parseInt(numeroCnjDesformatado.substring(9,13));
		int nr_origem_processo =Integer.parseInt(numeroCnjDesformatado.substring(16));
		
		String query = "select ul.ds_nome " +
						" from pje.tb_processo_parte pp " +
						" join pje.tb_usuario_login ul  on ul.id_usuario = pp.id_pessoa " +
						" join pje.tb_processo_trf pr on pr.id_processo_trf=pp.id_processo_trf" +
						"  where nr_sequencia=" + nr_sequencia +
						" 	and nr_digito_verificador=" +nr_digito_verificador+
						" 	and nr_ano=" +nr_ano+
						" 	and nr_origem_processo =" + nr_origem_processo;
		
		executaBusca(query);
		
		ArrayList<String> lista =new  ArrayList<String>();
		while (rs.next()) {
			lista.add(rs.getString(1));			
		}
		return lista;		
	}
	
	
}
