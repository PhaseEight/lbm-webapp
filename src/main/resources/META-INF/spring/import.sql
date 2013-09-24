--SET SCHEMA = APP;

-- Clear Down the Databas
DELETE FROM ORGANISATION_COUNTRY;
DELETE FROM ORGANISATION;
DELETE FROM ORGANISATION_COUNTRY; 
DELETE FROM ENTRY_ATTRIBUTE;
DELETE FROM PERSON_ENTRY;
DELETE FROM PERSON_LOGBOOK; 
DELETE FROM DETAIL_ATTRIBUTE; 
DELETE FROM DETAIL;
DELETE FROM LOGBOOK;
DELETE FROM PERSON; 
DELETE FROM COUNTRY;
DELETE FROM SEC_USER_ROLE;
DELETE FROM SEC_ROLE;
DELETE FROM SEC_USER;


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


-- insert persons
INSERT INTO PERSON (id, version, sec_user_id, country_id) values(2,1,1,1);
INSERT INTO PERSON (id, version, sec_user_id, country_id) values(3,1,2,2);
INSERT INTO PERSON (id, version, sec_user_id, country_id) values(4,1,3,1);

-- insert Details
INSERT INTO DETAIL (id, VERSION, NAME, display_value) values(1,0,'exposure_suit', 'Exposure suite');
INSERT INTO DETAIL (id, VERSION, NAME, display_value) values(2,0,'cylinder', 'Cylinder');
INSERT INTO DETAIL (id, VERSION, NAME, display_value) values(3,0,'propelsion', 'Propelsion');
INSERT INTO DETAIL_ATTRIBUTE (id, version, detail_id, name, common_name) values(1, 0, 2, 'start_pressure','Start pressure');

-- insert logbooks
INSERT INTO LOGBOOK (id, VERSION,CODE, NAME) values (1,1,'dm_logbook', 'Divemaster Logbook');
INSERT INTO LOGBOOK (id, VERSION,CODE, NAME) values (5,1,'coarse_fishing_logbook', 'Coarse Fishing Logbook');

INSERT INTO PERSON_LOGBOOK (id, version, logbook_id, person_id, name) values(1,1,1,2,'Scuba Diving Logbook');
INSERT INTO PERSON_LOGBOOK (id, version, logbook_id, person_id, name) values(2,1,5,3,'Carp Fishing Logbook');

INSERT INTO PERSON_ENTRY (id, version, person_id, entry_number, person_logbook_id) values(1, 0, 2, 1, 2);
INSERT INTO ENTRY_ATTRIBUTE (id, version, detail_attribute_id, person_entry_id, display_value, sort_value) values(1, 0, 1, 1, '100', 100);

INSERT INTO ORGANISATION (id, VERSION, NAME, common_name, head_office_address_1, head_office_address_2, head_office_address_3, post_code, email) values(1, 0, 'Professional Association of Diving Instructors', 'PADI','ho1', 'ho2', 'ho3', 'pc2', 'head-contact@padi.com');
INSERT INTO ORGANISATION_COUNTRY (id, version, phone, fax, organisation_id, country_id) values(1, 0, '0044020710810', '0044020710810', 1, 1);

--SELECT * from SEC_ROLE;
--SELECT * FROM SEC_USER;
--SELECT * FROM SEC_USER_ROLE;
--SELECT * FROM PERSON;
--SELECT * FROM DETAIL;
--SELECT * FROM DETAIL_ATTRIBUTE;
--SELECT * FROM LOGBOOK;
--SELECT * FROM PERSON_LOGBOOK;
--SELECT * FROM PERSON_ENTRY;
--SELECT * FROM ENTRY_ATTRIBUTE;
--SELECT * FROM ORGANISATION;
--SELECT * FROM COUNTRY;-
--SELECT * FROM ORGANISATION_COUNTRY;