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
    name varchar (150) not null
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