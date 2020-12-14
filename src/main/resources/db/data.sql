DELETE
FROM user_roles;
DELETE
FROM dish;
DELETE
FROM vote;
DELETE
FROM restaurant;
DELETE
FROM users;

INSERT INTO users (id, name, email, password)
VALUES (0, 'Admin', 'admin@gmail.com', '{noop}adminpass'),
       (1, 'UserOne', 'userone@yandex.ru', '{noop}useronepass'),
       (2, 'UserTwo', 'usertwo@yandex.ru', '{noop}usertwoopass');

INSERT INTO user_roles (user_id, role)
VALUES (0, 'ADMIN'),
       (0, 'USER'),
       (1, 'USER'),
       (2, 'USER');


INSERT INTO restaurant (id, name)
VALUES (0, 'MacDonalds'),
       (1, 'KFC'),
       (2, 'Burger King');

INSERT INTO dish (id, date_time, name, price, restaurant_id)
VALUES (0, today(), 'Bigmac', 300, 0),
       (1, today(), 'Coca cola', 100, 0),
       (2, today(), 'Chicken nuggets', 150, 1),
       (3, today(), 'Chicken wings', 270, 1),
       (4, '2020-11-18', 'Cheeseburger', 110, 2),
       (5, '2020-11-18', 'Double cheeseburger', 150, 2);

INSERT INTO vote (id, user_id, restaurant_id, date_time)
VALUES (0, 0, 1,today()),
       (1, 1, 2, '2020-11-24');
