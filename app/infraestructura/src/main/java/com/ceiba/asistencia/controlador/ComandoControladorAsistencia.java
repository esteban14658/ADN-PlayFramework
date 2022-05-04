package infraestructura.src.main.java.com.ceiba.asistencia.controlador;

import aplicacion.src.main.java.com.ceiba.asistencia.comando.ComandoAsistencia;
import aplicacion.src.main.java.com.ceiba.asistencia.comando.manejador.ManejadorCrearAsistencia;
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

public class ComandoControladorAsistencia extends Controller {

    private final ManejadorCrearAsistencia manejadorCrearAsistencia;
    private final HttpExecutionContext ec;

    @Inject
    public ComandoControladorAsistencia(ManejadorCrearAsistencia manejadorCrearAsistencia, HttpExecutionContext ec) {
        this.manejadorCrearAsistencia = manejadorCrearAsistencia;
        this.ec = ec;
    }

    public CompletionStage<Result> insertar(Http.Request request){
        JsonNode json = request.body().asJson();
        final ComandoAsistencia comandoAsistencia = Json.fromJson(json, ComandoAsistencia.class);
        return this.manejadorCrearAsistencia.ejecutar(comandoAsistencia).thenApplyAsync(datos -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("id", datos);
            return created(Json.toJson(respuesta));
        }, ec.current());
    }

}
