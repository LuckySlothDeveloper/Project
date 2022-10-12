-- 댓글 게시판

CREATE TABLE t_reply(
	rno			NUMBER			NOT NULL,
	bno			NUMBER			NOT NULL,
	replyer		VARCHAR2(100)	NOT NULL,
	replyText	VARCHAR2(1000)	NOT NULL,
	regDay		DATE	DEFAULT SYSDATE,
	updateDay	DATE	DEFAULT SYSDATE,
	CONSTRAINT pk_t_reply_rno PRIMARY KEY(rno),
	CONSTRAINT fk_t_reply_bno FOREIGN KEY(bno) REFERENCES t_board(bno) ON DELETE CASCADE
)

-- 시퀀스 생성
CREATE SEQUENCE seq_reply

SELECT * FROM t_reply

INSERT INTO t_reply(rno, bno, replyer, replyText, regDay, updateDay)
(SELECT seq_reply.NEXTVAL, 4097, '익명', '댓글 페이징 테스트', SYSDATE, SYSDATE FROM t_reply)

DELETE FROM t_reply