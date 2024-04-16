INSERT INTO users (username, password, full_name, email, address) VALUES
('mickey', '{noop}password', 'John Doe', 'john.doe@example.com', '123 Main St, Anytown USA'),
('yelo', '{noop}password', 'Jane Smith', 'jane.smith@example.com', '456 Oak Rd, Somewhere City'),
('nicky', '{noop}password', 'Bob Johnson', 'bob.johnson@example.com', '789 Elm St, Elsewhere Town');

INSERT INTO cart (username) VALUES ('mickey');
INSERT INTO cart (username) VALUES ('yelo');
INSERT INTO cart (username) VALUES ('nicky');

INSERT INTO favourite (username) VALUES ('mickey');
INSERT INTO favourite (username) VALUES ('yelo');
INSERT INTO favourite (username) VALUES ('nicky');

INSERT INTO user_roles(username, role) VALUES ('mickey', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('mickey', 'ROLE_ADMIN');

INSERT INTO user_roles(username, role) VALUES ('yelo', 'ROLE_ADMIN');
INSERT INTO user_roles(username, role) VALUES ('yelo', 'ROLE_USER');

INSERT INTO user_roles(username, role) VALUES ('nicky', 'ROLE_USER');

INSERT INTO book (author, availability, description, name, price) VALUES ('Scott E', 10, 'The Model Thinker by Scott E. Page is a guide to thinking about complex problems. It offers practical tools for modeling and analyzing social, economic or political phenomena, and provides insights on how to make better decisions by understanding the world in a more nuanced way.', 'The Model Thinker', 174);
INSERT INTO book (author, availability, description, name, price) VALUES ('F. Scott Fitzgerald', 5, 'The Model Thinker by Scott E. Page is a guide to thinking about complex problems. It offers practical tools for modeling and analyzing social, economic or political phenomena, and provides insights on how to make better decisions by understanding the world in a more nuanced way.', 'The Great Gatsby', 9);
INSERT INTO book (author, availability, description, name, price) VALUES ('Jane Austen', 20, 'A beloved English classic, following the lives and loves of the Bennet sisters in 19th century England.', 'Pride and Prejudice', 8.9);
INSERT INTO book (author, availability, description, name, price) VALUES ('George Orwell', 20, 'A dystopian novel depicting a totalitarian society where individualism and freedom are suppressed.', '1984', 7.9);
INSERT INTO book (author, availability, description, name, price) VALUES ('Paulo Coelho', 10, 'A philosophical and inspirational tale about a young shepherd following his dreams and discovering the meaning of life.', 'The Alchemist', 11.9);

INSERT INTO comment (USERNAME, CONTENT, BOOK_ID) VALUES ('mickey', 'Good Book', 1);
INSERT INTO comment (USERNAME, CONTENT, BOOK_ID) VALUES ('yelo', 'Bad Book', 1);

INSERT INTO BOOK_ITEM (CART_ID,BOOK_ID,QUANTITY) VALUES (1,1,10);
INSERT INTO BOOK_ITEM (CART_ID,BOOK_ID,QUANTITY) VALUES (1,2,1);