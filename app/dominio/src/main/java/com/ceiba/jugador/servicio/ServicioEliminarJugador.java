package dominio.src.main.java.com.ceiba.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.DuplicidadExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ServicioEliminarJugador {

    public static final String NO_PUEDE_ELIMINAR_UN_JUGADOR_CON_FACTURA_PREVIA = "No puede eliminar un jugador con factura previa";
    public static final String NO_SE_PUEDE_ELIMINAR_UN_JUGADOR_CON_ASISTENCIAS_PREVIAS = "No se puede eliminar un jugador con asistencias previas";
    private final RepositorioJugador repositorioJugador;

    @Inject
    public ServicioEliminarJugador(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    public CompletionStage<Void> ejecutar(Long id){
        validarExistenciaDeAsistencia(id);
        validarExistenciaDeFactura(id);
        return this.repositorioJugador.eliminar(id);
    }


    @SneakyThrows
    private void validarExistenciaDeFactura(Long id){
        Boolean existe = this.repositorioJugador.existeJugadorConFactura(id)
                .thenApplyAsync(r ->
                    r
                ).toCompletableFuture().get();
        if (Boolean.TRUE.equals(existe)){
            throw new DuplicidadExcepcion(NO_PUEDE_ELIMINAR_UN_JUGADOR_CON_FACTURA_PREVIA);
        }
    }

    @SneakyThrows
    private void validarExistenciaDeAsistencia(Long id){
        Boolean existe = this.repositorioJugador.existeJugadorConAsistencias(id)
                .thenApplyAsync(r ->
                    r
                ).toCompletableFuture().get();
        if (Boolean.TRUE.equals(existe)){
            throw new DuplicidadExcepcion(NO_SE_PUEDE_ELIMINAR_UN_JUGADOR_CON_ASISTENCIAS_PREVIAS);
        }
    }
}
