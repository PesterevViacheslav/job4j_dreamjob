CREATE SEQUENCE IF NOT EXISTS post_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS post (
   id INTEGER DEFAULT nextval('post_sq'),
   name TEXT,
   description TEXT,
   created TIMESTAMP default current_timestamp,
   CONSTRAINT pk_post PRIMARY KEY(id)
);
CREATE SEQUENCE IF NOT EXISTS candidate_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS candidate (
   id INTEGER DEFAULT nextval('candidate_sq'),
   name TEXT,
   CONSTRAINT pk_candidate PRIMARY KEY(id)
);