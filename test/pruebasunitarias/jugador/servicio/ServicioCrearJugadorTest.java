package pruebasunitarias.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioCrearJugador;
import excepciones.DuplicidadExcepcion;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.Mockito;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ServicioCrearJugadorTest {

    @SneakyThrows
    @Test
    public void deberiaCrearElJugadorDeManeraCorrecta() {
        Jugador jugador = new JugadorTestDataBuilder().build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorDocumento(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.crear(jugador)).thenReturn(CompletableFuture.completedFuture(10L));

        ServicioCrearJugador servicioCrearJugador = new ServicioCrearJugador(repositorioJugador);

        Long idJugador = servicioCrearJugador.ejecutar(jugador).toCompletableFuture().get();

        assertEquals("10", idJugador.toString());
        Mockito.verify(repositorioJugador, Mockito.times(1)).crear(jugador);
    }

    @Test
    public void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelJugador() {
        Jugador jugador = new JugadorTestDataBuilder().build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorDocumento(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearJugador servicioCrearJugador = new ServicioCrearJugador(repositorioJugador);

        assertThrows(DuplicidadExcepcion.class, () -> servicioCrearJugador.ejecutar(jugador));
    }
}
