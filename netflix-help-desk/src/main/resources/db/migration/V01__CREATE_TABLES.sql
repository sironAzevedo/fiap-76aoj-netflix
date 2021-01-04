CREATE TABLE IF NOT EXISTS tb_status_help_desk
(
	ID serial NOT NULL,
	description  VARCHAR (256) NOT NULL,
	CONSTRAINT pk_status_help_desk primary key (ID)
);

CREATE TABLE IF NOT EXISTS tb_help_desk
(
    ID           serial NOT NULL,
    id_user      BIGINT NOT NULL,
    description  VARCHAR (256) NOT NULL,
    start_date   DATE NOT NULL,
    end_date     DATE,
    id_status    BIGINT NOT NULL,
    CONSTRAINT pk_help_desk primary key (ID),
    CONSTRAINT fk1_help_desk FOREIGN KEY (id_status) REFERENCES tb_status_help_desk (ID)
);