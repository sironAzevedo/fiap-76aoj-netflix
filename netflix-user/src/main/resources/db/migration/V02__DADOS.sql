INSERT INTO tb_user (NAME, EMAIL, PASSWORD, STATUS) VALUES ('Siron Azevedo', 'siron@fiap.com', '$2a$10$z6wj8DpUw4mK3QFWJLi97e4YLsS8G/okSzRwMZzA9IudWJjibc4By', 'ACTIVE');
INSERT INTO tb_user (NAME, EMAIL, PASSWORD, STATUS) VALUES ('Anadson Passos', 'anadson@fiap.com', '$2a$10$z6wj8DpUw4mK3QFWJLi97e4YLsS8G/okSzRwMZzA9IudWJjibc4By', 'ACTIVE');
INSERT INTO tb_user (NAME, EMAIL, PASSWORD, STATUS) VALUES ('Jhonatan Soares', 'jhonatan@fiap.com', '$2a$10$z6wj8DpUw4mK3QFWJLi97e4YLsS8G/okSzRwMZzA9IudWJjibc4By', 'ACTIVE');
INSERT INTO tb_user (NAME, EMAIL, PASSWORD, STATUS) VALUES ('Nataly Ivonete', 'nataly@fiap.com', '$2a$10$z6wj8DpUw4mK3QFWJLi97e4YLsS8G/okSzRwMZzA9IudWJjibc4By', 'ACTIVE');
INSERT INTO tb_user (NAME, EMAIL, PASSWORD, STATUS) VALUES ('Cliente da Silva', 'cliente@email.com', '$2a$10$ySagFsDGnfybO.6uoihZr.ffvU904wbn6KaoojBXDhR0owV.y7h2.', 'ACTIVE');

INSERT INTO tb_role (NAME) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (NAME) VALUES ('ROLE_USER');

INSERT INTO tb_user_role VALUES (1, 1);
INSERT INTO tb_user_role VALUES (2, 1);
INSERT INTO tb_user_role VALUES (3, 1);
INSERT INTO tb_user_role VALUES (4, 1);
INSERT INTO tb_user_role VALUES (5, 2);