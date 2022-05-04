-- asistencia table

-- !Ups

CREATE TABLE asistencia (
 id SERIAL,
 fecha DATE NOT NULL,
 jugador INT NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (jugador) REFERENCES jugador(id)
);