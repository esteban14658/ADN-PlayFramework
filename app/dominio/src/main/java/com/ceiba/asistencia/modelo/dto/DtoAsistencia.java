package dominio.src.main.java.com.ceiba.asistencia.modelo.dto;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DtoAsistencia {

    private Long id;
    private LocalDate fecha;
    private DtoJugador jugador;

    public DtoAsistencia() {
    }

    public DtoAsistencia(Long id, LocalDate fecha, DtoJugador jugador) {
        this.id = id;
        this.fecha = fecha;
        this.jugador = jugador;
    }
}
