 

declare v_seq number;
begin
  insert into tb_credito      (ID_PLANO_EXECUCAO,      DT_RECEBIMENTO,      SETOR,      PROCESSO,      
  DT_DISTRIBUICAO,      NM_EXEQUENTE,     
   DT_ANTERIORIDADE,      IN_PRIORIDADE,      VL_DIVIDA,      LOCALIZACAO,      OBSERVACAO)      
   values(   1,'30/05/2018','VT4RJ','0012000-90.2008.5.01.0004','01/02/2008','MARCELO CASTELO BRANCO REIS','18/01/2012','VBR','0,00','Gaveta A','Pagar logo');
   
   select seq_credito.currval into v_seq from dual;    

  insert into tb_hist_sit_credito( IN_SITUACAO,DT_SITUACAO,ID_USUARIO,id_credito)
   values('RB',TO_DATE('17/10/2018 11:18:01', 'DD/MM/YYYY HH24:MI:SS'),1,v_seq ); 
 end;
 

