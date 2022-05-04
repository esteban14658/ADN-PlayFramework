package aplicacion.src.main.java.com.ceiba.factura.comando.manejador;

import dominio.src.main.java.com.ceiba.factura.servicio.ServicioEliminarFactura;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ManejadorEliminarFactura {

    private final ServicioEliminarFactura servicioEliminarFactura;

    @Inject
    public ManejadorEliminarFactura(ServicioEliminarFactura servicioEliminarFactura) {
        this.servicioEliminarFactura = servicioEliminarFactura;
    }

    public CompletionStage<Void> ejecutar(Long id){
        return this.servicioEliminarFactura.ejecutar(id);
    }
}
