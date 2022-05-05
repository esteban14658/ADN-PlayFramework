package pruebasdeintegracion.factura.controlador;

import aplicacion.src.main.java.com.ceiba.factura.comando.ComandoFactura;
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
import pruebasdeintegracion.factura.testdatabuilder.ComandoFacturaTestDataBuilder;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.CREATED;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

public class ComandoControladorFacturaTest extends WithApplication {

    private Config config;
    private Database db;

    @Override
    protected Application provideApplication() {
        db = new BaseDeDatos().getDb();
        config = ConfigFactory.load("application");
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void deberiaCrearUnaFactura() {
        ComandoFactura comandoFactura = new ComandoFacturaTestDataBuilder().build();
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyJson(Json.toJson(comandoFactura))
                .uri("/api/facturas");
        Result result = route(app, request);
        assertEquals(CREATED, result.status());
    }
}
