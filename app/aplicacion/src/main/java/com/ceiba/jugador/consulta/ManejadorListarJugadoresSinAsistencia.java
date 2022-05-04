package aplicacion.src.main.java.com.ceiba.jugador.consulta;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ManejadorListarJugadoresSinAsistencia {

    private final DaoJugador daoJugador;

    @Inject
    public ManejadorListarJugadoresSinAsistencia(DaoJugador daoJugador) {
        this.daoJugador = daoJugador;
    }

    public CompletionStage<List<DtoJugador>> ejecutar(){
        return this.daoJugador.listarSinAsistencias();
    }
}
