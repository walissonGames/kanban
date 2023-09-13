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