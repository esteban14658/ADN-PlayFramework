package aplicacion.src.main.java.com.ceiba.jugador.consulta;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ManejadorListarJugadores {
    private final DaoJugador daoJugador;

    @Inject
    public ManejadorListarJugadores(DaoJugador daoJugador) {
        this.daoJugador = daoJugador;
    }

    public CompletionStage<List<DtoJugador>> ejecutar(){
        return this.daoJugador.listar();
    }
}
