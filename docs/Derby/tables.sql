SET SCHEMA=APP;
DROP TABLE persistent_logins;

CREATE TABLE persistent_logins (series VARCHAR(64) PRIMARY KEY,
									username VARCHAR(64) NOT NULL,
                                    token VARCHAR(64) NOT NULL,
                                    last_used TIMESTAMP NOT NULL)

create table APP.persistent_logins (series varchar(255) not null, last_used TIMESTAMP DEFAULT CURRENT_TIMESTAMP, token varchar(255), username varchar(255), primary key (series))
                                    
SELECT * FROM persistent_logins;