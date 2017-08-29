create user 'homestead'@'localhost' identified by 'secret';
grant all privileges on *.* to 'homestead'@'localhost';

create table users(
	id int primary key auto_increment,
	name varchar(50),
	pwd varchar(100)
);

create table images(
	id int primary key auto_increment,
	image_name varchar(50),
	thumbnails blob,
	user_id int,
	foreign key(user_id) references users(id)
);

insert into users(name, pwd) values('Achyut', 'secret');

-- 32 x 32
-- 640 x 480
-- 960 x 720
-- 800 x 600
