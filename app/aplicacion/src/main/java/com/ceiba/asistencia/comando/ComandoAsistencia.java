package aplicacion.src.main.java.com.ceiba.asistencia.comando;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;

import java.time.LocalDate;

public class ComandoAsistencia {

    private Long id;
    private LocalDate fecha;
    private Jugador jugador;

    public ComandoAsistencia() {
    }

    public ComandoAsistencia(Long id, LocalDate fecha, Jugador jugador) {
        this.id = id;
        this.fecha = fecha;
        this.jugador = jugador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
