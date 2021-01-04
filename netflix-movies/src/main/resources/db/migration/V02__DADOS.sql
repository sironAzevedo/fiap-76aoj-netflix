
--INSERT MOVIE
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('A Hora do Rush 2', 'Quando a embaixada dos EUA em Hong Kong é atacada, o inspetor Lee e o detetive Carter unem forças contra um sindicato internacional do crime.', '2001-07-01');
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('Heróis', 'Dois jovens, ele com poderes telecinéticos e ela clarividente, unem-se para encontrar um terceiro paranormal e destruir a sombria agência chamada A Divisão.', '2009-07-01');
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('Nosso Último Verão', 'Estudantes de uma escola de Chicago encaram sonhos, relacionamentos e questões de identidade durante um verão transformador antes do começo da faculdade.', '2019-01-01');
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('Viagem 2 - A Ilha Misteriosa', 'Quando seu avô envia um pedido de socorro de uma ilha exótica, o adolescente Sean Anderson conta com um aliado improvável para salvá-lo: seu próprio padrasto.', '2012-01-01');
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('Baywatch', 'Para salvar uma praia, o salva-vidas Mitch Buchannon e um ex-campeão olímpico enfrentam um plano criminoso que ameaça o futuro do local.', '2017-01-01');

--INSERT MOVIE_CATEGORIA
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (1, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (1, 2);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (2, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (3, 6);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (4, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (4, 2);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (4, 17);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (5, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (5, 2);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (5, 6);

--INSERT MOVIE_KEYWORD
INSERT INTO tb_movie_keyword (id_movie, key_word) VALUES (1, 'Filmes de artes marciais');
INSERT INTO tb_movie_keyword (id_movie, key_word) VALUES (1, 'Comédia');
INSERT INTO tb_movie_keyword (id_movie, key_word) VALUES (1, 'Hong Kong');
INSERT INTO tb_movie_keyword (id_movie, key_word) VALUES (1, 'Jackie Chan');

INSERT INTO tb_movie_keyword (id_movie, key_word) VALUES (2, 'Fantasia');
INSERT INTO tb_movie_keyword (id_movie, key_word) VALUES (2, 'Ficção');

--INSERT MOVIE_WATCHED
INSERT INTO tb_movie_watched (id_user, id_movie, date_watched) VALUES (1, 1, '2020-12-01');
INSERT INTO tb_movie_watched (id_user, id_movie, date_watched) VALUES (2, 1, '2020-12-01');
INSERT INTO tb_movie_watched (id_user, id_movie, date_watched) VALUES (2, 2, '2020-12-01');

--INSERT MOVIE_LIKE
INSERT INTO tb_movie_like (id_user, id_movie, liked) VALUES (2, 1, 'YES');
INSERT INTO tb_movie_like (id_user, id_movie, liked) VALUES (2, 2, 'NO');

--INSERT MOVIE_WATCH_FUTURE
INSERT INTO tb_movie_watch_future (id_user, id_movie) VALUES (2, 4);