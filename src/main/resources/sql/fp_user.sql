create table  fp_user (
 user_id  bigint(20) NOT NULL AUTO_INCREMENT, 
 user_name varchar(20) NOT NULL,
 pass_wrod varchar(20) NOT NULL,
 create_date date,
 update_date date,
 state int(5),
 role_id bigint(20),
 PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;