DROP TABLE  IF EXISTS comment;
DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
   username VARCHAR(50) NOT NULL,
   password VARCHAR(50) NOT NULL,
   PRIMARY KEY (username)
);
CREATE TABLE user_roles (
    user_role_id INTEGER GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(500),
    availability INTEGER
);
CREATE TABLE attachment (
    id uuid default random_uuid() not null,
    content blob,
    content_type varchar(255),
    filename varchar(255),
    book_id INT,
    primary key (id),
    foreign key (book_id) references book(id)
);
CREATE TABLE comment (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     username VARCHAR(255),
     content TEXT,
     book_id BIGINT,
     CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Book(id)
);