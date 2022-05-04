package pruebasunitarias.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioObtenerPorDocumento;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.Mockito;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ServicioObtenerPorDocumentoJugadorTest {

    @Test
    public void deberiaLanzarUnaExepcionCuandoNoSeValideLaExistenciaDelJugador() {
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        Mockito.when(repositorioJugador.existePorDocumento(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioObtenerPorDocumento servicioObtenerPorDocumento = new ServicioObtenerPorDocumento(daoJugador, repositorioJugador);

        assertThrows(NoSeEncuentraExcepcion.class, () -> servicioObtenerPorDocumento.ejecutar(100000L));
    }

    @SneakyThrows
    @Test
    public void deberiaObtenerUnJugadorPorSuDocumento(){
        Long documento = 80808080L;
        DtoJugador dtoJugador = new JugadorTestDataBuilder().dtoJugador();
        Optional<DtoJugador> optional = Optional.of(dtoJugador);

        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);

        Mockito.when(repositorioJugador.existePorDocumento(documento)).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(daoJugador.obtenerPorDocumento(documento)).thenReturn(CompletableFuture.completedFuture(optional));

        ServicioObtenerPorDocumento servicioObtenerPorDocumento = new ServicioObtenerPorDocumento(daoJugador, repositorioJugador);
        Optional<DtoJugador> dtoJugadorRetornado = servicioObtenerPorDocumento.ejecutar(documento)
                .toCompletableFuture().get();

        assertEquals(dtoJugadorRetornado, optional);
    }
}
