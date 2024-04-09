INSERT INTO users VALUES ('keith', '{noop}keithpw');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_ADMIN');

INSERT INTO users VALUES ('john', '{noop}johnpw');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_ADMIN');

INSERT INTO users VALUES ('mary', '{noop}marypw');
INSERT INTO user_roles(username, role) VALUES ('mary', 'ROLE_USER');

INSERT INTO books (name, author, price, description, available)
VALUES ('The Model Thinker', 'Scott E', 174.00, 'Description of The Model Thinker', TRUE);

INSERT INTO comments (book_id, username, comment_text)
VALUES (1, 'keith', 'This book is great!');

INSERT INTO comments (book_id, username, comment_text)
VALUES (1, 'john', 'I enjoyed reading this book.');

INSERT INTO comments (book_id, username, comment_text)
VALUES (1, 'mary', 'Highly recommend this book.');