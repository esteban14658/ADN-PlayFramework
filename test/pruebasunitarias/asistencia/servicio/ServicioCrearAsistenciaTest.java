package pruebasunitarias.asistencia.servicio;

import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;
import dominio.src.main.java.com.ceiba.asistencia.puerto.repositorio.RepositorioAsistencia;
import dominio.src.main.java.com.ceiba.asistencia.servicio.ServicioCrearAsistencia;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.DuplicidadExcepcion;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.Mockito;
import pruebasunitarias.asistencia.servicio.testdatabuilder.AsistenciaTestDataBuilder;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

public class ServicioCrearAsistenciaTest {

    @SneakyThrows
    @Test
    public void deberiaCrearUnaAsistenciaCorrectamente(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Asistencia asistencia = new AsistenciaTestDataBuilder().conJugador(jugador).build();
        RepositorioAsistencia repositorioAsistencia = Mockito.mock(RepositorioAsistencia.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioAsistencia.registroDiario(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioAsistencia.crear(asistencia)).thenReturn(CompletableFuture.completedFuture(1L));

        ServicioCrearAsistencia servicioCrearAsistencia = new ServicioCrearAsistencia(repositorioAsistencia, repositorioJugador);

        Long idRetornado = servicioCrearAsistencia.ejecutar(asistencia)
                .toCompletableFuture().get();

        assertEquals("1", idRetornado.toString());
        Mockito.verify(repositorioAsistencia, Mockito.times(1)).crear(asistencia);
    }

    @Test
    public void deberiaLanzarUnaExcepcionSiElJugadorYaEsteRegistradoEnEsaFecha(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Asistencia asistencia = new AsistenciaTestDataBuilder().conJugador(jugador).build();
        RepositorioAsistencia repositorioAsistencia = Mockito.mock(RepositorioAsistencia.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioAsistencia.registroDiario(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));

        doAnswer(invocation -> {
            Asistencia asistenciaArg = invocation.getArgument(0);
            assertNotNull(asistenciaArg);
            return null;
        }).when(repositorioAsistencia).crear(any(Asistencia.class));

        ServicioCrearAsistencia servicioCrearAsistencia = new ServicioCrearAsistencia(repositorioAsistencia, repositorioJugador);

        assertThrows("No se puede registrar nuevamente hoy", DuplicidadExcepcion.class, () -> servicioCrearAsistencia.ejecutar(asistencia));
    }

    @Test
    public void deberiaLanzarUnaExcepcionSiElJugadorNoSeEncuentraRegistrado(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Asistencia asistencia = new AsistenciaTestDataBuilder().conJugador(jugador).build();
        RepositorioAsistencia repositorioAsistencia = Mockito.mock(RepositorioAsistencia.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioAsistencia.registroDiario(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));

        doAnswer(invocation -> {
            Asistencia asistenciaArg = invocation.getArgument(0);
            assertNotNull(asistenciaArg);
            return null;
        }).when(repositorioAsistencia).crear(any(Asistencia.class));

        ServicioCrearAsistencia servicioCrearAsistencia = new ServicioCrearAsistencia(repositorioAsistencia, repositorioJugador);

        assertThrows("El jugador no se encuentra registrado", NoSeEncuentraExcepcion.class, () -> servicioCrearAsistencia.ejecutar(asistencia));
    }
}
