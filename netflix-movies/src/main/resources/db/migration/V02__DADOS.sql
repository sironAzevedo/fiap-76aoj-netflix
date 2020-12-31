
--INSERT MOVIE
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('A Hora do Rush 2', 'Quando a embaixada dos EUA em Hong Kong é atacada, o inspetor Lee e o detetive Carter unem forças contra um sindicato internacional do crime.', '2001-07-01');
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('Heróis', 'Dois jovens, ele com poderes telecinéticos e ela clarividente, unem-se para encontrar um terceiro paranormal e destruir a sombria agência chamada A Divisão.', '2009-07-01');
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('Nosso Último Verão', 'Estudantes de uma escola de Chicago encaram sonhos, relacionamentos e questões de identidade durante um verão transformador antes do começo da faculdade.', '2019-01-01');


--INSERT MOVIE_CATEGORIA
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (1, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (1, 2);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (2, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (3, 6);