-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema abilityswapbd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema abilityswapbd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `abilityswapbd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `abilityswapbd` ;

-- -----------------------------------------------------
-- Table `abilityswapbd`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `abilityswapbd`.`categorias` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `abilityswapbd`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `abilityswapbd`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(50) NULL DEFAULT NULL,
  `apellidos` VARCHAR(100) NULL DEFAULT NULL,
  `edad` INT NULL DEFAULT NULL,
  `genero` VARCHAR(50) NULL DEFAULT NULL,
  `telefono` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `foto` LONGBLOB NULL DEFAULT NULL,
  `rutafoto` VARCHAR(100) NULL DEFAULT NULL,
  `habla_sobre_ti` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `abilityswapbd`.`habilidades_demandadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `abilityswapbd`.`habilidades_demandadas` (
  `id_habilidad` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `id_categoria` INT NOT NULL,
  PRIMARY KEY (`id_habilidad`),
  INDEX `fk_usuario_idx` (`usuario` ASC) VISIBLE,
  INDEX `fk_categoria_habilidades_demandadas_idx` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_habilidades_demandadas`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `abilityswapbd`.`categorias` (`id_categoria`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_usuario_habilidades_demandadas`
    FOREIGN KEY (`usuario`)
    REFERENCES `abilityswapbd`.`usuarios` (`id_usuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `abilityswapbd`.`habilidades_ofertadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `abilityswapbd`.`habilidades_ofertadas` (
  `id_habilidad` INT NOT NULL,
  `usuario` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `id_categoria` INT NOT NULL,
  PRIMARY KEY (`id_habilidad`),
  INDEX `fk_usuario_idx` (`usuario` ASC) VISIBLE,
  INDEX `fk_categoria_habilidades_ofertadas_idx` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_habilidades_ofertadas`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `abilityswapbd`.`categorias` (`id_categoria`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_usuario_habilidades_ofertadas`
    FOREIGN KEY (`usuario`)
    REFERENCES `abilityswapbd`.`usuarios` (`id_usuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `abilityswapbd`.`intercambios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `abilityswapbd`.`intercambios` (
  `idintercambio` INT NOT NULL AUTO_INCREMENT,
  `usuario_ofertada` INT NOT NULL,
  `usuario_demandada` INT NOT NULL,
  `habilidad_ofertada` INT NOT NULL,
  `habilidad_demandada` INT NOT NULL,
  `estado` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idintercambio`),
  INDEX `fk_usuario_ofertada` (`usuario_ofertada` ASC) VISIBLE,
  INDEX `fk_usuario_demandada` (`usuario_demandada` ASC) VISIBLE,
  INDEX `fk_habilidad_ofertada` (`habilidad_ofertada` ASC) VISIBLE,
  INDEX `fk_habilidad_demandada` (`habilidad_demandada` ASC) VISIBLE,
  CONSTRAINT `fk_habilidad_demandada`
    FOREIGN KEY (`habilidad_demandada`)
    REFERENCES `abilityswapbd`.`habilidades_demandadas` (`id_habilidad`),
  CONSTRAINT `fk_habilidad_ofertada`
    FOREIGN KEY (`habilidad_ofertada`)
    REFERENCES `abilityswapbd`.`habilidades_ofertadas` (`id_habilidad`),
  CONSTRAINT `fk_usuario_demandada`
    FOREIGN KEY (`usuario_demandada`)
    REFERENCES `abilityswapbd`.`usuarios` (`id_usuario`),
  CONSTRAINT `fk_usuario_ofertada`
    FOREIGN KEY (`usuario_ofertada`)
    REFERENCES `abilityswapbd`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
