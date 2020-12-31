CREATE TABLE IF NOT EXISTS tb_user
(
    ID           serial NOT NULL,
    NAME         VARCHAR (256) NOT NULL,
    EMAIL        VARCHAR (256) NOT NULL,
    PASSWORD     VARCHAR (256) NOT NULL,
    STATUS	     VARCHAR (256) NOT NULL,
    UNIQUE(EMAIL),    
    CONSTRAINT pk_user primary key (ID)
);

CREATE TABLE IF NOT EXISTS tb_role
(
    ID   serial NOT NULL,
    NAME VARCHAR (256) NOT NULL,
    CONSTRAINT pk_role primary key (ID)
);


CREATE TABLE IF NOT EXISTS tb_user_role
(
    id_user BIGINT NOT NULL,
    id_role BIGINT NOT NULL,

	CONSTRAINT pk_user_role primary key (id_user, id_role),
    CONSTRAINT fk1_user_role FOREIGN KEY (id_user) REFERENCES tb_user (ID),
    CONSTRAINT fk2_user_role FOREIGN KEY (id_role) REFERENCES tb_role (ID)
);