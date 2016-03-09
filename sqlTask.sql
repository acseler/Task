-- SQL TASK
drop table tasks;
drop table projects;

CREATE TABLE projects (
	id BIGINT PRIMARY KEY,
	name varchar(256)
);


CREATE TABLE tasks (
	id BIGINT PRIMARY KEY,
	name varchar(256),
	status varchar(24),
	project_id BIGINT DEFAULT NULL	
);


INSERT INTO projects(id, name) VALUES (1, 'Project1');
INSERT INTO projects(id, name) VALUES (2, 'Praject2');
INSERT INTO projects(id, name) VALUES (3, 'Garage');
INSERT INTO projects(id, name) VALUES (4, 'Nova');
INSERT INTO projects(id, name) VALUES (5, 'Favorite');
INSERT INTO projects(id, name) VALUES (6, 'Practice');

INSERT INTO tasks VALUES (1, 'Hometask', 'in process', 1);
INSERT INTO tasks VALUES (2, 'Newspaper', 'in process', 3);
INSERT INTO tasks VALUES (3, 'Novocaine', 'completed', 2);
INSERT INTO tasks VALUES (4, 'Test1', 'completed', 2);
INSERT INTO tasks VALUES (5, 'Test2', 'completed', 2);
INSERT INTO tasks VALUES (6, 'Test3', 'completed', 2);
INSERT INTO tasks VALUES (7, 'Test4', 'completed', 2);
INSERT INTO tasks VALUES (8, 'Test5', 'completed', 2);
INSERT INTO tasks VALUES (9, 'Test6', 'completed', 2);
INSERT INTO tasks VALUES (10, 'Test7', 'completed', 2);
INSERT INTO tasks VALUES (11, 'Test15', 'completed', 2);
INSERT INTO tasks VALUES (12, 'Test9', 'completed', 2);
INSERT INTO tasks VALUES (13, 'Test10', 'completed', 2);
INSERT INTO tasks VALUES (14, 'Test11', 'completed', 3);
INSERT INTO tasks VALUES (15, 'Test12', 'completed', 3);
INSERT INTO tasks VALUES (16, 'Test13', 'completed', 3);
INSERT INTO tasks VALUES (17, 'Test14', 'completed', 3);
INSERT INTO tasks VALUES (18, 'Test15', 'completed', 3);
INSERT INTO tasks VALUES (19, 'Test15', 'completed', 3);
INSERT INTO tasks VALUES (20, 'Test16', 'completed', 3);
INSERT INTO tasks VALUES (21, 'Test17', 'completed', 3);
INSERT INTO tasks VALUES (22, 'Test18', 'completed', 3);
INSERT INTO tasks VALUES (23, 'Test19', 'completed', 3);
INSERT INTO tasks VALUES (24, 'Test20', 'completed', 3);
INSERT INTO tasks VALUES (30, 'Test30', 'in process', 3);
INSERT INTO tasks VALUES (31, 'Test30', 'in process', 3);
INSERT INTO tasks VALUES (32, 'Test30', 'in process', 3);
INSERT INTO tasks VALUES (25, 'Test9', 'completed', 4);
INSERT INTO tasks VALUES (26, 'Test22', 'completed', 4);
INSERT INTO tasks VALUES (27, 'Test23', 'in process', 4);

INSERT INTO tasks VALUES (28, 'Test25', 'in process');
INSERT INTO tasks VALUES (29, 'Test24', 'in process');



-- Queries
-- Task 1
SELECT DISTINCT status as "statuses" FROM tasks
ORDER BY status ASC;
-- Task 2
SELECT b.name, COUNT(a.id) FROM tasks a
INNER JOIN  projects b ON
a.project_id = b.id
GROUP BY b.id
ORDER BY 2 DESC;
-- Task 3
SELECT b.name, COUNT(a.id) FROM tasks a
INNER JOIN projects b ON a.project_id = b.id
GROUP BY b.id
ORDER BY 1 ASC;
-- Task 4
SELECT a.id, a.name, a.status, b.name as "Project" FROM tasks a
INNER JOIN projects b ON a.project_id = b.id
WHERE b.name LIKE 'N%'; 
-- Task 5
SELECT b.name as "Project name", COUNT(a.id) as "Task count" FROM tasks a
RIGHT JOIN projects b ON a.project_id = b.id
WHERE b.name LIKE '%a%'
GROUP BY b.name;
-- Task 6
SELECT name as "List of task with duplicate names" FROM tasks
GROUP BY name
HAVING count(id) > 1
ORDER BY 1;
-- Task 7
SELECT a.name, a.status, COUNT(a.id), b.name FROM tasks a
INNER JOIN projects b ON a.project_id = b.id
WHERE b.name = 'Garage'
GROUP BY a.name, a.status, b.name
HAVING COUNT(a.id) > 1
ORDER BY 3 ASC;
-- Task 8
SELECT a.project_id, b.name, COUNT(a.id) as "Amount of tasks" FROM tasks a
INNER JOIN projects b ON a.project_id = b.id
WHERE a.status = 'completed'
GROUP BY a.project_id, b.name
HAVING COUNT(*) > 10
ORDER BY 1 ASC;