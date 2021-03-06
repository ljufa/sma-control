/**
  SMA schema - application
 */
create schema sma;

create table sma.user
(
    id      integer GENERATED BY DEFAULT AS IDENTITY not null primary key,
    auth_id text not null unique,
    created_date timestamp not null default current_timestamp
);

create table sma.external_account_type
(
    id   integer not null primary key,
    name text    not null
);

create table sma.account
(
    id                    text    not null primary key,
    user_id               integer    not null,
    external_account_id   text    not null,
    external_account_type integer not null,
    foreign key (external_account_type) references sma.external_account_type (id)
);

/**
  Twitter schema
 */
create schema twitter;
create table twitter.user_language
(
    language text not null,
    user_id  integer not null,
    foreign key (user_id) references sma.user (id)
);
create table twitter.user_author
(
    tw_user_id text not null,
    user_id    integer not null,
    foreign key (user_id) references sma.user (id)
);
create table twitter.user_hashtag
(
    tag     text not null,
    user_id integer not null,
    foreign key (user_id) references sma.user (id)
);
create table twitter.user_place
(
    place   text not null,
    user_id integer not null,
    foreign key (user_id) references sma.user (id)
);
create table twitter.user_filtered_search_rule
(
    rule_id    text not null,
    rule_query text not null,
    rule_tag   text not null,
    user_id    integer not null,
    foreign key (user_id) references sma.user (id)
);