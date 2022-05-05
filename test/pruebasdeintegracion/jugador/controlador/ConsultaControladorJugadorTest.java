package pruebasdeintegracion.jugador.controlador;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoFiltro;
import org.junit.Test;
import persistencia.BaseDeDatos;
import play.Application;
import play.db.Database;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;


import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class ConsultaControladorJugadorTest extends WithApplication {

    private Config config;
    private Database db;

    @Override
    protected Application provideApplication() {
        db = new BaseDeDatos().getDb();
        config = ConfigFactory.load("application");
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void deberiaListarJugadores() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/api/jugadores");
        Result result = route(app, request);
        assertTrue(result.body().contentLength().isPresent());
    }

    @Test
    public void deberiaListarJugadoresSinAsistenciaHoy() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/api/jugadores/asistencia");
        Result result = route(app, request);
        assertTrue(result.body().contentLength().isPresent());
    }

    @Test
    public void deberiaListarPorCategoria() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/api/jugadores/categoria/2022");
        Result result = route(app, request);
        assertTrue(result.body().contentLength().isPresent());
    }

    @Test
    public void deberiaListarElEquipo() {
        DtoFiltro dtoFiltro = new DtoFiltro("4", "4", "2");
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .bodyJson(Json.toJson(dtoFiltro))
                .uri("/api/jugadores/equipo");
        Result result = route(app, request);
        assertTrue(result.body().contentLength().isPresent());
    }

    @Test
    public void deberiaObtenerUnJugadorPorElDocumento() {
        String documento = "1010101010";
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/api/jugadores/jugador/" + documento);
        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
