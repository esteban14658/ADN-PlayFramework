-- jugador table

-- !Ups

CREATE TABLE jugador (
 id SERIAL,
 documento INT NOT NULL unique,
 nombre VARCHAR(40) NOT NULL,
 apellido VARCHAR(40) NOT NULL,
 fecha_nacimiento DATE NOT NULL,
 peso FLOAT NOT NULL,
 altura FLOAT NOT NULL,
 posicion VARCHAR(20) NOT NULL,
 pie_habil VARCHAR(20) NOT NULL,
 PRIMARY KEY (id)
);