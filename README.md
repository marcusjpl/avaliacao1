# Avaliacao1 - Prova
CRUD JSF + CDI + HIBERNATE + Bootstrap


## Configuracoes de Banco

```xml
<datasource jndi-name="java:jboss/datasources/oracleProvaDS" pool-name="provaDS" enabled="true">
   <connection-url>jdbc:oracle:thin:@localhost:1521:xe</connection-url>
   <driver>oracle</driver>
   <pool>
      <min-pool-size>1</min-pool-size>
      <max-pool-size>5</max-pool-size>
      <prefill>true</prefill>
   </pool>
   <security>
      <user-name>prova</user-name>
      <password>root</password>
   </security>
</datasource>
<drivers>
   <driver name="oracle" module="com.oracle">
      <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
   </driver>
</drivers>
```

## Script Tabelas
Link para o arquivo: [Script](https://github.com/marcusjpl/avaliacao1/blob/master/scriptSQL.sql)

Usuario padrao:
```sql
INSERT INTO PROVA.LOGIN(NOM_LOGIN,TXT_NOME) VALUES('admin','admin');
```
## Script Trigger
Link para o arquivo: [Trigger](https://github.com/marcusjpl/avaliacao1/blob/master/trigger.sql)

## Observacoes
Nao existe relacao da PROVA.VERSAO com PROVA.LOGIN, somente após salvar dados de auditoria, então para trigger gravar o usuário da sessao, solucionei esse problema usando tabela temporaria no Oracle.
