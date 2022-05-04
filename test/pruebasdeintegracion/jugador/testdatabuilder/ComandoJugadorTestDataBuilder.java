package pruebasdeintegracion.jugador.testdatabuilder;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;

import java.time.LocalDate;

public class ComandoJugadorTestDataBuilder {

    private Long id;
    private Long documento;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private float peso;
    private float altura;
    private String posicion;
    private String pieHabil;

    public ComandoJugadorTestDataBuilder() {
        documento = Long.valueOf(10101010);
        nombre = "Juanito";
        apellido = "Alimaña";
        fechaNacimiento = LocalDate.parse("2022-02-14");
        peso = (float) 54.6;
        altura = (float) 1.65;
        posicion = "Delantero";
        pieHabil = "Derecho";
    }

    public ComandoJugador build() {
        return new ComandoJugador(id, documento, nombre, apellido,
                fechaNacimiento, peso, altura, posicion, pieHabil);
    }
}
