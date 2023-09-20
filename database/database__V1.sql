CREATE TABLE IF NOT EXISTS tb_user (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(400) NOT NULL,
	nickname VARCHAR(40) NOT NULL,
	email VARCHAR(400) NOT NULL,
	encryptedPassword VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_kanban (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(400) NOT NULL,
	creationDate DATETIME NOT NULL,
	creatorId BIGINT NOT NULL,
	FOREIGN KEY (creatorId) REFERENCES tb_user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_kanbanTeam (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	kanbanId BIGINT NOT NULL,
	userId BIGINT NOT NULL,
	INDEX idx_kanban (kanbanId),
	FOREIGN KEY (kanbanId) REFERENCES tb_kanban(id) ON DELETE CASCADE,
	FOREIGN KEY (userId) REFERENCES tb_user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_taskStatus (
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(400) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_task (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(400) NOT NULL,
	description VARCHAR(4000) NOT NULL,
	deadline DATE NULL,
	kanbanId BIGINT NOT NULL,
	status INT NOT NULL,
	FOREIGN KEY (kanbanId) REFERENCES tb_kanban(id) ON DELETE CASCADE,
	FOREIGN KEY (status) REFERENCES tb_taskStatus(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_taskComment (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	comment VARCHAR (4000) NULL,
	image BLOB NULL,
	creationDate DATETIME NOT NULL,
	taskId BIGINT NOT NULL,
	INDEX idx_task (taskId),
	FOREIGN KEY (taskId) REFERENCES tb_task(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_taskActivity (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR (400) NULL,
	isCompleted BOOLEAN NOT NULL,
	taskId BIGINT NOT NULL,
	INDEX idx_task (taskId),
	FOREIGN KEY (taskId) REFERENCES tb_task(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_taskContributor (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	taskId BIGINT NOT NULL,
	userId BIGINT NOT NULL,
	INDEX idx_task (taskId),
	FOREIGN KEY (taskId) REFERENCES tb_task(id) ON DELETE CASCADE,
	FOREIGN KEY (userId) REFERENCES tb_user(id) ON DELETE CASCADE
);


INSERT INTO tb_user VALUES (-1, 'Walisson Cardoso Gomes', 'Walisson', 'walisson@ig.com', '$2a$12$xSS04ZfW9SDaTq6.jt0TMOQTRJ20PVk3Pr7U.2lmFyXovb2ykQ5FG'); -- password: 123456

INSERT INTO tb_kanban VALUES (-1, 'Wall\'s Kanban', '2023-09-12', -1);

INSERT INTO tb_kanbanTeam VALUES (-1, -1, -1);

INSERT INTO tb_taskStatus VALUES (0, 'Backlog');
INSERT INTO tb_taskStatus VALUES (1, 'Doing');
INSERT INTO tb_taskStatus VALUES (2, 'Done');

INSERT INTO tb_task VALUES (-1, 'Create database', 'The objective is to create a database for the project', '2023-10-02', -1, 2);
INSERT INTO tb_task VALUES (-2, 'Create backend', 'Create backend using spring boot', '2023-11-02', -1, 1);

INSERT INTO tb_taskComment VALUES (-1, 'The created file is under the shared folder', NULL, '2023-09-12', -1);

INSERT INTO tb_taskActivity VALUES (-1, 'Create database schema', 1, -1);
INSERT INTO tb_taskActivity VALUES (-2, 'Insert test', 1, -1);
INSERT INTO tb_taskActivity VALUES (-3, 'Create initial project', 0, -2);

INSERT INTO tb_taskContributor VALUES (-1, -1, -1);

select * from tb_taskActivity tta;
