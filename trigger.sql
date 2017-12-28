create or replace TRIGGER PROVA.VERSAO_AUD 
AFTER UPDATE ON PROVA.VERSAO
REFERENCING OLD AS OLD NEW AS NEW 
FOR EACH ROW
BEGIN

    IF (UPDATING) THEN    

        IF (:NEW.IND_LIBERADO <> :OLD.IND_LIBERADO) THEN
          INSERT INTO PROVA.AUD_VERSAO
           (COD_AUD_VERSAO, 
            COD_VERSAO, 
            NOM_COLUNA, 
            CONTEUDO_ANTIGO, 
            CONTEUDO_NOVO, 
            NOM_LOGIN)
           VALUES( PROVA.SQ_AUD_VERSAO.nextval,
            :OLD.COD_VERSAO,
            'IND_LIBERADO',
            :OLD.IND_LIBERADO,
            :NEW.IND_LIBERADO,
            (select prova.login_temp.login from prova.login_temp));
        END IF;
        
        IF (:NEW.IND_SITUACAO <> :OLD.IND_SITUACAO) THEN
          INSERT INTO PROVA.AUD_VERSAO
           (COD_AUD_VERSAO, 
            COD_VERSAO, 
            NOM_COLUNA, 
            CONTEUDO_ANTIGO, 
            CONTEUDO_NOVO, 
            NOM_LOGIN)
           VALUES( PROVA.SQ_AUD_VERSAO.nextval,
            :OLD.COD_VERSAO,
            'IND_SITUACAO',
            :OLD.IND_SITUACAO,
            :NEW.IND_SITUACAO,
            (select prova.login_temp.login from prova.login_temp));
        END IF;
        
        IF (:NEW.NRO_VERSAO <> :OLD.NRO_VERSAO) THEN
          INSERT INTO PROVA.AUD_VERSAO
           (COD_AUD_VERSAO, 
            COD_VERSAO, 
            NOM_COLUNA, 
            CONTEUDO_ANTIGO, 
            CONTEUDO_NOVO, 
            NOM_LOGIN)
           VALUES( PROVA.SQ_AUD_VERSAO.nextval,
            :OLD.COD_VERSAO,
            'NRO_VERSAO',
            :OLD.NRO_VERSAO,
            :NEW.NRO_VERSAO,
            (select prova.login_temp.login from prova.login_temp));
        END IF;
        
        IF (:NEW.DAT_VERSAO <> :OLD.DAT_VERSAO) THEN
          INSERT INTO PROVA.AUD_VERSAO
           (COD_AUD_VERSAO, 
            COD_VERSAO, 
            NOM_COLUNA, 
            CONTEUDO_ANTIGO, 
            CONTEUDO_NOVO, 
            NOM_LOGIN)
           VALUES( PROVA.SQ_AUD_VERSAO.nextval,
            :OLD.COD_VERSAO,
            'DAT_VERSAO',
            TO_CHAR(:OLD.DAT_VERSAO),
            TO_CHAR(:NEW.DAT_VERSAO),
            (select prova.login_temp.login from prova.login_temp));
        END IF;  
        
    END IF;
    
END VERSAO_AUD;