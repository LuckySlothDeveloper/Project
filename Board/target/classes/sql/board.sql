-- 게시판 테이블

CREATE TABLE t_board(
	bno			NUMBER			NOT NULL,
	writer		VARCHAR2(100)	NOT NULL,
	content		VARCHAR2(1000)	NOT NULL,
	title		VARCHAR2(100)	NOT	NULL,
	readCnt		NUMBER	DEFAULT 0,
	regDay		DATE	DEFAULT SYSDATE,
	updateDay	DATE	DEFAULT SYSDATE,
	CONSTRAINT pk_t_board_num	PRIMARY KEY(bno)
)

-- 시퀀스 생성
CREATE SEQUENCE seq_board
