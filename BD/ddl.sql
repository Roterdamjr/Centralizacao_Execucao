--------------------------------------------------------
-- Export file for user APP                           --
-- Created by roterdam.junior on 19/10/2018, 16:26:27 --
--------------------------------------------------------

spool ddl.log

prompt
prompt Creating table TB_PLANO_EXECUCAO
prompt ================================
prompt
create table APP.TB_PLANO_EXECUCAO
(
  NOME_EMPRESA      VARCHAR2(200),
  DEPOSITO_MENSAL   NUMBER(10,2) not null,
  ID_PLANO_EXECUCAO NUMBER not null,
  CNPJ              VARCHAR2(30),
  CONTA_CORRENTE    VARCHAR2(20),
  DT_DEFERIMENTO    DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP.TB_PLANO_EXECUCAO
  add constraint PLANO_PK primary key (ID_PLANO_EXECUCAO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TB_CREDITO
prompt =========================
prompt
create table APP.TB_CREDITO
(
  ID_CREDITO        NUMBER not null,
  ID_PLANO_EXECUCAO NUMBER not null,
  DT_RECEBIMENTO    DATE not null,
  SETOR             VARCHAR2(50),
  PROCESSO          VARCHAR2(25),
  DT_DISTRIBUICAO   DATE,
  NM_EXEQUENTE      VARCHAR2(200),
  DT_ANTERIORIDADE  DATE,
  IN_PRIORIDADE     VARCHAR2(3),
  VL_DIVIDA         NUMBER,
  LOCALIZACAO       VARCHAR2(100),
  OBSERVACAO        VARCHAR2(200),
  IN_SITUACAO       VARCHAR2(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP.TB_CREDITO
  add constraint CRED_PK primary key (ID_CREDITO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP.TB_CREDITO
  add constraint CRED_PLN_FK foreign key (ID_PLANO_EXECUCAO)
  references APP.TB_PLANO_EXECUCAO (ID_PLANO_EXECUCAO);
alter table APP.TB_CREDITO
  add constraint CRED_CHK
  check (in_prioridade in ('ARQ', 'QDG' ,	'QDU' ,	'IDS' ,'VBR' ,'DGR','S60'));
alter table APP.TB_CREDITO
  add constraint HIST_SIT_CHK
  check (in_situacao in ('RB', 'TR','PG'));

prompt
prompt Creating table TB_USUARIO
prompt =========================
prompt
create table APP.TB_USUARIO
(
  ID_USUARIO NUMBER not null,
  NM_USUARIO VARCHAR2(100) not null,
  SENHA      VARCHAR2(10) not null,
  LOGIN      VARCHAR2(20) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP.TB_USUARIO
  add constraint USUAR_PK primary key (ID_USUARIO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TB_HIST_SIT_CREDITO
prompt ==================================
prompt
create table APP.TB_HIST_SIT_CREDITO
(
  ID_HIST_SIT_CREDITO NUMBER not null,
  IN_SITUACAO         VARCHAR2(2),
  DT_SITUACAO         DATE,
  ID_USUARIO          NUMBER,
  ID_CREDITO          NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP.TB_HIST_SIT_CREDITO
  add constraint HIST_SIT_CRED_PK primary key (ID_HIST_SIT_CREDITO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP.TB_HIST_SIT_CREDITO
  add constraint HIST_SIT_CRED_FK foreign key (ID_CREDITO)
  references APP.TB_CREDITO (ID_CREDITO);
alter table APP.TB_HIST_SIT_CREDITO
  add constraint HIST_SIT_USU_FK foreign key (ID_USUARIO)
  references APP.TB_USUARIO (ID_USUARIO);
alter table APP.TB_HIST_SIT_CREDITO
  add constraint TIPO_SIT_CHK
  check (in_situacao in ('RB', 'TR','PG'));

prompt
prompt Creating sequence SEQ_CREDITO
prompt =============================
prompt
create sequence APP.SEQ_CREDITO
minvalue 1
maxvalue 9999999999999999999999999999
start with 201
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_HIST_SIT_CRED
prompt ===================================
prompt
create sequence APP.SEQ_HIST_SIT_CRED
minvalue 1
maxvalue 9999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating trigger TRG_CREDITO
prompt ============================
prompt
CREATE OR REPLACE TRIGGER APP.trg_credito
BEFORE INSERT ON tb_credito
FOR EACH ROW

BEGIN
  SELECT seq_credito.NEXTVAL
  INTO   :new.id_credito
  FROM   dual;
END;
/

prompt
prompt Creating trigger TRG_HIST_SIT_CRED
prompt ==================================
prompt
CREATE OR REPLACE TRIGGER APP.trg_hist_sit_cred
BEFORE INSERT ON tb_hist_sit_credito
FOR EACH ROW

BEGIN
  SELECT SEQ_HIST_SIT_CRED.NEXTVAL
  INTO   :new.ID_HIST_SIT_CREDITO
  FROM   dual;
END;
/


spool off
