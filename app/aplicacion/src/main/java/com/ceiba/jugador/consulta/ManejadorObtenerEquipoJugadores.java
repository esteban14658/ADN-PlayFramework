package aplicacion.src.main.java.com.ceiba.jugador.consulta;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoFiltro;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioObtenerEquipo;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ManejadorObtenerEquipoJugadores {

    private final ServicioObtenerEquipo servicioObtenerEquipo;

    @Inject
    public ManejadorObtenerEquipoJugadores(ServicioObtenerEquipo servicioObtenerEquipo) {
        this.servicioObtenerEquipo = servicioObtenerEquipo;
    }

    public CompletionStage<List<DtoJugador>> ejecutar(DtoFiltro dtoFiltro){
        return this.servicioObtenerEquipo.ejecutar(dtoFiltro);
    }
}
