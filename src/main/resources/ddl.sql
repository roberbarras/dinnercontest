create table users
(
    user_id     bigserial
        constraint users_pk
            primary key,
    user_name   varchar,
    access_name varchar not null,
    password varchar not null,
    creation_date timestamp,
    last_login timestamp,
    email varchar,
    local_privacy int,
    global_privacy int
);

create table tokens
(
	access_name varchar not null
		constraint tokens_pk
			primary key,
	token varchar
);


alter table users
    owner to postgres;

create unique index users_access_name_uindex
    on users (access_name);
