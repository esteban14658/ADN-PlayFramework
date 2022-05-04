package pruebasunitarias.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioEliminarJugador;
import excepciones.DuplicidadExcepcion;
import org.junit.Test;
import org.mockito.Mockito;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

public class ServicioEliminarJugadorTest {

    @Test
    public void deberiaEliminarElJugadorLlamandoAlRepositorio() {
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existeJugadorConAsistencias(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existeJugadorConFactura(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioEliminarJugador servicioEliminarJugador = new ServicioEliminarJugador(repositorioJugador);

        servicioEliminarJugador.ejecutar(1L);

        Mockito.verify(repositorioJugador, Mockito.times(1)).eliminar(1L);

    }

    @Test
    public void deberiaLanzarUnaExcepcionSiEstaRegistradoEnTablaFactura(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        doAnswer(invocation -> {
            Jugador jugadorArg = invocation.getArgument(0);
            assertNotNull(jugadorArg);
            return null;
        }).when(repositorioJugador).crear(any(Jugador.class));

        Mockito.when(repositorioJugador.existeJugadorConFactura(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioJugador.existeJugadorConAsistencias(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioEliminarJugador servicioEliminarJugador = new ServicioEliminarJugador(repositorioJugador);

        assertThrows(DuplicidadExcepcion.class, () -> servicioEliminarJugador.ejecutar(jugador.getId()));
    }

    @Test
    public void deberiaLanzarUnaExcepcionSiEstaRegistradoEnTablaAsistencia(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        doAnswer(invocation -> {
            Jugador jugadorArg = invocation.getArgument(0);
            assertNotNull(jugadorArg);
            return null;
        }).when(repositorioJugador).crear(any(Jugador.class));

        Mockito.when(repositorioJugador.existeJugadorConFactura(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existeJugadorConAsistencias(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioEliminarJugador servicioEliminarJugador = new ServicioEliminarJugador(repositorioJugador);

        assertThrows(DuplicidadExcepcion.class, () -> servicioEliminarJugador.ejecutar(jugador.getId()));
    }
}
