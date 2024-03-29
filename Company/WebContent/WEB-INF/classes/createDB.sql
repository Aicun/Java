create table users (
      username varchar(50) not null,
      password varchar(50) not null,
      enabled integer not null,
      name varchar(50) not null,
      manager_id varchar(50),
      salary DECIMAL,
      primary key (username),
      constraint fk_users_manager foreign key(manager_id) references users(username)
);

create table reports (
	  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
	  title varchar(50) not null,
	  content CLOB (64 K),
	  constraint primary_key primary key (id) 
);

create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username)
);

create table acl_sid (
	id bigint generated by default as identity(start with 100) not null primary key,
	principal integer not null,
	sid varchar(100) not null,
	constraint unique_uk_1 unique(sid,principal) 
);

create table acl_class (
	id bigint generated by default as identity(start with 100) not null primary key,
	class varchar(100) not null,
	constraint unique_uk_2 unique(class) );

create table acl_object_identity (
	id bigint generated by default as identity(start with 100) not null primary key,
	object_id_class bigint not null,
	object_id_identity bigint not null,
	parent_object bigint,
	owner_sid bigint not null,
	entries_inheriting integer not null,
	constraint unique_uk_3 unique(object_id_class,object_id_identity),
	constraint foreign_fk_1 foreign key(parent_object)references acl_object_identity(id),
	constraint foreign_fk_2 foreign key(object_id_class)references acl_class(id),
	constraint foreign_fk_3 foreign key(owner_sid)references acl_sid(id) );

create table acl_entry (
	id bigint generated by default as identity(start with 100) not null primary key,
	acl_object_identity bigint not null,ace_order int not null,sid bigint not null,
	mask integer not null,granting integer not null,audit_success integer not null,
audit_failure integer not null,
constraint unique_uk_4 unique(acl_object_identity,ace_order),
constraint foreign_fk_4 foreign key(acl_object_identity)
references acl_object_identity(id),
constraint foreign_fk_5 foreign key(sid) references acl_sid(id) );	

insert into users(username, password, enabled, name, salary) values ('mary', 'mary', 1, 'Mary', 6000);
insert into users(username, password, enabled, name, salary, manager_id) values ('bob', 'bob', 1, 'Bob', 5500, 'mary');
insert into users(username, password, enabled, name, salary, manager_id) values ('alex', 'alex', 1, 'Alex', 5000, 'bob');
insert into users(username, password, enabled, name, salary, manager_id) values ('dale', 'dale', 1, 'Dale', 6000, 'bob');

insert into authorities(username, authority) values ('alex', 'ROLE_USER'); 
insert into authorities(username, authority) values ('bob', 'ROLE_USER'); 
insert into authorities(username, authority) values ('mary', 'ROLE_USER'); 
insert into authorities(username, authority) values ('bob', 'ROLE_MANAGER'); 
insert into authorities(username, authority) values ('mary', 'ROLE_MANAGER'); 
insert into authorities(username, authority) values ('mary', 'ROLE_PRESIDENT'); 
insert into authorities(username, authority) values ('dale', 'ROLE_USER'); 