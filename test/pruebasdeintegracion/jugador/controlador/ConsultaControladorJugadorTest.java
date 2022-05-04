package pruebasdeintegracion.jugador.controlador;

import org.junit.Before;
import play.db.Database;
import play.db.Databases;
import play.db.evolutions.Evolution;
import play.db.evolutions.Evolutions;

import javax.inject.Inject;

public class ConsultaControladorJugadorTest {

    @Inject
    Database database;

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
}
