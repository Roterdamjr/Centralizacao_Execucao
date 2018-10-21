select (select to_char(max(dt_distribuicao), 'dd/mm/yyyy')
          from tb_distribuicao_1grau d1
          join tb_distribuicao d on d.sq_distribuicao = d1.sq_distribuicao
         where sq_processo = pr.sq_processo) dt_distribuicao,
       s.sg_setor,
       (select count(*) from SAP.TB_CLE_PROCESSO_MIGRADO c 
       where c.sq_processo=pr.sq_processo)
  from tb_processo pr
  join tb_setor s on s.sq_setor = pr.sq_setor_julgador
 where nr_processo_atual =?
