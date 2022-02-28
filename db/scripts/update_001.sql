create database cars;

create table users (
    id serial primary key,
    name varchar(100) not null,
    email varchar(100) unique not null,
    password varchar(100) not null
);

create table engines(
    id serial primary key,
    name varchar(100) unique not null,
    power int not null
);

create table models (
    id serial primary key,
    name varchar (150) not null,
    brand_id int references brands(id)
);

create table brands (
    id serial primary key,
    name varchar(50) unique not null
);

create table bodies (
    id serial primary key,
    name varchar(50) unique not null
);

create table cars (
    vin varchar(100) primary key,
    production timestamp,
    engine_id int references engines(id),
    body_id int references bodies(id),
    model_id int references models(id),
    brand_id int references brands(id)
);

create table history_owner (
    id serial primary key,
    user_id int references users(id),
    car_vin int references cars(vin)
);

create table photos (
    id serial primary key,
    name varchar(255) not null
);

create table adverts (
    id serial primary key,
    date timestamp,
    description text,
    user_id references users(id),
    car_vin references cars(vin),
    photo_id references photos(id),
    sold boolean default false
);

insert into bodies (name) values ('Универсал');
insert into bodies (name) values ('Седан');
insert into bodies (name) values ('Купэ');
insert into bodies (name) values ('Хетчбэк');
insert into bodies (name) values ('Пикап');

insert into brands (name) values ('Toyota');
insert into brands (name) values ('Mitsubishi');
insert into brands (name) values ('Renault');
insert into brands (name) values ('BMW');

insert into models (name, brand_id) values ('Corolla', 1);
insert into models (name, brand_id) values ('Avensis', 1);
insert into models (name, brand_id) values ('Chaser', 1);
insert into models (name, brand_id) values ('Lancer', 2);
insert into models (name, brand_id) values ('Logan', 3);
insert into models (name, brand_id) values ('Sandero', 3);
insert into models (name, brand_id) values ('Duster', 3);
insert into models (name, brand_id) values ('M5', 4);

insert into engines (name, power) values ('1.3', 88);
insert into engines (name, power) values ('1.6', 123);
insert into engines (name, power) values ('2.0', 150);
insert into engines (name, power) values ('3.0', 215);







