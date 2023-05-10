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
  `solvedSum` INT NULL,
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
  `rate` INT NOT NULL,
  `reg_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
-- 
--
--
--
INSERT INTO review(User_id, Problem_id, content, rate)
VALUES("김인범", 2, "???", 300);

DELETE FROM review
WHERE id = 3;

INSERT INTO User
VALUES("김인범", "tester", "test321", "testBOJ", "test@naver.com", 3);

INSERT INTO Problem
VALUES(2, 1);

DESC review;

select * from User;
select * from Problem;
select * from Review;

SELECT round(sum(rate) / count(*), 2)
FROM Review
WHERE Problem_id = 3;

SELECT id
FROM Problem
WHERE id in (SELECT DISTINCT p.Problem_id
FROM (SELECT * FROM Review ORDER BY reg_date DESC) p);

SELECT DISTINCT p.Problem_id
FROM (SELECT * FROM Review ORDER BY reg_date DESC) p;
--
-- 
-- 
-- 문제의 평균 평점을 구하기 위한 이벤트 및 프로시저 
CREATE EVENT `algorithm-roadmap-db`.grade_init
ON SCHEDULE EVERY 1 MINUTE
COMMENT '매일 문제의 평균 평점을 업데이트합니다.'
DO CALL Problem_grade_init();

DELIMITER $$
CREATE PROCEDURE Problem_grade_init()
BEGIN
	DECLARE i INT DEFAULT 1;
    
    WHILE (i <= 10) DO
		UPDATE `Problem` SET grade
		= (SELECT round(sum(rate) / count(*), 2) FROM Review WHERE Problem_id = i)
		WHERE id = i;
        
        SET i = i + 1;
    END WHILE;
END $$
DELIMITER ;

ALTER EVENT `algorithm-roadmap-db`.event_test ON SCHEDULE EVERY 1 SECOND;
SHOW VARIABLES LIKE 'event%';
SELECT * FROM information_schema.events;
SHOW CREATE EVENT `algorithm-roadmap-db`.event_test;
drop event grade_init;