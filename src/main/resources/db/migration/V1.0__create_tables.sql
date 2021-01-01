CREATE TABLE users(
    id          BIGINT IDENTITY PRIMARY KEY,
    username    VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    enabled     BOOLEAN NOT NULL
);

CREATE TABLE user_authorities(
    id        BIGINT IDENTITY PRIMARY KEY,
    authority VARCHAR(100) NOT NULL,
    user_id   BIGINT       NOT NULL
);

ALTER TABLE user_authorities ADD FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

INSERT INTO users(id,username,password,enabled) VALUES (1,'jadams','$2a$10$l5Ol5a37XdvFrIFQZ0Q3F.QEzxQrOz/mD4AtEwgksOYW5tdbnrt.e',true);

