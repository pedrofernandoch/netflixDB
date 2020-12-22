
INSERT INTO Plano (Id, Nome, Valor, QualidadeMax, QtdTelas, NumMaxPerfis) VALUES (1, 'Basico', 39.90, 720, 1, 2);
INSERT INTO Plano (Id, Nome, Valor, QualidadeMax, QtdTelas, NumMaxPerfis) VALUES (2, 'BasicoHD', 49.90, 1080, 1, 2);
INSERT INTO Plano (Id, Nome, Valor, QualidadeMax, QtdTelas, NumMaxPerfis) VALUES (3, 'Avançado', 45.90,720, 3, 5);
INSERT INTO Plano (Id, Nome, Valor, QualidadeMax, QtdTelas, NumMaxPerfis) VALUES (4, 'AvançadoHD', 55.90, 1080, 3, 5);
INSERT INTO Plano (Id, Nome, Valor, QualidadeMax, QtdTelas, NumMaxPerfis) VALUES (5, 'FamiliaHD', 59.90, 1080, 5, 8);

INSERT INTO Usuario (CPF, Nome, Email, DataNasc, Plano)  VALUES(96917262030, 'Eduardo Carlos de Souza' ,'Edcarlos@gmail.com', TO_DATE('01/01/2015', 'dd/mm/yyyy'),2);
INSERT INTO Usuario (CPF, Nome, Email, DataNasc, Plano)  VALUES (56055095009, 'Carla Maria Antonieta', 'Carlita@hotmail.com', TO_DATE('28/03/1992', 'dd/mm/yyyy'), 3);
INSERT INTO Usuario (CPF, Nome, Email, DataNasc, Plano)  VALUES (20372442072, 'Lucas Morais e Silva', 'Lusilva@gmail.com', TO_DATE('03/12/1980', 'dd/mm/yyyy'), 5);
INSERT INTO Usuario (CPF, Nome, Email, DataNasc, Plano)  VALUES (74338892056, 'Michelle Santos', 'Misantos@yahoo.com', TO_DATE('15/07/1989', 'dd/mm/yyyy'), 4);
INSERT INTO Usuario (CPF, Nome, Email, DataNasc, Plano)  VALUES (23276743019, 'Diego Magno Fiorutti', 'Didifiorutti@gmail.com', TO_DATE('02/12/2000', 'dd/mm/yyyy'), 1);

INSERT INTO Dispositivo (Usuario ,Nome, SistOperacional) VALUES (96917262030, 'CompDoEdu','Linux');
INSERT INTO Dispositivo (Usuario ,Nome, SistOperacional) VALUES (56055095009, 'IphoneDaCarla', 'IOS');
INSERT INTO Dispositivo (Usuario ,Nome, SistOperacional) VALUES (20372442072, 'CompDoLucas', 'Windows');
INSERT INTO Dispositivo (Usuario ,Nome, SistOperacional) VALUES (74338892056, 'AndroidDaMi', 'Android');
INSERT INTO Dispositivo (Usuario ,Nome, SistOperacional) VALUES (23276743019, 'DidiSmartTV', 'Android');

INSERT INTO Acesso(Usuario, NomeDisp, IPv4, HoraAcesso) VALUES (96917262030, 'CompDoEdu', '192.168.25.35', TO_TIMESTAMP('2020-03-12 12:07:04','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Acesso(Usuario, NomeDisp, IPv4, HoraAcesso) VALUES (56055095009, 'IphoneDaCarla', '192.155.13.12', TO_TIMESTAMP('2020-07-22 15:03:34','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Acesso(Usuario, NomeDisp, IPv4, HoraAcesso) VALUES (20372442072, 'CompDoLucas', '10.1.168.25.35', TO_TIMESTAMP('2020-12-19 07:54:31','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Acesso(Usuario, NomeDisp, IPv4, HoraAcesso) VALUES (74338892056, 'AndroidDaMi', '192.255.255.1', TO_TIMESTAMP('2020-02-07 22:31:52','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Acesso(Usuario, NomeDisp, IPv4, HoraAcesso) VALUES (23276743019, 'DidiSmartTV', '144.138.95.55', TO_TIMESTAMP('2020-07-22 15:03:34','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO FormaDePagamento(Usuario, Tipo) VALUES (96917262030,'Cartão De Crédito');
INSERT INTO FormaDePagamento(Usuario, Tipo) VALUES (56055095009, 'PayPal');
INSERT INTO FormaDePagamento(Usuario, Tipo) VALUES (20372442072,'Débito Automático');
INSERT INTO FormaDePagamento(Usuario, Tipo) VALUES (74338892056, 'PayPal');
INSERT INTO FormaDePagamento(Usuario, Tipo) VALUES (23276743019, 'Cartão De Crédito');

INSERT INTO NumCartao(NumCartao, Bandeira, DataVenc, CodSeg, NomeTitular) VALUES (5297024281433889,'Mastercard', TO_DATE('20/11/2021', 'dd/mm/yyyy'),123,'Eduardo Carlos de Souza');
INSERT INTO NumCartao(NumCartao, Bandeira, DataVenc, CodSeg, NomeTitular) VALUES (4929486473204882,'Visa' , TO_DATE('20/08/2021', 'dd/mm/yyyy'),741,'Maristela Guimarães');
INSERT INTO NumCartao(NumCartao, Bandeira, DataVenc, CodSeg, NomeTitular) VALUES (5141318897690454, 'Mastercard' , TO_DATE('02/05/2023', 'dd/mm/yyyy'),113, 'Lucas Morais e Silva');
INSERT INTO NumCartao(NumCartao, Bandeira, DataVenc, CodSeg, NomeTitular) VALUES (4485047633575151, 'Visa' , TO_DATE('25/12/2025', 'dd/mm/yyyy'),123,'Michelle Santos');
INSERT INTO NumCartao(NumCartao, Bandeira, DataVenc, CodSeg, NomeTitular) VALUES (5342363587869502, 'Mastercard' , TO_DATE('23/01/2022', 'dd/mm/yyyy'),392,'Diego Magno Fiorutti');

INSERT INTO CartaoCredito(Usuario, NumCartao) VALUES (96917262030,5297024281433889);
INSERT INTO CartaoCredito(Usuario, NumCartao) VALUES (23276743019 ,5342363587869502);
INSERT INTO CartaoCredito(Usuario, NumCartao) VALUES (56055095009,4929486473204882);
INSERT INTO CartaoCredito(Usuario, NumCartao) VALUES (20372442072, 5141318897690454);
INSERT INTO CartaoCredito(Usuario, NumCartao) VALUES (74338892056, 4485047633575151);

INSERT INTO DebitoAutomatico(Usuario, CPF, Nome, Sobrenome, Banco, Agencia, Conta) VALUES (96917262030, 96917262030, 'Eduardo', 'Carlos de Souza', 'Banco do Brasil', 0001, 1244116);
INSERT INTO DebitoAutomatico(Usuario, CPF, Nome, Sobrenome, Banco, Agencia, Conta) VALUES (23276743019, 23276743019, 'Carla', 'Maria Antonieta', 'Itaú', 4412, 84739287);
INSERT INTO DebitoAutomatico(Usuario, CPF, Nome, Sobrenome, Banco, Agencia, Conta) VALUES (56055095009, 56055095009, 'Lucas' , 'Morais e Silva', 'Bradesco', 1323,2313124);
INSERT INTO DebitoAutomatico(Usuario, CPF, Nome, Sobrenome, Banco, Agencia, Conta) VALUES (20372442072, 20372442072, 'Michelle', 'Santos', 'Santander', 312, 4324344);
INSERT INTO DebitoAutomatico(Usuario, CPF, Nome, Sobrenome, Banco, Agencia, Conta) VALUES (74338892056, 74338892056, 'Diego', 'Magno Fiorutti', 'Santander', 834, 321423434);

INSERT INTO PayPal(Usuario, Email, Senha) VALUES (96917262030, 'Edcarlos@gmaill.com', 'senhaDoEduardo');
INSERT INTO PayPal(Usuario, Email, Senha) VALUES (23276743019, 'Carlita@hotmail.com', 'senhadacarla');
INSERT INTO PayPal(Usuario, Email, Senha) VALUES (56055095009, 'Lusilva@gmail.com', 'senhadoLucas');
INSERT INTO PayPal(Usuario, Email, Senha) VALUES (20372442072, 'Misantos@yahoo.com', 'senhaDaMichelle');
INSERT INTO PayPal(Usuario, Email, Senha) VALUES (74338892056, 'Didifiorutti@gmail.com', 'SenhaDoDiego');

INSERT INTO Faturas(CodFatura, Periodo, Usuario, Plano) VALUES (1, TO_DATE('20/12/2022', 'dd/mm/yyyy'),  96917262030, 2);
INSERT INTO Faturas(CodFatura, Periodo, Usuario, Plano) VALUES (2, TO_DATE('20/12/2022', 'dd/mm/yyyy'), 23276743019, 3);
INSERT INTO Faturas(CodFatura, Periodo, Usuario, Plano) VALUES (3, TO_DATE('20/12/2022', 'dd/mm/yyyy'), 56055095009, 5);
INSERT INTO Faturas(CodFatura, Periodo, Usuario, Plano) VALUES (4, TO_DATE('20/12/2022', 'dd/mm/yyyy'), 20372442072, 4);
INSERT INTO Faturas(CodFatura, Periodo, Usuario, Plano) VALUES (5, TO_DATE('20/12/2022', 'dd/mm/yyyy'), 74338892056, 1);

INSERT INTO Idioma(Id, Nome) VALUES (1, 'Inglês');
INSERT INTO Idioma(Id, Nome) VALUES (2, 'Espanhol');
INSERT INTO Idioma(Id, Nome) VALUES (3, 'Português');
INSERT INTO Idioma(Id, Nome) VALUES (4, 'Mandarim');
INSERT INTO Idioma(Id, Nome) VALUES (5, 'Alemão');

INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('Eduardo',96917262030, 'Adulto');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('julinha', 96917262030, 'Infantil');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('marquinhos', 96917262030, 'Infantil');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('clarinha', 96917262030, 'Infantil');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ( 'Lucas',56055095009,'Adulto');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('gabrielzinho', 56055095009, 'Infantil');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('joaozinho', 56055095009, 'Infantil');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('Carla', 23276743019, 'Adulto');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('Michelle', 20372442072, 'Adulto');
INSERT INTO Perfil(Alias, Usuario, Tipo) VALUES ('Diego', 74338892056, 'Adulto');

INSERT INTO Adulto (Alias, Usuario, QualidStreaming,Legenda, Idioma) VALUES ('Eduardo', 96917262030, 1080, 3,3 );
INSERT INTO Adulto (Alias, Usuario, QualidStreaming,Legenda, Idioma) VALUES ('Carla', 23276743019, 720,3,1);
INSERT INTO Adulto (Alias, Usuario, QualidStreaming,Legenda, Idioma) VALUES ('Lucas', 56055095009, 720, 3,2);
INSERT INTO Adulto (Alias, Usuario, QualidStreaming,Legenda, Idioma) VALUES ('Michelle', 20372442072, 1080, 4,1);
INSERT INTO Adulto (Alias, Usuario, QualidStreaming,Legenda, Idioma) VALUES ('Diego', 74338892056, 1080, 5,4);

INSERT INTO Infantil (Alias, Usuario, FaixaEtaria, Adulto) VALUES ('julinha', 96917262030, 08, 'Eduardo');
INSERT INTO Infantil (Alias, Usuario, FaixaEtaria, Adulto) VALUES ('marquinhos', 96917262030, 10, 'Eduardo');
INSERT INTO Infantil (Alias, Usuario, FaixaEtaria, Adulto) VALUES ('clarinha', 96917262030, 12, 'Eduardo');
INSERT INTO Infantil (Alias, Usuario, FaixaEtaria, Adulto) VALUES ('gabrielzinho', 56055095009, 14,'Lucas');
INSERT INTO Infantil (Alias, Usuario, FaixaEtaria, Adulto) VALUES ('joaozinho', 56055095009, 16, 'Lucas');

INSERT INTO Amizade (Perfil1, Usuario1, Perfil2, Usuario2, DataAceitacao, DataSolicitacao) VALUES ('julinha', 96917262030,'marquinhos', 96917262030, TO_DATE('01/01/2020', 'dd/mm/yyyy'),TO_DATE('20/11/2019', 'dd/mm/yyyy'));
INSERT INTO Amizade (Perfil1, Usuario1, Perfil2, Usuario2, DataAceitacao, DataSolicitacao) VALUES ('Eduardo', 96917262030,'Carla', 23276743019, TO_DATE('20/06/2020', 'dd/mm/yyyy'), TO_DATE('20/04/2020', 'dd/mm/yyyy'));
INSERT INTO Amizade (Perfil1, Usuario1, Perfil2, Usuario2, DataAceitacao, DataSolicitacao) VALUES ('Carla', 23276743019, 'joaozinho', 56055095009, TO_DATE('10/10/2020', 'dd/mm/yyyy'),TO_DATE('20/11/2020', 'dd/mm/yyyy'));
INSERT INTO Amizade (Perfil1, Usuario1, Perfil2, Usuario2, DataAceitacao, DataSolicitacao) VALUES ('Diego', 74338892056,'Lucas',56055095009, TO_DATE('20/12/2018', 'dd/mm/yyyy'),TO_DATE('07/01/2019', 'dd/mm/yyyy'));
INSERT INTO Amizade (Perfil1, Usuario1, Perfil2, Usuario2, DataAceitacao, DataSolicitacao) VALUES ('gabrielzinho', 56055095009, 'clarinha', 96917262030, TO_DATE('12/05/2020', 'dd/mm/yyyy'), TO_DATE('15/06/2020', 'dd/mm/yyyy'));

INSERT INTO Serie (Id, Titulo, AnoLancamento, QtdTemporadas) VALUES (1,'Anos Incríveis',1988,6);
INSERT INTO Serie (Id, Titulo, AnoLancamento, QtdTemporadas) VALUES (2,'Black Mirror', 2011,5);
INSERT INTO Serie (Id, Titulo, AnoLancamento, QtdTemporadas) VALUES (3,'Breaking Bad',2008, 5);
INSERT INTO Serie (Id, Titulo, AnoLancamento, QtdTemporadas) VALUES (4,'Friends', 1994, 10);
INSERT INTO Serie (Id, Titulo, AnoLancamento, QtdTemporadas) VALUES (5,'Game of Thrones', 2011, 8);

INSERT INTO Temporada (Serie, Numero, Titulo, QtdEpisodios) VALUES (1,5,'Aquele ano', 24);
INSERT INTO Temporada (Serie, Numero, Titulo, QtdEpisodios) VALUES (2,4, 'O mundo é doido', 8);
INSERT INTO Temporada (Serie, Numero, Titulo, QtdEpisodios) VALUES (3,3, 'Drogas caseiras', 12);
INSERT INTO Temporada (Serie, Numero, Titulo, QtdEpisodios) VALUES (4,2, 'Amigos para sempre', 24);
INSERT INTO Temporada (Serie, Numero, Titulo, QtdEpisodios) VALUES (5,1, 'Tá pegando fogo',12);

INSERT INTO Media(Id, Titulo, AnoLancamento, Thumb, Duracao, Sinopse, ClassificacaoEtaria, Avaliacao, NumeroDoEp, Serie, Temporada) VALUES (1,'Sabado incrivel', 1993, 'thumb1', 33, 'Todo mundo se diverte em um sabádo', 10, 88, 3,1,5 );
INSERT INTO Media(Id, Titulo, AnoLancamento, Thumb, Duracao, Sinopse, ClassificacaoEtaria, Avaliacao, NumeroDoEp, Serie, Temporada) VALUES (2,'As maquinas dominaram tudo', 2015, 'thumb2', 59, 'Agora são as maquinas que dominam o mundo, o que os humanos vão fazer?', 18, 90, 7, 2,4 );
INSERT INTO Media(Id, Titulo, AnoLancamento, Thumb, Duracao, Sinopse, ClassificacaoEtaria, Avaliacao, NumeroDoEp, Serie, Temporada) VALUES (3,'Dia de furia', 2011, 'thumb3', 55, 'O protagonista e enganado e agora está enfurecido', 16, 79, 8,3,3 );
INSERT INTO Media(Id, Titulo, AnoLancamento, Thumb, Duracao, Sinopse, ClassificacaoEtaria, Avaliacao, NumeroDoEp, Serie, Temporada) VALUES (4,'Amigos na lanchonete', 1995, 'thumb4', 28,'Amigos conversando sobre aletoriedades na lanchonete', 00, 81, 04, 4,2 );
INSERT INTO Media(Id, Titulo, AnoLancamento, Thumb, Duracao, Sinopse, ClassificacaoEtaria, Avaliacao, NumeroDoEp, Serie, Temporada) VALUES (5,'O Inicio', 2011, 'thumb5', 59, 'o Inicio da jornada', 16, 88, 1,5,1  );

INSERT INTO Recomendacoes(Alias, Usuario, PerfilAmigo, UsuarioAmigo,  Media, Data, Comentario) VALUES ('julinha' , 96917262030, 'marquinhos' ,96917262030, 1,  DATE '2022-12-02', 'Olha que serie maneira');
INSERT INTO Recomendacoes(Alias, Usuario, PerfilAmigo, UsuarioAmigo,  Media, Data, Comentario) VALUES ('Eduardo',96917262030,'Carla', 23276743019, 2, DATE '2020-11-13', 'Esse episodio me lembrou de voce');
INSERT INTO Recomendacoes(Alias, Usuario, PerfilAmigo, UsuarioAmigo,  Media, Data, Comentario) VALUES ('Carla', 23276743019, 'joaozinho', 56055095009, 3, TO_DATE('17/12/2020', 'dd/mm/yyyy'), 'Olha que serie legal joaozinho');
INSERT INTO Recomendacoes(Alias, Usuario, PerfilAmigo, UsuarioAmigo,  Media, Data, Comentario) VALUES ('Diego', 74338892056,'Lucas',56055095009,4, DATE '2020-11-11' ,'Demais');
INSERT INTO Recomendacoes(Alias, Usuario, PerfilAmigo, UsuarioAmigo,  Media, Data, Comentario) VALUES ('gabrielzinho' , 56055095009,'clarinha', 96917262030, 5 , DATE '2020-10-10', 'Melhor episodio que já vi' );

INSERT INTO LegendaMedia(Media, Idioma) VALUES (1,3);
INSERT INTO LegendaMedia(Media, Idioma) VALUES (2,3);
INSERT INTO LegendaMedia(Media, Idioma) VALUES (3,1);
INSERT INTO LegendaMedia(Media, Idioma) VALUES (4,2);
INSERT INTO LegendaMedia(Media, Idioma) VALUES (4,4);

INSERT INTO AudioMedia(Media, Idioma, Original) VALUES (1,5,3);
INSERT INTO AudioMedia(Media, Idioma, Original) VALUES (2,4,2);
INSERT INTO AudioMedia(Media, Idioma, Original) VALUES (3,3,4);
INSERT INTO AudioMedia(Media, Idioma, Original) VALUES (4,2,5);
INSERT INTO AudioMedia(Media, Idioma, Original) VALUES (5,1,1);

INSERT INTO Ator(CPF, Nome) VALUES (11367961009, 'Bill Delight');
INSERT INTO Ator(CPF, Nome) VALUES (04200905094, 'Aaron Goldman');
INSERT INTO Ator(CPF, Nome) VALUES (22713045045, 'Clarisse Motta');
INSERT INTO Ator(CPF, Nome) VALUES (48994941061, 'Audray Minardi');
INSERT INTO Ator(CPF, Nome) VALUES (89379668082, 'Sergio de Sá');

INSERT INTO Diretor(CPF, Nome) VALUES (66315064094, 'Andrew Ortner');
INSERT INTO Diretor(CPF, Nome) VALUES (97942809099,'David Benioff');
INSERT INTO Diretor(CPF, Nome) VALUES (47170927080, 'David Crane');
INSERT INTO Diretor(CPF, Nome) VALUES (69670235006, 'Barney Reisz');
INSERT INTO Diretor(CPF, Nome) VALUES (62856797032, 'Neal Marlens');

INSERT INTO Atuacoes(CPF, Media) VALUES (11367961009,1);
INSERT INTO Atuacoes(CPF, Media) VALUES (04200905094,2);
INSERT INTO Atuacoes(CPF, Media) VALUES (22713045045,3);
INSERT INTO Atuacoes(CPF, Media) VALUES (48994941061,4);
INSERT INTO Atuacoes(CPF, Media) VALUES (89379668082,5);

INSERT INTO Direcoes(CPF, Media, Cargo) VALUES (66315064094,1,'Diretor principal');
INSERT INTO Direcoes(CPF, Media, Cargo) VALUES (97942809099,2, 'Diretor de fotografia');
INSERT INTO Direcoes(CPF, Media, Cargo) VALUES (47170927080,3, 'Diretor de Som');
INSERT INTO Direcoes(CPF, Media, Cargo) VALUES (69670235006,4, 'Diretor Geral');
INSERT INTO Direcoes(CPF, Media, Cargo) VALUES (62856797032,5, 'Diretor de animação');

INSERT INTO Generos(Nome, Descricao) VALUES ('Ação','envolve uma história de protagonistas contra antagonistas');
INSERT INTO Generos(Nome, Descricao) VALUES ('Suspense', 'criar uma sensação de expectativa no público,');
INSERT INTO Generos(Nome, Descricao) VALUES ('Romance','envolve o relacionamento afetivo entre pessoas');
INSERT INTO Generos(Nome, Descricao) VALUES ('Aventura','narrativa de aventura composta das ações das personagens');
INSERT INTO Generos(Nome, Descricao) VALUES ('Ficção', 'arrativa imaginária, irreal ');

INSERT INTO GenerosMedia(Genero, Media) VALUES ('Ação',1);
INSERT INTO GenerosMedia(Genero, Media) VALUES ('Suspense', 2);
INSERT INTO GenerosMedia(Genero, Media) VALUES ('Romance', 3);
INSERT INTO GenerosMedia(Genero, Media) VALUES ('Aventura', 4);
INSERT INTO GenerosMedia(Genero, Media) VALUES ('Ficção', 5);

INSERT INTO PreferenciaGenero(Alias, Usuario, Genero, Nota)VALUES ('Eduardo',96917262030,'Ação',9) ;
INSERT INTO PreferenciaGenero(Alias, Usuario, Genero, Nota)VALUES ('Eduardo',96917262030, 'Ficção', 8);
INSERT INTO PreferenciaGenero(Alias, Usuario, Genero, Nota)VALUES ('Michelle', 20372442072, 'Romance', 10);
INSERT INTO PreferenciaGenero(Alias, Usuario, Genero, Nota)VALUES ('joaozinho', 56055095009, 'Aventura', 10);
INSERT INTO PreferenciaGenero(Alias, Usuario, Genero, Nota)VALUES ('Lucas',56055095009, 'Suspense', 9);

INSERT INTO Exibicao(Alias, Usuario, Media, Data, TempoAssistido) VALUES ('Eduardo',96917262030,1,TO_DATE('20/12/2020', 'dd/mm/yyyy'),30);
INSERT INTO Exibicao(Alias, Usuario, Media, Data, TempoAssistido) VALUES ('Michelle', 20372442072, 2,TO_DATE('23/11/2020', 'dd/mm/yyyy'),20 );
INSERT INTO Exibicao(Alias, Usuario, Media, Data, TempoAssistido) VALUES ('joaozinho', 56055095009, 3,TO_DATE('15/10/2020', 'dd/mm/yyyy'),45 );
INSERT INTO Exibicao(Alias, Usuario, Media, Data, TempoAssistido) VALUES ('Lucas',56055095009,4,TO_DATE('07/09/2020', 'dd/mm/yyyy'),50 );
INSERT INTO Exibicao(Alias, Usuario, Media, Data, TempoAssistido) VALUES ('julinha', 96917262030, 5 ,TO_DATE('01/08/2020', 'dd/mm/yyyy'),13);

INSERT INTO Avaliacao(Alias, Usuario, Media, Data, Nota) VALUES ('Eduardo',96917262030,1,TO_DATE('20/12/2020', 'dd/mm/yyyy'),80);
INSERT INTO Avaliacao(Alias, Usuario, Media, Data, Nota) VALUES ('Michelle', 20372442072, 2,TO_DATE('23/11/2020', 'dd/mm/yyyy'),55);
INSERT INTO Avaliacao(Alias, Usuario, Media, Data, Nota) VALUES ('joaozinho', 56055095009, 3,TO_DATE('15/10/2020', 'dd/mm/yyyy'),89);
INSERT INTO Avaliacao(Alias, Usuario, Media, Data, Nota) VALUES ('Lucas',56055095009,4,TO_DATE('07/09/2020', 'dd/mm/yyyy'),32);
INSERT INTO Avaliacao(Alias, Usuario, Media, Data, Nota) VALUES ('julinha', 96917262030, 5 ,TO_DATE('01/08/2020', 'dd/mm/yyyy'),49);

INSERT INTO Opiniao(Alias, Usuario, Media, Data, Texto) VALUES ('Eduardo',96917262030,1,TO_DATE('20/12/2020', 'dd/mm/yyyy'),'Muito divertido e empolgante');
INSERT INTO Opiniao(Alias, Usuario, Media, Data, Texto) VALUES ('Michelle', 20372442072, 2,TO_DATE('23/11/2020', 'dd/mm/yyyy'),'bem mais ou menos, pode melhorar');
INSERT INTO Opiniao(Alias, Usuario, Media, Data, Texto) VALUES ('Carla', 23276743019, 3,TO_DATE('15/10/2020', 'dd/mm/yyyy'), 'Bom demais, adorei o protagonista');
INSERT INTO Opiniao(Alias, Usuario, Media, Data, Texto) VALUES ('Lucas',56055095009,4,TO_DATE('07/09/2020', 'dd/mm/yyyy'), 'Bem ruim, não vou assistir mais');
INSERT INTO Opiniao(Alias, Usuario, Media, Data, Texto) VALUES ('Diego', 74338892056, 5 ,TO_DATE('01/08/2020', 'dd/mm/yyyy'), 'Nunca mais quero ouvir falar sobre');
