DELETE
FROM user_roles;
DELETE
FROM dishes;
DELETE
FROM vote_restaurant;
DELETE
FROM restaurant;
DELETE
FROM users;

INSERT INTO users (id, name, email, password)
VALUES (0, 'Admin', 'admin@gmail.com', '{noop}adminpass'),
       (1, 'UserOne', 'userone@yandex.ru', '{noop}useronepass'),
       (2, 'UserTwo', 'usertwo@yandex.ru', '{noop}usertwoopass');

INSERT INTO user_roles (user_id, role)
VALUES (0, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');


INSERT INTO restaurant (id, name)
VALUES (0, 'Mac'),
       (1, 'Pelmeni'),
       (2, 'SushiVesla');

INSERT INTO dishes (id, date, name, price, restaurant_id)
VALUES (0, today(), 'bigmac', 300, 0),
       (1, today(), 'Coca cola', 100, 0),
       (2, today(), 'fries', 100, 0),
       (3, '2020-10-25', 'Pelmeni', 400, 1),
       (4, '2020-10-25', 'Sushi', 500, 2);

INSERT INTO vote_restaurant (id, date, restaurant_id, user_id)
VALUES (0, '2020-10-25', 0, 1),
       (1, '2017-05-20', 1, 2);
