DELETE
FROM USER_ROLES;
DELETE
FROM USERS;
DELETE
FROM RESTAURANT;
DELETE
FROM LUNCH;
DELETE
FROM VOTE_RESTAURANT;

INSERT INTO USERS (id, name, email, password)
VALUES (0, 'Admin', 'admin@gmail.com', 'adminpass'),
       (1, 'UserOne', 'userone@yandex.ru', 'useronepass'),
       (2, 'UserTwo', 'usertwo@yandex.ru', 'usertwoopass');

INSERT INTO USER_ROLES (user_id, role)
VALUES (0, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');



INSERT INTO RESTAURANT (id, name)
VALUES (0, 'Mac'),
       (1, 'Pelmeni'),
       (2, 'SushiVesla');

INSERT INTO LUNCH (id, date, name, price, restaurant_id)
VALUES (0, today(), 'Макчикен', 300, 0),
       (1, today(), 'Кола', 100, 0),
       (2, today(), 'Картошка фри', 100, 0),
       (3, '2020-10-25', 'Пельмени', 400, 1),
       (4, '2020-10-25', 'Роллы', 500, 2);

INSERT INTO VOTE_RESTAURANT (id, vote_date, restaurant_id, user_id)
VALUES (0, '2020-10-25', 0, 1),
       (1, '2017-05-20', 1, 2);
