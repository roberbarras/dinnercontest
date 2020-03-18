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
    user_id       bigserial not null,
    access_name   varchar,
    token         varchar,
    creation_date timestamp,
    constraint tokens_pk
        unique (user_id, token)
);

create table groups
(
    group_id      bigserial not null
        constraint groups_pk
            primary key,
    group_name    varchar,
    creation_date timestamp
);

create table user_group
(
	"user" bigserial not null
		constraint user_group_users_fk
			references users
				on update cascade on delete cascade,
	"group" bigserial not null
		constraint user_group_groups_fk
			references groups
				on update cascade on delete cascade,
	creation_date timestamp,
	removal_date timestamp,
	valid boolean,
	constraint user_group_pk
		primary key ("user", "group")
);

create table categories
(
    category_id   bigserial not null
        constraint category_pk
            primary key,
    group_id      bigserial not null
        constraint group_category_groups_group_id_fk
            references groups
            on update cascade on delete cascade,
    category_name varchar   not null,
    weighing      integer   not null,
    user_id       bigserial not null
        constraint category_user_fk
            references users,
    creation_date timestamp,
    removal_date  timestamp
);

create table restaurants
(
	id_restaurant bigserial not null
		constraint restaurant_pk
			primary key,
	name varchar not null,
	host bigserial not null
		constraint restaurant_users_fk
			references users,
	id_group bigserial not null
		constraint restaurant_group_fk
			references groups,
	date timestamp,
	address varchar,
	photo varchar,
	visible boolean default true not null,
	creation_date timestamp,
	visible_date timestamp
);

create table actions
(
	action_id bigserial not null
		constraint actions_pk
			primary key,
	title varchar not null,
	message varchar not null
);

create table notification
(
	notification_id bigserial not null
		constraint notification_pk
			primary key,
	owner_group bigserial not null,
	title varchar not null,
	message varchar not null
);

create unique index group_group_name_uindex
    on groups (group_name);

create unique index users_access_name_uindex
    on users (access_name);

alter table users owner to postgres;

alter table groups owner to postgres;

alter table user_group owner to postgres;

alter table tokens owner to postgres;

alter table restaurant owner to postgres;

alter table actions owner to postgres;