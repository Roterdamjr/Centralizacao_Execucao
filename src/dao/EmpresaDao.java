package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DaoBase;

public class EmpresaDao extends DaoBase{
	
	
	public List buscaTodos(){
		List<String> lista = new ArrayList<String>();
		String obj;
		
		String query = "select nome,deposito_mensal from tb_empresa t1";		
		
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

		String obj;
		
		String query = "select nome,deposito_mensal from tb_empresa t1";		
		
		return executaBusca(query);


	}
	
}
