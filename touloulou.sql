create database touloulou;
use touloulou;
create table employee (
	id int not null auto_increment,
    firstname varchar(128) not null,
    lastname varchar(128) not null,
    email varchar(45) not null,
    age tinyint unsigned not null,
    roleTitle varchar(45) not null,
    phoneNumber varchar(10) not null,
    address varchar(128) not null,
    primary key (id),
    unique key id_unique (id),
    unique key email_unique (email)
) engine InnoDB;