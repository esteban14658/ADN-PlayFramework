package dominio.src.main.java.com.ceiba.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.DuplicidadExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ServicioCrearJugador {

    private static final String EL_JUGADOR_YA_EXISTE_EN_EL_SISTEMA = "El jugador ya existe en el sistema";

    private final RepositorioJugador repositorioJugador;

    @Inject
    public ServicioCrearJugador(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    public CompletionStage<Long> ejecutar(Jugador jugador) {
        validarExistenciaPrevia(jugador);
        return this.repositorioJugador.crear(jugador);
    }

    @SneakyThrows
    private void validarExistenciaPrevia(Jugador jugador) {
        Boolean existe = this.repositorioJugador.existePorDocumento(jugador.getDocumento())
                .thenApplyAsync(r ->
                    r
                ).toCompletableFuture().get();
        if (Boolean.TRUE.equals(existe)){
            throw new DuplicidadExcepcion(EL_JUGADOR_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
