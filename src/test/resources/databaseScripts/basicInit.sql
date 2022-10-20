--CREATE DATABASE parking;

USE parking;

CREATE TABLE vehicle(
	id CHAR(12) PRIMARY KEY,
    plate CHAR(15) UNIQUE NOT NULL,
    model VARCHAR(10) NOT NULL,
    color VARCHAR(10) NOT NULL,
    owner_id CHAR(17) NOT NULL,
    entry_date DATETIME NOT NULL,
    exit_date DATETIME DEFAULT NULL,
    bill DOUBLE DEFAULT NULL
);

CREATE TABLE parking_space(
	id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id CHAR(12) DEFAULT NULL,
    CONSTRAINT `fk_vehicle_id` FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);

CREATE TABLE parking_history(
    id INT PRIMARY KEY AUTO_INCREMENT,
	parking_space_id INT NOT NULL,
    vehicle_plate CHAR(15) NOT NULL,
    entry_date DATETIME NOT NULL,
    exit_date DATETIME NOT NULL,
    bill DOUBLE NOT NULL
);


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

INSERT INTO parking_space(vehicle_id)
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