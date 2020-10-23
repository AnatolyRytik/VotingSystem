DROP TABLE IF EXISTS vote_restaurant;
DROP TABLE IF EXISTS lunch;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant;


CREATE TABLE users
(
    id       BIGINT               NOT NULL,
    name     VARCHAR(255)         NOT NULL,
    email    VARCHAR(255)         NOT NULL,
    password VARCHAR(255)         NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE NOT NULL,
    CONSTRAINT email_idx UNIQUE (email)
);
ALTER TABLE users
    ADD CONSTRAINT users_id PRIMARY KEY (id);


CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE restaurant
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT name_idx UNIQUE (name)
);
ALTER TABLE restaurant
    ADD CONSTRAINT restaurant_id PRIMARY KEY (id);


CREATE TABLE lunch
(
    id            BIGINT  NOT NULL,
    lunch_date    DATE    NOT NULL,
    name          VARCHAR(255),
    price         INTEGER NOT NULL,
    restaurant_id BIGINT  NOT NULL,
    CONSTRAINT restaurant_id_name_idx UNIQUE (restaurant_id, name),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
ALTER TABLE lunch
    ADD CONSTRAINT lunch_id PRIMARY KEY (id);

CREATE TABLE vote_restaurant
(
    id            BIGINT                  NOT NULL,
    vote_date     TIMESTAMP DEFAULT NOW() NOT NULL,
    restaurant_id BIGINT                  NOT NULL,
    user_id       BIGINT                  NOT NULL,
    CONSTRAINT USER_VOTE_DATE_IDX UNIQUE (user_id, vote_date),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
ALTER TABLE vote_restaurant
    ADD CONSTRAINT vote_restaurant_ID PRIMARY KEY (id);

