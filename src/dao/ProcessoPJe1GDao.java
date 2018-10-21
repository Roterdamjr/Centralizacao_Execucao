package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBasePJe1G;
import modelo.Advogado;
import modelo.Parte;
import modelo.Processo;


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
	
	public Processo buscaDados(String numeroCnjFormatado) throws Exception{

		Processo processo=new Processo();
		
		String numeroCnjDesformatado=numeroCnjFormatado.replace(".", "").replace("-", "");
	
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
				processo.setSistemaOrigem("PJe1G");
				
				for (Advogado adv : buscaAdvogados(numeroCnjFormatado)) {
					processo.addAdvogado(adv);
				}
				for (Parte pt : buscaPartes(numeroCnjFormatado)) {
					processo.addParte(pt);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (achou){
			
			return processo;
		}
		else{ 
			return null;
		}
	}
	
	private ArrayList<Parte> buscaPartes(String numeroCnjFormatado) throws Exception{
		
		/*select ul.ds_nome 
		from pje.tb_processo_parte pp 
		join pje.tb_usuario_login ul  on ul.id_usuario = pp.id_pessoa
		join pje.tb_processo_trf pr on pr.id_processo_trf=pp.id_processo_trf
		 where nr_sequencia= 0101921
		and nr_digito_verificador=48
		and nr_ano=2017
		and nr_origem_processo =0003
		*/
		
		ArrayList<Parte> lista = new ArrayList<Parte>();

		// partes
		String query = "  select (select ul.ds_nome"
				+ "		          from tb_usuario_login ul"
				+ "		         where ul.id_usuario = pp.id_pessoa) as nome,"
				+ "		       (select pdi.nr_documento_identificacao"
				+ "		          from tb_pess_doc_identificacao pdi"
				+ "		         where pdi.cd_tp_documento_identificacao in ('CPF', 'CPJ', 'RJC')"
				+ "		           and in_principal = 'S'"
				+ "		           and pdi.id_pessoa = pp.id_pessoa"
				+ "		         order by id_pessoa_doc_identificacao limit 1) documento"
				+ "      		  from tb_processo_parte pp"
				+ "		  left join tb_processo p"
				+ "		    on p.id_processo = pp.id_processo_trf"
				+ "		 where p.nr_processo = '" + numeroCnjFormatado
				+ "'		and id_tipo_parte <> 7" + " order by pp.in_participacao";

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

		ArrayList<Advogado> lista = new ArrayList<Advogado>();

		// advogados
		String query = "  select (select upper(ul.ds_nome)"
				+ "          from tb_usuario_login ul"
				+ "         where ul.id_usuario = pp.id_pessoa) as nome,"
				+ "       (select (u.cd_estado || pa.nr_oab || pa.ds_letra_oab) oab"
				+ "          from tb_pessoa_advogado pa"
				+ "          join tb_estado u"
				+ "            on u.id_estado = pa.id_uf_oab"
				+ "           and pa.id = pp.id_pessoa"
				+ "         order by pa.dt_cadastro desc limit 1)"
				+ "  from tb_processo_parte pp" + "  left join tb_processo p"
				+ "    on p.id_processo = pp.id_processo_trf"
				+ " where p.nr_processo = '" + numeroCnjFormatado
				+ "'   and id_tipo_parte = 7" + " order by pp.in_participacao ";

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
	
}
