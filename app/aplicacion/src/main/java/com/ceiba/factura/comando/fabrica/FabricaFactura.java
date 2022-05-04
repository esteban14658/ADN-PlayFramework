package aplicacion.src.main.java.com.ceiba.factura.comando.fabrica;

import aplicacion.src.main.java.com.ceiba.factura.comando.ComandoFactura;
import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;

public class FabricaFactura {

    public Factura crear(ComandoFactura comandoFactura){
        return new Factura(
            comandoFactura.getId(),
            comandoFactura.getValor(),
            comandoFactura.getFechaIngreso(),
            comandoFactura.getFechaCaducidad(),
            comandoFactura.getJugador(),
            comandoFactura.getEstado(),
            comandoFactura.getDescripcion()
        );
    }
}
