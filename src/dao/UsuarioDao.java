package dao;

import java.sql.SQLException;

import modelo.PlanoDeExecucao;
import modelo.Usuario;
import jdbc.DaoBase;

public class UsuarioDao extends DaoBase{
	
	public Usuario buscaPorLogin(String login) throws Exception{
		
		String query ="select id_usuario,nm_usuario,senha from tb_usuario where login='"+login	+"'"  ;
	
		executaBusca(query);		

		Usuario usuario =new Usuario();
		
		try {
			while (rs.next()) {
				usuario.setId(rs.getInt(1));				
				usuario.setLogin(login);
				usuario.setNome(rs.getString(2));
				usuario.setSenha(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	}
}
