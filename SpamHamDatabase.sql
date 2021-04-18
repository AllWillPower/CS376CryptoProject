CREATE DATABASE IF NOT EXISTS spamFilterData;
USE spamFilterData;

/* removes all contents of database baseData to refill for new executions */
/*DROP TABLE baseData;*/
CREATE TABLE IF NOT EXISTS learnData(
id int primary key,
flag Varchar(4),
message Varchar(160)
);


CREATE TABLE IF NOT EXISTS baseData(
id int primary key,
message Varchar(160)
);

CREATE TABLE IF NOT EXISTS ham(
id int primary key,
message Varchar(160)
);

CREATE TABLE IF NOT EXISTS spam(
id int primary key,
message Varchar(160)
);

/*
INSERT INTO baseData (id, message)
VALUES (1, 'text');
*/

/*   values for testing data
INSERT INTO baseData (id, message)
VALUES (1, 'Go until jurong point, crazy.. Available only in bugis n great world la e buffet... Cine there got amore wat...');

INSERT INTO baseData (id, message)
VALUES (2, 'Ok lar... Joking wif u oni...');

INSERT INTO baseData (id, message)
VALUES (3, 'Free entry in 2 a wkly comp to win FA Cup final tkts 21st May 2005. Text FA to 87121 to receive entry question(std txt rate)T&Cs apply 08452810075over18s');

INSERT INTO baseData (id, message)
VALUES (4, 'U dun say so early hor... U c already then say...');

INSERT INTO baseData (id, message)
VALUES (5, 'Nah I dont think he goes to usf, he lives around here though');

INSERT INTO baseData (id, message)
VALUES (6, 'FreeMsg Hey there darling its been 3 weeks now and no word back! Id like some fun you up for it still? Tb ok! XxX std chgs to send, å£1.50 to rcv');

INSERT INTO baseData (id, message)
VALUES (7, 'Even my brother is not like to speak with me. They treat me like aids patent.');

INSERT INTO baseData (id, message)
VALUES (8, 'As per your request Melle Melle (Oru Minnaminunginte Nurungu Vettam) has been set as your callertune for all Callers. Press *9 to copy your friends Callertune');

INSERT INTO baseData (id, message)
VALUES (9, 'WINNER!! As a valued network customer you have been selected to receivea å£900 prize reward! To claim call 09061701461. Claim code KL341. Valid 12 hours only.');

INSERT INTO baseData (id, message)
VALUES (10, 'Had your mobile 11 months or more? U R entitled to Update to the latest colour mobiles with camera for Free! Call The Mobile Update Co FREE on 08002986030');
*/

/*
INSERT INTO learnData (id, flag, message)
VALUES (1, spam, "This is a spam or ham message");
*/

/*
INSERT INTO learnData (id, flag, message)
VALUES (1, "ham", "This is a spam or ham message");

INSERT INTO learnData (id, flag, message)
VALUES (2, "spam", "Free entry in 2 a wkly comp to win FA Cup final tkts 21st May 2005. Text FA to 87121 to receive entry question(std txt rate)T&C's apply 08452810075over18's");

INSERT INTO learnData (id, flag, message)
VALUES (3, "ham", "Nah I don't think he goes to usf, he lives around here though");

INSERT INTO learnData (id, flag, message)
VALUES (4, "spam", "FreeMsg Hey there darling it's been 3 week's now and no word back! I'd like some fun you up for it still? Tb ok! XxX std chgs to send, å£1.50 to rcv");

INSERT INTO learnData (id, flag, message)
VALUES (5, "ham", "As per your request 'Melle Melle (Oru Minnaminunginte Nurungu Vettam)' has been set as your callertune for all Callers. Press *9 to copy your friends Callertune");

INSERT INTO learnData (id, flag, message)
VALUES (6, "ham", "I'm gonna be home soon and i don't want to talk about this stuff anymore tonight, k? I've cried enough today.");

INSERT INTO learnData (id, flag, message)
VALUES (7, "spam", "XXXMobileMovieClub: To use your credit, click the WAP link in the next txt message or click here>> http://wap. xxxmobilemovieclub.com?n=QJKGIGHJJGCBL");

INSERT INTO learnData (id, flag, message)
VALUES (8, "spam", "England v Macedonia - dont miss the goals/team news. Txt ur national team to 87077 eg ENGLAND to 87077 Try:WALES, SCOTLAND 4txt/Ì¼1.20 POBOXox36504W45WQ 16+");
*/




SELECT * FROM learnData;


/* SELECT * FROM baseData; */