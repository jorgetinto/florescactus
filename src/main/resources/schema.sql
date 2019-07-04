/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jpino
 * Created: 29-jun-2019
 */

-- MySQL Script generated by MySQL Workbench
-- Sat Jun 29 16:47:30 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema florescactusdatabase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema florescactusdatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `florescactusdatabase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `florescactusdatabase` ;

-- -----------------------------------------------------
-- Table `florescactusdatabase`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `florescactusdatabase`.`clientes` (
  `Id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `razon_social` VARCHAR(45) NOT NULL,
  `comuna` VARCHAR(45) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `florescactusdatabase`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `florescactusdatabase`.`usuarios` (
  `id` BIGINT(20) NOT NULL  AUTO_INCREMENT,
  `apellido_materno` VARCHAR(255) NULL DEFAULT NULL,
  `apellido_paterno` VARCHAR(255) NULL DEFAULT NULL,
  `correo` VARCHAR(55) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_cdmw5hxlfj78uf4997i3qyyw5` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `florescactusdatabase`.`muro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `florescactusdatabase`.`muro` (
  `Id` BIGINT(20) NOT NULL  AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(60) NULL DEFAULT NULL,
  `tipo_intalacion` TINYINT(1) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `fecha_creacion` DATE NULL DEFAULT NULL,
  `usuarios_id` BIGINT(20) NOT NULL,
  `clientes_Id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_muro_usuarios_idx` (`usuarios_id` ASC) VISIBLE,
  INDEX `fk_muro_clientes1_idx` (`clientes_Id` ASC) VISIBLE,
  CONSTRAINT `fk_muro_clientes1`
    FOREIGN KEY (`clientes_Id`)
    REFERENCES `florescactusdatabase`.`clientes` (`Id`),
  CONSTRAINT `fk_muro_usuarios`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `florescactusdatabase`.`usuarios` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `florescactusdatabase`.`decisiones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `florescactusdatabase`.`decisiones` (
  `Id` BIGINT(20) NOT NULL  AUTO_INCREMENT,
  `humedad` DOUBLE NULL DEFAULT NULL,
  `temperatura` DOUBLE NULL DEFAULT NULL,
  `temporada` VARCHAR(45) NULL DEFAULT NULL,
  `pronostico` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_creacion` DATETIME NULL,
  `muro_Id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Decisiones_muro1_idx` (`muro_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Decisiones_muro1`
    FOREIGN KEY (`muro_Id`)
    REFERENCES `florescactusdatabase`.`muro` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `florescactusdatabase`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `florescactusdatabase`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `florescactusdatabase`.`horarios_riego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `florescactusdatabase`.`horarios_riego` (
  `Id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Horario` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `muro_Id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_horarios_Riego_muro1_idx` (`muro_Id` ASC) VISIBLE,
  CONSTRAINT `fk_horarios_Riego_muro1`
    FOREIGN KEY (`muro_Id`)
    REFERENCES `florescactusdatabase`.`muro` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
