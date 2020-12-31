CREATE TABLE IF NOT EXISTS tb_category
(
    ID    serial NOT NULL,
    NAME  VARCHAR (256) NOT NULL,
    CONSTRAINT pk_category primary key (ID)
);