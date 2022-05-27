DROP TABLE person IF EXISTS;

CREATE TABLE person  (
    person_id BIGINT NOT NULL auto_increment PRIMARY KEY,
    firstName VARCHAR(20),
    lastName VARCHAR(20)
);
