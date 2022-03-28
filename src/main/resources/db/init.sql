DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts
(
    did  integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username varchar(40) NOT NULL UNIQUE,
    password varchar(40) NOT NULL,
    email varchar(150) NOT NULL UNIQUE
);

INSERT INTO accounts (username, password, email) VALUES ('admin', 'admin', 'admin@admin.de');
