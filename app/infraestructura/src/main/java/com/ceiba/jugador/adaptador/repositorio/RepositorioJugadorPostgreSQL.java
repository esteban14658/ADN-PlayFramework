package infraestructura.src.main.java.com.ceiba.jugador.adaptador.repositorio;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.repositorio.RepositorioJugador;
import infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao.MapeoJugador;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RepositorioJugadorPostgreSQL implements RepositorioJugador {

    private final Database db;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public RepositorioJugadorPostgreSQL(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Long> crear(Jugador jugador) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "INSERT INTO public.jugador(" +
                            "documento, nombre, apellido, fecha_nacimiento, peso, altura, posicion, pie_habil) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    Long id = 0L;
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                        stmt.setLong(1, jugador.getDocumento());
                        stmt.setString(2, jugador.getNombre());
                        stmt.setString(3, jugador.getApellido());
                        stmt.setTimestamp(4, convertir(jugador.getFechaNacimiento()));
                        stmt.setFloat(5, jugador.getPeso());
                        stmt.setFloat(6, jugador.getAltura());
                        stmt.setString(7, jugador.getPosicion());
                        stmt.setString(8, jugador.getPieHabil());
                        int affectedRows = stmt.executeUpdate();

                        if (affectedRows > 0){
                            try (ResultSet rs = stmt.getGeneratedKeys()){
                                if (rs.next()){
                                    id = rs.getLong(1);
                                }
                            } catch (SQLException ex){
                                System.out.println(ex.getMessage());
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return id;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Void> actualizar(Jugador jugador) {
        return CompletableFuture.runAsync(
                () -> {
                    String SQL = "UPDATE public.jugador " +
                            "SET documento = " + jugador.getDocumento().toString() + ", nombre = '" + jugador.getNombre() +
                            "', apellido = '" + jugador.getApellido() +"', fecha_nacimiento = '" + jugador.getFechaNacimiento().toString() +"'," +
                            " peso = " + jugador.getPeso() + ", altura = " + jugador.getAltura() + "," +
                            " posicion = '" + jugador.getPosicion() + "', pie_habil = '" + jugador.getPieHabil() +
                            "' WHERE id = " + jugador.getId().toString();
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }, executionContext
        );
    }

    // DELETE FROM public.jugador
    //	WHERE id = ;
    @Override
    public CompletionStage<Void> eliminar(Long id) {
        return CompletableFuture.runAsync(
                () -> {
                    String SQL = "DELETE FROM public.jugador " +
                            "WHERE id = " + id.toString();
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Boolean> existe(String nombre) {
        return null;
    }

    @Override
    public CompletionStage<Boolean> existePorId(Long id) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.jugador " +
                            "WHERE id = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.setLong(1, id);
                        ResultSet rs = stmt.executeQuery();
                        int cantidad = 0;
                        if (rs.next()){
                            cantidad = rs.getInt(1);
                        }
                        if (cantidad > 0){
                            return true;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Boolean> existePorDocumento(Long documento) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.jugador " +
                            "WHERE documento = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.setLong(1, documento);
                        ResultSet rs = stmt.executeQuery();
                        int cantidad = 0;
                        if (rs.next()){
                            cantidad = rs.getInt(1);
                        }
                        if (cantidad > 0){
                            return true;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Boolean> existeJugadorConFactura(Long id) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.factura " +
                            "WHERE jugador = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.setLong(1, id);
                        ResultSet rs = stmt.executeQuery();
                        int cantidad = 0;
                        if (rs.next()){
                            cantidad = rs.getInt(1);
                        }
                        if (cantidad > 0){
                            return true;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Boolean> existeJugadorConAsistencias(Long id) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.asistencia " +
                                "WHERE jugador = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.setLong(1, id);
                        ResultSet rs = stmt.executeQuery();
                        int cantidad = 0;
                        if (rs.next()){
                            cantidad = rs.getInt(1);
                        }
                        if (cantidad > 0){
                            return true;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }, executionContext
        );
    }

    private Timestamp convertir (LocalDate fecha){
        Timestamp timestamp = Timestamp.valueOf(fecha.atStartOfDay());
        return timestamp;
    }
}
