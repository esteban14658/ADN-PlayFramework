package pruebasdeintegracion.asistencia.controlador;

import akka.actor.ActorSystem;
import aplicacion.src.main.java.com.ceiba.asistencia.comando.ComandoAsistencia;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dominio.src.main.java.com.ceiba.asistencia.puerto.repositorio.RepositorioAsistencia;
import infraestructura.src.main.java.com.ceiba.asistencia.adaptador.repositorio.RepositorioAsistenciaPostgreSQL;
import org.junit.Test;
import org.mockito.Mockito;
import persistencia.BaseDeDatos;
import persistencia.DatabaseExecutionContext;
import play.Application;
import play.api.Configuration;
import play.db.Database;
import play.db.Databases;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import pruebasdeintegracion.asistencia.testdatabuilder.ComandoAsistenciaTestDataBuilder;
import scala.collection.immutable.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.mvc.Http.Status.CREATED;
import static play.test.Helpers.*;

public class ComandoControladorAsistenciaTest extends WithApplication {

    private Config config;
    private Database db;

    @Override
    protected Application provideApplication() {
        db = Databases.createFrom(
                "test",
                "org.h2.Driver",
                "jdbc:h2:mem:test"
        );
        config = ConfigFactory.load("application.test");
        return new GuiceApplicationBuilder()
                .build();
    }

    @Test
    public void deberiaCrearUnaAsistencia() {
        ComandoAsistencia comandoAsistencia = new ComandoAsistenciaTestDataBuilder().build();
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyJson(Json.toJson(comandoAsistencia))
                .uri("/api/asistencias");
        Result result = route(app, request);
        assertEquals("application/json", result.contentType().get());
        assertEquals(CREATED, result.status());
    }
}
