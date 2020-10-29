--연습용 게스트 test
SELECT * FROM GUEST;
INSERT INTO GUEST (GUEST_ID,GUEST_PWD,GUEST_NAME, GUEST_BIRTHDAY,GUEST_PHONE) VALUES ('nottest','1234','nottest','1993-09-04',000-0000-0000);
INSERT INTO GUEST (GUEST_ID,GUEST_PWD,GUEST_NAME,GUEST_PHONE) VALUES ('test2','1234','test2Name','010-2222-2222');
DELETE FROM GUEST;

--연습용 admin test
SELECT * FROM ADMIN ;
INSERT INTO ADMIN VALUES ('testadmin','1234','testadmin');

--연습용 qna test
SELECT * FROM QNA;
DELETE FROM QNA q2 WHERE QNA_NO =10;
INSERT INTO QNA (GUEST_ID,QNA_TITLE,RES_YN,notice_yn,qna_secret,qna_password) VALUES ('nottest','nottesttest','n','n','y','123');
INSERT INTO QNA (GUEST_ID,QNA_TITLE,RES_YN,notice_yn) VALUES ('test','testTitl2123123','n',0);
INSERT INTO QNA (GUEST_ID,QNA_TITLE,RES_YN,notice_yn) VALUES ('test','testTitl4','n',0);
INSERT INTO QNA (ADMIN_ID,QNA_TITLE,DEL_YN, notice_yn) VALUES ('testadmin','admintestolenfoej','n', 'y');
INSERT INTO QNA (GUEST_ID,QNA_TITLE,QNA_CONTENT,RES_YN,) VALUES ('test2','what','what?','n');
DELETE qna;
SELECT * FROM QNA_VIEW ;

--공지사항 출력
SELECT QNA_NO,ADMIN_ID,QNA_TITLE,QNA_CONTENT,QNA_FILE,QNA_REGDATE,DEL_YN FROM QNA WHERE GUEST_ID IS NULL;

--guest 문의 내역확인
SELECT QNA_NO,QNA_TITLE,QNA_FILE,QNA_REGDATE,RES_YN FROM QNA_VIEW WHERE GUEST_ID = 'test' ORDER BY QNA_NO DESC;

--update test
UPDATE QNA 
	SET QNA_CONTENT = '이거 수정되는거맞나',
		QNA_REGDATE = SYSDATE 
	WHERE QNA_NO =1;
SELECT GUEST_ID ,GUEST_NAME FROM GUEST WHERE GUEST_ID ='test';

--총 공지사항 갯수
SELECT COUNT(*) AS count FROM QNA;

--페이징 처리 후 게시글 조회
SELECT * FROM qna WHERE ADMIN_ID IS NOT NULL AND DEL_YN ='n'ORDER BY QNA_NO DESC; --공지사항
SELECT * FROM (SELECT rownum RN, a.* FROM (SELECT * FROM QNA  ORDER BY notice_yn asc,QNA_NO ) a) WHERE rn BETWEEN 10 AND 20;
SELECT rownum, a.* FROM (SELECT * FROM qna ORDER BY notice_yn desc, qna_no desc) a WHERE rownum BETWEEN 1 AND 10;

--지수가 만들어준 페이징 코드
SELECT * FROM (SELECT rownum RN, a.* FROM (SELECT * FROM QNA WHERE DEL_YN ='y' ORDER BY notice_yn DESC ,QNA_NO DESC) a) WHERE rn BETWEEN 1 AND 10;
SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY notice_yn DESC, qna_no) no , q.* FROM qna q ORDER BY notice_yn desc, qna_no desc) WHERE NO BETWEEN 1 AND 5;
SELECT * FROM qna;

--답변 qna
UPDATE QNA 
	SET RES_YN ='n'
	WHERE QNA_NO = 2;

INSERT INTO QNA (ADMIN_ID,QNA_TITLE,QNA_CONTENT,QNA_FILE,QNA_REGDATE,QNA_REFNO) VALUES ('testadmin','답변','이내용은 답변내용입니다.',NULL,sysdate,68);
  
INSERT INTO QNA(ADMIN_ID, QNA_TITLE, QNA_CONTENT, QNA_FILE, NOTICE_YN) VALUES('testadmin','첫첫첫','내용내용내용',null,'y');




