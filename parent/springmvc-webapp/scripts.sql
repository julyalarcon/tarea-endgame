drop database if exists tareaEndgame;
create database if not exists tareaEndgame;
use tareaEndgame;

create table if not exists usuarios(
	id int not null auto_increment,
	nombre varchar(100),
	usuario varchar(100),
	correo varchar(100),
	clave varchar(100),
	pagina varchar(100),
	telefono varchar(100),
	calle varchar(100),
	ciudad varchar(100),
	codigo_postal varchar(100),
	razon_social varchar(100),
	area varchar(100),
	latitud varchar(100),
	longitud varchar(100),
 	primary key (id)
);