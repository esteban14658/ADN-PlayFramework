package aplicacion.src.main.java.com.ceiba.asistencia.comando.manejador;

import aplicacion.src.main.java.com.ceiba.asistencia.comando.ComandoAsistencia;
import aplicacion.src.main.java.com.ceiba.asistencia.comando.fabrica.FabricaAsistencia;
import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;
import dominio.src.main.java.com.ceiba.asistencia.servicio.ServicioCrearAsistencia;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ManejadorCrearAsistencia {

    private final FabricaAsistencia fabricaAsistencia;
    private final ServicioCrearAsistencia servicioCrearAsistencia;


    @Inject
    public ManejadorCrearAsistencia(FabricaAsistencia fabricaAsistencia, ServicioCrearAsistencia servicioCrearAsistencia) {
        this.fabricaAsistencia = fabricaAsistencia;
        this.servicioCrearAsistencia = servicioCrearAsistencia;
    }

    public CompletionStage<Long> ejecutar(ComandoAsistencia comandoAsistencia){
        Asistencia asistencia = this.fabricaAsistencia.crear(comandoAsistencia);
        return this.servicioCrearAsistencia.ejecutar(asistencia);
    }
}
