# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                            bigint auto_increment not null,
  nick                          varchar(255),
  age                           integer,
  sex                           varchar(255),
  birth                         datetime(6),
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists user;

