#Título: Sistema de Controle Acadêmico
#Nomes:	Alex de Sousa Alves - 558446 
#		Francisco Lucas Xavier Mapurunga Vieira - 563655
#		João Paulo Guilherme Moreira Mota - 552545
#Equipe: 06
-- Alteração realizada em 21/07/2025: Criação de Banco de dados no script

# Cria o banco de dados se não existir;
CREATE DATABASE IF NOT EXISTS Equipe558446;


# Tabela departamento
CREATE TABLE IF NOT EXISTS departamento (
	codigo INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	PRIMARY KEY (codigo));


# Tabela curso
CREATE TABLE IF NOT EXISTS curso (
	codigo INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	creditos_minimos INT NOT NULL,
	id_departamento INT NOT NULL,
	PRIMARY KEY (codigo),
    FOREIGN KEY (id_departamento)
		REFERENCES departamento (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela disciplina
CREATE TABLE IF NOT EXISTS disciplina (
	codigo INT NOT NULL,
	nome VARCHAR(45) NOT NULL,
	ementa TEXT(100) NULL,
	creditos INT NOT NULL,
	tipo ENUM('obrigatória', 'optativa') NOT NULL,
	id_curso INT NOT NULL,
	PRIMARY KEY (codigo),
	FOREIGN KEY (id_curso)
		REFERENCES curso (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela professor
CREATE TABLE IF NOT EXISTS professor (
	siape INT NOT NULL,
	nome VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL,
	data_ingresso DATE NOT NULL,
	id_departamento INT NOT NULL,
	PRIMARY KEY (siape),
	FOREIGN KEY (id_departamento)
		REFERENCES departamento (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE);

# Tabela para atributo multivalorado email_professor
CREATE TABLE IF NOT EXISTS email_professor (
	email VARCHAR(150) NOT NULL,
	siape INT NOT NULL,
	PRIMARY KEY (email, siape),
    FOREIGN KEY (siape)
		REFERENCES professor (siape)
		ON DELETE CASCADE
		ON UPDATE CASCADE);

# Tabela para atributo multivalorado telefone_professor
CREATE TABLE IF NOT EXISTS telefone_professor (
	numero VARCHAR(15) NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	siape INT NOT NULL,
	PRIMARY KEY (numero, siape),
		FOREIGN KEY (siape)
		REFERENCES professor (siape)
		ON DELETE CASCADE
		ON UPDATE CASCADE);

# Tabela aluno
CREATE TABLE IF NOT EXISTS aluno (
	matricula INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	endereco VARCHAR(150) NOT NULL,
	id_curso INT NOT NULL,
	tipo ENUM('graduacao', 'pos') NOT NULL,
	PRIMARY KEY (matricula),
	FOREIGN KEY (id_curso)
		REFERENCES curso (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela para atributo multivalorado telefone_aluno
CREATE TABLE IF NOT EXISTS telefone_aluno (
	matricula INT NOT NULL,
	numero VARCHAR(15) NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	PRIMARY KEY (matricula, numero),
    FOREIGN KEY (matricula)
		REFERENCES aluno (matricula)
		ON DELETE CASCADE
		ON UPDATE CASCADE);

# Tabela aluno_graduacao
CREATE TABLE IF NOT EXISTS aluno_graduacao (
	matricula INT NOT NULL,
	ano_ingresso INT NOT NULL,
	PRIMARY KEY (matricula),
    FOREIGN KEY (matricula)
		REFERENCES aluno (matricula)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela aluno_pos_graduacao
CREATE TABLE IF NOT EXISTS aluno_pos_graduacao (
	matricula INT NOT NULL,
	orientador_siape INT NOT NULL,
	PRIMARY KEY (matricula),
    FOREIGN KEY (matricula)
		REFERENCES aluno (matricula)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (orientador_siape)
		REFERENCES professor (siape)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela disciplina_cursada
CREATE TABLE IF NOT EXISTS disciplina_cursada (
	matricula_aluno INT NOT NULL,
	codigo_disciplina INT NOT NULL,
	media_final DECIMAL(4,2),
	frequencia FLOAT NOT NULL,
	PRIMARY KEY (matricula_aluno, codigo_disciplina),
	FOREIGN KEY (matricula_aluno)
		REFERENCES aluno (matricula)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (codigo_disciplina)
		REFERENCES disciplina (codigo)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);


# Tabela disciplina_professor
CREATE TABLE IF NOT EXISTS disciplina_professor (
	id_disciplina INT NOT NULL,
	siape_professor INT NOT NULL,
	PRIMARY KEY (id_disciplina, siape_professor),
    FOREIGN KEY (id_disciplina)
		REFERENCES disciplina (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (siape_professor)
		REFERENCES professor (siape)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela pré requisito
CREATE TABLE IF NOT EXISTS pre_requisito (
	id_disciplina INT NOT NULL,
	id_disciplina_requisito INT NOT NULL,
	PRIMARY KEY (id_disciplina, id_disciplina_requisito),
    FOREIGN KEY (id_disciplina)
		REFERENCES disciplina (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (id_disciplina_requisito)
		REFERENCES disciplina (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE);

# Tabela acesso_sistema
CREATE TABLE IF NOT EXISTS acesso_sistema (
	id_acesso INT NOT NULL AUTO_INCREMENT,
	login VARCHAR(50) NOT NULL,
	senha VARCHAR(50) NOT NULL,
	nome VARCHAR(100) NOT NULL,
	nivel_acesso ENUM('DBA', 'funcionario', 'comum') NOT NULL,
	matricula_aluno INT NULL,
	siape_professor INT NULL,
	id_departamento_funcionario INT NULL,
	PRIMARY KEY (id_acesso),
    FOREIGN KEY (matricula_aluno)
		REFERENCES aluno (matricula)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
		FOREIGN KEY (siape_professor)
    REFERENCES professor (siape)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (id_departamento_funcionario)
		REFERENCES departamento (codigo)
		ON DELETE CASCADE
		ON UPDATE CASCADE);


# Tabela de formação pós graduação
CREATE TABLE IF NOT EXISTS formacao_pos_graduacao (
	matricula_aluno INT NOT NULL,
	id_curso INT NOT NULL,
	PRIMARY KEY (matricula_aluno, id_curso),
	FOREIGN KEY (matricula_aluno)
		REFERENCES aluno_pos_graduacao (matricula)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (id_curso)
		REFERENCES curso (codigo)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);
        


# Visão de funcionário
CREATE OR REPLACE VIEW v_funcionario AS
	SELECT * FROM departamento;
    
# Visão de professor
CREATE OR REPLACE VIEW v_professor AS
	SELECT * FROM disciplina_cursada;
    
# Visão de aluno
CREATE OR REPLACE VIEW v_aluno AS
	SELECT * FROM disciplina_cursada;