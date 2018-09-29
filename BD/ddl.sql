create table TB_PLANO_EXECUCAO
(
  NOME_EMPRESA      VARCHAR2(200),
  DEPOSITO_MENSAL   NUMBER(10,2) not null,
  ID_PLANO_EXECUCAO NUMBER not null,
  CNPJ              VARCHAR2(30),
  CONTA_CORRENTE    VARCHAR2(20),
  DT_DEFERIMENTO    DATE
);

create table TB_CREDITO
(
  ID_CREDITO        NUMBER not null,
  ID_PLANO_EXECUCAO NUMBER,
  DT_RECEBIMENTO    DATE,
  SETOR             VARCHAR2(50),
  PROCESSO          VARCHAR2(25),
  DT_DISTRIBUICAO   DATE,
  NM_EXEQUENTE      VARCHAR2(200),
  DT_ANTERIORIDADE  DATE,
  IN_PRIORIDADE     VARCHAR2(3),
  VL_PEDIDO         NUMBER,
  LOCALIZACAO       VARCHAR2(100),
  OBSERVACAO        VARCHAR2(200)
);
CREATE OR REPLACE TRIGGER trg_credito
BEFORE INSERT ON tb_credito
FOR EACH ROW

BEGIN
  SELECT seq_credito.NEXTVAL
  INTO   :new.id_credito
  FROM   dual;
END;
