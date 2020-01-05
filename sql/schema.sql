drop table if exists bookings;
drop table if exists flights;
drop table if exists customerdetails;
drop table if exists customers;

create table customers (
    id serial not null constraint customers_pk primary key,
    number varchar(255) not null unique,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table customerdetails (
    id serial not null constraint customerdetails_pk primary key,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    country varchar(255) not null,
    customers_id int REFERENCES customers(id)
);

create table flights (
    id serial not null constraint flights_pk primary key,
    number varchar(255) not null unique,
    flyingfrom varchar(255) not null,
    flyingto varchar(255) not null,
    departingdate timestamp not null,
    returningdate timestamp not null,
    maxnumberpassengers integer not null
);

create table bookings (
    id serial not null constraint bookings_pk primary key,
    number varchar(255) not null unique,
    bookingon timestamp not null,
    customers_id int REFERENCES customers(id),
    flights_id int REFERENCES flights(id)
);
