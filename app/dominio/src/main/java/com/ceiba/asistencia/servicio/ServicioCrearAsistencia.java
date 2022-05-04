package dominio.src.main.java.com.ceiba.asistencia.servicio;

import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;
import dominio.src.main.java.com.ceiba.asistencia.puerto.repositorio.RepositorioAsistencia;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.DuplicidadExcepcion;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ServicioCrearAsistencia {

    public static final String NO_SE_PUEDE_REGISTRAR_NUEVAMENTE_HOY = "No se puede registrar nuevamente hoy";
    public static final String EL_JUGADOR_NO_SE_ENCUENTRA_REGISTRADO = "El jugador no se encuentra registrado";
    private final RepositorioAsistencia repositorioAsistencia;
    private final RepositorioJugador repositorioJugador;

    @Inject
    public ServicioCrearAsistencia(RepositorioAsistencia repositorioAsistencia, RepositorioJugador repositorioJugador) {
        this.repositorioAsistencia = repositorioAsistencia;
        this.repositorioJugador = repositorioJugador;
    }

    public CompletionStage<Long> ejecutar(Asistencia asistencia){
        validarExistenciaJugador(asistencia);
        validarAsistenciaDeHoy(asistencia);
        return this.repositorioAsistencia.crear(asistencia);
    }

    @SneakyThrows
    private void validarExistenciaJugador(Asistencia asistencia) {
        Boolean existe = this.repositorioJugador.existePorId(asistencia.getJugador().getId())
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (!existe){
            throw new NoSeEncuentraExcepcion(EL_JUGADOR_NO_SE_ENCUENTRA_REGISTRADO);
        }
    }

    @SneakyThrows
    private void validarAsistenciaDeHoy(Asistencia asistencia) {
        Boolean existe = this.repositorioAsistencia.registroDiario(asistencia.getJugador().getId())
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (existe){
            throw new DuplicidadExcepcion(NO_SE_PUEDE_REGISTRAR_NUEVAMENTE_HOY);
        }
    }
}
