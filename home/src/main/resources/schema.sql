-- Use first query below to create a database on a new system
DROP DATABASE IF EXISTS two_tables;
CREATE DATABASE two_tables
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_as_cs;

USE two_tables;
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
	username VARCHAR(11) PRIMARY KEY,
	user_password VARCHAR(120) NOT NULL,
	email VARCHAR(40) NOT NULL,
	employee_id INT,
	CONSTRAINT empid_usersfk_employees
		FOREIGN KEY (employee_id)
		REFERENCES employees(id),
	CONSTRAINT email_usersuk
		UNIQUE (email),
	CONSTRAINT empid_usersuk
		UNIQUE (employee_id)
	);
CREATE TABLE roles(
	id INT PRIMARY KEY AUTO_INCREMENT,
	role_name VARCHAR(15),
	CONSTRAINT role_rolesuk
		UNIQUE (role_name)
	);
CREATE TABLE user_roles(
	username VARCHAR(11) NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY(username,role_id),
	CONSTRAINT empid_urolesfk_users
		FOREIGN KEY (username)
		REFERENCES users(username),
	CONSTRAINT roleid_urolesfk_roles
		FOREIGN KEY (role_id)
		REFERENCES roles(id)
	);
CREATE TABLE user_secrets(
	username VARCHAR(11) PRIMARY KEY,
	secret_question VARCHAR(100) NOT NULL,
	secret_answer VARCHAR(20) NOT NULL,
	CONSTRAINT username_usecretsfk_users
		FOREIGN KEY (username)
		REFERENCES users(username)
	);
CREATE TABLE incentive_types(
	id INT PRIMARY KEY,
	incentive_name VARCHAR(30) NOT NULL,
	CONSTRAINT incname_inctypesuk
		UNIQUE (incentive_name)
	);
CREATE TABLE incentives(
	employee_id INT,
	incentive_id INT NOT NULL,
	reason VARCHAR(100) NOT NULL,
	quantity INT NOT NULL,
	cost INT NOT NULL,
	amount INT NOT NULL,
	CONSTRAINT empid_incfk_users
		FOREIGN KEY (employee_id)
		REFERENCES employees(id),
	CONSTRAINT incid_incfk_inctypes
		FOREIGN KEY (incentive_id)
		REFERENCES incentive_types(id)
	);
CREATE TABLE employee_incentives(
	employee_id INT PRIMARY KEY,
	total_incentives BIGINT NOT NULL DEFAULT 0,
	CONSTRAINT empid_empincfk_users
		FOREIGN KEY (employee_id)
		REFERENCES employees(id)
	);