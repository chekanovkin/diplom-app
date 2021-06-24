insert into usr (id, username, password, name, surname, patronymic, salary, department, position, email, phone)
values (1, 'admin', 'admin', 'Андрей', 'Чекановкин', 'Евгеньевич', '100000', 'Отдел разработки', 'Java-разработчик', 'ymnyaga@yandex.ru', '8(989)824-36-16'),
(2, 'user', 'user', 'Шачнов', 'Никита', 'Алексеевич', '150000', 'Отдел разработки', 'Менеджер проекта', 'shachnov@yandex.ru', '8(951)843-16-28');

insert into user_role (user_id, roles)
values(1, 'ADMIN'), (1, 'CHIEF'), (2, 'ADMIN'), (2, 'CHIEF');