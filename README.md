# Sistema de Gerenciamento Acadêmico 🎓

Este projeto consiste em um sistema desktop desenvolvido em Java para gerenciar dados acadêmicos de uma instituição de ensino superior. Ele contempla funcionalidades como cadastro de alunos, professores, disciplinas, turmas e consultas diversas.

## 🧱 Arquitetura do sistema

O sistema foi desenvolvido com foco em modularidade e separação de responsabilidades. Cada entidade do domínio (como Aluno, Professor, Curso, etc.) possui:

- Um pacote de **modelos** (`.model`) que representa os dados
- Um pacote de **acesso a dados** (`.dao`) que gerencia a persistência

Além disso, o projeto inclui:

- Pacote de **utilitários** para funções auxiliares
- Pacote de **consultas** para lógica de busca e filtragem
- Scripts SQL organizados em `recursos/sql`
- Documentação visual do banco em `docs/Diagrama.mwb`

Essa estrutura facilita a manutenção, testes e expansão futura do sistema.

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
