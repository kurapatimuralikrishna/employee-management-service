
USE two_tables;
INSERT INTO teams
VALUES 
	(1,"linux"),
	(2,"AI"),
    (3,"hardware"),
    (4,"design"),
    (5,"legal");
    
INSERT INTO genders(id,gname)
VALUES 
	(1,"MALE"),
    (2,"FEMALE");

INSERT INTO employees
VALUES
	(1,"Hari",2,1);

INSERT INTO employees (id,full_name,team,gender)
VALUES
	(2,"Suresh",3,1),
    (3,"Surya",1,1),
    (4,"Naresh",4,1),
    (5,"Aditya",5,1),
    (6,"Kumari",2,2),
    (7,"Priya",3,2),
    (8,"Sita",4,2),
    (9,"Jahnavi",5,2),
    (10,"Kalpana",5,2);

UPDATE employees
SET 
	full_name = "Savitha"
WHERE id = 8;

INSERT INTO tasks
VALUES 
	(2,0);
INSERT INTO tasks(employee_id,instructions)
VALUES
    (4,0),
    (9,0),
    (1,0),
    (8,0),
    (7,0),
    (6,0),
    (3,0),
    (5,0),
    (10,0);

UPDATE tasks
SET
	instructions = employee_id*employee_id;

INSERT INTO salaries(employee_id,package)
VALUES 
	(1,2.7),
	(2,3.7),
    (3,3.2),
    (4,23.8),
    (5,16.2),
    (6,255.9),
    (7,124.67),
    (8,1.2),
    (9,1.3),
    (10,1.2);

INSERT INTO ages(employee_id,age)
VALUES 
	(1,24),
	(2,26),
    (3,35),
    (4,43),
    (5,42),
    (6,41),
    (7,56),
    (8,19),
    (9,20),
    (10,19);

UPDATE salaries
SET payment_status = "Complete"
WHERE employee_id IN (2,9,6);  

UPDATE salaries
SET payment_status = "In progress"
WHERE employee_id IN (1,10);

INSERT INTO roles
VALUES
	(1,"ROLE_USER"),
	(2,"ROLE_MOD"),
	(3,"ROLE_ADMIN");

INSERT INTO users(username,user_password,email,employee_id)
VALUES
	("6",
	"$2a$12$25zic1HvD31VxhL8EWNs8emn6x1.VhvRyIk7di1kXxY8mWXFhMWIC",
	"6@office.com",
	6);
INSERT INTO user_roles(username,role_id)
VALUES
	("6",3);
INSERT INTO user_secrets(username,secret_question,secret_answer)
VALUES
	("6",
	"Who is the most beautiful person in the world",
	"6");
INSERT INTO users(username,user_password,email,employee_id)
VALUES
	("7",
	"$2a$12$syph3Dwqd9fflOfwU1gNq.IFQtIA.IxOgNuDPP730itRQRN/fxM0q",
	"7@office.com",
	7);
INSERT INTO user_roles(username,role_id)
VALUES
	("7",1),
	("7",2);
INSERT INTO user_secrets(username,secret_question,secret_answer)
VALUES
	("7",
	"When will the usiverse end?",
	"7");
INSERT INTO employee_incentives(employee_id)
VALUES 
	(1), 
	(2), 
	(3), 
	(4), 
	(5), 
	(6), 
	(7), 
	(8), 
	(9), 
	(10);
INSERT INTO incentive_types(id,incentive_name)
VALUES
	(1,"TRAVEL"),
	(2,"OVERTIME"),
	(3,"EQUIPMENT"),
	(4,"ACOMPLISHMENT"),
	(5,"OTHER");
	