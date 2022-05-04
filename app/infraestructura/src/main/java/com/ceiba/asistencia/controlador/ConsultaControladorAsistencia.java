package infraestructura.src.main.java.com.ceiba.asistencia.controlador;

import aplicacion.src.main.java.com.ceiba.asistencia.consulta.ManejadorListarAsistencias;
import dominio.src.main.java.com.ceiba.asistencia.modelo.dto.DtoAsistencia;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ConsultaControladorAsistencia extends Controller {

    private final ManejadorListarAsistencias manejadorListarAsistencias;
    private final HttpExecutionContext ec;

    @Inject
    public ConsultaControladorAsistencia(ManejadorListarAsistencias manejadorListarAsistencias, HttpExecutionContext ec) {
        this.manejadorListarAsistencias = manejadorListarAsistencias;
        this.ec = ec;
    }

    public CompletionStage<Result> listar(){
        return this.manejadorListarAsistencias.ejecutar().thenApplyAsync(resultado -> {
            final List<DtoAsistencia> lista = resultado;
            return ok(Json.toJson(lista));
        }, ec.current());
    }
}
