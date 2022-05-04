package dominio.src.main.java.com.ceiba.factura.servicio;

import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import dominio.src.main.java.com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import excepciones.DuplicidadExcepcion;
import excepciones.MalaPeticionExcepcion;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.concurrent.CompletionStage;

public class ServicioCrearFactura {

    public static final String YA_TIENE_UNA_FACTURA_ASIGNADA = "Ya tiene una factura asignada";
    public static final String SOLO_SE_PUEDE_INGRESAR_VALORES_DE_1_3_Y_6_MESES = "Solo se puede ingresar valores de 1, 3 y 6 meses";
    public static final int UN_MES = 1;
    public static final int TRIMESTRAL = 3;
    public static final int SEMESTRAL = 6;
    public static final String EL_JUGADOR_NO_SE_ENCUENTRA_REGISTRADO = "El jugador no se encuentra registrado";

    private final RepositorioFactura repositorioFactura;
    private final RepositorioJugador repositorioJugador;

    @Inject
    public ServicioCrearFactura(RepositorioFactura repositorioFactura, RepositorioJugador repositorioJugador) {
        this.repositorioFactura = repositorioFactura;
        this.repositorioJugador = repositorioJugador;
    }

    public CompletionStage<Long> ejecutar(Factura factura, Long meses){
        validarExistenciaPrevia(factura);
        validarExisteElJugador(factura);
        return this.repositorioFactura.crear(ingresoDeDatos(factura, meses));
    }

    private Factura ingresoDeDatos(Factura factura, Long meses) {
        String fechaInicio = String.valueOf(LocalDate.now());
        String fechaCaducidad = factura.sumarMeses(fechaInicio, meses);
        double valorFactura = 0;
        if (meses == UN_MES){
            valorFactura = factura.mensualidadNormal();
        } else if (meses == TRIMESTRAL){
            valorFactura = factura.promocionTresMeses();
        } else if (meses == SEMESTRAL){
            valorFactura = factura.promocionSeisMeses();
        } else {
            throw new MalaPeticionExcepcion(SOLO_SE_PUEDE_INGRESAR_VALORES_DE_1_3_Y_6_MESES);
        }
        Long valor = Math.round(valorFactura);
        factura.setValor(valor);
        factura.setFechaIngreso(LocalDate.parse(fechaInicio));
        factura.setFechaCaducidad(LocalDate.parse(fechaCaducidad));
        return factura;
    }

    @SneakyThrows
    private void validarExistenciaPrevia(Factura factura) {
        Boolean existe = this.repositorioFactura.existePorIdJugador(factura.getJugador().getId())
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (existe){
            throw new DuplicidadExcepcion(YA_TIENE_UNA_FACTURA_ASIGNADA);
        }
    }

    @SneakyThrows
    private void validarExisteElJugador(Factura factura) {
        Boolean existe = this.repositorioJugador.existePorId(factura.getJugador().getId())
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (!existe){
            throw new NoSeEncuentraExcepcion(EL_JUGADOR_NO_SE_ENCUENTRA_REGISTRADO);
        }
    }
}
