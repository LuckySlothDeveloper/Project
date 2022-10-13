-- 조회수 테이블

CREATE TABLE t_readCnt(
	ip		VARCHAR2(300),
	bno		NUMBER,
	CONSTRAINT pk_t_readCnt_ipNbno PRIMARY KEY(ip, bno) 
)