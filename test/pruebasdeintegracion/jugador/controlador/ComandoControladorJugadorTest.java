package pruebasdeintegracion.jugador.controlador;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
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
import pruebasdeintegracion.jugador.testdatabuilder.ComandoJugadorTestDataBuilder;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;
import static play.test.Helpers.route;

public class ComandoControladorJugadorTest extends WithApplication {

    private Config config;
    private Database db;

    @Override
    protected Application provideApplication() {
        db = new BaseDeDatos().getDb();
        config = ConfigFactory.load("application");
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void registrarJugador() {
        ComandoJugador comandoJugador = new ComandoJugadorTestDataBuilder().build();
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyJson(Json.toJson(comandoJugador))
                .uri("/api/jugadores");
        Result result = route(app, request);
        assertEquals(CREATED, result.status());
    }

    @Test
    public void deberiaActualizarUnJugador() {
        Long idJugador = 46L;
        ComandoJugador comandoJugador = new ComandoJugadorTestDataBuilder().build();
        comandoJugador.setDocumento(10293822L);
        comandoJugador.setId(idJugador);
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(PUT)
                .bodyJson(Json.toJson(comandoJugador))
                .uri("/api/jugadores/46");
        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}

