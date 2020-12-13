drop table movie;
drop table schedule;
drop table Room;
drop table ticket;
drop table member;
--01 액션 02 로맨스 03 코미디 04 스릴러 05 애니메이션
CREATE TABLE movie(
	movieNo number primary key,
	movieName VARCHAR2(20),
	category number,
	runtime number,
	img VARCHAR2(50),
	info VARCHAR2(200)
);

CREATE TABLE member(
	id VARCHAR2(20) primary key,
	pw VARCHAR2(20),
	email VARCHAR2(50),
	tel VARCHAR2(20),
	birth date
);
CREATE TABLE schedule( --상영관 -- 관람시간 영화관이랑 연결해주는 테이블
	schNo number primary key,
	movieNo number,
	runDay date,
	runtime number,
	roomNo number
)

CREATE TABLE Room(
	roomNo number,
	schNo number,
	seatCnt number -- 그 상영관에 얼마나 좌석이 예매가 되어있는지 카운트
)
CREATE TABLE ticket (
	ticketNo number primary key,
	bookDate date,
	schNo number,
	seatNo number,
	id VARCHAR2(20)
)
drop table schedule;
drop table Room;
drop table ticket;
select * from room;
select * from ticket;
-- 01 액선 02 로멘스 03 코미디 04 스릴러 05 애니메이션
insert into movie values(10000,'어벤저스',01,120,'1.jpg','재밌다 ');
insert into movie values(10001,'노팅힐',02, 120 , '2.jpg','감동적이다 ');
insert into movie values(10002,'아이언맨',01, 120 , '3.jpg','멋있따');
insert into movie values(10003,'겨울왕국2',05, 130 , '4.jpg','재밌다 ');
insert into movie values(10004,'엑시트',03, 140 , '5.jpg','킬링타임 ');
insert into movie values(10005,'반도',04, 155 , '6.jpg','잘생겼다 ');
insert into movie values(10006,'23아이덴티디',04, 150 , '7.jpg','꿀잼');

insert into member values('test','1234','test@test.com','010-1234-1234','2002-05-12');
insert into member values('admin','admin','admin@admin.com','010-1234-1234','2002-05-12');
                                           --sysdate varchar2 -> date 
insert into schedule values(1,10000,TO_DATE('2020/11/11 11:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(2,10000,TO_DATE('2020/11/11 1:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(3,10000,TO_DATE('2020/11/11 3:10','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(4,10000,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(5,10001,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,2);
insert into schedule values(6,10002,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,3);
insert into schedule values(7,10003,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,4);
insert into schedule values(8,10004,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,5);
insert into schedule values(9,10005,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,6);
insert into schedule values(10,10006,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,7);
select * from SCHEDULE;
                          --좌석 번호 : 1 - 20 
insert into ticket values(1,SYSDATE,1,15,'test');
insert into ticket values(2,SYSDATE,1,14,'test');

                  --상영관 --스케줄번호 --예매좌석 카운트 
insert into room values(1,1,1);
insert into room values(2,5,1);
insert into room values(3,6,1);
insert into room values(4,7,1);
insert into room values(5,8,1);
insert into room values(6,9,1);
insert into room values(7,10,1);
--티켓이 insert 될 때마다 seatCnt(예매한 좌석수 ) 갯수도 증가해야한다 
update room set seatCnt = seatCnt +1 where schNo = 1; 


select movieName, DECODE(category, 01, '액션',02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') category,img,mt.runtime,info,schNo,roomNo,to_char(runDay, 'mm/dd') as 날짜 ,to_char(runDay, 'HH24:MI') as 상영시간, roomno
from movie mt, schedule st where mt.movieNo = st.movieNo and mt.movieNo = 10000;

select * from room;
select * from member;

select st.movieNo, roomNo, runDay, bookDate, seatNo, id from ticket tt, schedule st where tt.schNo = st.schNo and st.schNo = 1;
SELECT movieName , mt.runtime , mt.movieNo , st.schNo , st.roomNo , runDay, ro.seatcnt FROM movie mt , schedule st, room ro WHERE mt.movieNo = st.movieNo AND st.movieNo = 10000;

SELECT mt.movieName, DECODE(category, 01, '액션',02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') category, img, mt.runtime, st.runDay, roomNo, seatNo FROM movie mt, ticket tt, schedule st where tt.schNo = st.schNo and st.movieNo = mt.movieNo and tt.id = 'test';

SELECT mt.movieName, DECODE(category, 01, '액션',02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') category, img, mt.runtime, to_char(st.runDay, 'mm/dd'), roomNo, seatNo FROM movie mt, ticket tt, schedule st where tt.schNo = st.schNo and st.movieNo = mt.movieNo and tt.id = 'test';