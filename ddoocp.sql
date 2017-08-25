create table users(
	id int primary key auto_increment,
	name varchar(50),
	pwd varchar(100)
);

create table images(
	id int primary key auto_increment,
	image blob,
	user_id int,
	foreign key(user_id) references users(id)
);

-- 640 x 480
-- 960 x 720
-- 800 x 600
