package teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import java.util.Date;

import utilitarios.Utilitario;
import jdbc.DaoBase;

public class SAEDao extends DaoBase{
	
	public ResultSet buscaReemssaSapweb(){		
		String query =  "select nr_processo_atual,sq_processo from LX_REMESSA_SAPWEB";			
		return executaBusca(query);
	}
	
	public void insereCredito() throws Exception{
		
		FileReader fr = new FileReader("C:/Eclipse projects kepler/Centralizacao_Execucao/queries/InsereCredito.sql");
		//FileReader fr = new FileReader("C:/Eclipse projects kepler/Centralizacao_Execucao/queries/InserePlano.sql");
		
	    BufferedReader buffReader = new BufferedReader(fr);
		
	    String linha;
	    String query="";
	    
	    while ((linha = buffReader.readLine()) != null)
	    {    	
	    	query+=linha;
	    }
	    fr.close();
	    
	    PreparedStatement stmt= getStatmentParam(query);
	    stmt.setInt(1,1);	    		
	    stmt.setDate(2,Utilitario.converteStringParaSQLData("30/05/2018"));	    	    
	    stmt.setString(3,"VT");	    
	    stmt.setString(4,"0012000-90.2008.5.01.0004");
	    stmt.setDate(5,Utilitario.converteStringParaSQLData("01/02/2008"));	   
	    stmt.setString(6,"MARCELO CASTELO BRANCO REIS");
	    stmt.setDate(7,Utilitario.converteStringParaSQLData("18/01/2012"));
	    stmt.setString(8,"VBR");
	    stmt.setInt(9,10);
	    stmt.setString(10,"Gaveta A");
	    stmt.setString(11,"Pagar logo");

	    System.out.println(query);
/*		String query=" 	declare v_seq number;	 " +
				" 	begin	 " +
				" 	  insert into tb_credito      (ID_PLANO_EXECUCAO,      DT_RECEBIMENTO,      SETOR,      PROCESSO,      	 " +
				" 	  DT_DISTRIBUICAO,      NM_EXEQUENTE,     	 " +
				" 	   DT_ANTERIORIDADE,      IN_PRIORIDADE,      VL_DIVIDA,      LOCALIZACAO,      OBSERVACAO)      	 " +
				" 	   values(   1,'30/05/2018','VT4RJ','0012000-90.2008.5.01.0004','01/02/2008','MARCELO CASTELO BRANCO REIS','18/01/2012','VBR','0,00','Gaveta A','Pagar logo');	 " +
				" 	   	 " +
				" 	   select seq_credito.currval into v_seq from dual;    	 " +
				" 	  insert into tb_hist_sit_credito( IN_SITUACAO,DT_SITUACAO,ID_USUARIO,id_credito)	 " +
				" 	   values('RB',TO_DATE('17/10/2018 11:18:01', 'DD/MM/YYYY HH24:MI:SS'),1,v_seq ); 	 " +
				" 	 end;	 " ;
		*/
				
	    stmt.execute();
		
	
	}
}
