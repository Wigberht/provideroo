create table account
(
  id int auto_increment
    primary key,
  balance decimal(15,2) default '0.00' not null,
  currency_shortname char(3) default 'UAH' not null,
  currency_symbol char null
)
;

create table chat
(
  id int auto_increment
    primary key,
  title varchar(50) null,
  constraint chat_id_uindex
  unique (id)
)
;

create table chat_user
(
  user_id int null,
  chat_id int null,
  constraint chat_user_chat_id_fk
  foreign key (chat_id) references chat (id)
)
;

create index chat_user_chat_id_fk
  on chat_user (chat_id)
;

create index chat_user_user_id_fk
  on chat_user (user_id)
;

create table message
(
  id int auto_increment
    primary key,
  message text not null,
  was_read tinyint(1) default '0' null,
  updated_at datetime default CURRENT_TIMESTAMP null,
  created_at datetime default CURRENT_TIMESTAMP null,
  user_id int not null,
  chat_id int not null,
  constraint message_id_uindex
  unique (id),
  constraint message_chat_id_fk
  foreign key (chat_id) references chat (id)
    on update cascade on delete cascade
)
;

create index message_user_id_fk
  on message (user_id)
;

create index message_chat_id_fk
  on message (chat_id)
;

create table role
(
  id int auto_increment
    primary key,
  name varchar(50) null,
  constraint role_id_uindex
  unique (id)
)
;

create table service
(
  id int auto_increment
    primary key,
  title varchar(50) not null,
  constraint service_id_uindex
  unique (id)
)
;

create table subscriber
(
  id int auto_increment
    primary key,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  birth_date date not null,
  user_id int null,
  account_id int null,
  constraint subscriber_id_uindex
  unique (id),
  constraint subscriber_account_id_fk
  foreign key (account_id) references account (id)
    on update set null on delete set null
)
;

create index subscriber_account_id_fk
  on subscriber (account_id)
;

create index subscriber_user_id_fk
  on subscriber (user_id)
;

create table tariff
(
  id int auto_increment
    primary key,
  title varchar(50) null,
  description text null,
  number_of_days int default '0' null,
  cost decimal(15,2) default '0.00' null,
  currency_shortname char(3) default 'UAH' null,
  service_id int null,
  constraint tariff_id_uindex
  unique (id),
  constraint tariff_service_id_fk
  foreign key (service_id) references service (id)
)
;

create index tariff_service_id_fk
  on tariff (service_id)
;

create index title
  on tariff (title, description)
;

create index title_2
  on tariff (title, description)
;

create table tariff_subscriber
(
  id int auto_increment
    primary key,
  start date default '1000-01-01' null,
  end date default '1000-01-01' null,
  prolong tinyint(1) default '0' null,
  tariff_id int null,
  subscriber_id int null,
  constraint tariff_subscriber_id_uindex
  unique (id),
  constraint tariff_subscriber_tariff_id_fk
  foreign key (tariff_id) references tariff (id),
  constraint tariff_subscriber_subscriber_id_fk
  foreign key (subscriber_id) references subscriber (id)
)
;

create index tariff_subscriber_subscriber_id_fk
  on tariff_subscriber (subscriber_id)
;

create index tariff_subscriber_tariff_id_fk
  on tariff_subscriber (tariff_id)
;

create table user
(
  id int auto_increment
    primary key,
  login varchar(50) not null,
  password char(64) not null,
  banned tinyint(1) default '0' null,
  updated_at datetime default CURRENT_TIMESTAMP null,
  created_at datetime default CURRENT_TIMESTAMP null,
  role_id int null,
  constraint user_id_uindex
  unique (id),
  constraint user_role_id_fk
  foreign key (role_id) references role (id)
)
;

create index user_role_id_fk
  on user (role_id)
;

alter table chat_user
  add constraint chat_user_user_id_fk
foreign key (user_id) references user (id)
;

alter table message
  add constraint message_user_id_fk
foreign key (user_id) references user (id)
  on update cascade on delete cascade
;

alter table subscriber
  add constraint subscriber_user_id_fk
foreign key (user_id) references user (id)
;

create procedure search_subscriber_utf (IN str1 char(50), IN str2 char(50), IN str3 char(50))
  BEGIN
    select *

    from subscriber as s
      inner join user as u on u.id = s.user_id
      inner join account as a on a.id = s.account_id


    where s.first_name LIKE concat("%",str1,"%")
          or s.first_name LIKE concat("%",str2,"%")
          or s.first_name LIKE concat("%",str3,"%")
          or s.last_name LIKE concat("%",str1,"%")
          or s.last_name LIKE concat("%",str2,"%")
          or s.last_name LIKE concat("%",str3,"%")
          or u.login LIKE concat("%",str1,"%")
          or u.login LIKE concat("%",str2,"%")
          or u.login LIKE concat("%",str3,"%");

  END;

create procedure search_tariff (IN q varchar(200))
  BEGIN
    select
      t.id,
      t.title as title,
      t.description,
      t.number_of_days,
      t.cost,
      t.currency_shortname,
      t.service_id,
      s.title as service_title

    from tariff as t
      inner join service as s on s.id = t.service_id

    where t.title REGEXP q
          or t.description REGEXP q
          or s.title REGEXP q;
  END;

create procedure search_tariff_utf (IN str1 char(50), IN str2 char(50), IN str3 char(50))
  BEGIN
    select t.id,
      t.title as title,
      t.description,
      t.number_of_days,
      t.cost,
      t.currency_shortname,
      t.service_id,
      s.title as service_title

    from tariff as t
      inner join service as s on s.id = t.service_id

    where t.title LIKE concat("%",str1,"%")
          or t.title LIKE concat("%",str2,"%")
          or t.title LIKE concat("%",str3,"%")
          or t.description LIKE concat("%",str1,"%")
          or t.description LIKE concat("%",str2,"%")
          or t.description LIKE concat("%",str3,"%")
          or s.title LIKE concat("%",str1,"%")
          or s.title LIKE concat("%",str2,"%")
          or s.title LIKE concat("%",str3,"%");

  END;

