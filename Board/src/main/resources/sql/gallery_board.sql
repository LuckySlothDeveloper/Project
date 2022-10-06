-- 갤러리 게시글 테이블

CREATE TABLE t_gallery_board(
	gbno		NUMBER			NOT NULL,
	title		VARCHAR2(100)	NOT NULL,
	content		VARCHAR2(500)	NOT NULL,
	regDay		DATE	DEFAULT SYSDATE,
	updateDay	DATE	DEFAULT SYSDATE,
	CONSTRAINT pk_t_gallery_board_no PRIMARY KEY(gbno)
)

CREATE SEQUENCE seq_gallery_board
