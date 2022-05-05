package infraestructura.src.main.java.com.ceiba.jugador.controlador;

import aplicacion.src.main.java.com.ceiba.jugador.consulta.*;
import com.fasterxml.jackson.databind.JsonNode;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoFiltro;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ConsultaControladorJugador extends Controller {

    private final ManejadorListarJugadores manejadorListarJugadores;
    private final ManejadorObtenerEquipoJugadores manejadorObtenerEquipoJugadores;
    private final ManejadorObtenerPorDocumentoJugador manejadorObtenerPorDocumentoJugador;
    private final ManejadorListarJugadoresPorCategoria manejadorListarJugadoresPorCategoria;
    private final ManejadorListarJugadoresSinAsistencia manejadorListarJugadoresSinAsistencia;
    private final HttpExecutionContext ec;

    @Inject
    public ConsultaControladorJugador(ManejadorListarJugadores manejadorListarJugadores, ManejadorObtenerEquipoJugadores manejadorObtenerEquipoJugadores,
                                      ManejadorObtenerPorDocumentoJugador manejadorObtenerPorDocumentoJugador, ManejadorListarJugadoresPorCategoria manejadorListarJugadoresPorCategoria,
                                      ManejadorListarJugadoresSinAsistencia manejadorListarJugadoresSinAsistencia, HttpExecutionContext ec) {
        this.manejadorListarJugadores = manejadorListarJugadores;
        this.manejadorObtenerEquipoJugadores = manejadorObtenerEquipoJugadores;
        this.manejadorObtenerPorDocumentoJugador = manejadorObtenerPorDocumentoJugador;
        this.manejadorListarJugadoresPorCategoria = manejadorListarJugadoresPorCategoria;
        this.manejadorListarJugadoresSinAsistencia = manejadorListarJugadoresSinAsistencia;
        this.ec = ec;
    }

    public CompletionStage<Result> listar(){
        return this.manejadorListarJugadores.ejecutar().thenApplyAsync(resultado -> {
            final List<DtoJugador> listaJugadores = resultado;
            return ok(Json.toJson(listaJugadores));
        }, ec.current());
    }

    public CompletionStage<Result> obtenerEquipo(Http.Request request){
        JsonNode json = request.body().asJson();
        final DtoFiltro dtoFiltro = Json.fromJson(json, DtoFiltro.class);
        return this.manejadorObtenerEquipoJugadores.ejecutar(dtoFiltro).thenApplyAsync(resultado -> {
            final List<DtoJugador> listaJugadores = resultado;
            return ok(Json.toJson(listaJugadores));
        }, ec.current());
    }

    public CompletionStage<Result> listarPorCategoria(String fecha){
        return this.manejadorListarJugadoresPorCategoria.ejecutar(fecha).thenApplyAsync(resultado ->
            ok(Json.toJson(resultado))
        , ec.current());
    }

    public CompletionStage<Result> listarJugadoresSinAsistencia(){
        return this.manejadorListarJugadoresSinAsistencia.ejecutar().thenApplyAsync(resultado ->
            ok(Json.toJson(resultado))
        , ec.current());
    }

    public CompletionStage<Result> obtenerPorDocumento(String documento){
        return this.manejadorObtenerPorDocumentoJugador.ejecutar(Long.parseLong(documento)).thenApplyAsync(resultado ->
            ok(Json.toJson(resultado.get()))
        , ec.current());
    }
}
