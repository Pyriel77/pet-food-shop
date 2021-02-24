--CREATE TABLE IF NOT EXISTS foodstock (id serial, foodid int, quantity int, price int);
--DROP TABLE petfood;

CREATE TABLE IF NOT EXISTS petfood (id serial primary key,foodtype text, itemname text,brand text,expiration date);
INSERT INTO petfood (foodtype,itemname,brand,expiration)
VALUES  ('CATFOOD','BeautyFood','Wishcash','2021-04-24'),
		('CATFOOD','MeaoFood','FreshNapf','2021-04-24'),
		('DOGFOOD','SpiceFood','FreshNapf','2021-04-24'),
		('DOGFOOD','SpiceFood','Wishcash','2000-02-22'),
		('DOGFOOD','SpiceFood','Wishcash','2021-04-24'),
		('FISHFOOD','SweetWorms','FreshNapf','2021-04-24'),
		('CATFOOD','SpiceFood','Wishcash','2000-02-22');

select * from petfood;