package filtros;

import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ErrorHandlerMiddleware implements HttpErrorHandler {

    @Override
    public CompletionStage<Result> onClientError(Http.RequestHeader request, int statusCode, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", statusCode);
        response.put("error", message);
        return CompletableFuture.completedFuture(
                Results.status(statusCode, Json.toJson(response))
        );
    }

    @Override
    public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getClass().getName());
        response.put("mensaje", exception.getMessage());
        switch (exception.getClass().getName()){
            case "excepciones.DuplicidadExcepcion":
                return CompletableFuture.completedFuture(
                        Results.status(409, Json.toJson(response))
                );
            case "excepciones.NoSeEncuentraExcepcion":
                return CompletableFuture.completedFuture(
                        Results.notFound(Json.toJson(response))
                );
            case "excepciones.MalaPeticionExcepcion":
                return CompletableFuture.completedFuture(
                        Results.badRequest(Json.toJson(response))
                );
            default:
                return CompletableFuture.completedFuture(
                        Results.internalServerError(Json.toJson(response))
                );
        }
    }
}
