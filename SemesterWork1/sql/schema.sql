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

create table post(
    id serial primary key,
    title varchar,
    text varchar,
    account_id int,
    foreign key (account_id) references account (id)
);

create table subscription(
     subscriber_id int,
     foreign key (subscriber_id) references account (id),
     account_id int,
     foreign key (account_id) references account (id),
     primary key (subscriber_id, account_id)
);

update post set title = 'bvhsbj', text = 'sjvjsknjnvjksn' where id = 2;
delete from post where id = 52;
drop table post;
insert into post(text, account_id) VALUES ('hiiii', 2);

select *, post.id as post_id, account.id as account_id from post left join account on post.account_id = account.id where account_id = 2;

select  count(id) from post as count;
select count(id) as count from post where account_id = 3