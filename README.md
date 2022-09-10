# PruebaDispapeles

Script SQL

create database dispapeles;
use dispapeles;

create table cliente (
	numeroidentificacion varchar(10),
	tipoidentificacion varchar(2),
	nombre varchar(50),
	apellidos varchar(50),
	edad int,
	telefono varchar(10), 
	direccion varchar(50),
	primary key (numeroidentificacion)
); 
