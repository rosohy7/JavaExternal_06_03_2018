#DROP DATABASE CalendarEvents;
#SET SQL_SAFE_UPDATES=0;
#SHOW CHARACTER SET LIKE 'utf8%';
CREATE DATABASE IF NOT EXISTS CalendarEvents CHARACTER SET utf8;
USE CalendarEvents;

CREATE TABLE IF NOT EXISTS dates (
calendar_date DATE PRIMARY KEY,
is_business_day BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS calendar_events(
id INT auto_increment PRIMARY KEY,
description varchar(200) DEFAULT 'no description',
start_time time DEFAULT '000:00:00',
length time DEFAULT '000:00:00',
calendar_date DATE,
FOREIGN KEY(calendar_date) 
REFERENCES dates(calendar_date)
ON UPDATE CASCADE ON DELETE NO ACTION
);
#INSERT INTO calendar_events(description,start_time) VALUES('Встреча Кирилла Лица','10:0:0');
SELECT * FROM calendar_events;
SELECT * FROM dates;
#DELETE FROM calendar_events;