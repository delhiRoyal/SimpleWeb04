-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Category` (
  `c_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `c_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`c_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Book` (
  `b_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `b_no_of_pages` VARCHAR(5) NULL,
  `c_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`b_id`),
  INDEX `fk_Book_Category1_idx` (`c_id` ASC),
  CONSTRAINT `fk_Book_Category1`
    FOREIGN KEY (`c_id`)
    REFERENCES `library`.`Category` (`c_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`language` (
  `l_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `l_code` VARCHAR(10) NOT NULL,
  `l_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`l_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Book_Locale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Book_Locale` (
  `bl_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bl_name` VARCHAR(100) NOT NULL,
  `bl_description` VARCHAR(500) NULL,
  `b_id` INT UNSIGNED NOT NULL,
  `l_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`bl_id`),
  INDEX `fk_Book_Locale_Book1_idx` (`b_id` ASC),
  INDEX `fk_Book_Locale_language1_idx` (`l_id` ASC),
  CONSTRAINT `fk_Book_Locale_Book1`
    FOREIGN KEY (`b_id`)
    REFERENCES `library`.`Book` (`b_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_Locale_language1`
    FOREIGN KEY (`l_id`)
    REFERENCES `library`.`language` (`l_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Category_Locale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Category_Locale` (
  `cl_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cl_name` VARCHAR(100) NOT NULL,
  `cl_description` VARCHAR(500) NULL,
  `c_id` INT UNSIGNED NOT NULL,
  `l_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`cl_id`),
  INDEX `fk_Category_Locale_Category1_idx` (`c_id` ASC),
  INDEX `fk_Category_Locale_language1_idx` (`l_id` ASC),
  CONSTRAINT `fk_Category_Locale_Category1`
    FOREIGN KEY (`c_id`)
    REFERENCES `library`.`Category` (`c_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_Locale_language1`
    FOREIGN KEY (`l_id`)
    REFERENCES `library`.`language` (`l_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`role` (
  `r_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `r_role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`r_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`user` (
  `u_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_name` VARCHAR(150) NULL,
  `u_password` VARCHAR(300) NULL,
  `r_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`u_id`),
  INDEX `fk_user_role1_idx` (`r_id` ASC),
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`r_id`)
    REFERENCES `library`.`role` (`r_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
