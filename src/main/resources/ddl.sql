create table users
(
    user_id     bigint  not null generated always as identity
        constraint users_pk
            primary key,
    user_name   varchar,
    access_name varchar not null
);

create unique index users_access_name_uindex
    on users (access_name);


