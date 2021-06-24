create table task (
  id int8 not null,
  name varchar(255),
  status varchar(255),
  totalTime varchar(255),
  elapsedTime varchar(255),
  taskType varchar(255),
  user_id int8,
  primary key (id)
);

alter table if exists task
add constraint FK_userId
foreign key (user_id) references usr;