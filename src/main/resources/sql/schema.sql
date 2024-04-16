DROP TABLE IF EXISTS book_item CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS attachment CASCADE;
DROP TABLE IF EXISTS comment CASCADE;
CREATE TABLE IF NOT EXISTS users (
      username VARCHAR(255),
      password VARCHAR(255),
      full_name VARCHAR(255),
      email VARCHAR(255),
      address VARCHAR(255),
      PRIMARY KEY (username)
);
CREATE TABLE IF NOT EXISTS user_roles (
    user_role_id INTEGER GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);
CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(500),
    availability INTEGER
);
CREATE TABLE IF NOT EXISTS attachment (
    id uuid default random_uuid() not null,
    content blob,
    content_type varchar(255),
    filename varchar(255),
    book_id INT,
    primary key (id),
    foreign key (book_id) references book(id)
);
CREATE TABLE IF NOT EXISTS comment (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     username VARCHAR(255),
     content TEXT,
     book_id BIGINT,
     CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE IF NOT EXISTS cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(255) DEFAULT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE  IF NOT EXISTS book_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    cart_id INT,
    quantity INTEGER,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (cart_id) REFERENCES cart (id)
);

CREATE TABLE  IF NOT EXISTS favourite (
   id INT AUTO_INCREMENT PRIMARY KEY,
   username varchar(255) DEFAULT NULL,
   FOREIGN KEY (username) REFERENCES users (username)
);