SET SCHEMA APP;

--DROP TABLE APP.PERSISTENT_LOGIN;

SELECT * FROM APP.PERSISTENT_LOGINS;

select *from sec_user;

INSERT INTO SEC_USER 
(active, username, first_name, last_name, version, password, email, postal_code, city, ID,country) 
values
(true, 'peterneil', 'Peter', 'Neil', 1, 'password', 'peterneil_world@yahoo.co.uk', 'WS14 9ZY', 'Lichfield', 0,1)

select * from sec_user

select id, version, active, LAST_UPDATE_DATE as luts, name as "title_name", code as "title_code" from Logbook


SELECT * FROM APP.SEC_USER;

update APP.SEC_USER set active=false, deleted=true;

SELECT * from SEC_USER where username = 'thenewuser';
select * from SEC_USER_ROLE;

SELECT su.username, 'ROLE_' || UPPER(sr.name), su.deleted, su.active FROM
				APP.sec_user_role sur, APP.sec_user su,
				APP.sec_role sr
				WHERE
				su.ID = sur.user_ID
				AND
				sur.role_ID = sr.ID
				AND
				su.username = 'thenewuser';


SELECT su.username, 'ROLE_' || UPPER(sr.name), su.deleted, su.active FROM
				APP.sec_user_role sur, APP.sec_user su,
				APP.sec_role sr
				WHERE
				su.ID = sur.user_ID
				AND
				sur.role_ID = sr.ID
				AND
				su.username = 'peterneil';
				
SELECT su.username, 'ROLE_' || UPPER(sr.name) FROM
				APP.sec_user_role sur, APP.sec_user su,
				APP.sec_role sr
				WHERE
				su.ID = sur.user_ID
				AND
				sur.role_ID = sr.ID
				AND
				su.username = 'peterneil1'
				
select
        this_.ID as ID1_17_1_,
        this_.version as version2_17_1_,
        this_.username as username3_17_1_,
        this_.ACTIVE as ACTIVE4_17_1_,
        this_.LAST_UPDATE_DATE as LAST5_17_1_,
        this_.email as email6_17_1_,
        this_.password as password7_17_1_,
        this_.first_name as first8_17_1_,
        this_.last_name as last9_17_1_,
        this_.address as address10_17_1_,
        this_.city as city11_17_1_,
        this_.province as provinc12_17_1_,
        this_.country as country13_17_1_,
        this_.postal_code as postal14_17_1_,
        this_.phone_number as phone15_17_1_,
        this_.website as website16_17_1_,
        this_.password_hint as passwor17_17_1_,
        this_.deleted as is18_17_1_,
        roles2_.user_id as user2_17_3_,
        role3_.ID as role1_18_3_,
        role3_.ID as ID1_0_0_,
        role3_.version as version2_0_0_,
        role3_.name as name3_0_0_,
        role3_.description as descript4_0_0_,
        role3_.ACTIVE as ACTIVE5_0_0_,
        role3_.LAST_UPDATE_DATE as LAST6_0_0_ 
    from
        APP.sec_user this_ 
    left outer join
        APP.sec_user_role roles2_ 
            on this_.ID=roles2_.user_id 
    left outer join
        APP.SEC_ROLE role3_ 
            on roles2_.role_id=role3_.ID 
    where
        (
            this_.username='peterneil' 
            and this_.ACTIVE=false
            and this_.deleted=true
        )
				
				