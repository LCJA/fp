create table vension(
id bigint(40)  AUTO_INCREMENT,
v_id varchar(40) unique,
v_context varchar(800),
create_date timestamp ,
update_date timestamp ,
state int(10) ,
ext1 varchar(40),
ext2 varchar(400),
ext3 varchar(400),
ext4 varchar(400),
ext5 varchar(400),
 PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
