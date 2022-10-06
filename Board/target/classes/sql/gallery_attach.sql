-- 갤러리 첨부파일 테이블

CREATE TABLE t_gallery_attach(
	uuid		VARCHAR2(100)	NOT NULL,
	uploadPath	VARCHAR2(200)	NOT NULL,
	fileName	VARCHAR2(100)	NOT NULL,
	gbno		NUMBER,
	CONSTRAINT pk_t_gallery_attach_uuid PRIMARY KEY(uuid),
	CONSTRAINT fk_t_gallery_attach_gbno FOREIGN	KEY(gbno) REFERENCES t_gallery_board(gbno) ON DELETE CASCADE
)