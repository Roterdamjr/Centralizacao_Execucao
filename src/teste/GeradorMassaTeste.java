package teste;

import java.sql.ResultSet;

import modelo.Parte;
import modelo.Processo;

public class GeradorMassaTeste {
	
	public static ResultSet remessasSapweb(){	
		return new SAEDao().buscaReemssaSapweb(); 	
	}
	
	public static Processo dadosDeProcesso(){	
		Parte parteA =new Parte();
		parteA.setNome("Roterdam");
		Parte parteB =new Parte();
		parteB.setNome("Marcia");
		Parte parteC =new Parte();
		parteC.setNome("Pedro");
		Processo processo= new Processo();
		processo.addParte(parteA);
		processo.addParte(parteB);
		processo.addParte(parteC);
		processo.setSiglaSetor("12VT");
		processo.setDataDistribuicao("02/06/2016");
		
		return processo;	
	}
}
