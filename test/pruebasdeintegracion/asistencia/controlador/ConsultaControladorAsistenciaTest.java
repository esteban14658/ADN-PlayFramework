package pruebasdeintegracion.asistencia.controlador;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;
import persistencia.BaseDeDatos;
import play.Application;
import play.db.Database;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class ConsultaControladorAsistenciaTest extends WithApplication {

    private Config config;
    private Database db;

    @Override
    protected Application provideApplication() {
        db = new BaseDeDatos().getDb();
        config = ConfigFactory.load("application");
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void deberiaListarAsistencias() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/api/asistencias");
        Result result = route(app, request);
        assertTrue(result.body().contentLength().isPresent());
    }
}
