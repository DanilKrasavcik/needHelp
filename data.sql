
create table user (
                      id bigint auto_increment primary key ,
                      name varchar(255) not null ,
                      age int not null ,
                      email varchar(255) not null ,
                      password varchar(255) not null ,
                      username varchar(255)
);

create table roles (
                       id bigint primary key auto_increment,
                       name varchar(255)
);

ALTER TABLE roles
    ADD UNIQUE (name);

insert into roles(name) values ('ROLE_USER', 'ROLE_ADMIN');

create table user_role (
                           users_id bigint ,
                           roles_id bigint
);

alter table user_role
    add foreign key (users_id) references user(id);
alter table user_role
    add foreign key (roles_id) references roles(id);

insert into user_role (users_id, roles_id) VALUES (1, 1);
insert into user_role (users_id, roles_id) VALUES (2, 1);
insert into user_role (users_id, roles_id) VALUES (2, 2);