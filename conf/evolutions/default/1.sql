# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin_data (
  username                      varchar(255),
  password                      varchar(255),
  privilege                     varchar(255)
);

create table competition_results (
  presenter                     varchar(255),
  ui_design                     integer,
  user_experience               integer,
  practicality                  integer,
  originality                   integer,
  presentation                  integer,
  comments                      varchar(255)
);

create table users (
  username                      varchar(255)
);


# --- !Downs

drop table if exists admin_data;

drop table if exists competition_results;

drop table if exists users;

