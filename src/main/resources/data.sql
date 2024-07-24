-- 테이블이 이미 존재하는 경우 삭제
DROP TABLE IF EXISTS sleep_on_user;

-- 테이블 생성
CREATE TABLE sleep_on_user (
                               id VARCHAR(45) NOT NULL,
                               pass VARCHAR(45) NOT NULL,
                               name VARCHAR(45) NOT NULL,
                               age VARCHAR(45) NOT NULL,
                               tel VARCHAR(45),
                               manager CHAR(1) NOT NULL DEFAULT 'N',
                               PRIMARY KEY (id)
);

-- 데이터 삽입
INSERT INTO sleep_on_user (id, pass, name, age, tel, manager)
VALUES ('admin@sleepon.com', 'SleepOn1234', 'SleepOn 상담사', '2000-01-01', '010-1234-5678', 'Y');

INSERT INTO sleep_on_user (id, pass, name, age, tel, manager)
VALUES ('user1@gmail.com', 'user1234', 'User One', '2001-05-20', '010-1111-2222', 'N');