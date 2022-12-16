-- FOR TESTING/DEBUGGING
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS claims;
DROP TABLE IF EXISTS covid;

CREATE TABLE users (
id SERIAL PRIMARY KEY,
username VARCHAR(500) UNIQUE,
PASSWORD VARCHAR(500),
is_worker BOOLEAN
);

INSERT INTO users (username, PASSWORD, is_worker) VALUES 
	('testuser', 'abc123', false),
	('test2', 'abc123', false);

CREATE TABLE claims (
id SERIAL PRIMARY KEY,
amount INTEGER,
description VARCHAR(500),
status VARCHAR(8),
created_by INTEGER references users(id),
pending BOOLEAN
);

CREATE TABLE covid (
id INTEGER references users(id),
has_covid BOOLEAN,
had_covid BOOLEAN,
date_updated DATE
);
