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
VALUES (100000, 'Admin', 'admin@gmail.com', 'adminpass'),
       (100001, 'UserOne', 'userone@yandex.ru', 'useronepass'),
       (100002, 'UserTwo', 'usertwo@yandex.ru', 'usertwoopass');

INSERT INTO USER_ROLES (user_id, role)
VALUES (100000, 'ROLE_ADMIN'),
       (100001, 'ROLE_USER'),
       (100002, 'ROLE_USER');

INSERT INTO RESTAURANT (id, name)
VALUES (100000, 'Mac'),
       (100001, 'Pelmeni'),
       (100002, 'SushiVesla');

INSERT INTO LUNCH (id, lunch_date, name, price, restaurant_id)
VALUES (100000, today(), 'Макчикен', 300, 100000),
       (100001, today(), 'Кола', 100, 100000),
       (100002, today(), 'Картошка фри', 100, 100000),
       (100003, '2020-10-25', 'Пельмени', 400, 100001),
       (100004, '2020-10-25', 'Роллы', 500, 100002);

INSERT INTO VOTE_RESTAURANT (id, vote_date, restaurant_id, user_id)
VALUES (100000, '2020-10-25', 100000, 100001),
       (100001, '2017-05-20', 100001, 100002);
