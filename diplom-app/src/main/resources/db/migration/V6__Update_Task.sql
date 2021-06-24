ALTER TABLE task ADD COLUMN project_id int8;

alter table if exists task
add constraint FK_projectId
foreign key (project_id) references project;