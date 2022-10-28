--CREATE DATABASE parking;

USE parking;

CREATE TABLE vehicle(
    plate VARCHAR(50) PRIMARY KEY,
    model VARCHAR(10) NOT NULL,
    color VARCHAR(10) NOT NULL,
    owner_id CHAR(17) NOT NULL,
    entry_date DATETIME NOT NULL,
    exit_date DATETIME DEFAULT NULL,
    bill DOUBLE DEFAULT NULL
);

CREATE TABLE parking_space(
	id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_plate VARCHAR(50) DEFAULT NULL,
    CONSTRAINT `fk_vehicle_plate` FOREIGN KEY (vehicle_plate) REFERENCES vehicle(plate)
);

CREATE TABLE parking_history(
    id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_plate VARCHAR(50) NOT NULL,
    entry_date DATETIME NOT NULL,
    exit_date DATETIME NOT NULL,
    bill DOUBLE NOT NULL
);

CREATE TABLE user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) UNIQUE NOT NULL,
	password BINARY(68) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL
);


INSERT INTO user(username, password, authority, enabled)
VALUES ("admin", "{bcrypt}$2a$12$LtV33fThQAShE6T38W0/z.G6Gi7.QsPI3lgIne7bKxQi6GNhwsq6m","ROLE_ADMIN", TRUE);


--  The script below runs fine on MySQl workbench, but can't run on this TestContainer MySQL database
--  Haven't yet found a solution, so I will create the parking spaces manually

--DELIMITER #
--CREATE PROCEDURE load_parking_spaces()
--BEGIN
--	DECLARE maxParkingSpaces INT DEFAULT 50;
--    DECLARE counter INT DEFAULT 0;
--
--    TRUNCATE TABLE parking_space;
--
--    START TRANSACTION;
--    WHILE counter < maxParkingSpaces DO
--		INSERT INTO parking_space (vehicle_id)
--        VALUES (NULL);
--
--        SET counter = counter + 1;
--
--    END WHILE;
--    COMMIT;
--
--END #
--DELIMITER ;
--CALL load_parking_spaces();

INSERT INTO parking_space(vehicle_plate)
    VALUES
        (null),
        (null),
        (null),
        (null),
        (null),
        (null),
        (null),
        (null),
        (null),
        (null);