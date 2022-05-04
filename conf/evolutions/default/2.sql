-- factura table

-- !Ups

CREATE TABLE factura (
 id SERIAL,
 valor INT NOT NULL,
 fecha_ingreso DATE NOT NULL,
 fecha_caducidad DATE NOT NULL,
 jugador INT NOT NULL,
 estado INT NOT NULL,
 descripcion VARCHAR(100) NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (jugador) REFERENCES jugador(id)
);