 create table  fp_active (
  openid   varchar(40) NOT NULL,
  active  bigint(40) NOT NULL,
  create_date date NOT NULL,
  state int(5),
  ext1 varchar(40),
  ext2 varchar(40),
  ext3 varchar(40),
  ext4 varchar(40),
  ext5 varchar(40)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE PROCEDURE  execute_query_active
(in i_openid varchar(40),out v_avg bigint,
out v_max bigint,
out v_min bigint,
out v_all bigint)
BEGIN
 select avg(active) into v_avg from fp_active where openid=i_openid and state=1;
 select active into v_max from fp_active where openid=i_openid and state=1 group by active desc;
 select active into v_min  from fp_active where openid=i_openid and state=1 group by active ;
 select sum(active) into v_all  from fp_active where openid=i_openid and state=1;
END ;

call execute_query_active('asdas',@v_avg,@v_max,@v_min,@v_all)

select @v_avg,@v_max,@v_min,@v_all from dual
