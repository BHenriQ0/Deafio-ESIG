# Deafio-ESIG

>>> Gerenciador de Tarefas <<<

Um sistema simples de gerenciamento de tarefas utilizando Jakarta EE (JSF, CDI, JPA/Hibernate), PostgreSQL, PrimeFaces e OmniFaces.

Funcionalidades Implementadas

Modelagem de Entidades: Responsavel, Prioridade, Situacao e Tarefa com relacionamentos JPA.

Persistência: DAOs com Java SE (JPAUtil) e transações RESOURCE_LOCAL.

Filtros Dinâmicos: Busca de tarefas por número, título/descrição, responsável, prioridade e situação (Criteria API).

Beans JSF: Beans @Named com escopos @RequestScoped e @ViewScoped, injeção de DAOs e inicialização com @PostConstruct.

Interface Web: Páginas XHTML com PrimeFaces (DataTable, Dialog, DatePicker) e CSS customizado.

>>>Dependências Principais <<<

Jakarta EE API (JSF, CDI, JPA)

Hibernate ORM

PostgreSQL JDBC Driver

PrimeFaces

OmniFaces

Lombok (annotation processing)

Pré-requisitos

Java JDK 11 ou superior

Maven 3.6+

PostgreSQL instalado (porta padrão 5432)


>>>>Configuração do Banco de Dados<<<

1: Crie o banco

CREATE DATABASE "gerenciador-de-tarefas";

2: Ajuste usuário e senha em src/main/resources/META-INF/persistence.xml:

<property name="jakarta.persistence.jdbc.user" value="<seu_usuario>"/>
<property name="jakarta.persistence.jdbc.password" value="<sua_senha>"/> 

>>> Execução Local <<<

1: Clone o repositório:

git clone https://github.com/seu-usuario/gerenciador-de-tarefas.git
cd gerenciador-de-tarefas 

2: Compile e empacote:

mvn clean package

3: Execute com Java SE (embedded Tomcat):

mvn tomcat7:run

4: Acesse em http://localhost:8080/demo/listar-tarefa.xhtml


