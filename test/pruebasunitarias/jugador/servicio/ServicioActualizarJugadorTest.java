package pruebasunitarias.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioActualizarJugador;
import excepciones.NoSeEncuentraExcepcion;
import org.junit.Test;
import org.mockito.Mockito;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertThrows;

public class ServicioActualizarJugadorTest {

    @Test
    public void deberiaValidarLaExistenciaPreviaDelJugador(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorDocumento(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioActualizarJugador servicioActualizarJugador = new ServicioActualizarJugador(repositorioJugador);

        assertThrows(NoSeEncuentraExcepcion.class, () -> servicioActualizarJugador.ejecutar(jugador));
    }

    @Test
    public void deberiaActualizarCorrectamenteEnElRepositorio() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorDocumento(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioActualizarJugador servicioActualizarJugador = new ServicioActualizarJugador(repositorioJugador);

        servicioActualizarJugador.ejecutar(jugador);

        Mockito.verify(repositorioJugador, Mockito.times(1)).actualizar(jugador);
    }
}
