/*
DELETE FROM USER_ROLES;
DELETE FROM USERS;

INSERT INTO USERS (id, name, email, password)
VALUES (0, 'User', 'user@yandex.ru', 'password'),
       (1, 'Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);*/
