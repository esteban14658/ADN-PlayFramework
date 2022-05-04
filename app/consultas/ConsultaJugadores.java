package consultas;

import com.typesafe.config.Config;
import dominio.modelos.JugadorDTO;
import dominio.modelos.MapeoJugador;
import dominio.repositorios.JugadorDAO;
import dominio.servicios.serviciojugador.ServicioJugadorRecord;
import io.vavr.concurrent.Future;
import org.skife.jdbi.v2.DBI;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ConsultaJugadores{

    private Database db;
    private Config config;
    private DatabaseExecutionContext executionContext;

    @Inject
    public ConsultaJugadores(Database db, Config config, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.config = config;
        this.executionContext = executionContext;
    }

    public Future<List<ServicioJugadorRecord>> listarJugadores(){
//        return Future.of(() -> new DBI(db.getDataSource()).onDemand(JugadorDAO.class).listarJugadores())
//                .map(List::ofAll);
        return null;
    }

    public CompletionStage<List<JugadorDTO>> listar(){
        List<JugadorDTO> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
            () -> {
                Connection connection = db.getConnection();
                try {
                    Statement stmt = connection.createStatement();
                    String query = "SELECT * FROM public.jugador";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()){
                        System.out.println("Mira: " + rs.getString("nombre"));
                        list.add(new MapeoJugador().mapRow(rs));
                    }
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return list;
            }, executionContext
        );
    }

    public CompletionStage<JugadorDTO> listarPorDocumento(Long documento) {
        return CompletableFuture.supplyAsync(
                () -> {
                    Connection connection = db.getConnection();
                    JugadorDTO jugadorDTO = new JugadorDTO();
                    try {
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE documento = " + documento.toString();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            jugadorDTO = new MapeoJugador().mapRow(rs);
                        }
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return jugadorDTO;
                }, executionContext
        );
    }

    public CompletionStage<List<JugadorDTO>> listarPorPosicion(String posicion){
        List<JugadorDTO> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    Connection connection = db.getConnection();
                    try {
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE posicion = '" + posicion + "'";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Cantidad de datos: " + list.size());
                    return list;
                }, executionContext
        );
    }

    public CompletionStage<List<JugadorDTO>> listarJugadoresSinFactura(){
        List<JugadorDTO> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    Connection connection = db.getConnection();
                    try {
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE posicion = '" + "" + "'";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            list.add(new MapeoJugador().mapRow(rs));
                        }
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Cantidad de datos: " + list.size());
                    return list;
                }, executionContext
        );
    }
}
