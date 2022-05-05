package pruebasdeintegracion.factura.testdatabuilder;

import aplicacion.src.main.java.com.ceiba.factura.comando.ComandoFactura;
import com.github.javafaker.Faker;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;

import java.time.LocalDate;

public class ComandoFacturaTestDataBuilder {


    private static Faker faker = new Faker();
    private Long id;
    private Long valor;
    private LocalDate fechaIngreso;
    private LocalDate fechaCaducidad;
    private Jugador jugador;
    private Integer estado;
    private String descripcion;
    private Long meses;

    public ComandoFacturaTestDataBuilder() {
        valor = Long.valueOf(100000);
        fechaIngreso = LocalDate.parse("2022-03-09");
        fechaCaducidad = LocalDate.parse("2022-06-09");
        jugador = new Jugador(faker.random().nextLong(50),faker.random().nextLong(999999999), "Juanito", "Perez", LocalDate.now(),
                (float) 45.6, (float) 1.65, "Portero", "Derecho");
        estado = 1;
        descripcion = "Tres meses";
        meses = 3L;
    }

    public ComandoFactura build() {
        return new ComandoFactura(id, valor, fechaIngreso, fechaCaducidad, jugador, estado, descripcion, meses);
    }
}
