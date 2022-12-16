# project-2-Alchemy-backend

## Description

<!-- description here -->

## Created by

<!-- info here -->

## Setting up the app

- Create `.env` file in root directory
- Copy [environment variables](#environment-variables) with your DB port, DB name, and port needed
- Create PostgreSQL database (Docker recommended)
- Use SQL statements inside `project2.sql` to create tables and add test users

<!-- content here -->

## Running the app

<!-- content here -->

## Environment variables

**DB_PORT** - Database port - Defaults to 5433
**DB_NAME** - Database name - Defaults to postgres
**PORT** - Javalin port - Default 8083

## Models

**Users**
id
username
password
is_worker (true | **false**)

**Claims**
id
amount
description
status
created_by (REFERENCES **user.id**)
pending

**Covid**
user_id (REFERENCES **user.id**)
has_covid
had_covid
date_updated

## API Routes

| Method   | Path       | Description                              |
| -------- | ---------- | ---------------------------------------- |
|          | **LOGIN**  |                                          |
| **POST** | /login     | Login user with username and password    |
| **GET**  | /logout    | Logout user                              |
| **POST** | /register  | Register user with username and password |
|          | **CLAIMS** |                                          |
|          |            |                                          |
|          | **USER**   |                                          |

<!-- routes here -->

## Web Routes

| Method  | Path      | Description                                                 |
| ------- | --------- | ----------------------------------------------------------- |
| **GET** | /         | Home page (shows claims if logged in, login page otherwise) |
| **GET** | /login    | Login page                                                  |
| **GET** | /register | Register page                                               |
| **GET** | /logout   | Logout page                                                 |

<!-- routes here -->
