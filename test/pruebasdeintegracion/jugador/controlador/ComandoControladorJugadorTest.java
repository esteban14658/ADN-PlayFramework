package pruebasdeintegracion.jugador.controlador;

import aplicacion.src.main.java.com.ceiba.jugador.comando.ComandoJugador;
import com.typesafe.config.ConfigFactory;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import pruebasdeintegracion.jugador.testdatabuilder.ComandoJugadorTestDataBuilder;

import com.typesafe.config.Config;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.CREATED;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

public class ComandoControladorJugadorTest {

    private Config config;
    private Application application;

    @Before
    public void setUp(){
        config = ConfigFactory.load();
        application = new GuiceApplicationBuilder().build();
    }
/*
    @Before
    public void setUp() throws Exception {
        database = Databases.createFrom(
                "db-test",
                "org.h2.Driver",
                "jdbc:h2:mem:test;MODE=PostgreSQL"
        );
        Evolutions.applyEvolutions(database, Evolutions.forDefault(
                new Evolution(
                        1,
                        "CREATE TABLE jugador (\n" +
                                " id SERIAL,\n" +
                                " documento INT NOT NULL unique,\n" +
                                " nombre VARCHAR(40) NOT NULL,\n" +
                                " apellido VARCHAR(40) NOT NULL,\n" +
                                " fecha_nacimiento DATE NOT NULL,\n" +
                                " peso FLOAT NOT NULL,\n" +
                                " altura FLOAT NOT NULL,\n" +
                                " posicion VARCHAR(20) NOT NULL,\n" +
                                " pie_habil VARCHAR(20) NOT NULL,\n" +
                                " PRIMARY KEY (id)\n" +
                                ");",
                        "DROP TABLE jugador"
                )
        ));
    }

    @After
    public void tearDown() throws Exception {
        database.shutdown();
    }
*/
    /*
    @SneakyThrows
    @Test
    public void deberiaCrearUnJugador() {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> envio = new HashMap<>();
        envio.put("id", null);
        envio.put("documento", 1010101001);
        envio.put("nombre", "Diego");
        envio.put("apellido", "Peralta");
        envio.put("fechaNacimiento", "2011-02-04");
        envio.put("peso", 47.2);
        envio.put("altura", 1.56);
        envio.put("posicion", "Mediocampista");
        envio.put("pieHabil", "Izquierdo");
        JsonNode json = objectMapper.convertValue(envio, JsonNode.class);
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyJson(json)
                .uri("/api/jugadores");

        ComandoJugador comandoJugador = new ComandoJugador();
        comandoJugador.setId(Long.parseLong(envio.get("id").toString()));
        comandoJugador.setDocumento(Long.parseLong(envio.get("documento").toString()));
        comandoJugador.setNombre(envio.get("nombre").toString());
        comandoJugador.setApellido(envio.get("apellido").toString());
        comandoJugador.setFechaNacimiento(LocalDate.parse(envio.get("fechaNacimiento").toString()));
        comandoJugador.setPeso(Float.parseFloat(envio.get("peso").toString()));
        comandoJugador.setAltura(Float.parseFloat(envio.get("altura").toString()));
        comandoJugador.setPosicion(envio.get("posicion").toString());
        comandoJugador.setPieHabil(envio.get("pieHabil").toString());

        Mockito.when(manejadorCrearJugador.ejecutar(comandoJugador))
                        .thenReturn(CompletableFuture.completedFuture(1L));

        Mockito.when(repositorioJugador.existePorDocumento(Mockito.anyLong()))
                .thenReturn(CompletableFuture.completedFuture(false));

        Result result = comandoControladorJugador.insertar(request.build())
                        .toCompletableFuture().get();
        assertEquals(CREATED, result.status());
    }*/

    @Test
    public void deberiaInsertar() {

        ComandoJugador comandoJugador = new ComandoJugadorTestDataBuilder().build();
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(Json.toJson(comandoJugador))
                .uri("/api/jugadores");

        Result result = route(application, request);
        assertEquals(CREATED, result.status());
    }
}
