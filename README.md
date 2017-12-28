# avaliacao1 - Prova
CRUD JSF + CDI + HIBERNATE

Configuracoes de Banco:

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
