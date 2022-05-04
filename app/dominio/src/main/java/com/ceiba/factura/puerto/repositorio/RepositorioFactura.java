package dominio.src.main.java.com.ceiba.factura.puerto.repositorio;

import com.google.inject.ImplementedBy;
import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import infraestructura.src.main.java.com.ceiba.factura.adaptador.repositorio.RepositorioFacturaPostgreSQL;

import java.util.concurrent.CompletionStage;

@ImplementedBy(RepositorioFacturaPostgreSQL.class)
public interface RepositorioFactura {

    CompletionStage<Long> crear(Factura factura);

    CompletionStage<Void> eliminar(Long id);

    CompletionStage<Boolean> existePorIdJugador(Long idJugador);

    CompletionStage<Boolean> existePorId(Long id);
}
