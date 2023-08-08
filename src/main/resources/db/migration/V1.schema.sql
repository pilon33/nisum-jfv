drop table if exists "phones" CASCADE;
drop table if exists "users" CASCADE;

CREATE TABLE "users"
(
    "uuid"             uuid          not null,
    "version"          long          not null,
    "created_at"       datetime      not null,
    "last_modified_at" datetime,
    "deleted_at"       datetime,
    "is_deleted"       boolean,
    "name"             varchar(100)  not null,
    "email"            varchar(100)  not null,
    "password"         varchar(100)  not null,
    "token"            varchar(5000) not null,
    "is_active"        boolean       not null default true,
    "last_login" datetime,
    primary key ("uuid")
);

create table "phones"
(
    "uuid"             uuid         not null,
    "version"          long         not null,
    "created_at"       datetime     not null,
    "last_modified_at" datetime,
    "deleted_at"       datetime,
    "is_deleted"       boolean,
    "user_uuid"        uuid,
    "number"           varchar(100) not null,
    "city_code"        varchar(10)  not null,
    "country_code"     varchar(10)  not null,
    primary key ("uuid")
);

alter table "phones"
    add constraint "FKgwdq888r5nwtgf7smd9beq1ix" foreign key ("user_uuid") references "users";


