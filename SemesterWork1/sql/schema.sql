create table account(
    id serial primary key,
    first_name varchar,
    last_name varchar,
    email varchar,
    password varchar,
    token varchar
);

create table comment(
    id serial primary key,
    text text,
    account_id int,
    foreign key (account_id) references account (id) on delete cascade,
    num_vacancy int,
    post_id int,
    foreign key (post_id) references post (id) on delete cascade
);

create table post(
    id serial primary key,
    title varchar,
    text text,
    account_id int,
    foreign key (account_id) references account (id) on delete cascade
);

create table subscription(
     subscriber_id int,
     foreign key (subscriber_id) references account (id) on delete cascade,
     account_id int,
     foreign key (account_id) references account (id) on delete cascade,
     primary key (subscriber_id, account_id)
);

-- password: Qqqq1
insert into account(first_name, last_name, email, password)
values  ('Ivan', 'Ivanov', 'ivanov@gmail.com', '$2a$10$j.I8iYMhrvPFEo/tk9nAPewuFrgLhpN2eLPuHDjsq6rM/rIk8ehRG')
