package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Credito;
import jdbc.DaoBase;

public class PagamentoDao extends DaoBase{
	
	public List buscaPorEmpresaEPeriodo(int idEmpresa, int mes, int ano){
		
		List<Credito> lista = new ArrayList<Credito>();
	
		
		String query = "select * from tb_pagamento_credito t "
					+ "	where fk_empresa=" +  idEmpresa 
					+ " and MES_APURACAO= " +mes+ " and ANO_APURACAO=" +ano;		
		
		executaBusca(query);
	
		try {
			while (rs.next()) {
				Credito obj=new Credito();
				 
				obj.setSetor(rs.getString("setor"));
				Date dt = rs.getDate("DT_RECEBIMENTO");
				obj.setDataRecebimento(dt);
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
				//System.out.println (rs.getString("exequente"));  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
}
