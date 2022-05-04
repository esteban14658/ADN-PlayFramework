package infraestructura.src.main.java.com.ceiba.factura.controlador;

import aplicacion.src.main.java.com.ceiba.factura.consulta.ManejadorListarFactura;
import aplicacion.src.main.java.com.ceiba.factura.consulta.ManejadorListarJugadoresSinFactura;
import dominio.src.main.java.com.ceiba.factura.modelo.dto.DtoFactura;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ConsultaControladorFactura extends Controller {

    private final ManejadorListarFactura manejadorListarFactura;
    private final ManejadorListarJugadoresSinFactura manejadorListarJugadoresSinFactura;
    private final HttpExecutionContext ec;

    @Inject
    public ConsultaControladorFactura(ManejadorListarFactura manejadorListarFactura, ManejadorListarJugadoresSinFactura manejadorListarJugadoresSinFactura,
                                      HttpExecutionContext ec) {
        this.manejadorListarFactura = manejadorListarFactura;
        this.manejadorListarJugadoresSinFactura = manejadorListarJugadoresSinFactura;
        this.ec = ec;
    }

    public CompletionStage<Result> listar(){
        return this.manejadorListarFactura.ejecutar().thenApplyAsync(resultado -> {
            final List<DtoFactura> listaFactura = resultado;
            return ok(Json.toJson(listaFactura));
        }, ec.current());
    }

    public CompletionStage<Result> listarJugadorSinFactura(){
        return this.manejadorListarJugadoresSinFactura.ejecutar().thenApplyAsync(resultado -> {
            return ok(Json.toJson(resultado));
        }, ec.current());
    }
}
