create table account(
    id serial primary key,
    first_name varchar,
    last_name varchar,
    email varchar,
    password varchar
);

create table comment(
    id serial primary key,
    text varchar,
    num_vacancy int
);


drop table account;