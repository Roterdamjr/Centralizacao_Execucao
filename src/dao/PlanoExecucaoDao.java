package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DaoBase;

public class PlanoExecucaoDao extends DaoBase{
	

	
	public List buscaTodos(){
		List<String> lista = new ArrayList<String>();
		String obj;
		
		String query = "select ID_PLANO_EXECUCAO,NOME_EMPRESA from TB_PLANO_EXECUCAO t1";		
		
		executaBusca(query);

		try {
			while (rs.next()) {				
				obj=rs.getString(1);
				lista.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public ResultSet buscaTodosComResultset(){
		
		String query = "select ID_PLANO_EXECUCAO,NOME_EMPRESA,deposito_mensal from TB_PLANO_EXECUCAO t1";		
		
		return executaBusca(query);


	}
	
}
