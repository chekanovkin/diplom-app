create sequence hibernate_sequence start 1 increment 1;

create table project (
  id int8 not null,
  description varchar(255),
  name varchar(255),
  readiness_degree int4 not null,
  primary key (id)
);

create table user_role (
  user_id int8 not null,
  roles varchar(255)
);

create table users_groups (
  user_id int8 not null,
  project_id int8 not null,
  primary key (user_id, project_id)
);

create table users_projects (
  project_id int8 not null,
  user_id int8 not null,
  primary key (project_id, user_id)
);

create table usr (
  id int8 not null,
  department varchar(255),
  email varchar(255),
  filename varchar(255),
  name varchar(255),
  password varchar(255) not null,
  patronymic varchar(255),
  phone varchar(255),
  position varchar(255),
  qualification varchar(255),
  salary float8 not null,
  surname varchar(255),
  username varchar(255) not null,
  primary key (id)
);

alter table if exists usr
add constraint UK_dfui7gxngrgwn9ewee3ogtgym unique (username);

alter table if exists user_role
add constraint FKfpm8swft53ulq2hl11yplpr5
foreign key (user_id) references usr;

alter table if exists users_groups
add constraint FKhbc0btwnpem0ha4chcwiv56co
foreign key (project_id) references project;

alter table if exists users_groups
add constraint FKrtd370bj8u6v6g4j4rgj7geap
foreign key (user_id) references usr;

alter table if exists users_projects
add constraint FKoxfg33oqflplvddjuluy4xwii
foreign key (user_id) references usr;

alter table if exists users_projects
add constraint FK5gka63hbj8siyiahiqs0kupe2
foreign key (project_id) references project;