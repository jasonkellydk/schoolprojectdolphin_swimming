
CREATE TABLE IF NOT EXISTS `dolphin_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `role` INT UNSIGNED NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` TEXT(90) NOT NULL,
  `created_at` TIMESTAMP(6) NULL,
  `updated_at` TIMESTAMP(6) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;