CREATE TABLE public.ids (
  table_name varchar(50) PRIMARY KEY,
  id_value int
);

CREATE TABLE public.users (
  id BIGINT PRIMARY KEY,
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  CONSTRAINT unique_name UNIQUE (login)
);

CREATE TABLE public.projects (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT user_foreignkey FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE public.tasks (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  create_date DATE NOT NULL,
  deadline_date DATE,
  status VARCHAR(15),
  priority VARCHAR(15),
  project BIGINT NOT NULL,
  CONSTRAINT project_foreignkey FOREIGN KEY (project) REFERENCES projects(id)
);