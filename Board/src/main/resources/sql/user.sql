-- 유저 테이블

CREATE TABLE t_user(
	userId			VARCHAR2(100)	NOT NULL,
	password		VARCHAR2(500)	NOT NULL,
	userName		VARCHAR2(30)	NOT NULL,
	adminCk			NUMBER	DEFAULT 0,
	regDay			DATE	DEFAULT SYSDATE,
	CONSTRAINT pk_t_user_userId PRIMARY KEY(userId)
)

SELECT * FROM t_user

UPDATE t_user SET adminck = 1 WHERE userId = 'admin1' 