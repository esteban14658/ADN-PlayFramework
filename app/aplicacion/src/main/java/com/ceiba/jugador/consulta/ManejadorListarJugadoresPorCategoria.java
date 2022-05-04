package aplicacion.src.main.java.com.ceiba.jugador.consulta;

import dominio.src.main.java.com.ceiba.asistencia.puerto.dao.DaoAsistencia;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ManejadorListarJugadoresPorCategoria {

    private final DaoJugador daoJugador;

    @Inject
    public ManejadorListarJugadoresPorCategoria(DaoJugador daoJugador) {
        this.daoJugador = daoJugador;
    }

    public CompletionStage<List<DtoJugador>> ejecutar(String fecha){
        return this.daoJugador.listarPorCategoria(fecha);
    }
}
