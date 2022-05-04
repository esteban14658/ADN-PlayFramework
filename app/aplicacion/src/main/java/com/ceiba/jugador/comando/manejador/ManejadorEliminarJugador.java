package aplicacion.src.main.java.com.ceiba.jugador.comando.manejador;

import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioEliminarJugador;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ManejadorEliminarJugador {

    private final ServicioEliminarJugador servicioEliminarJugador;

    @Inject
    public ManejadorEliminarJugador(ServicioEliminarJugador servicioEliminarJugador) {
        this.servicioEliminarJugador = servicioEliminarJugador;
    }

    public CompletionStage<Void> ejecutar(Long id) {
        return this.servicioEliminarJugador.ejecutar(id);
    }
}
