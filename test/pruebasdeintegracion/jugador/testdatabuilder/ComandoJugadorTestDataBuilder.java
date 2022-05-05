package pruebasdeintegracion.jugador.testdatabuilder;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import com.github.javafaker.Faker;

import java.time.LocalDate;

public class ComandoJugadorTestDataBuilder {
    private static Faker faker = new Faker();
    private Long id;
    private final Long documento;
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaNacimiento;
    private final float peso;
    private final float altura;
    private final String posicion;
    private final String pieHabil;

    public ComandoJugadorTestDataBuilder() {
        documento = faker.random().nextLong(999999999);
        nombre = faker.name().name();
        apellido = faker.name().lastName();
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
