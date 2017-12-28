# Avaliacao1 - Prova
CRUD JSF + CDI + HIBERNATE + Bootstrap


## Configuracoes de Banco -standalone.xml WildFly 10

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

## persistence.xml

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    version="2.1" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
    http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
    <persistence-unit name="prova-dev" transaction-type="JTA">
        <description>Dev persistence unit</description>
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <!-- java transaction api || JNDI -->
        <jta-data-source>java:jboss/datasources/oracleProvaDS</jta-data-source>
        <properties>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="false" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.OracleDialect"/>
        </properties>
    </persistence-unit>
</persistence>
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
