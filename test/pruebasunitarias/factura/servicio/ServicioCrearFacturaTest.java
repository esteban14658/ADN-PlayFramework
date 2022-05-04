package pruebasunitarias.factura.servicio;

import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import dominio.src.main.java.com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import dominio.src.main.java.com.ceiba.factura.servicio.ServicioCrearFactura;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.DuplicidadExcepcion;
import excepciones.MalaPeticionExcepcion;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pruebasunitarias.factura.servicio.testdatabuilder.FacturaTestDataBuilder;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class ServicioCrearFacturaTest {

    @SneakyThrows
    @Test
    public void deberiaCrearLaFacturaDeManeraCorrecta() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioFactura.crear(factura)).thenReturn(CompletableFuture.completedFuture(3L));

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);

        Long idRetornado = servicioCrearFactura.ejecutar(factura, 3L)
                .toCompletableFuture().get();

        assertEquals("3", idRetornado.toString());
        Mockito.verify(repositorioFactura, Mockito.times(1)).crear(factura);
    }

    @SneakyThrows
    @Test
    public void deberiaCrearLaFacturaDeManeraCorrectaCuandoSoloEsUnMes() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioFactura.crear(factura)).thenReturn(CompletableFuture.completedFuture(3L));

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);

        Long idRetornado = servicioCrearFactura.ejecutar(factura, 1L)
                .toCompletableFuture().get();

        assertEquals("3", idRetornado.toString());
        Mockito.verify(repositorioFactura, Mockito.times(1)).crear(factura);
    }

    @SneakyThrows
    @Test
    public void deberiaCrearLaFacturaDeManeraCorrectaCuandoSonSeisMeses() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioFactura.crear(factura)).thenReturn(CompletableFuture.completedFuture(3L));

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);

        Long idRetornado = servicioCrearFactura.ejecutar(factura, 1L)
                .toCompletableFuture().get();

        assertEquals("3", idRetornado.toString());
        Mockito.verify(repositorioFactura, Mockito.times(1)).crear(factura);
    }

    @Test
    public void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeUnaFacturaActiva(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));

        doAnswer(invocation -> {
            // assert
            Factura facturaArg = invocation.getArgument(0);
            assertNotNull(facturaArg);
            return null;
        }).when(repositorioFactura).crear(any(Factura.class));

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        assertThrows("Ya tiene una factura asignada", DuplicidadExcepcion.class, () -> servicioCrearFactura.ejecutar(factura, 1L));
    }

    @Test
    public void deberiaLanzarUnaExepcionCuandoElJugadorNoSeEncuentreRegistrado(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));

        doAnswer(invocation -> {
            // assert
            Factura facturaArg = invocation.getArgument(0);
            assertNotNull(facturaArg);
            return null;
        }).when(repositorioFactura).crear(any(Factura.class));

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        assertThrows("El jugador no se encuentra registrado", NoSeEncuentraExcepcion.class, () -> servicioCrearFactura.ejecutar(factura, 1L));
    }

    @Test
    public void deberiaDevolverElValorCuandoSeIngresaElPlanMensual() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        ArgumentCaptor<Factura> facturaArgumentCaptor = ArgumentCaptor.forClass(Factura.class);
        servicioCrearFactura.ejecutar(factura, 1L);
        verify(repositorioFactura).crear(facturaArgumentCaptor.capture());
        Factura capturarValor = facturaArgumentCaptor.getValue();

        assertEquals("100000", capturarValor.getValor().toString());
    }

    @Test
    public void deberiaDevolverElValorCuandoSeIngresaElPlanTrimestral() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        ArgumentCaptor<Factura> facturaArgumentCaptor = ArgumentCaptor.forClass(Factura.class);
        servicioCrearFactura.ejecutar(factura, 3L);
        verify(repositorioFactura).crear(facturaArgumentCaptor.capture());
        Factura capturarValor = facturaArgumentCaptor.getValue();

        assertEquals("255000", capturarValor.getValor().toString());
    }

    @Test
    public void deberiaDevolverElValorCuandoSeIngresaElPlanSemestral() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        ArgumentCaptor<Factura> facturaArgumentCaptor = ArgumentCaptor.forClass(Factura.class);
        servicioCrearFactura.ejecutar(factura, 6L);
        verify(repositorioFactura).crear(facturaArgumentCaptor.capture());
        Factura capturarValor = facturaArgumentCaptor.getValue();

        assertEquals("420000", capturarValor.getValor().toString());
    }

    @Test
    public void deberiaDevolverElValorDeLaFechaCuandoSeIngresaElPlanMensual() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        ArgumentCaptor<Factura> facturaArgumentCaptor = ArgumentCaptor.forClass(Factura.class);
        servicioCrearFactura.ejecutar(factura, 1L);
        verify(repositorioFactura).crear(facturaArgumentCaptor.capture());
        Factura capturarValor = facturaArgumentCaptor.getValue();

        String mes = factura.sumarMeses(LocalDate.now().toString(), 1L);
        LocalDate fecha = LocalDate.parse(mes);
        assertEquals(fecha.getMonthValue(), capturarValor.getFechaCaducidad().getMonthValue());
    }

    @Test
    public void deberiaDevolverElValorDeLaFechaCuandoSeIngresaElPlanTrimestrall() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        ArgumentCaptor<Factura> facturaArgumentCaptor = ArgumentCaptor.forClass(Factura.class);
        servicioCrearFactura.ejecutar(factura, 3L);
        verify(repositorioFactura).crear(facturaArgumentCaptor.capture());
        Factura capturarValor = facturaArgumentCaptor.getValue();

        String mes = factura.sumarMeses(LocalDate.now().toString(), 3L);
        LocalDate fecha = LocalDate.parse(mes);
        assertEquals(fecha.getMonthValue(), capturarValor.getFechaCaducidad().getMonthValue());
    }

    @Test
    public void deberiaDevolverElValorDeLaFechaCuandoSeIngresaElPlanSemestral() {
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        ArgumentCaptor<Factura> facturaArgumentCaptor = ArgumentCaptor.forClass(Factura.class);
        servicioCrearFactura.ejecutar(factura, 6L);
        verify(repositorioFactura).crear(facturaArgumentCaptor.capture());
        Factura capturarValor = facturaArgumentCaptor.getValue();

        String mes = factura.sumarMeses(LocalDate.now().toString(), 6L);
        LocalDate fecha = LocalDate.parse(mes);
        assertEquals(fecha.getMonthValue(), capturarValor.getFechaCaducidad().getMonthValue());
    }

    @Test
    public void deberiaLanzarUnaExepcionCuandoSeDigiteUnMesNoAceptado(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();
        Factura factura = new FacturaTestDataBuilder().conJugador(jugador).build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioJugador.existePorId(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repositorioFactura.existePorIdJugador(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(false));

        doAnswer(invocation -> {
            // assert
            Factura facturaArg = invocation.getArgument(0);
            assertNotNull(facturaArg);
            return null;
        }).when(repositorioFactura).crear(any(Factura.class));

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, repositorioJugador);
        assertThrows("Solo se puede ingresar valores de 1, 3 y 6 meses", MalaPeticionExcepcion.class, () -> servicioCrearFactura.ejecutar(factura, 9L));
    }

}
