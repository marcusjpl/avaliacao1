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
   <driver name="h2" module="com.h2database.h2">
      <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
   </driver>
</drivers>
```

## Script Tabelas


## Script Trigger

