package infraestructura.src.main.java.com.ceiba.factura.adaptador.repositorio;

import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import dominio.src.main.java.com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RepositorioFacturaPostgreSQL implements RepositorioFactura {

    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public RepositorioFacturaPostgreSQL(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Long> crear(Factura factura) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "INSERT INTO public.factura(" +
                            "valor, fecha_ingreso, fecha_caducidad, jugador, estado, descripcion) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                    Long id = 0L;
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                        stmt.setLong(1, factura.getValor());
                        stmt.setTimestamp(2, convertir(factura.getFechaIngreso()));
                        stmt.setTimestamp(3, convertir(factura.getFechaCaducidad()));
                        stmt.setLong(4, factura.getJugador().getId());
                        stmt.setInt(5, factura.getEstado());
                        stmt.setString(6, factura.getDescripcion());
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
    public CompletionStage<Void> eliminar(Long id) {
        return CompletableFuture.runAsync(
                () -> {
                    String SQL = "DELETE FROM public.factura " +
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
    public CompletionStage<Boolean> existePorIdJugador(Long idJugador) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.factura " +
                            "WHERE CAST(fecha_caducidad AS DATE) >= CAST(now() AS DATE) " +
                            "AND CAST (fecha_ingreso AS DATE) <= CAST(now() AS DATE) AND " +
                            "jugador = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.setLong(1, idJugador);
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
    public CompletionStage<Boolean> existePorId(Long id) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.factura " +
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

    private Timestamp convertir (LocalDate fecha){
        Timestamp timestamp = Timestamp.valueOf(fecha.atStartOfDay());
        return timestamp;
    }
}
