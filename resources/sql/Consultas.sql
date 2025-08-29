
-- (Tabela de acesso_sistema)
-- Classe - AcessoDAO

-- Insere um novo registro de acesso ao sistema junto a um login, senha, nome, nivel de acesso e ID do departamento.
INSERT INTO acesso_sistema (login, senha, nome, nivel_acesso, id_departamento_funcionario) VALUES (?, ?, ?, ?, ?);

-- Mostra todos os registros que a tabela acesso possui.
SELECT * FROM acesso;

-- Faz a atualização de dados de um acesso ao sistema especifico com base no id_acesso, como login, senha, nome, nivel de acesso e ID do departamento.
UPDATE acesso_sistema SET login = ?, senha = ?, nome = ?, nivel_acesso = ?, id_departamento_funcionario = ? WHERE id_acesso = ?;

-- Remove um registro de acesso ao sistema com base no id_acesso.
DELETE FROM acesso_sistema WHERE id_acesso = ?;

-- (Tabela aluno)
-- Classe - AlunoDAO 

-- Faz a inserção de um novo aluno com determinado nome, endereço e id do curso e tipo (graduação ou pós-graduação).
INSERT INTO aluno (nome, endereco, id_curso, tipo) VALUES (?, ?, ?, ?);

-- Mostra todos os registros que a tabela aluno possui.
SELECT * FROM aluno;

-- Faz a atualizão de um registro especifico de algum aluno, como nome, endereço, id do curso e tipo, com base na sua matricula.
UPDATE aluno SET nome = ?, endereco = ?, id_curso = ?, tipo = ? WHERE matricula = ?;

-- Faz a remorção de um aluno com base na sua matricula.
DELETE FROM aluno WHERE matricula = ?;

-- Faz a busca de um aluno especifico pela matricula.
SELECT * FROM aluno WHERE matricula = ?;

-- Retorna os alunos que estão cursando uma disciplina especifica (codigo disciplina) e que ainda não possuem uma média final.
SELECT A.matricula, A.nome, A.endereco, A.id_curso, A.tipo FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE D.codigo = ? AND media_final IS NULL;

-- Retorna alunos de pós-graduação que a estão sendo orietados por um professor especifico com base seu siape. 
SELECT * FROM aluno A JOIN aluno_pos_graduacao APG ON A.matricula = APG.matricula JOIN professor ON APG.orientador_siape = professor.siape WHERE professor.siape = ?;

-- Mostra todos os alunos de um determinado curso (id_curso).
SELECT * FROM aluno WHERE id_curso = ?;

-- Mostra alunos que já cursaram todas as disciplinas obrigatarias. 
SELECT a.matricula, a.nome, a.endereco, a.id_curso, a.tipo FROM aluno a JOIN disciplina_cursada dc ON a.matricula = dc.matricula_aluno JOIN disciplina d ON dc.codigo_disciplina = d.codigo WHERE a.id_curso = ? AND d.tipo = 'obrigatória' GROUP BY a.matricula, a.nome, a.endereco, a.id_curso, a.tipo HAVING COUNT(DISTINCT d.codigo) = (SELECT COUNT(*) FROM disciplina WHERE id_curso = ? AND tipo = 'obrigatória');

-- Mostra alunos que não cursaram nenhuma optativa do seu curso.
SELECT a.matricula, a.nome, a.endereco, a.id_curso, a.tipo FROM aluno a WHERE id_curso = ? AND NOT EXISTS (SELECT 1 FROM disciplina_cursada dc JOIN disciplina d ON dc.codigo_disciplina = d.codigo WHERE dc.matricula_aluno = a.matricula AND d.tipo = 'optativa' AND d.id_curso = a.id_curso);

-- Retorna 1 aluno por matricula.
SELECT 1 FROM aluno WHERE matricula = ?;

-- (Tabela de aluno_graduacao)
-- Classe - AlunoGraduacaoDAO 

-- Insere um aluno de graduação junto a sua matricula e ano de ingresso.
INSERT INTO aluno_graduacao (matricula, ano_ingresso) VALUES (?, ?);

-- Retorna todos os alunos de graduação.
SELECT * FROM aluno_graduacao;

-- Atualiza o registro de ano de ingresso de um aluno de graduação, com base na sua matricula.
UPDATE aluno_graduacao SET ano_ingresso = ? WHERE matricula = ?;

-- Remove um aluno de garduação com base sua matricula.
DELETE FROM aluno_graduacao WHERE matricula = ?;

-- (Tabela de aluno_pos_graduacao)
-- Classe - AlunoPosGraduacaoDAO

-- Adiciona um aluno de pós-graduação com matricula e siape de seu orientador.
INSERT INTO aluno_pos_graduacao (matricula, orientador_siape) VALUES (?, ?);

-- Retorna todos os alunos de pós-graduação.
SELECT * FROM aluno_pos_graduacao;

-- Faz a alteração do orientador de um aluno de pós-graduação com base sua matricula.
UPDATE aluno_pos_graduacao SET orientador_siape = ? WHERE matricula = ?;

-- Remove um aluno de pós-graduação com base na sua matricula.
DELETE FROM aluno_pos_graduacao WHERE matricula = ?;


-- Classe - ConsultasDAO
-- Retorna todos dados de um aluno especifico com base na sua matricula.
SELECT * FROM aluno A WHERE A.matricula = ?;

-- Retona o nome do curso em que o aluno está matriculado fazendo uma junção entre as tabelas aluno e curso.
SELECT C.nome FROM aluno A JOIN curso C ON A.id_curso = C.codigo WHERE A.matricula = ?;

-- Retorna as disciplinas em que o aluno está cursando atualmente e que ainda não possuem uma média final.
SELECT D.nome FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE A.matricula = ? AND DC.media_final IS NULL;

-- Retorna as disicplinas em que o aluno foi com média >= 7 e frenquência > 75%.
SELECT D.nome FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE A.matricula = ? AND DC.media_final IS NOT NULL AND DC.media_final >= 7.0 AND DC.frequencia > 0.75;

-- Retona os nomes dos cursos  que estejam vinculados a um departamento especifico.
SELECT C.nome FROM curso C JOIN departamento D ON D.codigo = C.id_departamento WHERE D.codigo = ?;

-- Retorna as disciplinas obrigatórias de um curso especifico.
SELECT nome FROM disciplina D WHERE id_curso = ? AND tipo = 'obrigatória';

-- Retorna as disciplinas optativas de um determinado curso.
SELECT nome FROM disciplina D WHERE id_curso = ? AND tipo = 'optativa';

-- Retorna os nomes dos alunos matriculados em um curso.
SELECT A.nome FROM aluno A JOIN curso C ON A.id_curso = C.codigo WHERE C.codigo = ?;

-- Retorna os alunos que cursaram todas as disciplinas obrigatorias, com uma subconsulta que conta quantas obrigatorias o aluno cursou e compara com as disciplinas obrigatoria totais do curso possui.
SELECT A.nome FROM (SELECT matricula_aluno FROM disciplina_cursada DC JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE D.tipo = 'obrigatória' AND D.id_curso = ? GROUP BY matricula_aluno HAVING count(D.codigo) = (SELECT count(*) FROM disciplina D WHERE D.id_curso = ? AND D.tipo = 'obrigatória')) DCO JOIN aluno A ON DCO.matricula_aluno = A.matricula;

-- Retorna o nome dos alunos que estão matriculados em uma disciplina especifica porém sem sua nota final ainda cadastrada.
SELECT A.nome FROM aluno A JOIN disciplina_cursada DC ON A.matricula = DC.matricula_aluno JOIN disciplina D ON DC.codigo_disciplina = D.codigo WHERE D.codigo = ? AND DC.media_final IS NULL;

-- Mostra os nomes das disciplinas que são pré-requisito de uma disciplina especifica.
SELECT DR.nome FROM disciplina D JOIN pre_requisito PR ON D.codigo = PR.id_disciplina JOIN disciplina DR ON DR.codigo = PR.id_disciplina_requisito WHERE D.codigo = ?;

-- Retornas as disciplinas que tem determinada disciplina x como pré-requisito.
SELECT D.nome FROM disciplina D JOIN pre_requisito PR ON D.codigo = PR.id_disciplina JOIN disciplina DR ON DR.codigo = PR.id_disciplina_requisito WHERE DR.codigo = ?;

-- (Tabela curso)
-- Classe - CursoDAO 

-- Insere um novo registro de curso com nome, creditos minimos e o id no qual o curso está alocado.
INSERT INTO curso (nome, creditos_minimos, id_departamento) VALUES (?, ?, ?);

-- Retorna todos os cursos cadastrados.
SELECT * FROM curso;

-- Atualiza os dados de um determinado curso com base no seu codigo.
UPDATE curso SET nome = ?, creditos_minimos = ?, id_departamento = ? WHERE codigo = ?;

-- Faz a remoção de um determinado curso, com base no seu codigo.
DELETE FROM curso WHERE codigo = ?;

-- Retorna todos os dados de um curso filtrado pelo seu codigo. 
SELECT * FROM curso WHERE codigo = ?;

-- Retorna apenas o nome de um curso filtrado pelo seu codigo.
SELECT nome FROM curso WHERE codigo = ?;

-- Retorna todos os cursos vinculados a um determinado departamento.
SELECT * FROM curso WHERE id_departamento = ?;

-- (Tabela departamento)
-- Classe - DepartamentoDAO 

-- Adiciona um novo departamento.
INSERT INTO departamento (nome) VALUES (?);

-- Retorna todos os departamentos. 
SELECT * FROM departamento;

-- Atualiza o nome de um departamento com base no seu codigo.
UPDATE departamento SET nome = ? WHERE codigo = ?;

-- Remove um departamento com base no seu codigo.
DELETE FROM departamento WHERE codigo = ?;

-- Retorna um de departamento especifico pelo seu codigo.
SELECT * FROM departamento WHERE codigo = ?;

-- (Tabela disciplina_cursada)
-- Classe - DisciplinaCursadaDAO 

-- Insere uma disciplina cursada por um aluno com matricula, codigo da disciplina, media final e sua frequencia.
INSERT INTO disciplina_cursada (matricula_aluno, codigo_disciplina, media_final, frequencia) VALUES (?, ?, ?, ?);

-- retorna todas as disciplinas cursadas.
SELECT * FROM disciplina_cursada;

-- Atualiza a média final e frequência de uma determinda disciplina cursada.
UPDATE disciplina_cursada SET media_final = ?, frequencia = ? WHERE matricula_aluno = ? AND codigo_disciplina = ?;

-- Remove uma disciplina cursada com base na matricula do aluno e codigo dessa disciplina.
DELETE FROM disciplina_cursada WHERE matricula_aluno = ? AND codigo_disciplina = ?;

-- Mostra todas as disciplinas cursadas por um aluno.
SELECT matricula_aluno, codigo_disciplina, media_final, frequencia FROM disciplina_cursada WHERE matricula_aluno = ?;

-- Retorna os dados de uma disciplina cursada com base na matricula do aluno e no codigo da disciplina.
SELECT * FROM disciplina_cursada WHERE matricula_aluno = ? AND codigo_disciplina = ?;

-- Atualiza a média final e a frequência de uma disciplina cursada, com base na matricula do aluno e codigo da disciplina.
UPDATE disciplina_cursada SET media_final = ?, frequencia = ? WHERE matricula_aluno = ? AND codigo_disciplina = ?;

-- (Tabela dsiciplina) 
-- Classe - DisciplinaDAO 

-- Adiciona uma nova disciplina com codigo, nome, ementa, creditos, tipo e o id do curso.
INSERT INTO disciplina (codigo, nome, ementa, creditos, tipo, id_curso) VALUES (?, ?, ?, ?, ?, ?);

-- Retorna todas as disciplinas.
SELECT * FROM disciplina;

-- Atualiza os dados de uma disciplina.
UPDATE disciplina SET nome = ?, ementa = ?, creditos = ?, tipo = ?, id_curso = ? WHERE codigo = ?;

-- Deleta uma disicplina com base no seu codigo.
DELETE FROM disciplina WHERE codigo = ?;

-- Retorna uma disciplina especifica com base no seu codigo.
SELECT * FROM disciplina WHERE codigo = ?;

-- Retorna todas as disciplinas ministradas por um pprofessor.
SELECT * FROM disciplina D JOIN disciplina_professor DP ON D.codigo = DP.id_disciplina JOIN professor P ON P.siape = DP.siape_professor WHERE P.siape = ?;

-- Retorna todas as disicplinas com base no id do curso.
SELECT * FROM disciplina WHERE id_curso = ?;


-- (Tabela disciplina_professor)
-- Classe - DisciplinaProfessorDAO 

-- Associa um professor a uma disciplina especifica.
INSERT INTO disciplina_professor (id_disciplina, siape_professor) VALUES (?, ?);

-- Retorna todas as associações entre professor e disicplinas.
SELECT * FROM disciplina_professor;

-- Atualiza a disciplina vinculada a um determinado professor.
UPDATE disciplina_professor SET id_disciplina = ? WHERE id_disciplina = ? AND siape_professor = ?;

-- Remove a associacão entre um professor e disciplina.
DELETE FROM disciplina_professor WHERE id_disciplina = ? AND professor_siape = ?;


-- (Tabela email_professor)
-- Classe - EmailProfessorDAO 

-- Insere um email a um professor.
INSERT INTO email_professor (email, siape) VALUES (?, ?);

-- Retorna todos os emails de professores.
SELECT * FROM email_professor;

-- Atualiza o email de um professor com base seu siape.
UPDATE email_professor SET email = ? WHERE email = ? AND siape = ?;

-- Remove o email de um professor.
DELETE FROM email_professor WHERE email = ? AND siape = ?;

-- (Tabela formacao_pos_graduacao)
-- Classe - FormacaoPosGraduacaoDAO 

-- Insere a formação de pós-graduação de um aluno com sua matricula e o id do curso.
INSERT INTO formacao_pos_graudacao (matricula_aluno, id_curso) VALUES (?, ?);

-- Retorna todas as formação de pós-graduação. 
SELECT * FROM formacao_pos_graduacao;

-- Atualiza a pós-graduação de um aluno.
UPDATE formacao_pos_graduacao SET id_curso = ? WHERE matricula_aluno = ? AND id_curso = ?;

-- Remove a formação de pós-graduação de um aluno.
DELETE FROM formacao_pos_graduacao WHERE matricula_aluno = ?;

-- (Tabela pre_requisito)
-- Classe - PreRequisitoDAO 

-- Define um pré-requisito entre disciplinas.
INSERT INTO pre_requisito (id_disciplina, id_disciplina_requisito) VALUES (?, ?);

-- Retorna todos os pré-requisitos.
SELECT * FROM pre_requisito;

-- Remove um pré-requisito.
DELETE FROM pre_requisito WHERE id_disciplina = ? AND id_disciplina_requisito = ?;

-- Retorna os pré-requisitos de uma disciplina especifica.
SELECT PR.id_disciplina, PR.id_disciplina_requisito FROM disciplina D1 JOIN pre_requisito PR ON D1.codigo = PR.id_disciplina JOIN disciplina D2 ON D2.codigo = PR.id_disciplina_requisito WHERE D1.codigo = ?;

-- Retorna as disciplina que exigem uma disciplina especifica como pré-requisito.
SELECT PR.id_disciplina, PR.id_disciplina_requisito FROM disciplina D1 JOIN pre_requisito PR ON D1.codigo = PR.id_disciplina JOIN disciplina D2 ON D2.codigo = PR.id_disciplina_requisito WHERE D2.codigo = ?;

-- (Tabela professor)
-- Classe - ProfessorDAO 

-- Adiciona um professor, com nome, data de dnascimento, data de ingresso e o id do departamento em que está alocado.
INSERT INTO professor (nome, data_nascimento, data_ingresso, id_departamento) VALUES (?, ?, ?, ?);

-- Retorna todos os professores.
SELECT * FROM professor;

-- Atualiza os dados de um professor.
UPDATE professor SET nome = ?, data_nascimento = ?, data_ingresso = ?, id_departamento = ? WHERE siape = ?;

-- Remove um professor com base no seu siape.
DELETE FROM professor WHERE siape = ?;

-- Cálculo total de creditos das disciplinas ministradas por um derterminado professor.
SELECT P.siape, count(D.creditos) total FROM professor P JOIN disciplina_professor DP ON P.siape = DP.siape_professor JOIN disciplina D ON DP.id_disciplina = D.codigo WHERE P.siape = ? GROUP BY P.siape;

-- Retorna 1 professor por matricula.
SELECT 1 FROM professor WHERE siape = ?;

-- Retorna os dados de professor com base no siape.
SELECT * FROM professor WHERE siape = ?;


-- (Tabela telefone_aluno)
-- Classe - TelefoneAlunoDAO 

-- Insere um telefone a um aluno.
INSERT INTO telefone_aluno (matricula, numero, descricao) VALUES (?, ?, ?);

-- Retorna todos os telefones de alunos.
SELECT * FROM telefone_aluno;

-- Atualiza o telefone de um aluno.
UPDATE telefone_aluno SET numero = ?, descricao = ? WHERE matricula = ?; 

-- Remove o telefone de um aluno.
DELETE FROM telefone_aluno WHERE matricula = ?;

-- Retorna o telefone de um aluno especifco com base na sua matricula.
SELECT * FROM telefone_aluno WHERE matricula = ?;

-- (Tabela telefone_professor)
-- Classe - TelefoneProfessorDAO 

-- Insere um telefone a um professor.
INSERT INTO telefone_professor (numero, descricao, siape) VALUES (?, ?, ?);

-- Retorna o telefone de todos os professores.
SELECT * FROM telefone_professor;

-- Atualiza o telefone de um professor.
UPDATE telefone_professor SET numero = ?, descricao = ? WHERE siape = ?;

-- Remove o telefone de um professor.
DELETE FROM telefone_professor WHERE numero = ? AND siape = ?;
