-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cheyplbgdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cheyplbgdb` ;

-- -----------------------------------------------------
-- Schema cheyplbgdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cheyplbgdb` DEFAULT CHARACTER SET utf8 ;
USE `cheyplbgdb` ;

-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `create_date` DATETIME NULL,
  `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` TINYINT(4) NOT NULL DEFAULT '1',
  `company` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `address2` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` CHAR(2) NOT NULL,
  `postal_code` VARCHAR(10) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `customer_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_address_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job` ;

CREATE TABLE IF NOT EXISTS `job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `active` TINYINT(4) NOT NULL DEFAULT '1',
  `address_id` INT NULL,
  `estimate` DECIMAL NULL DEFAULT NULL,
  `paid` TINYINT(4) NOT NULL DEFAULT '0',
  `customer_id` INT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_job_address1_idx` (`address_id` ASC),
  INDEX `fk_job_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_job_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `department` ;

CREATE TABLE IF NOT EXISTS `department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `part`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `part` ;

CREATE TABLE IF NOT EXISTS `part` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `job_id` INT NOT NULL,
  `condition` VARCHAR(45) NOT NULL,
  `available` TINYINT(4) NOT NULL DEFAULT '1',
  `ship_time` INT NULL,
  `department_id` INT NOT NULL,
  `image` VARCHAR(500) NULL,
  `create_date` DATETIME NULL,
  `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_part_job1_idx` (`job_id` ASC),
  INDEX `fk_part_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_part_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_part_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee` ;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `active` TINYINT(4) NOT NULL DEFAULT '1',
  `address_id` INT NOT NULL,
  `wage` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_employee_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_department` ;

CREATE TABLE IF NOT EXISTS `job_department` (
  `job_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`job_id`, `department_id`),
  INDEX `fk_job_type_has_job_job1_idx` (`job_id` ASC),
  INDEX `fk_job_type_has_job_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_job_type_has_job_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_type_has_job_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_employee` ;

CREATE TABLE IF NOT EXISTS `job_employee` (
  `job_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`job_id`, `employee_id`),
  INDEX `fk_job_has_employee_employee1_idx` (`employee_id` ASC),
  INDEX `fk_job_has_employee_job1_idx` (`job_id` ASC),
  CONSTRAINT `fk_job_has_employee_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_has_employee_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employee_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee_type` ;

CREATE TABLE IF NOT EXISTS `employee_type` (
  `employee_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`, `department_id`),
  INDEX `fk_job_type_has_employee_employee1_idx` (`employee_id` ASC),
  INDEX `fk_employee_type_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_job_type_has_employee_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_type_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `permit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `permit` ;

CREATE TABLE IF NOT EXISTS `permit` (
  `id` INT NOT NULL,
  `identifier` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NULL,
  `job_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_permit_job1_idx` (`job_id` ASC),
  CONSTRAINT `fk_permit_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS cheyenne@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'cheyenne'@'localhost' IDENTIFIED BY 'cheyenne';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'cheyenne'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `create_date`, `last_update`, `active`, `company`) VALUES (1, 'Kyle', 'Carlson', 'Kyle@gmail.com', NULL, NULL, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `phone`, `customer_id`) VALUES (1, '25 RB St', NULL, 'Stanford', 'MN', NULL, '3545683658', NULL);
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `phone`, `customer_id`) VALUES (2, '35 BR Ave', NULL, 'Gilmore', 'MN', NULL, '1651312612', NULL);
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `phone`, `customer_id`) VALUES (3, '3564 Min Ct', NULL, 'Jiminey', 'MN', NULL, '564354654', NULL);
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `phone`, `customer_id`) VALUES (4, '1354 Private Ln', NULL, 'Folsom', 'MN', NULL, '542543535', NULL);
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `phone`, `customer_id`) VALUES (5, '2000 Made Up Job', NULL, 'Prior Lake', 'MN', NULL, '123456789', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `job` (`id`, `name`, `active`, `address_id`, `estimate`, `paid`, `customer_id`, `create_date`, `last_update`) VALUES (1, 'Carlson', 1, 5, 50000, 0, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `department`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `department` (`id`, `name`) VALUES (1, 'Plumbing');
INSERT INTO `department` (`id`, `name`) VALUES (2, 'Heating');
INSERT INTO `department` (`id`, `name`) VALUES (3, 'Septic');

COMMIT;


-- -----------------------------------------------------
-- Data for table `part`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `part` (`id`, `name`, `price`, `job_id`, `condition`, `available`, `ship_time`, `department_id`, `image`, `create_date`, `last_update`) VALUES (1, '8FT PVC 3\"', '20.99', 1, 'New', 0, 7, 1, 'https://m.media-amazon.com/images/I/61BI8gtFNVL.jpg', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`, `active`, `address_id`, `wage`) VALUES (1, 'David', 'Cottrell', 'cheyenneplbg@gmail.com', 1, 1, NULL);
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`, `active`, `address_id`, `wage`) VALUES (2, 'Douglas', 'Cottrell', 'Doug@gmail.com', 1, 2, NULL);
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`, `active`, `address_id`, `wage`) VALUES (3, 'Erik', 'Johnson', 'ejohnson14@gmail.com', 1, 3, NULL);
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`, `active`, `address_id`, `wage`) VALUES (4, 'Donald', 'Cottrell', 'Don@gmail.com', 1, 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_department`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `job_department` (`job_id`, `department_id`) VALUES (1, 1);
INSERT INTO `job_department` (`job_id`, `department_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `job_employee` (`job_id`, `employee_id`) VALUES (1, 1);
INSERT INTO `job_employee` (`job_id`, `employee_id`) VALUES (1, 3);
INSERT INTO `job_employee` (`job_id`, `employee_id`) VALUES (1, 2);
INSERT INTO `job_employee` (`job_id`, `employee_id`) VALUES (1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `employee_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `employee_type` (`employee_id`, `department_id`) VALUES (1, 1);
INSERT INTO `employee_type` (`employee_id`, `department_id`) VALUES (1, 2);
INSERT INTO `employee_type` (`employee_id`, `department_id`) VALUES (1, 3);
INSERT INTO `employee_type` (`employee_id`, `department_id`) VALUES (2, 2);
INSERT INTO `employee_type` (`employee_id`, `department_id`) VALUES (3, 1);
INSERT INTO `employee_type` (`employee_id`, `department_id`) VALUES (4, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `permit`
-- -----------------------------------------------------
START TRANSACTION;
USE `cheyplbgdb`;
INSERT INTO `permit` (`id`, `identifier`, `type`, `job_id`, `create_date`, `last_update`) VALUES (1, 'A-15163518', 'Plumbing', 1, NULL, NULL);
INSERT INTO `permit` (`id`, `identifier`, `type`, `job_id`, `create_date`, `last_update`) VALUES (2, 'G-AFHAFDHA', 'Ventilation', 1, NULL, NULL);

COMMIT;

