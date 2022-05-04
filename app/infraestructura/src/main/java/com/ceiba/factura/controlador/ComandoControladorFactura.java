package infraestructura.src.main.java.com.ceiba.factura.controlador;

import aplicacion.src.main.java.com.ceiba.factura.comando.ComandoFactura;
import aplicacion.src.main.java.com.ceiba.factura.comando.manejador.ManejadorCrearFactura;
import aplicacion.src.main.java.com.ceiba.factura.comando.manejador.ManejadorEliminarFactura;
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

public class ComandoControladorFactura extends Controller {

    private final ManejadorCrearFactura manejadorCrearFactura;
    private final ManejadorEliminarFactura manejadorEliminarFactura;
    private final HttpExecutionContext ec;

    @Inject
    public ComandoControladorFactura(ManejadorCrearFactura manejadorCrearFactura, ManejadorEliminarFactura manejadorEliminarFactura,
                                     HttpExecutionContext ec) {
        this.manejadorCrearFactura = manejadorCrearFactura;
        this.manejadorEliminarFactura = manejadorEliminarFactura;
        this.ec = ec;
    }

    public CompletionStage<Result> insertar(Http.Request request){
        JsonNode json = request.body().asJson();
        final ComandoFactura factura = Json.fromJson(json, ComandoFactura.class);
        return this.manejadorCrearFactura.ejecutar(factura).thenApplyAsync(datos -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("id", datos);
            return created(Json.toJson(respuesta));
        }, ec.current());
    }

    public CompletionStage<Result> eliminar(String id){
        return this.manejadorEliminarFactura.ejecutar(Long.parseLong(id)).thenApplyAsync(res -> {
            return noContent();
        }, ec.current());
    }
}
