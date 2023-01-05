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
	('tom@tom.com', 'tomspw', false),
	('mark@tom.com', 'markspw', false);

CREATE TABLE claims (
id SERIAL PRIMARY KEY,
amount INTEGER,
description VARCHAR(500),
status VARCHAR(8),
created_by VARCHAR(100),
pending BOOLEAN
);

INSERT INTO claims (
id,
amount,
description,
status,
created_by,
pending
) VALUES (
1,
5400,
'broken leg',
'pending',
'tom@tom.com',
true
),
(
2,
7800,
'surgery',
'pending',
'tom@tom.com',
true
);

CREATE TABLE covid (
id INTEGER references users(id),
has_covid BOOLEAN,
had_covid BOOLEAN,
date_updated DATE
);
