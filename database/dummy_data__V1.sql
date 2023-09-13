INSERT INTO tb_user VALUES (-1, 'Walisson Cardoso Gomes', 'Walisson', 'walisson@ig.com', '$2a$12$xSS04ZfW9SDaTq6.jt0TMOQTRJ20PVk3Pr7U.2lmFyXovb2ykQ5FG'); -- password: 123456

INSERT INTO tb_kanban VALUES (-1, 'Wall\'s Kanban', '2023-09-12', -1);

INSERT INTO tb_kanbanTeam VALUES (-1, -1, -1);

INSERT INTO tb_taskStatus VALUES (1, 'Backlog');
INSERT INTO tb_taskStatus VALUES (2, 'Doing');
INSERT INTO tb_taskStatus VALUES (3, 'Done');

INSERT INTO tb_task VALUES (-1, 'Create database', 'The objective is to create a database for the project', '2023-10-02', -1, 2);
INSERT INTO tb_task VALUES (-2, 'Create backend', 'Create backend using spring boot', '2023-11-02', -1, 1);

INSERT INTO tb_taskComment VALUES (-1, 'The created file is under the shared folder', NULL, '2023-09-12', -1);

INSERT INTO tb_taskActivity VALUES (-1, 'Create database schema', 1, -1);
INSERT INTO tb_taskActivity VALUES (-2, 'Insert test', 1, -1);
INSERT INTO tb_taskActivity VALUES (-3, 'Create initial project', 0, -2);

INSERT INTO tb_taskContributor VALUES (-1, -1, -1);

select * from tb_taskActivity tta;
