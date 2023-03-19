DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
	userId          varchar(12)		NOT NULL,
	password		varchar(12)		NOT NULL,
	name			varchar(20)		NOT NULL,
	email			varchar(50),

	PRIMARY KEY               (userId)
);

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
	questionId 			bigint				auto_increment,
	writer				varchar(30)			NOT NULL,
	title				varchar(50)			NOT NULL,
	contents			varchar(5000)		NOT NULL,
	createdDate			timestamp			NOT NULL,
	countOfAnswer int,
	PRIMARY KEY               (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS (
	answerId 			bigint				auto_increment,
	writer				varchar(30)			NOT NULL,
	contents			varchar(5000)		NOT NULL,
	createdDate			timestamp			NOT NULL,
	questionId			bigint				NOT NULL,
	PRIMARY KEY         (answerId)
);
