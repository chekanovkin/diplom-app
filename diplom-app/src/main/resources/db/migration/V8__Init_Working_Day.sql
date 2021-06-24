create table working_day (
  id int8 not null,
  worked_day date,
  user_id int8,
  primary key (id)
);

alter table if exists working_day
add constraint FK_worked_userId
foreign key (user_id) references usr;