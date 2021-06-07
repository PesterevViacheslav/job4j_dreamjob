CREATE SEQUENCE IF NOT EXISTS post_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS post (
   id INTEGER DEFAULT nextval('post_sq'),
   name TEXT,
   description TEXT,
   created TIMESTAMP default current_timestamp,
   CONSTRAINT pk_post PRIMARY KEY(id)
);
CREATE SEQUENCE IF NOT EXISTS photo_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS photo (
   id INTEGER DEFAULT nextval('photo_sq'),
   name TEXT,
   CONSTRAINT pk_photo PRIMARY KEY(id)
);
CREATE SEQUENCE IF NOT EXISTS city_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS city (
   id INTEGER DEFAULT nextval('city_sq'),
   name TEXT,
   CONSTRAINT pk_city PRIMARY KEY(id)
);
CREATE SEQUENCE IF NOT EXISTS candidate_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS candidate (
   id INTEGER DEFAULT nextval('candidate_sq'),
   name TEXT,
   photo_id INTEGER,
   city_id INTEGER,
   CONSTRAINT pk_candidate PRIMARY KEY(id),
   CONSTRAINT fk_candidate_photo FOREIGN KEY(photo_id) REFERENCES photo(id),
   CONSTRAINT fk_candidate_city FOREIGN KEY(city_id) REFERENCES city(id)
);
CREATE SEQUENCE IF NOT EXISTS users_sq MINVALUE 0 START WITH 0;
CREATE TABLE IF NOT EXISTS users (
   id INTEGER DEFAULT nextval('users_sq'),
   name TEXT,
   email TEXT,
   password TEXT,
   CONSTRAINT pk_users PRIMARY KEY(id)
);
INSERT INTO city VALUES(default,'Arkhangelsk'),(default,'SPB'),(default,'Moscow');
