
INSERT INTO tb_user (NAME, EMAIL, PASSWORD, STATUS) VALUES ('Siron Azevedo', 'siron@fiap.com', '$2a$10$vlUpoD3T8/mIaCbE5hDNeOJM1Vrbv8KqHDNPgpoqNUZ9CmeSVPTUm', 'ACTIVE');

INSERT INTO tb_role (NAME) VALUES ('ADMIN');
INSERT INTO tb_role (NAME) VALUES ('USER');

INSERT INTO tb_user_role VALUES (1, 1);