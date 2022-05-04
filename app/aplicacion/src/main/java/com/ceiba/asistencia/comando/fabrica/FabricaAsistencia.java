package aplicacion.src.main.java.com.ceiba.asistencia.comando.fabrica;

import aplicacion.src.main.java.com.ceiba.asistencia.comando.ComandoAsistencia;
import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;

import java.time.LocalDate;

public class FabricaAsistencia {

    public Asistencia crear(ComandoAsistencia comandoAsistencia){
        return new Asistencia(
                comandoAsistencia.getId(),
                LocalDate.now(),
                comandoAsistencia.getJugador()
        );
    }
}
