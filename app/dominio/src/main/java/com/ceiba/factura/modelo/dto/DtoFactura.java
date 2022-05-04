package dominio.src.main.java.com.ceiba.factura.modelo.dto;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DtoFactura {

    private Long id;
    private Integer valor;
    private LocalDate fechaIngreso;
    private LocalDate fechaCaducidad;
    private DtoJugador jugador;
    private Integer estado;
    private String descripcion;

    public DtoFactura() {
    }

    public DtoFactura(Long id, Integer valor, LocalDate fechaIngreso,
                      LocalDate fechaCaducidad, DtoJugador jugador, Integer estado, String descripcion) {
        this.id = id;
        this.valor = valor;
        this.fechaIngreso = fechaIngreso;
        this.fechaCaducidad = fechaCaducidad;
        this.jugador = jugador;
        this.estado = estado;
        this.descripcion = descripcion;
    }
}
