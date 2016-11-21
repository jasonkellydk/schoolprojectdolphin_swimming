-- MySQL Script generated by MySQL Workbench
-- Sat Nov 19 19:33:24 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema dolphin_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dolphin_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dolphin_db` DEFAULT CHARACTER SET utf8 ;
USE `dolphin_db` ;

-- -----------------------------------------------------
-- Table `dolphin_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dolphin_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `role` INT UNSIGNED NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP(6) NULL,
  `updated_at` TIMESTAMP(6) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;