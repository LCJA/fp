  
    create table  fp_count (
  openid   varchar(40) NOT NULL,
  btn_type  varchar(20) NOT NULL,
  create_date date NOT NULL,
  state int(5),
  ext1 bigint(20),
  ext2 varchar(40),
   ext3 varchar(40),
   ext4 varchar(40),
   ext5 varchar(40)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table fp_count alter column ext1 set default 0;