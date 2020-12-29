DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1000;

CREATE TABLE users
(
    id         BIGINT    DEFAULT global_seq.nextval PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL,
    CONSTRAINT unique_users_email_idx UNIQUE (email)
);


CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_unique_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE restaurant
(
    id   BIGINT DEFAULT global_seq.nextval PRIMARY KEY,
    name VARCHAR(255),
    CONSTRAINT unique_restaurant_name_idx UNIQUE (name)
);


CREATE TABLE dish
(
    id            BIGINT DEFAULT global_seq.nextval PRIMARY KEY,
    lunch_date    DATE    NOT NULL,
    name          VARCHAR(255),
    price         INTEGER NOT NULL,
    restaurant_id BIGINT  NOT NULL,
    CONSTRAINT dish_name_idx UNIQUE (restaurant_id, lunch_date, name),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            BIGINT DEFAULT global_seq.nextval PRIMARY KEY,
    vote_date     DATE   NOT NULL,
    restaurant_id BIGINT NOT NULL,
    user_id       BIGINT NOT NULL,
    CONSTRAINT user_vote_date_idx UNIQUE (user_id, vote_date),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);


