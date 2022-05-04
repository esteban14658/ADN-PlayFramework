package aplicacion.src.main.java.com.ceiba.jugador.comando.manejador;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import aplicacion.src.main.java.com.ceiba.jugador.comando.fabrica.FabricaJugador;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioCrearJugador;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ManejadorCrearJugador {

    private final FabricaJugador fabricaJugador;
    private final ServicioCrearJugador servicioCrearJugador;

    @Inject
    public ManejadorCrearJugador(FabricaJugador fabricaJugador, ServicioCrearJugador servicioCrearJugador) {
        this.fabricaJugador = fabricaJugador;
        this.servicioCrearJugador = servicioCrearJugador;
    }

    public CompletionStage<Long> ejecutar(ComandoJugador comandoJugador){
        Jugador jugador = this.fabricaJugador.crear(comandoJugador);
        return this.servicioCrearJugador.ejecutar(jugador);
    }
}
