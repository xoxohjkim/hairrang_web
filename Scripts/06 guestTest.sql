SELECT * FROM guest;
SELECT * FROM GUEST_VIEW;
SELECT GUEST_ID,GUEST_NAME,GUEST_BIRTHDAY,GUEST_PHONE,GUEST_EMAIL,GUEST_GENDER,GUEST_JOIN_DATE,GUEST_NOTE,DEL_YN,INFO_YNFROM GUEST_VIEW;

--INSERT INTO(GUEST_ID, GUEST_PWD, GUEST_NAME, GUEST_BIRTHDAY, GUEST_PHONE,  GUEST_GENDER, GUEST_NOTE, INFO_YN ) 
INSERT INTO guest(GUEST_ID, GUEST_PWD, GUEST_NAME, GUEST_BIRTHDAY, GUEST_PHONE, GUEST_EMAIL, GUEST_GENDER, GUEST_NOTE, INFO_YN) VALUES ('test2', '1234', '김혜진2', TO_date('1991-12-19','yyyy--MM-dd'), '010-5656-5656', 'text2@tes2t.com', 1, '메모', 'y');

UPDATE GUEST SET GUEST_NAME = ?, GUEST_BIRTHDAY = ?, GUEST_PHONE =?, GUEST_NOTE = ?, info_yn WHERE GUEST_ID = 'test2';

SELECT GUEST_ID FROM guest WHERE GUEST_NAME = '김혜진' AND GUEST_EMAIL='hoon@test.co.kr';

SELECT * FROM guest WHERE GUEST_ID = 'test2' AND GUEST_NAME = '김혜진' AND GUEST_EMAIL='hoon@test.co.kr';

UPDATE guest SET guest_pwd = '4321' WHERE guest_id = 'test';

select * from guest_view where guest_id = 'test2', guest_name = ?, guest_email

UPDATE guest SET guest_pwd = '1234' WHERE GUEST_id = 'chini91';