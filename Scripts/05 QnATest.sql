SELECT QNA_NO,GUEST_ID,QNA_TITLE,QNA_REGDATE,RES_YN FROM QNA_VIEW;

SELECT * FROM GUEST g ;

INSERT INTO GUEST (GUEST_ID,GUEST_PWD,GUEST_NAME,GUEST_PHONE) VALUES ('test','1234','testName','010-1111-111');

SELECT * FROM QNA;
INSERT INTO QNA (QNA_NO,GUEST_ID,QNA_TITLE,RES_YN) VALUES (1,'test','testTitle','n');
INSERT INTO QNA (QNA_NO,GUEST_ID,QNA_TITLE,RES_YN) VALUES (2,'test','testTitl2','n');

DELETE 
 FROM QNA;