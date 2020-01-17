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

create table groups
(
    group_id     bigserial
        constraint groups_pk
            primary key,
	group_name varchar
);

create table user_group
(
	"user" bigserial
		constraint user_group_users_fk
			references users
				on update cascade on delete cascade,
	"group" bigserial
		constraint user_group_groups_fk
			references groups
				on update cascade on delete cascade,
	constraint user_group_pk
		primary key ("user", "group")
);



create unique index users_access_name_uindex
    on users (access_name);

create unique index group_group_name_uindex
    on groups (group_name);

alter table tokens owner to postgres;

alter table users owner to postgres;

alter table groups owner to postgres;

alter table user_group owner to postgres;
