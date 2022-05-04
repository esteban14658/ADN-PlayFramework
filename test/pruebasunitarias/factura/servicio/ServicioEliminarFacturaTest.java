package pruebasunitarias.factura.servicio;

import dominio.src.main.java.com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import dominio.src.main.java.com.ceiba.factura.servicio.ServicioEliminarFactura;
import excepciones.NoSeEncuentraExcepcion;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

public class ServicioEliminarFacturaTest {

    @Test
    public void deberiaEliminarLaFacturaLlamandoAlRepositorio() {
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioEliminarFactura servicioEliminarFactura = new ServicioEliminarFactura(repositorioFactura);
        servicioEliminarFactura.ejecutar(1L);
        Mockito.verify(repositorioFactura, Mockito.times(1)).eliminar(1L);
    }

    @Test
    public void deberiaLanzarUnaExcepcionSiNoExisteUnaFacturaConDichoId(){
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioEliminarFactura servicioEliminarFactura = new ServicioEliminarFactura(repositorioFactura);
        assertThrows("No existe una factura con dicho id", NoSeEncuentraExcepcion.class, () -> servicioEliminarFactura.ejecutar(1L));
    }
}
