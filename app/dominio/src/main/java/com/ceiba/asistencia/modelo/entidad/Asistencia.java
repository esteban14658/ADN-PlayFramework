package dominio.src.main.java.com.ceiba.asistencia.modelo.entidad;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Asistencia {

    private Long id;
    private LocalDate fecha;
    private Jugador jugador;

    public Asistencia(Long id, LocalDate fecha, Jugador jugador) {
        this.id = id;
        this.fecha = fecha;
        this.jugador = jugador;
    }
}
