package aplicacion.src.main.java.com.ceiba.factura.comando.manejador;

import aplicacion.src.main.java.com.ceiba.factura.comando.ComandoFactura;
import aplicacion.src.main.java.com.ceiba.factura.comando.fabrica.FabricaFactura;
import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import dominio.src.main.java.com.ceiba.factura.servicio.ServicioCrearFactura;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ManejadorCrearFactura {

    private final FabricaFactura fabricaFactura;
    private final ServicioCrearFactura servicioCrearFactura;

    @Inject
    public ManejadorCrearFactura(FabricaFactura fabricaFactura, ServicioCrearFactura servicioCrearFactura) {
        this.fabricaFactura = fabricaFactura;
        this.servicioCrearFactura = servicioCrearFactura;
    }

    public CompletionStage<Long> ejecutar(ComandoFactura comandoFactura){
        Factura factura = this.fabricaFactura.crear(comandoFactura);
        return this.servicioCrearFactura.ejecutar(factura, comandoFactura.getMeses());
    }
}
