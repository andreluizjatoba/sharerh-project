-- data loads for h2 test

--create table tb_account(
--  id bigint not null auto_increment,
--  name_owner varchar(50) not null,
--  agency_code varchar(4) not null,
--  number_account varchar(8) not null,
--  digit_verification varchar(3) not null,
--  register_id varchar(20) not null,
--  unique (number_account),
--  unique (register_id),
--  primary key (id)
-- );

insert into tb_account(id, name_owner, agency_code, number_account, digit_verification, register_id)
  values (1, 'pedro luiz', '1', '001', '0', '1');

insert into tb_account(id, name_owner, agency_code, number_account, digit_verification, register_id)
  values (2, 'joão luiz', '2', '002', '1', '2');
 
insert into tb_account(id, name_owner, agency_code, number_account, digit_verification, register_id)
  values (3, 'andré luiz', '1', '003', '0', '3');
