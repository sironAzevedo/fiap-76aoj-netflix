CREATE TABLE IF NOT EXISTS tb_movie
(
    ID           serial NOT NULL,
    TITLE        VARCHAR (256) NOT NULL,
    SUMMARY      VARCHAR (256) NOT NULL,
    RELEASE_DATE DATE NOT NULL,
    CONSTRAINT pk_movie primary key (ID)
);

CREATE TABLE IF NOT EXISTS tb_movie_category
(
    id_movie    BIGINT NOT NULL,
    id_category BIGINT NOT NULL,

	CONSTRAINT pk_movie_category primary key (id_movie, id_category),
    CONSTRAINT fk1_movie_category FOREIGN KEY (id_movie) REFERENCES tb_movie (ID)
);

CREATE TABLE IF NOT EXISTS tb_movie_keyword
(
	ID serial NOT NULL,
	id_movie BIGINT NOT NULL,
	key_word VARCHAR (256) NOT NULL,
	
	CONSTRAINT pk_movie_keyword primary key (ID),
	CONSTRAINT fk1_movie_keyword FOREIGN KEY (id_movie) REFERENCES tb_movie (ID)
);

CREATE TABLE IF NOT EXISTS tb_movie_watched
(
	id_user      BIGINT NOT NULL,
	id_movie     BIGINT NOT NULL,
	date_watched DATE NOT NULL,
	
	CONSTRAINT pk_movie_watched primary key (id_user, id_movie),
	CONSTRAINT fk1_movie_watched FOREIGN KEY (id_movie) REFERENCES tb_movie (ID)
);
