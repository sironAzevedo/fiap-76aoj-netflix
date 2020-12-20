
--INSERT MOVIE
INSERT INTO tb_movie (TITLE, SUMMARY, RELEASE_DATE) VALUES ('A Hora do Rush 2', 'Quando a embaixada dos EUA em Hong Kong é atacada, o inspetor Lee e o detetive Carter unem forças contra um sindicato internacional do crime.', '2001-07-01');

--INSERT CATEGORIES
INSERT INTO tb_category (`name`) VALUES ('Ação');
INSERT INTO tb_category (`name`) VALUES ('Aventura');
INSERT INTO tb_category (`name`) VALUES ('Cinema de arte');
INSERT INTO tb_category (`name`) VALUES ('Chanchada');
INSERT INTO tb_category (`name`) VALUES ('Cinema catástrofe');
INSERT INTO tb_category (`name`) VALUES ('Comédia');
INSERT INTO tb_category (`name`) VALUES ('Comédia romântica');
INSERT INTO tb_category (`name`) VALUES ('Comédia dramática');
INSERT INTO tb_category (`name`) VALUES ('Comédia de ação');
INSERT INTO tb_category (`name`) VALUES ('Dança');
INSERT INTO tb_category (`name`) VALUES ('Documentário');
INSERT INTO tb_category (`name`) VALUES ('Docuficção');
INSERT INTO tb_category (`name`) VALUES ('Drama');
INSERT INTO tb_category (`name`) VALUES ('Espionagem');
INSERT INTO tb_category (`name`) VALUES ('Faroeste');
INSERT INTO tb_category (`name`) VALUES ('Fantasia científica');
INSERT INTO tb_category (`name`) VALUES ('Ficção científica');
INSERT INTO tb_category (`name`) VALUES ('Filmes de guerra');
INSERT INTO tb_category (`name`) VALUES ('Musical');
INSERT INTO tb_category (`name`) VALUES ('Filme policial');
INSERT INTO tb_category (`name`) VALUES ('Romance');
INSERT INTO tb_category (`name`) VALUES ('Seriado');
INSERT INTO tb_category (`name`) VALUES ('Suspense');
INSERT INTO tb_category (`name`) VALUES ('Terror');

INSERT INTO tb_movie_category (id_movie, id_category) VALUES (1, 1);
INSERT INTO tb_movie_category (id_movie, id_category) VALUES (1, 2);