create table users
(
    user_id     bigserial
        constraint users_pk
            primary key,
    user_name   varchar,
    access_name varchar not null,
    creation_date timestamp
);

alter table users
    owner to postgres;

create unique index users_access_name_uindex
    on users (access_name);
