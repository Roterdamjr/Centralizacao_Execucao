package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import modelo.Credito;
import jdbc.DaoBase;

public class TriagemDao extends DaoBase{
	

	public List<Credito> buscaPorEmpresa(int idEmpresa){
		
		List<Credito> lista = new ArrayList<Credito>();
			
		String query = "select * from tb_triagem_credito_fev t "
					+ "	where fk_empresa=" +  idEmpresa; 						
		
		executaBusca(query);
	
		try {
			while (rs.next()) {
				Credito obj=new Credito();
				 
/*				obj.setSetor(rs.getString("setor"));
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
				obj.setObservacao(rs.getString("observacao"));*/
				
				lista.add(obj);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public ResultSet buscaRsPorEmpresa(int idEmpresa){
/*		String query = "select setor,  to_char(dt_recebimento,'dd/mm/yyyy'), processo,exequente,"
				+ " to_char(anterioridade,'dd/mm/yyyy') ," 
				+"in_classificacao,PAG_PARC_ALV,VALOR_PEDIDO,VALOR_PAGO,localizacao,observacao"
				+ " from tb_triagem_credito_fev t "
				+ "	where fk_empresa=" +  idEmpresa; */
					

		String query ="select setor,   to_char(dt_recebimento, 'dd/mm/yyyy')," +
       " processo,  exequente," +
       " to_char(anterioridade, 'dd/mm/yyyy') anterioridade," +      
       " case in_classificacao" +
       "  when 'A80' then          'Acima 80 anos'" +
       "  when 'QDG' then          'Quadro Geral'" +
       "  when 'QDU' then          'Quadro Geral União'" +
       "  when 'IDS' then          'Idoso'" +
       "  when 'VBR' then          'Verbas rescisórias'" +
       "  when 'DGR' then          'Doença Grave'" +
       "  when 'S60' then          'Até 60 S.M'" +
       " end classif ," +
       " t.pag_parc_alv, t.valor_pedido, t.valor_pago,  t.localizacao, t.observacao" +
		" from tb_triagem_credito_fev t" +
		" where fk_empresa = " +  idEmpresa;
		
		System.out.println(query);
	
		executaBusca(query);
		return rs;
	}
	
	public int insereLinha(String[] valores){
		//apgar linhas, colocar aviso de salvamento
		
		String query ="insert into tb_triagem_credito (setor,processo) values ('a','b')";
		System.out.println("salvando");
		PreparedStatement stmt;
		int retorno=0;
				
		try {
			stmt = connection.prepareStatement(query);
			retorno=stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	
}
