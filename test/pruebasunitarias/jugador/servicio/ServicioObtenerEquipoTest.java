package pruebasunitarias.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoFiltro;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;
import dominio.src.main.java.com.ceiba.jugador.servicio.ServicioObtenerEquipo;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.Mockito;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ServicioObtenerEquipoTest {

    @SneakyThrows
    @Test
    public void deberiaObtenerUnEquipoDeManeraCorrecta(){
        DtoFiltro dtoFiltro = new DtoFiltro("4", "4", "2");
        List<DtoJugador> listaJugadores = new JugadorTestDataBuilder().listaDeJugadores();
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        Mockito.when(daoJugador.listarPorPosicion("Portero")).thenReturn(CompletableFuture.completedFuture(listaJugadores));
        Mockito.when(daoJugador.listarPorPosicion("Defensa")).thenReturn(CompletableFuture.completedFuture(listaJugadores));
        Mockito.when(daoJugador.listarPorPosicion("Mediocampista")).thenReturn(CompletableFuture.completedFuture(listaJugadores));
        Mockito.when(daoJugador.listarPorPosicion("Delantero")).thenReturn(CompletableFuture.completedFuture(listaJugadores));

        ServicioObtenerEquipo servicioObtenerEquipo = new ServicioObtenerEquipo(daoJugador);
        List<DtoJugador> listaJugadoresRetornados = servicioObtenerEquipo.ejecutar(dtoFiltro)
                .toCompletableFuture().get();

        assertTrue(!listaJugadoresRetornados.isEmpty());
    }

    @Test
    public void deberiaLanzarUnaExepcionCuandoHayaMayorCantidadDeJugadoresPermitidos(){
        DtoFiltro dtoFiltro = new DtoFiltro("4", "4", "4");
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        ServicioObtenerEquipo servicioObtenerEquipo = new ServicioObtenerEquipo(daoJugador);

        assertThrows(IndexOutOfBoundsException.class, () -> servicioObtenerEquipo.ejecutar(dtoFiltro));
    }

}
