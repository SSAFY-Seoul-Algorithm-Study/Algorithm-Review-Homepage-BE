DROP SCHEMA IF EXISTS `algorithm-roadmap-db` ;
CREATE SCHEMA IF NOT EXISTS `algorithm-roadmap-db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `algorithm-roadmap-db` ;

-- -----------------------------------------------------
-- Table `algorithm-roadmap-db`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algorithm-roadmap-db`.`User` ;

CREATE TABLE IF NOT EXISTS `User` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `BOJid` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `img` VARCHAR(200) NULL,
  `orgImg` VARCHAR(200) NULL,
  `difficulty` INT NOT NULL,
  `algo` VARCHAR(45) NOT NULL,
  `again` BOOL NOT NULL,
  `solvedCnt` INT NULL,
  `week` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `algorithm-roadmap-db`.`Problem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algorithm-roadmap-db`.`Problem` ;

CREATE TABLE IF NOT EXISTS `Problem` (
  `id` INT NOT NULL,
  `grade` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `algorithm-roadmap-db`.`Problem-Cluster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algorithm-roadmap-db`.`Problem-Cluster` ;

CREATE TABLE IF NOT EXISTS `Problem-Cluster` (
  `Problem_id` INT NOT NULL,
  `category` VARCHAR(45) NULL,
  PRIMARY KEY (`Problem_id`),
  INDEX `fk_Problem-Cluster_Problem_idx` (`Problem_id` ASC) VISIBLE,
  CONSTRAINT `fk_Problem-Cluster_Problem`
    FOREIGN KEY (`Problem_id`)
    REFERENCES `algorithm-roadmap-db`.`Problem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algorithm-roadmap-db`.`Review` ;

CREATE TABLE IF NOT EXISTS `Review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `User_id` VARCHAR(45) NOT NULL,
  `Problem_id` INT NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `rate` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Review_User1_idx` (`User_id` ASC) VISIBLE,
  INDEX `fk_Review_Problem1_idx` (`Problem_id` ASC) VISIBLE,
  CONSTRAINT `fk_Review_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `algorithm-roadmap-db`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Review_Problem1`
    FOREIGN KEY (`Problem_id`)
    REFERENCES `algorithm-roadmap-db`.`Problem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO User
VALUES("test123", "tester", "test321!!", "testBOJ", "test@naver.com", NULL,NULL,3,"DFS",true,3,2);

select * from user;
