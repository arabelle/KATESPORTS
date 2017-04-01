drop table Rental;
drop table Equipment;
drop Table MatchSummary;
drop Table MatchInfo;
drop table Player;
drop Table Referee;
drop Table Team;

CREATE TABLE Equipment (
	item_id NUMBER(10) NOT NULL,
	item_type VARCHAR(255),
	item_name VARCHAR(255),
	CONSTRAINT equipment_pk PRIMARY KEY (item_id)
);

grant select on Equipment to public;

INSERT INTO Equipment
VALUES(1232, 'ball', 'basketball');
INSERT INTO Equipment
VALUES(223, 'ball', 'volleyball');
INSERT INTO Equipment
VALUES(333, 'gear', 'hockey mask');
INSERT INTO Equipment
VALUES(476, 'gear', 'knee pads');
INSERT INTO Equipment
VALUES(512, 'equipment', 'volleyball net');
INSERT INTO Equipment
VALUES(69, 'equipment', 'soccer goal');
INSERT INTO Equipment
VALUES(7, 'gear', 'tennis racket');

CREATE TABLE Team (
	team_name VARCHAR2(255),
	coach_name VARCHAR2(255),
	phone_no CHAR(12),
	CONSTRAINT team_pk PRIMARY KEY (team_name),
	CONSTRAINT team_ck UNIQUE (phone_no)
);

grant select on Team to public;

INSERT INTO Team
VALUES('Grannies', 'Mary Stannard', '400-236-9933');
INSERT INTO Team
VALUES('Non-Losers', 'Eli Jones', '400-199-3011');
INSERT INTO Team
VALUES('Hot Cheetos', 'George John', '400-177-5622');
INSERT INTO Team
VALUES('Ball Is Life', 'Amanda Bayo', '400-907-4333');
INSERT INTO Team
VALUES('AndroidT', 'Ilya Merkel', '400-632-5452');

CREATE TABLE Referee (
	ref_id NUMBER(10),
	name VARCHAR2(255),
	phone_no CHAR(12),
	CONSTRAINT ref_pk PRIMARY KEY(ref_id),
	CONSTRAINT ref_ck UNIQUE (phone_no)
);

grant select on Referee to public;

INSERT INTO Referee
VALUES(1, 'Harry Jordan', '400-311-3787');
INSERT INTO Referee
VALUES(2, 'Melissa Kennedy', '400-655-4341');
INSERT INTO Referee
VALUES(3, 'John Zhang', '400-822-9702');
INSERT INTO Referee
VALUES(4, 'Oliver Helinski', '400-199-5011');
INSERT INTO Referee
VALUES(5, 'Gina Yao', '400-199-5012');

CREATE TABLE Player (
	player_id NUMBER(10) NOT NULL, 
	name VARCHAR2(255),
	phone_no CHAR(12), 
	age NUMBER(3), 
	weight NUMBER(3), 
	matches_won NUMBER(5), 
	matches_lost NUMBER(5), 
	team_name VARCHAR2(255) NOT NULL,
	CONSTRAINT player_pk PRIMARY KEY (player_id),
	CONSTRAINT player_ck UNIQUE (phone_no),
	CONSTRAINT team_fk FOREIGN KEY (team_name) REFERENCES Team(team_name)
);

grant select on Player to public;

INSERT INTO Player
VALUES(1, 'Amy Anderson', '400-200-1000', 21, 102, 20, 2, 'Grannies');
INSERT INTO Player
VALUES(2, 'Bob Biggins', '400-213-2323', 16, 200, 30, 2, 'Grannies');
INSERT INTO Player
VALUES(3, 'Cathy Collingwood', '400-233-1215', 22, 120, 10, 5, 'Non-Losers');
INSERT INTO Player
VALUES(4, 'Doris Dunder', '400-356-5656', 11, 140, 10, 6, 'Non-Losers');
INSERT INTO Player
VALUES(5, 'Mandi Boyers', '400-895-5745', 15, 124, 10, 5, 'Non-Losers');
INSERT INTO Player
VALUES(6, 'Tiffani Scaglionee', '400-896-5745', 12, 124, 10, 5, 'Non-Losers');
INSERT INTO Player
VALUES(7, 'Melinda Gerhardt', '400-897-5745', 13, 124, 0, 5, 'Hot Cheetos');
INSERT INTO Player
VALUES(8, 'Tayna Lepley', '400-898-5745', 16, 124, 0, 52, 'Hot Cheetos');
INSERT INTO Player
VALUES(9, 'Noma Geter', '400-899-5745', 16, 123, 0, 12, 'Hot Cheetos');
INSERT INTO Player
VALUES(10, 'Jeannine Mcmurry', '400-890-5745', 19, 171, 2, 2, 'Hot Cheetos');
INSERT INTO Player
VALUES(11, 'Stevie Granville', '400-891-5745', 15, 114, 1, 78, 'Hot Cheetos');
INSERT INTO Player
VALUES(12, 'Ruth Oyola', '400-892-5735', 15, 112, 20, 2, 'Ball Is Life');
INSERT INTO Player
VALUES(13, 'Kami Quint', '400-895-5775', 15, 132, 60, 1, 'Ball Is Life');
INSERT INTO Player
VALUES(14, 'Alexis Mckeon', '400-875-5295', 15, 135, 16, 2, 'Ball Is Life');
INSERT INTO Player
VALUES(15, 'Hae Heywood', '400-845-5105', 17, 129, 25, 1, 'Ball Is Life');
INSERT INTO Player
VALUES(16, 'Benton Gunia', '400-825-5245', 19, 124, 8, 1, 'Ball Is Life');
INSERT INTO Player
VALUES(17, 'Ranee Keach', '400-815-5655', 11, 129, 11, 0, 'Ball Is Life');
INSERT INTO Player
VALUES(18,'Migdalia Falcon','300-500-1005',11,123,12,4,'Grannies');
INSERT INTO Player
VALUES(19,'Tana Rohrbaugh','300-200-1004',19,115,14,2,'Grannies');
INSERT INTO Player
VALUES(20,'Sherilyn Pitt','300-630-1003',22,112,62,12,'Grannies');
INSERT INTO Player
VALUES(21,'Eartha Absher','300-680-1002',23,161,23,3,'Grannies');
INSERT INTO Player
VALUES(22,'Verdie Brar','300-700-1001',12,177,23,2,'Grannies');
INSERT INTO Player
VALUES(23,'Ross Vanetten','300-600-1005',14,116,52,2,'Grannies');
INSERT INTO Player
VALUES(24,'Penelope Frederic','300-600-1004',17,116,42,3,'Grannies');
INSERT INTO Player
VALUES(25,'Georgiann Gravel','300-600-1003',18,117,1,12,'Grannies');
INSERT INTO Player
VALUES(26,'Majorie Mailloux','300-600-1002',17,123,2,23,'AndroidT');
INSERT INTO Player
VALUES(27,'Lance Laux','300-600-1001',18,134,3,53,'AndroidT');
INSERT INTO Player
VALUES(28,'Kamala Schwager','300-600-1000',16,163,13,3,'AndroidT');
INSERT INTO Player
VALUES(29,'Delinda Litt','300-600-9990',16,178,14,2,'AndroidT');
INSERT INTO Player
VALUES(30,'Anderson Marenco','300-600-9989',16,132,13,2,'AndroidT');
INSERT INTO Player
VALUES(31,'Hosea Rittenberry','300-600-9988',17,134,2,12,'AndroidT');
INSERT INTO Player
VALUES(32,'Stacee Hernandes','300-600-9987',19,145,52,3,'AndroidT');
INSERT INTO Player
VALUES(33,'William Litchford','300-600-9986',23,155,12,7,'AndroidT');
INSERT INTO Player
VALUES(34,'Jerrell Beckmann','300-600-9985',22,121,42,0,'AndroidT');
INSERT INTO Player
VALUES(35,'Katina Bolding','300-600-9984',21,111,12,9,'Ball Is Life');
INSERT INTO Player
VALUES(36,'Elden Seawell','300-600-9983',22,131,41,6,'Ball Is Life');
INSERT INTO Player
VALUES(37,'Jackqueline Hopple','300-600-9982',22,141,11,8,'Ball Is Life');
INSERT INTO Player
VALUES(38,'Annis Richmond','300-600-9981',11,112,13,5,'Ball Is Life');
INSERT INTO Player
VALUES(39,'Cecelia Glynn','300-600-9980',15,111,12,6,'Ball Is Life');
INSERT INTO Player
VALUES(40,'Janna Roling','300-600-9979',17,131,22,3,'Ball Is Life');
INSERT INTO Player
VALUES(41,'Peg Hollifield','300-600-9978',19,151,22,5,'Ball Is Life');
INSERT INTO Player
VALUES(42,'Nelda Ballance','300-600-9977',13,113,12,3,'Hot Cheetos');
INSERT INTO Player
VALUES(43,'Huey Schnabel','300-600-9976',15,113,32,1,'Hot Cheetos');
INSERT INTO Player
VALUES(44,'Terry Dull','300-600-9975',17,151,1,23,'Hot Cheetos');
INSERT INTO Player
VALUES(45,'Sheron Pass','300-600-9974',17,121,12,1,'Hot Cheetos');
INSERT INTO Player
VALUES(46,'Salvatore Glavin','300-600-9973',17,123,4,12,'Non-Losers');
INSERT INTO Player
VALUES(47,'Hallie Lout','300-600-9972',18,123,3,23,'Non-Losers');
INSERT INTO Player
VALUES(48,'Donovan Onken','300-600-9971',18,145,5,24,'Non-Losers');
INSERT INTO Player
VALUES(49,'Jonna Schaible','300-600-9970',13,123,3,25,'Non-Losers');
INSERT INTO Player
VALUES(50,'Kyle Klemp','300-600-9969',14,141,13,1,'Non-Losers');
INSERT INTO Player
VALUES(51,'Carmelita Torgeson','300-600-9968',17,142,2,12,'Non-Losers');
INSERT INTO Player
VALUES(52,'Coralie Northrop','300-600-9967',19,123,23,9,'Non-Losers');


CREATE TABLE Rental (
	rental_id NUMBER(10) NOT NULL,
	player_id NUMBER(10) NOT NULL,
	item_id NUMBER(10) NOT NULL,
	CONSTRAINT rental_pk PRIMARY KEY (rental_id),
	CONSTRAINT rental_ck UNIQUE (player_id, item_id),
	CONSTRAINT player_fk FOREIGN KEY (player_id) REFERENCES Player(player_id) ON DELETE CASCADE,
	CONSTRAINT item_fk FOREIGN KEY (item_id) REFERENCES Equipment(item_id)
);

grant select on Rental to public;	

INSERT INTO Rental
VALUES(1, 1, 7);
INSERT INTO Rental
VALUES(2, 2, 223); 
INSERT INTO Rental
VALUES(3, 2, 512); 
INSERT INTO Rental
VALUES(4, 2, 476); 
INSERT INTO Rental
VALUES(5, 4, 1232);
INSERT INTO Rental
VALUES(6, 3, 333);
INSERT INTO Rental
VALUES(7, 1, 69);

CREATE TABLE MatchSummary (
	home_team VARCHAR2(255) NOT NULL,
	away_team VARCHAR2(255) NOT NULL,
	home_score NUMBER(5) NOT NULL,
	away_score NUMBER(5) NOT NULL,
	winner VARCHAR2(255) NOT NULL,
	loser VARCHAR2(255) NOT NULL,
	CONSTRAINT ms_pk PRIMARY KEY (home_team, away_team, home_score, away_score),
	CONSTRAINT home_team_fk FOREIGN KEY (home_team) REFERENCES Team(team_name),
	CONSTRAINT away_team_fk FOREIGN KEY (away_team) REFERENCES Team(team_name),
	CONSTRAINT winner_fk FOREIGN KEY (winner) REFERENCES Team(team_name),
	CONSTRAINT loser_fk FOREIGN KEY (loser) REFERENCES Team(team_name),
	CONSTRAINT winner_team CHECK (winner = home_team OR winner = away_team),
	CONSTRAINT loser_team CHECK (loser = home_team OR loser = away_team)
);

grant select on MatchSummary to public;

INSERT INTO MatchSummary
VALUES('Non-Losers', 'Grannies', 56, 59, 'Grannies', 'Non-Losers');
INSERT INTO MatchSummary
VALUES('Hot Cheetos', 'Non-Losers', 44, 76, 'Non-Losers', 'Hot Cheetos');
INSERT INTO MatchSummary
VALUES('Hot Cheetos', 'Grannies', 53, 62, 'Grannies',  'Hot Cheetos');
INSERT INTO MatchSummary
VALUES('Ball Is Life', 'Hot Cheetos', 72, 32, 'Ball Is Life', 'Hot Cheetos');
INSERT INTO MatchSummary
VALUES('Ball Is Life', 'AndroidT', 67, 63, 'Ball Is Life', 'AndroidT');
INSERT INTO MatchSummary
VALUES ('Ball Is Life', 'AndroidT', 11, 3, 'Ball Is Life', 'AndroidT');
INSERT INTO MatchSummary
VALUES ('Non-Losers', 'Ball Is Life', 5, 20, 'Ball Is Life', 'Non-Losers');
INSERT INTO MatchSummary
VALUES  ('Grannies', 'Hot Cheetos', 53, 26, 'Grannies', 'Hot Cheetos');
INSERT INTO MatchSummary
VALUES  ('AndroidT', 'Non-Losers', 8, 1, 'AndroidT', 'Non-Losers');
INSERT INTO MatchSummary
VALUES  ('Non-Losers', 'AndroidT', 1, 8, 'AndroidT', 'Non-Losers');
INSERT INTO MatchSummary
VALUES   ('AndroidT', 'Grannies', 10, 3, 'AndroidT', 'Grannies');
INSERT INTO MatchSummary
VALUES ('Hot Cheetos', 'Grannies',32,43, 'Grannies','Hot Cheetos');
INSERT INTO MatchSummary
VALUES ('Hot Cheetos','Non-Losers',25,32,'Non-Losers','Hot Cheetos');
INSERT INTO MatchSummary
VALUES ('Hot Cheetos','Ball Is Life',26,43,'Ball Is Life','Hot Cheetos');
INSERT INTO MatchSummary
VALUES ('Hot Cheetos','Ball Is Life',27,45,'Ball Is Life','Hot Cheetos');
INSERT INTO MatchSummary
VALUES ('Hot Cheetos','Non-Losers',45,62,'Non-Losers','Hot Cheetos');
INSERT INTO MatchSummary
VALUES ('Grannies','Non-Losers',57,43,'Grannies','Non-Losers');
INSERT INTO MatchSummary
VALUES ('Grannies','Ball Is Life',44,12,'Grannies','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Grannies','Ball Is Life',66,31,'Grannies','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Grannies','Non-Losers',34,13,'Grannies','Non-Losers');
INSERT INTO MatchSummary
VALUES ('Grannies','Non-Losers',23,51,'Non-Losers','Grannies');
INSERT INTO MatchSummary
VALUES ('Non-Losers','Ball Is Life',73,34,'Non-Losers','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Non-Losers','Ball Is Life',23,15,'Non-Losers','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Non-Losers','Ball Is Life',52,86,'Ball Is Life','Non-Losers');
INSERT INTO MatchSummary
VALUES ('Non-Losers', 'Grannies',15,34, 'Grannies','Non-Losers');
INSERT INTO MatchSummary
VALUES ('Non-Losers', 'Grannies',43,23,'Non-Losers', 'Grannies');
INSERT INTO MatchSummary
VALUES ('Ball Is Life', 'Grannies',23,25, 'Grannies','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Ball Is Life', 'Grannies',24,73, 'Grannies','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Ball Is Life','Non-Losers',26,34,'Non-Losers','Ball Is Life');
INSERT INTO MatchSummary
VALUES ('Ball Is Life','Non-Losers',45,23,'Ball Is Life','Non-Losers');
INSERT INTO MatchSummary
VALUES ('Ball Is Life','Hot Cheetos',84,27,'Ball Is Life','Hot Cheetos');
INSERT INTO MatchSummary
VALUES ('Ball Is Life','Hot Cheetos',34,26,'Ball Is Life','Hot Cheetos');

CREATE TABLE MatchInfo (
	match_id NUMBER(10) NOT NULL,
	home_team VARCHAR2(255) NOT NULL,
	away_team VARCHAR2(255) NOT NULL,
	home_score NUMBER(5),
	away_score NUMBER(5),
	start_time TIMESTAMP,
	end_time TIMESTAMP,
	ref_id NUMBER(10) NOT NULL,
	CONSTRAINT mi_pk PRIMARY KEY (match_id),
	CONSTRAINT matchref_ck UNIQUE (start_time, end_time, ref_id),
	CONSTRAINT match_ck UNIQUE (home_team, away_team, start_time, end_time),
	CONSTRAINT ref_fk FOREIGN KEY (ref_id) REFERENCES Referee(ref_id) ON DELETE SET NULL,
	CONSTRAINT home_fk FOREIGN KEY (home_team) REFERENCES Team(team_name),
	CONSTRAINT away_fk FOREIGN KEY (away_team) REFERENCES Team(team_name),
	CONSTRAINT time_check CHECK (end_time > start_time)
);

grant select on MatchInfo to public;

INSERT INTO MatchInfo
VALUES (1, 'Non-Losers', 'Grannies', 56, 59, to_timestamp('01-11-2017 19:45','MM-DD-YYYY HH24:MI'), to_timestamp('01-11-2017 21:45','MM-DD-YYYY HH24:MI'), 1);
INSERT INTO Matchinfo
VALUES (2, 'Hot Cheetos', 'Non-Losers', 44, 76, to_timestamp('01-12-2017 15:30','MM-DD-YYYY HH24:MI'), to_timestamp('01-12-2017 18:30','MM-DD-YYYY HH24:MI'), 5);
INSERT INTO MatchInfo
VALUES (3, 'Hot Cheetos', 'Grannies', 53, 62, to_timestamp('01-13-2017 12:00','MM-DD-YYYY HH24:MI'), to_timestamp('01-13-2017 13:30','MM-DD-YYYY HH24:MI'), 2);
INSERT INTO MatchInfo
VALUES (4, 'Ball Is Life', 'Hot Cheetos', 72, 32, to_timestamp('02-14-2017 14:00','MM-DD-YYYY HH24:MI'), to_timestamp('02-14-2017 18:05','MM-DD-YYYY HH24:MI'), 1);
INSERT INTO MatchInfo
VALUES (5, 'AndroidT', 'Grannies', 10, 3, to_timestamp('02-15-2017 14:00','MM-DD-YYYY HH24:MI'), to_timestamp('02-15-2017 18:05','MM-DD-YYYY HH24:MI'), 3);
INSERT INTO MatchInfo
VALUES (6, 'Ball Is Life', 'AndroidT', 67, 63, to_timestamp('02-16-2017 11:00','MM-DD-YYYY HH24:MI'), to_timestamp('02-16-2017 13:13','MM-DD-YYYY HH24:MI'), 1);
INSERT INTO MatchInfo
VALUES (7, 'Ball Is Life', 'AndroidT', 11, 3, to_timestamp('04-15-2017 17:00','MM-DD-YYYY HH24:MI'), to_timestamp('04-15-2017 19:20','MM-DD-YYYY HH24:MI'), 5);
INSERT INTO MatchInfo
VALUES (8, 'Non-Losers', 'Ball Is Life', 5, 20, to_timestamp('04-19-2017 10:00','MM-DD-YYYY HH24:MI'), to_timestamp('04-19-2017 10:22','MM-DD-YYYY HH24:MI'), 4);
INSERT INTO MatchInfo
VALUES (9, 'Grannies', 'Hot Cheetos', 53, 26, to_timestamp('08-17-2017 12:00','MM-DD-YYYY HH24:MI'), to_timestamp('08-17-2017 13:30','MM-DD-YYYY HH24:MI'), 4);
INSERT INTO MatchInfo
VALUES (10, 'AndroidT', 'Hot Cheetos', 8, 1, to_timestamp('08-18-2017 12:00','MM-DD-YYYY HH24:MI'), to_timestamp('08-18-2017 13:30','MM-DD-YYYY HH24:MI'), 2);
INSERT INTO MatchInfo
VALUES (11, 'Non-Losers', 'AndroidT', 1, 8, to_timestamp('08-29-2017 15:00','MM-DD-YYYY HH24:MI'), to_timestamp('08-29-2017 18:30','MM-DD-YYYY HH24:MI'), 3);
INSERT INTO MatchInfo
VALUES (12,'Hot Cheetos', 'Grannies',32,43,to_timestamp('09-01-2017 9:45','MM-DD-YYYY HH24:MI'),to_timestamp('09-01-2017 10:45','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (13,'Hot Cheetos','Non-Losers',25,32,to_timestamp('09-02-2017 10:45','MM-DD-YYYY HH24:MI'),to_timestamp('09-02-2017 11:45','MM-DD-YYYY HH24:MI'),3);
INSERT INTO MatchInfo
VALUES (14,'Hot Cheetos','Ball Is Life',26,43,to_timestamp('09-02-2017 19:45','MM-DD-YYYY HH24:MI'),to_timestamp('09-02-2017 21:00','MM-DD-YYYY HH24:MI'),4);
INSERT INTO MatchInfo
VALUES (15,'Hot Cheetos','Ball Is Life',27,45,to_timestamp('09-03-2017 12:45','MM-DD-YYYY HH24:MI'),to_timestamp('09-03-2017 13:45','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (16,'Hot Cheetos','Non-Losers',45,62,to_timestamp('09-14-2017 19:30','MM-DD-YYYY HH24:MI'),to_timestamp('09-14-2017 21:00','MM-DD-YYYY HH24:MI'),3);
INSERT INTO MatchInfo
VALUES (17,'Grannies','Non-Losers',57,43,to_timestamp('10-10-2017 16:45','MM-DD-YYYY HH24:MI'),to_timestamp('10-10-2017 18:45','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (18,'Grannies','Ball Is Life',44,12,to_timestamp('10-13-2017 14:45','MM-DD-YYYY HH24:MI'),to_timestamp('10-13-2017 15:35','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (19,'Grannies','Ball Is Life',66,31,to_timestamp('10-14-2017 14:45','MM-DD-YYYY HH24:MI'),to_timestamp('10-14-2017 16:25','MM-DD-YYYY HH24:MI'),4);
INSERT INTO MatchInfo
VALUES (20,'Grannies','Non-Losers',34,13,to_timestamp('10-16-2017 20:35','MM-DD-YYYY HH24:MI'),to_timestamp('10-16-2017 21:25','MM-DD-YYYY HH24:MI'),5);
INSERT INTO MatchInfo
VALUES (21,'Grannies','Non-Losers',23,51,to_timestamp('10-18-2017 19:15','MM-DD-YYYY HH24:MI'),to_timestamp('10-18-2017 21:05','MM-DD-YYYY HH24:MI'),2);
INSERT INTO MatchInfo
VALUES (22,'Non-Losers','Ball Is Life',73,34,to_timestamp('11-12-2017 10:55','MM-DD-YYYY HH24:MI'),to_timestamp('11-12-2017 12:35','MM-DD-YYYY HH24:MI'),2);
INSERT INTO MatchInfo
VALUES (23,'Non-Losers','Ball Is Life',23,15,to_timestamp('11-16-2017 19:20','MM-DD-YYYY HH24:MI'),to_timestamp('11-16-2017 20:40','MM-DD-YYYY HH24:MI'),5);
INSERT INTO MatchInfo
VALUES (24,'Non-Losers','Ball Is Life',52,86,to_timestamp('11-18-2017 19:40','MM-DD-YYYY HH24:MI'),to_timestamp('11-18-2017 20:45','MM-DD-YYYY HH24:MI'),2);
INSERT INTO MatchInfo
VALUES (25,'Non-Losers', 'Grannies',15,34,to_timestamp('11-24-2017 9:00','MM-DD-YYYY HH24:MI'),to_timestamp('11-24-2017 10:50','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (26,'Non-Losers', 'Grannies',43,23,to_timestamp('12-11-2017 13:05','MM-DD-YYYY HH24:MI'),to_timestamp('12-11-2017 16:05','MM-DD-YYYY HH24:MI'),3);
INSERT INTO MatchInfo
VALUES (27,'Ball Is Life', 'Grannies',23,25,to_timestamp('12-21-2017 9:00','MM-DD-YYYY HH24:MI'),to_timestamp('12-21-2017 10:50','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (28,'Ball Is Life', 'Grannies',24,73,to_timestamp('12-21-2017 11:00','MM-DD-YYYY HH24:MI'),to_timestamp('12-21-2017 12:00','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (29,'Ball Is Life','Non-Losers',26,34,to_timestamp('12-22-2017 7:45','MM-DD-YYYY HH24:MI'),to_timestamp('12-22-2017 8:25','MM-DD-YYYY HH24:MI'),3);
INSERT INTO MatchInfo
VALUES (30,'Ball Is Life','Non-Losers',45,23,to_timestamp('12-23-2017 14:15','MM-DD-YYYY HH24:MI'),to_timestamp('12-23-2017 16:05','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (31,'Ball Is Life','Hot Cheetos',84,27,to_timestamp('12-25-2017 11:40','MM-DD-YYYY HH24:MI'),to_timestamp('12-25-2017 12:30','MM-DD-YYYY HH24:MI'),1);
INSERT INTO MatchInfo
VALUES (32,'Ball Is Life','Hot Cheetos',34,26,to_timestamp('12-30-2017 20:35','MM-DD-YYYY HH24:MI'),to_timestamp('12-30-2017 21:35','MM-DD-YYYY HH24:MI'),1);