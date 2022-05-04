package aplicacion.src.main.java.com.ceiba.jugador.consulta;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioObtenerPorDocumento;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class ManejadorObtenerPorDocumentoJugador {

    private final ServicioObtenerPorDocumento servicioObtenerPorDocumento;

    @Inject
    public ManejadorObtenerPorDocumentoJugador(ServicioObtenerPorDocumento servicioObtenerPorDocumento) {
        this.servicioObtenerPorDocumento = servicioObtenerPorDocumento;
    }

    public CompletionStage<Optional<DtoJugador>> ejecutar(Long documento){
        return this.servicioObtenerPorDocumento.ejecutar(documento);
    }
}
