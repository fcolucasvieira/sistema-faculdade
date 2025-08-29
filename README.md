# Sistema de Gerenciamento Acadêmico 🎓

Este projeto consiste em um sistema desktop desenvolvido em Java para gerenciar dados acadêmicos de uma instituição de ensino superior. Ele contempla funcionalidades como cadastro de alunos, professores, disciplinas, turmas e consultas diversas.

## 📁 Estrutura do projeto

sistema-faculdade/ ├── src/ │ ├── acesso.dao/ │ ├── acesso.model/ │ ├── aluno.dao/ │ ├── aluno.model/ │ ├── curso.dao/ │ ├── curso.model/ │ ├── departamento.dao/ │ ├── departamento.model/ │ ├── disciplina.dao/ │ ├── disciplina.model/ │ ├── formacao.dao/ │ ├── formacao.model/ │ ├── professor.dao/ │ ├── professor.model/ │ ├── consultas.dao/ │ ├── utils/ │ └── tests/ ├── resources/ │ └── sql/ │ ├── Criar_BD.sql │ ├── Povoamento.sql │ └── Consultas.sql ├── docs/ │ └── Diagrama.mwb ├── .gitignore ├── README.md └── arquivos padrão do Eclipse (.project, .classpath)


## 🤝 Projeto colaborativo

Este sistema foi desenvolvido como parte de um projeto acadêmico colaborativo. Embora este repositório esteja hospedado individualmente, o desenvolvimento contou com a participação ativa de colegas durante as etapas de modelagem, discussão de lógica, testes e validação. Agradeço imensamente a todos que contribuíram com ideias e sugestões ao longo do processo.

## ⚙️ Tecnologias utilizadas

- Java (JDK 21)
- Eclipse IDE
- MySQL
- JDBC
- MySQL Workbench (para modelagem do banco)

## 🚀 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/fcolucasvieira/sistema-faculdade.git
2. Importe o projeto no Eclipse:
    File > Import > Existing Projects into Workspace
    Selecione a pasta sistema-faculdade

3. Configure o banco de dados:
    Execute os scripts em resources/sql/ na sua instância MySQL
    Atualize as credenciais de conexão no código, se necessário

4. Compile e execute a classe principal

## 📌 Funcionalidades
Cadastro e gerenciamento de alunos, professores, disciplinas e cursos

Associação de turmas e formações

Consultas SQL específicas

Organização modular por domínio

Scripts de criação e povoamento do banco

## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.   
