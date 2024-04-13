INSERT INTO users VALUES ('keith', '{noop}keithpw');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_ADMIN');

INSERT INTO users VALUES ('john', '{noop}johnpw');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_ADMIN');

INSERT INTO users VALUES ('mary', '{noop}marypw');
INSERT INTO user_roles(username, role) VALUES ('mary', 'ROLE_USER');

INSERT INTO books (author, availability, description, name, price) VALUES ('Scott E', 10, 'The Model Thinker by Scott E. Page is a guide to thinking about complex problems. It offers practical tools for modeling and analyzing social, economic or political phenomena, and provides insights on how to make better decisions by understanding the world in a more nuanced way.', 'The Model Thinker', 174);
INSERT INTO books (author, availability, description, name, price) VALUES ('F. Scott Fitzgerald', 5, 'The Model Thinker by Scott E. Page is a guide to thinking about complex problems. It offers practical tools for modeling and analyzing social, economic or political phenomena, and provides insights on how to make better decisions by understanding the world in a more nuanced way.', 'The Great Gatsby', 9);
INSERT INTO books (author, availability, description, name, price) VALUES ('Jane Austen', 20, 'A beloved English classic, following the lives and loves of the Bennet sisters in 19th century England.', 'Pride and Prejudice', 8.9);
INSERT INTO books (author, availability, description, name, price) VALUES ('George Orwell', 20, 'A dystopian novel depicting a totalitarian society where individualism and freedom are suppressed.', '1984', 7.9);
INSERT INTO books (author, availability, description, name, price) VALUES ('Paulo Coelho', 10, 'A philosophical and inspirational tale about a young shepherd following his dreams and discovering the meaning of life.', 'The Alchemist', 11.9);

INSERT INTO comments (USERNAME, CONTENT, BOOK_ID) VALUES ('mickey', 'Good Book', 1);
INSERT INTO comments (USERNAME, CONTENT, BOOK_ID) VALUES ('yelo', 'Bad Book', 1);
