#Título: Sistema de Controle Acadêmico
#Nomes:	Alex de Sousa Alves - 558446 
#		Francisco Lucas Xavier Mapurunga Vieira - 563655
#		João Paulo Guilherme Moreira Mota - 552545
#Equipe: 06
#Última Alteração: 15/07/2025 as 22:21

INSERT INTO departamento(nome) VALUES
('Departamento de Ciências Exatas'),
('Departamento de Ciências Humanas'),
('Departamento de Ciências da Saúde'),
('Departamento de Engenharia e Tecnologia');

INSERT INTO curso(nome, creditos_minimos, id_departamento) VALUES
('Engenharia de Computação', 180, 4),
('Odontologia', 210, 3 ),
('Psicologia', 200, 3 ),
('Engenharia Elétrica', 180, 4),
('Física', 160, 1 ),
('Historia', 140, 2 );

INSERT INTO disciplina(codigo, nome, ementa, creditos, tipo, id_curso) VALUES
(12,'Banco de Dados','Conceitos SGBD', 6, 2, 1),
(05,'Programação Computacional','Linguagem C', 6, 2, 1),
(32,'Cálculo I','Derivadas e Integrais', 4, 1, 5),
(33,'Cálculo II','Resolução de integrais e sólidos de revolução', 4, 1, 5),
(51, 'Circuitos Eletricos I', 'Fundamentos de Circuitos', 6, 1, 4),
(52, 'Circuitos Eletricos II', 'Fundamentos de Circuitos', 4, 1, 4),
(88, 'Desenho para Engenharia', 'Tipos de Linhas, Cotas e AutoCAD', 4, 2, 4),
(21, 'Física I','Mecânica', 4, 1, 5),
(22, 'Física II','Gravitação, MHS e Ondas', 4, 1, 5),
(23, 'Física III','Eletromagnetismo Básico', 4, 1, 5),
(24, 'Física IV','Física Quântica', 6, 1, 5),
(65, 'Historiografia','Costrução Historica e Seus Métodos', 4, 1, 6),
(162, 'História do Brasil I','Descobrimento e seus Impactos', 4, 1, 6),
(181, 'História Contemporânea I','Revolução Francesa', 4, 1, 6),
(48, 'Anatomia Dental','Morfologia dos Dentes e Estruturas Anexas', 4, 2, 2),
(92, 'Farmacologia','Interações químicas com sistemas biológicos', 4, 2, 2),
(102, 'Imunologia Geral','Sistema Imunológico', 4, 2, 2),
(76, 'Introdução à Psicologia','História e Fundamentos da Psicologia', 4, 1, 3),
(211, 'Psicodiagnóstico','Funcionamento Psicológico de um Indivíduo', 4, 1, 3),
(115, 'Psicopatologia','Transtornos Mentais', 4, 1, 3),
(18, 'Estruturas de Dados','Conceitos Abstratos de Dados (TADs)', 4, 1, 1);

INSERT INTO professor (siape, nome, data_nascimento, data_ingresso, id_departamento) VALUES
(558356 , 'Fernando Henrique Pessoa', '1997-06-18', '2022-01-03', 4),
(511847 , 'Pedro Arthu Magalhães', '1987-02-09', '2015-11-06', 2),
(537963 , 'Fracisco José de Almeida', '1988-08-22', '2016-04-07', 1),
(512012 , 'Maria Nathalia de Araujo', '1981-06-04', '2008-12-23', 1),
(499531 , 'Jonas Queiroz dos Santos', '1974-04-26', '2006-02-09', 4),
(549803 , 'Camila Sousa Ximenes', '1989-11-18', '2014-07-19', 3),
(412412 , 'Bernadete Montenegro', '1964-01-12', '2002-05-21', 2),
(491224 , 'Gerson Pinheiro Silva', '1975-09-11', '2006-02-12', 2),
(444444 , 'Kátia Gisele Souza', '1992-12-02', '2013-01-02', 3);

INSERT INTO email_professor (email, siape) VALUES
('fernandohp@hotmail.com', 558356),
('pedro_ath12@outlook.com', 511847),
('franJ1@outlook.com', 537963),
('franjoseAl@gmail.com', 537963),
('naty01@gmail.com', 512012),
('jonaSousa2@yahoo.com', 499531),
('camy_50@gmail.com', 549803),
('BernadeteMontenegro41@yahoo.com', 412412),
('gersonpinhos@hotmail.com', 491224),
('KathyGisele@gmail.com', 444444);

INSERT INTO telefone_professor (numero, descricao, siape) VALUES
('88994978817', 'Celular', 558356),
('8832747890', 'Residencial', 511847),
('88994224014', 'Celular', 537963),
('88991319284', 'Celular', 512012),
('8840093258', 'Comercial', 499531),
('88997176107', 'Celular', 549803),
('8852352353', 'Comercial', 412412),
('88351524142', 'Residencial', 412412),
('88236235111', 'Comercial', 491224),
('88941222412', 'Celular', 444444);

INSERT INTO aluno (nome, endereco, id_curso, tipo) VALUES
('João Paulo Araujo', 'R. Ver. Francisco Felix Porto', 4, 2),
('Francisco Paiva Aragão','R. Pedro Guimarães', 2, 1),
('Leticia Rodrigues Sousa','R. José Maria Alverne', 1, 2),
('Renan Neri Almeida','R. Cordeiro de Andrade', 6, 2),
('Daniel Torres Martins','R. José Raimundo Lopes Ribeiro', 5, 1),
('Maria Helena Costa','R. 24 de Agosto', 3, 2),
('Lara Monteiro', 'R. Rua Paulo VI', 4, 1),
('Caio Fernandes','R. Pedro Guimarães', 6, 1),
('Bianca Luz Ponte','R. João XXIII', 1, 1),
('Enzo Dantas','R. Cordeiro de Andrade', 2, 1),
('Melina Rocha Lincon','R. Glória Catunda de Souza', 2, 1),
('Rafael Vilela','R. 24 de Agosto', 3, 2),
('Júlia Prado', 'R. Ver. Francisco Felix Porto', 4, 2),
('Ícaro Alves Nogueira','R. Joaquim Mariano', 1, 1),
('Tainá Silva Silveira','R. José Maria Alverne', 2, 1),
('Leandro Reis','R. Pedro Aguiar Carneiro', 6, 2),
('Nara Vasconcelos','R. Eduardo de Almeida Sanford', 5, 1),
('Theodoro Martins Pereira','R. Thomaz de Aragão', 1, 1);

INSERT INTO telefone_aluno (matricula, numero, descricao) VALUES
(1, '88994979807', 'Celular'),
(2, '88997764521', 'Celular'),
(3, '88991223563', 'Celular'),
(4, '88992567784', 'Celular'),
(5, '88993290515', 'Celular'),
(6, '88994315461', 'Celular'),
(7, '88982345610', 'Celular'),
(8, '85974561092', 'Celular'),
(8, '8861237845', 'Celular'),
(9, '8550782341', 'Celular'),
(10, '8843678920', 'Celular'),
(11, '8538456712', 'Celular'),
(12, '8827190436', 'Celular'),
(13, '8519876524', 'Celular'),
(14, '8836719820', 'Celular'),
(15, '8549563187', 'Celular'),
(16, '8858027643', 'Celular'),
(17, '8862910578', 'Celular'),
(18, '8574839216', 'Celular');

INSERT INTO aluno_graduacao (matricula, ano_ingresso) VALUES
(2, 2020),
(5, 2022),
(7, 2019),
(8, 2021),
(9, 2021),
(10, 2022),
(11, 2023),
(14, 2019),
(15, 2020),
(17, 2018),
(18, 2021);

INSERT INTO aluno_pos_graduacao (matricula, orientador_siape) VALUES
(3, 558356),
(6, 549803),
(1, 499531),
(4, 491224),
(12, 549803),
(13, 558356),
(16, 412412);

INSERT INTO disciplina_cursada (matricula_aluno, codigo_disciplina, media_final, frequencia) VALUES
(1, 12, 8.75, 0.90),
(1, 05, NULL, 0.90),
(2, 48, NULL, 0.80),
(3, 05, NULL, 0.95),
(4, 65, 8.00, 0.85),
(5, 21, 4.50, 0.45),
(6, 76, 9.50, 1.00),
(7, 88, 8.75, 0.90),
(8, 65, NULL, 0.80),
(8, 181, NULL, 0.80),
(9, 05, 7.50, 0.95),
(9, 18, NULL, 0.95),
(10, 48, 8.00, 0.85),
(11, 102, 5.50, 0.45),
(12, 76, 9.50, 1.00),
(13, 51, 8.00, 0.90),
(13, 52, NULL, 0.90),
(14, 12, NULL, 0.80),
(15, 92, 6.50, 0.95),
(16, 65, 8.00, 0.85),
<<<<<<< HEAD
(17, 32, 10.00, 0.8),
(17, 21, 8.50, 0.8),
(17, 22, 7.50, 0.9),
(17, 23, 9.00, 0.75),
(17, 24, NULL, 0.95),
=======
(17, 32, 10.00, 0.75),
(17, 21, 8.50, 0.75),
(17, 22, 7.50, 0.9),
(17, 23, 9.00, 0.9),
(17, 24, NULL, 0.85),
>>>>>>> bec20d440338b0bff19a3af6d966018d8d488a2b
(18, 05, 9.50, 1.00);

INSERT INTO disciplina_professor (id_disciplina, siape_professor) VALUES
(33, 512012),
(21, 512012),
(12, 558356),
(18, 499531),
(51, 499531),
(65, 511847),
(48, 549803),
(76, 549803),
(05, 558356),
(22, 537963),
(23, 537963),
(24, 537963),
(32, 512012),
(88, 499531),
(52, 499531),
(92, 549803),
(102, 549803),
(115, 444444),
(32, 537963),
(162, 511847),
(181, 412412),
(211, 444444),
(65, 491224),
(181, 491224);

INSERT INTO pre_requisito(id_disciplina, id_disciplina_requisito) VALUES
(12, 18),
(33, 32),
(22, 32),
(22, 21),
(24, 23),
(23, 22),
(52, 51),
(12, 05),
(18, 05);

INSERT INTO acesso_sistema (login, senha, nome, nivel_acesso, matricula_aluno, siape_professor, id_departamento_funcionario) VALUES
('Admin', 'Root', 'Admin', 1, NULL, NULL, NULL),
('1', 'Xz9!eRa3Lp@Q','Ricardo Almeida Silva', 2, NULL, NULL, 4),
('2', 'T6v#mY8@oFdC ', 'Lívia Duarte Mendes', 2, NULL, NULL, 1),
('3', 'bU41AqZ1!Mn9', 'Caio Fernandes', 2, NULL, NULL, 2),
('4', ' Wp#3tYu2$LaX', 'Estela Moura', 2, NULL, NULL, 3),
('5', 'kR@2zTb6^HqE', 'Henrique Vasconcelos', 2, NULL, NULL, 1),
('6', '4321', 'Nicolas Prado', 2, NULL, NULL, 4),
('412412', 'Bernadete12', 'Bernadete Montenegro', 3, NULL, 412412, NULL),
('444444', '1234', 'Kátia Gisele Souza', 3, NULL, 444444, NULL),
('491224', 'yP9!uKr^5WzF', 'Gerson Pinheiro Silva', 3, NULL, 491224, NULL),
('499531', 'Lq@7RvX#8Tyb', 'Jonas Queiroz dos Santos', 3, NULL, 499531, NULL),
('511847', 'oX$2NzM^vT6w', 'Pedro Arthu Magalhães', 3, NULL, 511847, NULL),
('512012', 'V3!qLp#Z8Wu9', 'Maria Nathalia de Araujo', 3, NULL, 512012, NULL),
('537963', 'tA@XzR6!fPmC', 'Fracisco José de Almeida', 3, NULL, 537963, NULL),
('549803', 'E5#mPt@9VxqY', 'Camila Sousa Ximenes', 3, NULL, 549803, NULL),
('558356', 'wL9^TuB#1eZm', 'Fernando Henrique Pessoa', 3, NULL, 558356, NULL),
('1', '1234', 'João Paulo Araujo', 3, 1, NULL, NULL),
('2', 'Gk4$NzA!7TqM', 'Francisco Paiva Aragão', 3, 2, NULL, NULL),
('3', 'Jp@Xr5Y!oWv8', 'Leticia Rodrigues Sousa', 3, 3, NULL, NULL),
('4', 'zV3!WtB@q9Lc', 'Renan Neri Almeida', 3, 4, NULL, NULL),
('5', 'Yx@8LpR#z6Um', 'Daniel Torres Martins', 3, 5, NULL, NULL),
('6', 'xF#2nQr!Wp7V', 'Maria Helena Costa', 3, 6, NULL, NULL),
('7', 'Um7^YpL$9xtC', 'Lara Monteiro', 3, 7, NULL, NULL),
('8', 'hL!Mz@f8Vq3X', 'Caio Fernandes', 3, 8, NULL, NULL),
('9', 'Z7#xWaRt!pLv', 'Bianca Luz Ponte', 3, 9, NULL, NULL),
('10', 'Nq^Yt9Lp$X6b', 'Enzo Dantas', 3, 10, NULL, NULL),
('11', 'sP@LxzT#4uVr', 'Melina Rocha Lincon', 3, 11, NULL, NULL),
('12', 'eX!9WtR@qLm5', 'Rafael Vilela', 3, 12, NULL, NULL),
('13', 'rZ@1Tpy#vWuM', 'Júlia Prado', 3, 13, NULL, NULL),
('14', 'Tm#X9qLp@7wY', 'Ícaro Alves Nogueira', 3, 14, NULL, NULL),
('15', 'Lp@qRt6!XvYZ', 'Tainá Silva Silveira', 3, 15, NULL, NULL),
('16', 'Gx#9LpQa!7Tr', 'Leandro Reis', 3, 16, NULL, NULL),
('17', '1234', 'Nara Vasconcelos', 3, 17, NULL, NULL),
('18', 'nY@4TpVz!qUX', 'Theodoro Martins Pereira', 3, 18, NULL, NULL);

INSERT INTO formacao_pos_graduacao (matricula_aluno, id_curso) VALUES
(1, 1),
(4, 6),
(4, 3),
(3, 1),
(6, 6),
(12, 3),
(12, 1),
(13, 1),
(16, 6);