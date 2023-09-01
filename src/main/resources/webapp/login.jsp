DROP TABLE IF EXISTS event_user;
DROP TABLE IF EXISTS authorities;

CREATE TABLE [IF NOT EXISTS] event_user (
    id BIGINT PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    username VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR (255) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
);

CREATE TABLE [IF NOT EXISTS] authorities (
    user_id BIGINT UNIQUE NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
};