SET SCHEMA = APP;
--DELETE FROM PERSON_QUALIFICATION;
--DELETE FROM PERSON_ENTRY;
--DELETE FROM PERSON_LOGBOOK_DETAIL;
--DELETE FROM PERSON_LOGBOOK;
--DELETE FROM PERSON;

--DROP TABLE PERSON_QUALIFICATION;
--DROP TABLE PERSON_ENTRY;
--DROP TABLE PERSON_LOGBOOK_DETAIL;
--DROP TABLE PERSON_LOGBOOK;
--DROP TABLE PERSON;

-- Clear Down the Databas
DELETE FROM ORGANISATION_COUNTRY;
DELETE FROM ORGANISATION;
DELETE FROM ORGANISATION_COUNTRY; 
DELETE FROM ENTRY_ATTRIBUTE;
DELETE FROM LOGBOOKUSER_LOGBOOK_ENTRY;
DELETE FROM LOGBOOKUSER_LOGBOOK; 
--DELETE FROM DETAIL_ATTRIBUTE; 
--DELETE FROM DETAIL;
DELETE FROM LOGBOOK;
DELETE FROM LOGBOOKUSER; 
DELETE FROM SEC_USER_ROLE;
DELETE FROM SEC_ROLE;

DELETE FROM SEC_USER;
DELETE FROM COUNTRY;


INSERT INTO COUNTRY (id, VERSION, ansi_code, display_name) values(1,0,'ZA', 'South Africa');
INSERT INTO COUNTRY (id, VERSION, ansi_code, display_name) values(2,0,'GB', 'United Kingdom');


-- Insert Roles
INSERT INTO SEC_ROLE (ID, VERSION, NAME, DESCRIPTION, ACTIVE, LAST_UPDATE_DATE) values (3, 1, 'LOGBOOKUSER', 'Logbook user role', true, CURRENT_TIMESTAMP);
INSERT INTO SEC_ROLE (ID, VERSION, NAME, DESCRIPTION, ACTIVE, LAST_UPDATE_DATE) values (2, 1, 'ADMIN', 'Logbook admin role', true, CURRENT_TIMESTAMP);
INSERT INTO SEC_ROLE (ID, VERSION, NAME, DESCRIPTION, ACTIVE, LAST_UPDATE_DATE) values (1, 1, 'GUESTUSER', 'Logbook guest role', true, CURRENT_TIMESTAMP);

--insert SecUsers
INSERT INTO SEC_USER (active, username, first_name, last_name, version, password, email, ID, locale) values(true, 'peterneil', 'Peter', 'Neil', 1, 'password', 'peterneil_world@yahoo.co.uk', 0, 'en_GB');
INSERT INTO SEC_USER (active, username, first_name, last_name, version, password, email, ID, locale) values(true, 'peterneiladmin', 'Peter', 'Neil', 1, 'password', 'saffadiver@yahoo.co.uk', 1, 'en_GB');
INSERT INTO SEC_USER (active, username, first_name, last_name, version, password, email, ID, locale) values(true, 'todelete', 'Peter', 'Neil', 1, 'password', 'todelete@yahoo.co.uk', 2, 'en_GB');
INSERT INTO SEC_USER (active, username, first_name, last_name, version, password, email, ID, locale) values(true, 'peterneil3', 'Peter', 'Neil', 1, 'password', 'peterneil3_world@yahoo.co.uk', 3, 'en_GB');
INSERT INTO SEC_USER (active, username, first_name, last_name, version, password, email, ID, locale) values(true, 'samplelogbookuser', 'Logbook', 'User', 1, 'password', 'peterneil-savedAndUpdated@yahoo.co.uk', 4, 'en_GB');
INSERT INTO SEC_USER (active, username, first_name, last_name, version, password, email, ID, locale) values(true, 'sampleguestuser', 'Logbook', 'Guest', 1, 'password', 'logbookguest@yahoo.co.uk', 9, 'en_GB');

--peterneil
INSERT INTO SEC_USER_ROLE (user_id, role_id) values(0,3);
INSERT INTO SEC_USER_ROLE (user_id, role_id) values(0,1); 

--peterneiladmin
INSERT INTO SEC_USER_ROLE (user_id, role_id) values(1,2);
INSERT INTO SEC_USER_ROLE (user_id, role_id) values(1,3);
INSERT INTO SEC_USER_ROLE (user_id, role_id) values(1,1);


-- insert LOGBOOKUSERs
INSERT INTO LOGBOOKUSER (id, version, sec_user_id, country_id) values(2,1,1,1);
INSERT INTO LOGBOOKUSER (id, version, sec_user_id, country_id) values(3,1,2,2);
INSERT INTO LOGBOOKUSER (id, version, sec_user_id, country_id) values(4,1,3,1);

-- insert logbooks
INSERT INTO LOGBOOK (id, VERSION,CODE, NAME) values (1,1,'dm_logbook', 'Divemaster Logbook');
INSERT INTO LOGBOOK (id, VERSION,CODE, NAME) values (5,1,'coarse_fishing_logbook', 'Coarse Fishing Logbook');

INSERT INTO LOGBOOKUSER_LOGBOOK (id, version, logbook_id, LOGBOOKUSER_id, name) values(1,1,1,2,'Scuba Diving Logbook');
INSERT INTO LOGBOOKUSER_LOGBOOK (id, version, logbook_id, LOGBOOKUSER_id, name) values(2,1,5,3,'Carp Fishing Logbook');

INSERT INTO LOGBOOKUSER_LOGBOOK_ENTRY (id, version, LOGBOOKUSER_id, entry_number, LOGBOOKUSER_logbook_id) values(1, 0, 2, 1, 2);

INSERT INTO ORGANISATION (id, VERSION, name, code, name_or_number, street, province, postal_code, email, country_id) values(1, 0, 'Professional Association of Diving Instructors', 'PADI','ho1', 'ho2', 'ho3', 'pc2', 'head-contact@padi.com',1);
INSERT INTO ORGANISATION_COUNTRY (id, version, phone, fax, organisation_id, country_id) values(1, 0, '0044020710810', '0044020710810', 1, 1);


-- insert Details
--INSERT INTO DETAIL (id, VERSION, NAME, display_value) values(1,0,'exposure_suit', 'Exposure suite');
--INSERT INTO DETAIL (id, VERSION, NAME, display_value) values(2,0,'cylinder', 'Cylinder');
--INSERT INTO DETAIL (id, VERSION, NAME, display_value) values(3,0,'propelsion', 'Propelsion');
--INSERT INTO DETAIL_ATTRIBUTE (id, version, detail_id, name, common_name) values(1, 0, 2, 'start_pressure','Start pressure');

-- This is the user's captured value for their dive's start_pressure
--INSERT INTO ENTRY_ATTRIBUTE (id, version, detail_attribute_id, LOGBOOKUSER_LOGBOOK_entry_id, display_value, sort_value) values(1, 0, 1, 1, '100', 100);

--SELECT * from SEC_ROLE;
--SELECT * FROM SEC_USER;
--SELECT * FROM SEC_USER_ROLE;
--SELECT * FROM LOGBOOKUSER;
--SELECT * FROM DETAIL;
--SELECT * FROM DETAIL_ATTRIBUTE;
--SELECT * FROM LOGBOOK;
--SELECT * FROM LOGBOOKUSER_LOGBOOK;
--SELECT * FROM LOGBOOKUSER_LOGBOOK_ENTRY;
--SELECT * FROM ENTRY_ATTRIBUTE;
--SELECT * FROM ORGANISATION;
--SELECT * FROM COUNTRY;-
--SELECT * FROM ORGANISATION_COUNTRY;
