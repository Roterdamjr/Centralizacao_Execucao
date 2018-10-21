package utilitarios;

import modelo.Usuario;

public class EscopoGlobal {
	private static Usuario usuarioLogado;

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		EscopoGlobal.usuarioLogado = usuarioLogado;
	}


	
}
