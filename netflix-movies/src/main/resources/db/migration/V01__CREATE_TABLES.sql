CREATE SCHEMA IF NOT EXISTS PUBLIC;

CREATE TABLE tb_movie
(
    ID           BIGINT (20) NOT NULL AUTO_INCREMENT,
    TITLE        VARCHAR (256) NOT NULL,
    SUMMARY      VARCHAR (256) NOT NULL,
    RELEASE_DATE DATE NOT NULL,
    CONSTRAINT pk_movie primary key (ID)
);

CREATE TABLE tb_category
(
    ID    BIGINT(20) NOT NULL AUTO_INCREMENT,
    NAME  VARCHAR (256) NOT NULL,
    CONSTRAINT pk_category primary key (ID)
);

CREATE TABLE tb_movie_category
(
    id_movie    BIGINT (20) NOT NULL,
    id_category BIGINT (20) NOT NULL,

	CONSTRAINT pk_movie_category primary key (id_movie, id_category),
    CONSTRAINT fk1_movie_category FOREIGN KEY (id_movie) REFERENCES tb_movie (ID),
    CONSTRAINT fk2_movie_category FOREIGN KEY (id_category) REFERENCES tb_category (ID)
);