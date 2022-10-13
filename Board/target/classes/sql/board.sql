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

SELECT * FROM t_board WHERE bno = 4097

INSERT INTO t_board(bno, writer, content, title, readCnt, regDay, updateDay)
(SELECT seq_board.NEXTVAL, writer, content, title, readCnt, regDay, updateDay FROM t_board)

ALTER TABLE t_board ADD replyCnt NUMBER DEFAULT 0
