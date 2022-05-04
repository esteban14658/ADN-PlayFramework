package controladores;

import consultas.ConsultaJugadores;
import dominio.modelos.JugadorDTO;
import play.Logger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class JugadorControlador extends Controller {

    private final ConsultaJugadores consultaJugadores;
    private final HttpExecutionContext ec;
    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    public JugadorControlador(ConsultaJugadores consultaJugadores, HttpExecutionContext ec) {
        this.consultaJugadores = consultaJugadores;
        this.ec = ec;
    }

    public CompletionStage<Result> listarJugadores(){
        return this.consultaJugadores.listar().thenApplyAsync(res -> {
            final List<JugadorDTO> listaJugadores = res;
            return ok(Json.toJson(listaJugadores));
        }, ec.current());
    }

    public CompletionStage<Result> listarPorPosicion(String posicion){
        return this.consultaJugadores.listarPorPosicion(posicion).thenApplyAsync(res -> {
            final List<JugadorDTO> listaJugadores = res;
            return ok(Json.toJson(listaJugadores));
        }, ec.current());
    }

    public CompletionStage<Result> buscarPorDocumento(String documento) {
        return this.consultaJugadores.listarPorDocumento(Long.parseLong(documento)).thenApplyAsync(res -> {
            if (res.getDocumento() != null){
                return ok(Json.toJson(res));
            } else {
                return notFound();
            }
        }, ec.current());
    }
}
