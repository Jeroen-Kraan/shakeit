-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema shakeitup
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shakeitup
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shakeitup` DEFAULT CHARACTER SET latin1 ;
USE `shakeitup` ;

-- -----------------------------------------------------
-- Table `shakeitup`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shakeitup`.`location` (
  `location_id` INT(11) NOT NULL AUTO_INCREMENT,
  `longitude` DOUBLE NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `city` VARCHAR(32) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  INDEX `idx_location_location_id` (`location_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `shakeitup`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shakeitup`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `idx_users_user_id` (`user_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `shakeitup`.`users_has_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shakeitup`.`users_has_location` (
  `users_user_id` INT(11) NOT NULL,
  `location_location_id` INT(11) NOT NULL,
  PRIMARY KEY (`users_user_id`, `location_location_id`),
  INDEX `fk_users_has_location_location1_idx` (`location_location_id` ASC),
  INDEX `fk_users_has_location_users_idx` (`users_user_id` ASC),
  CONSTRAINT `fk_users_has_location_location1`
    FOREIGN KEY (`location_location_id`)
    REFERENCES `shakeitup`.`location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_location_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `shakeitup`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
