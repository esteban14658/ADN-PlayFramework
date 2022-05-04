package dominio.src.main.java.com.ceiba.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ServicioActualizarJugador {

    private static final String EL_JUGADOR_NO_EXISTE_EN_EL_SISTEMA = "El jugador no existe en el sistema";

    private final RepositorioJugador repositorioJugador;

    @Inject
    public ServicioActualizarJugador(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    public CompletionStage<Void> ejecutar(Jugador jugador) {
        validarExistenciaPrevia(jugador);
        return this.repositorioJugador.actualizar(jugador);
    }

    @SneakyThrows
    private void validarExistenciaPrevia(Jugador jugador){
        Boolean existe = this.repositorioJugador.existePorDocumento(jugador.getDocumento())
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (!existe){
            throw new NoSeEncuentraExcepcion(EL_JUGADOR_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
