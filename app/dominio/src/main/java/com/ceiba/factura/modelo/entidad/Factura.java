package dominio.src.main.java.com.ceiba.factura.modelo.entidad;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Factura {

    public static final long VALOR_MENSUAL = 100000L;
    public static final long TRES_MESES = 3L;
    public static final double MENOS_QUINCE_PORCIENTO = 0.85;
    public static final int SEIS_MESES = 6;
    public static final double MENOS_TREINTA_PORCIENTO = 0.7;

    private Long id;
    private Long valor;
    private LocalDate fechaIngreso;
    private LocalDate fechaCaducidad;
    private Jugador jugador;
    private Integer estado;
    private String descripcion;

    public Factura(Long id, Long valor, LocalDate fechaIngreso,
                   LocalDate fechaCaducidad, Jugador jugador, Integer estado, String descripcion) {
        this.id = id;
        this.valor = valor;
        this.fechaIngreso = fechaIngreso;
        this.fechaCaducidad = fechaCaducidad;
        this.jugador = jugador;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public double mensualidadNormal() { return VALOR_MENSUAL; }

    public double promocionTresMeses(){
        return VALOR_MENSUAL * TRES_MESES * MENOS_QUINCE_PORCIENTO;
    }

    public double promocionSeisMeses(){
        return VALOR_MENSUAL * SEIS_MESES * MENOS_TREINTA_PORCIENTO;
    }

    public String sumarMeses(String fecha, Long meses) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate fechaLocal = LocalDate.parse(fecha, formateador);
        fechaLocal = fechaLocal.plusMonths(meses);
        return fechaLocal.format(formateador);
    }

}
