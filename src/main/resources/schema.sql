DROP TABLE IF EXISTS articles;
CREATE TABLE articles
(
    id           INTEGER      NOT NULL AUTO_INCREMENT,
    uuid         BINARY(16)   NOT NULL,
    published_at TIMESTAMP    NOT NULL,
    title        VARCHAR(100) NOT NULL,
    author       VARCHAR(100) NOT NULL,
    content      TEXT         NOT NULL,
    PRIMARY KEY (id)
);

-- accounts
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_authority ON authorities (username,authority);