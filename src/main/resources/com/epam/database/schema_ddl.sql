-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema user_details
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema user_details
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user_details` DEFAULT CHARACTER SET utf8 ;
USE `user_details` ;

-- -----------------------------------------------------
-- Table `user_details`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_details`.`role` (
  `r_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `r_role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`r_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_details`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_details`.`user` (
  `u_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_name` VARCHAR(150) NOT NULL,
  `u_password` VARCHAR(300) NULL,
  `r_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE INDEX `u_name_UNIQUE` (`u_name` ASC),
  INDEX `fk_user_role_idx` (`r_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`r_id`)
    REFERENCES `user_details`.`role` (`r_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
