//Use first query below to create a database on a new system
CREATE DATABASE two_tables
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_as_cs;

CREATE TABLE employees(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    full_name VARCHAR(70) NOT NULL,
    team INT,
    gender INT
    );
CREATE TABLE tasks(
    employee_id INT PRIMARY KEY,
    instructions VARCHAR(100),
    CONSTRAINT empid_tasksfk_employees
		FOREIGN KEY (employee_id)
        REFERENCES employees(id)
    );
CREATE TABLE teams(
	team_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(105)
    );
CREATE TABLE salaries(
	employee_id INT PRIMARY KEY,
    package FLOAT,
    payment_status VARCHAR(20) NOT NULL DEFAULT "Pending",
    CONSTRAINT empid_salariesfk_employees
		FOREIGN KEY (employee_id)
        REFERENCES employees(id)
    );
CREATE TABLE ages(
	employee_id INT PRIMARY KEY,
    age int,	
    CONSTRAINT empid_agesfk_employees
		FOREIGN KEY (employee_id)
        REFERENCES employees(id)
    );
CREATE TABLE genders(
	id INT PRIMARY KEY AUTO_INCREMENT,
	gname VARCHAR(100)
    );
CREATE TABLE users(
	username VARCHAR(20) PRIMARY KEY,
	user_password VARCHAR(120) NOT NULL,
	email VARCHAR(40) NOT NULL,
	employee_id INT NOT NULL,
	CONSTRAINT empid_usersfk_employees
		FOREIGN KEY (employee_id)
		REFERENCES employees(id),
	CONSTRAINT email_usersuk
		UNIQUE (email),
	CONSTRAINT empid_users_uk
		UNIQUE (employee_id)
	);
CREATE TABLE roles(
	id INT PRIMARY KEY AUTO_INCREMENT,
	role_name VARCHAR(15),
	CONSTRAINT role_rolesuk
		UNIQUE (role_name)
	);
CREATE TABLE user_roles(
	username VARCHAR(20) NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY(username,role_id),
	CONSTRAINT username_urolesfk_users
		FOREIGN KEY (username)
		REFERENCES users(username),
	CONSTRAINT roleid_urolesfk_roles
		FOREIGN KEY (role_id)
		REFERENCES roles(id)
	);