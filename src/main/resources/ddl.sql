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
	access_name varchar,
	token varchar,
	constraint tokens_pk
		unique (access_name, token)
);

alter table tokens owner to postgres;




alter table users
    owner to postgres;

create unique index users_access_name_uindex
    on users (access_name);
