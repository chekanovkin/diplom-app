insert into project(id, description, name, readiness_degree)
values(1, '', 'Программный комплекс многофакторного подбора литературных произведений', 0),
(2, '', 'Программный комплекс для навигации внутри зданий и комплексов зданий со сложной внутренней организацией', 0),
(3, '', 'Система, прогнозирующая исход киберспортивных мероприятий', 0);

insert into users_projects(project_id, user_id)
values(1, 1), (1, 2), (2, 2), (2, 3), (3, 3);