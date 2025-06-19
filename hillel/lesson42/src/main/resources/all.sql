CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL
);


CREATE TABLE posts (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       content TEXT,
                       user_id INTEGER NOT NULL,
                       CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);