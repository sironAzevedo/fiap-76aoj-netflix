CREATE TABLE IF NOT EXISTS tb_serie
(
    ID           serial NOT NULL,
    TITLE        VARCHAR (256) NOT NULL,
    SUMMARY      VARCHAR (256) NOT NULL,
    RELEASE_DATE DATE NOT NULL,
    SEASON       BIGINT NOT NULL,
    CONSTRAINT pk_serie primary key (ID)
);

CREATE TABLE IF NOT EXISTS tb_serie_category
(
    id_serie    BIGINT NOT NULL,
    id_category BIGINT NOT NULL,

	CONSTRAINT pk_serie_category primary key (id_serie, id_category),
    CONSTRAINT fk1_serie_category FOREIGN KEY (id_serie) REFERENCES tb_serie (ID)
);

CREATE TABLE IF NOT EXISTS tb_serie_keyword
(
	ID serial NOT NULL,
	id_serie BIGINT NOT NULL,
	key_word VARCHAR (256) NOT NULL,
	
	CONSTRAINT pk_serie_keyword primary key (ID),
	CONSTRAINT fk1_serie_keyword FOREIGN KEY (id_serie) REFERENCES tb_serie (ID)
);

CREATE TABLE IF NOT EXISTS tb_serie_watched
(
	id_user      BIGINT NOT NULL,
	id_serie     BIGINT NOT NULL,
	date_watched DATE NOT NULL,
	
	CONSTRAINT pk_serie_watched primary key (id_user, id_serie),
	CONSTRAINT fk1_serie_watched FOREIGN KEY (id_serie) REFERENCES tb_serie (ID)
);

CREATE TABLE IF NOT EXISTS tb_serie_like
(
	id_user  BIGINT NOT NULL,
	id_serie BIGINT NOT NULL,
	liked    VARCHAR (3) NOT NULL,
	
	CONSTRAINT pk_serie_like primary key (id_user, id_serie),
	CONSTRAINT fk1_serie_like FOREIGN KEY (id_serie) REFERENCES tb_serie (ID)
);

CREATE TABLE IF NOT EXISTS tb_serie_watch_future
(
	id_user  BIGINT NOT NULL,
	id_serie BIGINT NOT NULL,
	
	CONSTRAINT pk_serie_watch_future primary key (id_user, id_serie),
	CONSTRAINT fk1_serie_watch_future FOREIGN KEY (id_serie) REFERENCES tb_serie (ID)
);
