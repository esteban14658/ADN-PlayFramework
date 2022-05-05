package pruebasdeintegracion.asistencia.testdatabuilder;

import aplicacion.src.main.java.com.ceiba.asistencia.comando.ComandoAsistencia;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;

import java.time.LocalDate;

public class ComandoAsistenciaTestDataBuilder {

    private Long id;
    private LocalDate fecha;
    private Jugador jugador;

    public ComandoAsistenciaTestDataBuilder() {
        this.fecha = LocalDate.now();
        this.jugador = new Jugador(4L, 1009876543L, "Juanito", "Perez", LocalDate.now(),
                (float) 45.6, (float) 1.65, "Portero", "Derecho");
    }

    public ComandoAsistencia build(){
        return new ComandoAsistencia(id, fecha, jugador);
    }
}
