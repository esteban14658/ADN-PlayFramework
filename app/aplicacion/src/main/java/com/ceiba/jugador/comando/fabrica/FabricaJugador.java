package aplicacion.src.main.java.com.ceiba.jugador.comando.fabrica;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;

public class FabricaJugador {

    public Jugador crear(ComandoJugador comandoJugador){
        return new Jugador(
                comandoJugador.getId(),
                comandoJugador.getDocumento(),
                comandoJugador.getNombre(),
                comandoJugador.getApellido(),
                comandoJugador.getFechaNacimiento(),
                comandoJugador.getPeso(),
                comandoJugador.getAltura(),
                comandoJugador.getPosicion(),
                comandoJugador.getPieHabil()
        );
    }
}
