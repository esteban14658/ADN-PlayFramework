package pruebasunitarias.factura.servicio.testdatabuilder;

import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.time.LocalDate;

public class FacturaTestDataBuilder {

    private Long id;
    private Long valor;
    private LocalDate fechaIngreso;
    private LocalDate fechaCaducidad;
    private Jugador jugador;
    private Integer estado;
    private String descripcion;

    public FacturaTestDataBuilder() {
        valor = Long.valueOf(420000);
        fechaIngreso = LocalDate.parse("2022-01-10");
        fechaCaducidad = LocalDate.parse("2022-04-09");
        jugador = new JugadorTestDataBuilder().build();
        estado = 1;
        descripcion = "tres meses";
    }

    public FacturaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public FacturaTestDataBuilder conValor(Long valor) {
        this.valor = valor;
        return this;
    }

    public FacturaTestDataBuilder conFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public FacturaTestDataBuilder conFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
        return this;
    }

    public FacturaTestDataBuilder conJugador(Jugador jugador) {
        this.jugador = jugador;
        return this;
    }

    public FacturaTestDataBuilder conEstado(Integer estado) {
        this.estado = estado;
        return this;
    }

    public FacturaTestDataBuilder conDescripcion(String descripcion){
        this.descripcion = descripcion;
        return this;
    }

    public Factura build() { return new Factura(id, valor, fechaIngreso, fechaCaducidad,
            jugador, estado, descripcion); }
}
