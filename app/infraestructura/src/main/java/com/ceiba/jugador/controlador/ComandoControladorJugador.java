package infraestructura.src.main.java.com.ceiba.jugador.controlador;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import aplicacion.src.main.java.com.ceiba.jugador.comando.manejador.ManejadorActualizarJugador;
import aplicacion.src.main.java.com.ceiba.jugador.comando.manejador.ManejadorCrearJugador;
import aplicacion.src.main.java.com.ceiba.jugador.comando.manejador.ManejadorEliminarJugador;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class ComandoControladorJugador extends Controller {

    private final ManejadorCrearJugador manejadorCrearJugador;
    private final ManejadorActualizarJugador manejadorActualizarJugador;
    private final ManejadorEliminarJugador manejadorEliminarJugador;
    private final HttpExecutionContext ec;

    @Inject
    public ComandoControladorJugador(ManejadorCrearJugador manejadorCrearJugador, ManejadorActualizarJugador manejadorActualizarJugador, ManejadorEliminarJugador manejadorEliminarJugador, HttpExecutionContext ec) {
        this.manejadorCrearJugador = manejadorCrearJugador;
        this.manejadorActualizarJugador = manejadorActualizarJugador;
        this.manejadorEliminarJugador = manejadorEliminarJugador;
        this.ec = ec;
    }

    public CompletionStage<Result> insertar(Http.Request request){
        JsonNode json = request.body().asJson();
        final ComandoJugador jugador = Json.fromJson(json, ComandoJugador.class);
        return this.manejadorCrearJugador.ejecutar(jugador).thenApplyAsync(datos -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("id", datos);
            return created(Json.toJson(respuesta));
        }, ec.current());
    }

    public CompletionStage<Result> actualizar(Http.Request request, String id){
        JsonNode json = request.body().asJson();
        final ComandoJugador jugador = Json.fromJson(json, ComandoJugador.class);
        return this.manejadorActualizarJugador.ejecutar(jugador, id).thenApplyAsync(res -> {
            return ok();
        }, ec.current());
    }

    public CompletionStage<Result> eliminar(String id){
        return this.manejadorEliminarJugador.ejecutar(Long.parseLong(id)).thenApplyAsync(res -> {
            return noContent();
        }, ec.current());
    }
}
