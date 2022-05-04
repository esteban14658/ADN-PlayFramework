package infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao;

import com.typesafe.config.Config;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class DaoJugadorPostgreSQL implements DaoJugador {

    private Database db;
    private Config config;
    private DatabaseExecutionContext executionContext;

    @Inject
    public DaoJugadorPostgreSQL(Database db, Config config, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.config = config;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<DtoJugador>> listar() {
        List<DtoJugador> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return list;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<List<DtoJugador>> listarPorPosicion(String posicion) {
        List<DtoJugador> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE posicion = '" + posicion + "'";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Cantidad de datos: " + list.size());
                    return list;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<List<DtoJugador>> listarJugadoresSinFactura() {
        List<DtoJugador> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT j.id, j.documento, j.nombre, j.apellido, j.fecha_nacimiento,\n" +
                                "j.peso, j.altura, j.posicion, j.pie_habil\n" +
                                "FROM public.factura c right join public.jugador j\n" +
                                "ON c.jugador = j.id\n" +
                                "WHERE CAST(fecha_caducidad AS date) >= CAST(now() AS date)\n" +
                                "AND CAST(fecha_ingreso AS date) <= CAST(now() AS date) AND\n" +
                                "c.estado != 1\n" +
                                "OR c.jugador is null";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return list;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<List<DtoJugador>> listarSinAsistencias() {
        List<DtoJugador> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT *\n" +
                                "FROM public.jugador\n" +
                                "WHERE NOT EXISTS(\n" +
                                "SELECT asistencia.id  FROM public.asistencia\n" +
                                "WHERE asistencia.jugador = jugador.id and\n" +
                                "        CAST(fecha AS date) = CAST(now() AS date)\n" +
                                ")";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return list;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<List<DtoJugador>> listarPorCategoria(String categoria) {
        List<DtoJugador> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE EXTRACT(YEAR FROM DATE (fecha_nacimiento)) = '" + categoria + "'";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return list;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Optional<DtoJugador>> obtenerPorDocumento(Long documento) {
        return CompletableFuture.supplyAsync(
                () -> {
                    DtoJugador dtoJugador = new DtoJugador();
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE documento = " + documento.toString();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            dtoJugador = new MapeoJugador().mapRow(rs);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Optional<DtoJugador> optional = Optional.of(dtoJugador);
                    return optional;
                }, executionContext
        );
    }
}
