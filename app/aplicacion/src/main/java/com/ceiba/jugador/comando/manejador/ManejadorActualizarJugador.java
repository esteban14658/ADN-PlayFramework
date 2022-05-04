package aplicacion.src.main.java.com.ceiba.jugador.comando.manejador;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import aplicacion.src.main.java.com.ceiba.jugador.comando.fabrica.FabricaJugador;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioActualizarJugador;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ManejadorActualizarJugador {

    private final FabricaJugador fabricaJugador;
    private final ServicioActualizarJugador servicioActualizarJugador;

    @Inject
    public ManejadorActualizarJugador(FabricaJugador fabricaJugador, ServicioActualizarJugador servicioActualizarJugador) {
        this.fabricaJugador = fabricaJugador;
        this.servicioActualizarJugador = servicioActualizarJugador;
    }

    public CompletionStage<Void> ejecutar(ComandoJugador comandoJugador, String id){
        Jugador jugador = this.fabricaJugador.crear(comandoJugador);
        jugador.setId(Long.parseLong(id));
        return this.servicioActualizarJugador.ejecutar(jugador);
    }
}
