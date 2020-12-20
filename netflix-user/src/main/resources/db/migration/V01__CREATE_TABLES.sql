CREATE SCHEMA IF NOT EXISTS PUBLIC;

CREATE TABLE tb_user
(
    ID           BIGINT  (20) NOT NULL AUTO_INCREMENT,
    NAME         VARCHAR (256) NOT NULL,
    EMAIL        VARCHAR (256) NOT NULL,
    PASSWORD     VARCHAR (256) NOT NULL,
    STATUS	     VARCHAR (256) NOT NULL,
    UNIQUE(EMAIL),    
    CONSTRAINT pk_user primary key (ID)
);

CREATE TABLE tb_role
(
    ID   BIGINT  (20) NOT NULL AUTO_INCREMENT,
    NAME VARCHAR (256) NOT NULL,
    CONSTRAINT pk_role primary key (ID)
);


CREATE TABLE tb_user_role
(
    id_user BIGINT (20) NOT NULL,
    id_role BIGINT (20) NOT NULL,

	CONSTRAINT pk_user_role primary key (id_user, id_role),
    CONSTRAINT fk1_user_role FOREIGN KEY (id_user) REFERENCES tb_user (ID),
    CONSTRAINT fk2_user_role FOREIGN KEY (id_role) REFERENCES tb_role (ID)
);