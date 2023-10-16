CREATE TABLE IF NOT EXISTS 'users' (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
password VARCHAR(45) NOT NULL,
enabled INT NOT NULL
);

CREATE TABLE IF NOT EXISTS 'authority' (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
authority VARCHAR(45) NOT NULL
);

INSERT INTO authority(username, authority) VALUES('Rick', 'write');
INSERT INTO users(username, password, enabled) VALUES('Rick', 'hoopoe', '1');