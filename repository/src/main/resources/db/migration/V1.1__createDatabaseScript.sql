ALTER TABLE IF EXISTS ONLY public.tour DROP CONSTRAINT IF EXISTS tour_hotel_id_fkey;
ALTER TABLE IF EXISTS ONLY public.tour DROP CONSTRAINT IF EXISTS tour_country_id_fkey;
-- ALTER TABLE IF EXISTS ONLY public.review DROP CONSTRAINT IF EXISTS review_user_id_fkey;
-- ALTER TABLE IF EXISTS ONLY public.review DROP CONSTRAINT IF EXISTS review_tour_id_fkey;
-- ALTER TABLE IF EXISTS ONLY public.usertour DROP CONSTRAINT IF EXISTS usertour_user_id_fkey;
-- ALTER TABLE IF EXISTS ONLY public.usertour DROP CONSTRAINT IF EXISTS usertour_tour_id_fkey;
-- DROP TABLE IF EXISTS "tour";
-- DROP TABLE IF EXISTS "review";
-- DROP TABLE IF EXISTS "usertour";
-- DROP TABLE IF EXISTS "users";
-- DROP TABLE IF EXISTS "country";
-- DROP TABLE IF EXISTS "hotel";
--
-- DROP SCHEMA public CASCADE;
-- CREATE SCHEMA public;
--
-- SET search_path = public;

create type userrole as enum
('ADMIN', 'MEMBER');

CREATE TABLE users (
id SERIAL PRIMARY KEY,
login varchar(255) NOT NULL,
password varchar(200) NOT NULL,
user_role userrole NOT NULL,
version integer DEFAULT 0 NOT NULL
);

CREATE TABLE country (
id serial primary key,
name varchar(100) NOT NULL,
version integer DEFAULT 0 NOT NULL
);

create type features as enum
('BALCONY', 'BREAKFAST', 'CONDITIONER', 'DRYER', 'PROJECTOR',
 'RADIO', 'SHOWER', 'TV', 'WC', 'WIFI');

CREATE TABLE hotel (
id SERIAL PRIMARY KEY,
name varchar(255) NOT NULL,
stars integer NOT NULL,
website varchar(255) NOT NULL,
latitude varchar(30) NOT NULL,
longitude varchar(30) NOT NULL,
features features[] NOT NULL,
version integer DEFAULT 0 NOT NULL
);

create type tourtype as enum
('ADVENTURE', 'CULINARY', 'CULTURAL', 'CYCLE', 'FAMILY',
 'HOLIDAY', 'HONEYMOON', 'INDULGENT', 'ROMANTIC', 'SEASONAL');

CREATE TABLE tour (
id SERIAL PRIMARY KEY,
photo varchar(100) NOT NULL,
date timestamp NOT NULL,
duration integer NOT NULL,
description varchar(1000) NOT NULL,
cost integer NOT NULL,
tour_type tourtype NOT NULL,
hotel_id integer NOT NULL,
country_id integer NOT NULL,
FOREIGN KEY (hotel_id) REFERENCES hotel(id),
FOREIGN KEY (country_id) REFERENCES country(id),
version integer DEFAULT 0 NOT NULL
);

CREATE TABLE usertour(
user_id int NOT NULL,
tour_id int NOT NULL,
PRIMARY KEY (user_id, tour_id),
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (tour_id) REFERENCES tour(id)
);

CREATE TABLE review (
id SERIAL PRIMARY KEY,
date timestamp NOT NULL,
text varchar(255) NOT NULL,
user_id integer NOT NULL,
tour_id integer NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (tour_id) REFERENCES tour(id),
version integer DEFAULT 0 NOT NULL
);