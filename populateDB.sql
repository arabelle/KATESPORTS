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
	CONSTRAINT team_pk PRIMARY KEY (team_name)
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
	CONSTRAINT ref_pk PRIMARY KEY(ref_id)
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
VALUES(5, 'Emilio Enrique', '400-895-5745', 15, 124, 10, 5, 'Non-Losers');

CREATE TABLE Rental (
	rental_id NUMBER(10) NOT NULL,
	player_id NUMBER(10) NOT NULL,
	item_id NUMBER(10) NOT NULL,
	CONSTRAINT rental_pk PRIMARY KEY (rental_id),
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
	CONSTRAINT ref_fk FOREIGN KEY (ref_id) REFERENCES Referee(ref_id) ON DELETE SET NULL,
	CONSTRAINT home_fk FOREIGN KEY (home_team) REFERENCES Team(team_name),
	CONSTRAINT away_fk FOREIGN KEY (away_team) REFERENCES Team(team_name),
	CONSTRAINT time_check CHECK (end_time > start_time)
);

grant select on MatchInfo to public;

INSERT INTO MatchInfo
VALUES(1, 'Non-Losers', 'Grannies', NULL, NULL, to_timestamp('01-12-2017 19:45','MM-DD-YYYY HH24:MI'), NULL, 4);
INSERT INTO MatchInfo
VALUES(2, 'Hot Cheetos', 'Non-Losers', NULL, NULL, to_timestamp('01-12-2017 15:30','MM-DD-YYYY HH24:MI'), NULL, 5);
INSERT INTO MatchInfo
VALUES(3, 'Hot Cheetos', 'Grannies', NULL, NULL, to_timestamp('01-12-2017 12:00','MM-DD-YYYY HH24:MI'), NULL, 5);
INSERT INTO MatchInfo
VALUES(4, 'Ball Is Life', 'Hot Cheetos', NULL, NULL, to_timestamp('01-14-2017 14:00','MM-DD-YYYY HH24:MI'), NULL, 1);
INSERT INTO MatchInfo
VALUES(5, 'Ball Is Life', 'AndroidT', NULL, NULL, to_timestamp('01-14-2017 11:00','MM-DD-YYYY HH24:MI'), NULL, 1);
