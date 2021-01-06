
--INSERT SERIE
INSERT INTO tb_serie (TITLE, SUMMARY, RELEASE_DATE, SEASON) VALUES ('The 100', 'Quase 100 anos após um apocalipse nuclear na Terra, os sobreviventes de uma estação espacial precisam buscar recursos para garantir a sobrevivência.', '2014-01-01', 6);
INSERT INTO tb_serie (TITLE, SUMMARY, RELEASE_DATE, SEASON) VALUES ('O Mundo Sombrio de Sabrina', 'Bruxa e também mortal, a jovem Sabrina Spellman fica dividida entre a vida normal de adolescente e o legado de sua família feiticeira.', '2018-01-01', 4);
INSERT INTO tb_serie (TITLE, SUMMARY, RELEASE_DATE, SEASON) VALUES ('Breaking Bad', 'Ao saber que tem câncer, um professor passa a fabricar metanfetamina pelo futuro da família, mudando o destino de todos.', '2008-01-01', 5);
INSERT INTO tb_serie (TITLE, SUMMARY, RELEASE_DATE, SEASON) VALUES ('Narcos', 'A notória série sobre o tráfico é baseada na história real de violência e poder dos cartéis colombianos.', '2015-01-01', 3);

--INSERT SERIE_CATEGORIA
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (1, 24);
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (1, 17);
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (2, 24);
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (2, 17);
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (3, 13);
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (4, 1);
INSERT INTO tb_serie_category (id_serie, id_category) VALUES (4, 2);

--INSERT SERIE_KEYWORD
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (1, 'apocalipse');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (1, 'Terra');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (1, '100');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (1, 'arca');

INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (2, 'Magia');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (2, 'Halloween');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (2, 'trevas');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (2, 'Bruxa');

INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (3, 'metanfetamina');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (3, 'química');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (3, 'drogas');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (3, 'câncer');

INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (4, 'Pablo Escobar');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (4, 'Colômbia');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (4, 'cartel');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (4, 'tráfico');
INSERT INTO tb_serie_keyword (id_serie, key_word) VALUES (4, 'drogas');

--INSERT SERIE_WATCHED
INSERT INTO tb_serie_watched (id_user, id_serie, date_watched) VALUES (1, 1, '2020-12-01');
INSERT INTO tb_serie_watched (id_user, id_serie, date_watched) VALUES (2, 1, '2020-12-01');
INSERT INTO tb_serie_watched (id_user, id_serie, date_watched) VALUES (2, 2, '2020-12-01');

--INSERT SERIE_LIKE
INSERT INTO tb_serie_like (id_user, id_serie, liked) VALUES (2, 1, 'YES');
INSERT INTO tb_serie_like (id_user, id_serie, liked) VALUES (2, 2, 'NO');

--INSERT SERIE_WATCH_FUTURE
INSERT INTO tb_serie_watch_future (id_user, id_serie) VALUES (2, 3);