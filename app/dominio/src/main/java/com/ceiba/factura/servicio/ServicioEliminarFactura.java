package dominio.src.main.java.com.ceiba.factura.servicio;

import dominio.src.main.java.com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ServicioEliminarFactura {

    public static final String NO_EXISTE_UNA_FACTURA_CON_DICHO_ID = "No existe una factura con dicho id";
    private final RepositorioFactura repositorioFactura;

    @Inject
    public ServicioEliminarFactura(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public CompletionStage<Void> ejecutar(Long id){
        validarExistenciaPrevia(id);
        return this.repositorioFactura.eliminar(id);
    }

    @SneakyThrows
    private void validarExistenciaPrevia(Long id){
        Boolean existe = this.repositorioFactura.existePorId(id)
                .thenApplyAsync(r ->
                    r
                )
                .toCompletableFuture().get();
        if (Boolean.FALSE.equals(existe)){
            throw new NoSeEncuentraExcepcion(NO_EXISTE_UNA_FACTURA_CON_DICHO_ID);
        }
    }
}
