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
    num_vacancy int
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

insert into account(first_name, last_name, email, password) VALUES
('Григорьева','Марина', 'mar@yandex.com', 'Qqqq1'),
('Гареев','Артем', 'artem@yandex.com', 'Qqqq2'),
('Ivanov','Ivan', 'ivan@yandex.com', 'Qqqq3');


update post set title = 'Привет', text = 'sjvjsknjnvjksn' where id = 3;
delete from post where id = 52;
drop table account cascade ;
insert into post(text, account_id) VALUES ('hiiii', 2);

select *, post.id as post_id, account.id as account_id from post left join account on post.account_id = account.id where account_id = 2;

select  count(id) from post as count;
select count(id) as count from post where account_id = 3;

insert into subscription(subscriber_id, account_id) VALUES (1,3),
                                                           (1,2);

with cte_subscrip as(select account_id as account_id
                     from account left join subscription s
                         on account.id = s.subscriber_id where id = 3 )
select count(*) from cte_subscrip left join account on cte_subscrip.account_id = account.id where account_id IS NOT NULL;

delete from account where id = 2


