package dominio.src.main.java.com.ceiba.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class ServicioObtenerPorDocumento {

    private static final String EL_JUGADOR_NO_EXISTE_EN_EL_SISTEMA = "El jugador no existe en el sistema";

    private final DaoJugador daoJugador;
    private final RepositorioJugador repositorioJugador;

    @Inject
    public ServicioObtenerPorDocumento(DaoJugador daoJugador, RepositorioJugador repositorioJugador) {
        this.daoJugador = daoJugador;
        this.repositorioJugador = repositorioJugador;
    }

    public CompletionStage<Optional<DtoJugador>> ejecutar(Long documento){
        validarExistenciaPrevia(documento);
        return this.daoJugador.obtenerPorDocumento(documento);
    }

    @SneakyThrows
    private void validarExistenciaPrevia(Long documento) {
        Boolean existe = this.repositorioJugador.existePorDocumento(documento)
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (!existe){
            throw new NoSeEncuentraExcepcion(EL_JUGADOR_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
