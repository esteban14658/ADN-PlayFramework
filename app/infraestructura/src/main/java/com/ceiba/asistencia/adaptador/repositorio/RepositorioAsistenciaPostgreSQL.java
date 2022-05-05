package infraestructura.src.main.java.com.ceiba.asistencia.adaptador.repositorio;

import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;
import dominio.src.main.java.com.ceiba.asistencia.puerto.repositorio.RepositorioAsistencia;
import excepciones.MalaPeticionExcepcion;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RepositorioAsistenciaPostgreSQL implements RepositorioAsistencia {

    private final Database db;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public RepositorioAsistenciaPostgreSQL(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Long> crear(Asistencia asistencia) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String sql = "INSERT INTO public.asistencia(" +
                            "fecha, jugador) " +
                            "VALUES (?, ?)";
                    Long id = 0L;
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        stmt.setTimestamp(1, convertir(asistencia.getFecha()));
                        stmt.setLong(2, asistencia.getJugador().getId());
                        int affectedRows = stmt.executeUpdate();

                        if (affectedRows > 0){
                            ResultSet rs = stmt.getGeneratedKeys();
                                if (rs.next()){
                                    id = rs.getLong(1);
                                }
                        }
                    } catch (SQLException e) {
                        throw new MalaPeticionExcepcion(e.getMessage());
                    }
                    return id;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Boolean> registroDiario(Long id) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String sql = "SELECT COUNT(*) FROM public.asistencia " +
                            "WHERE CAST(fecha AS date) = CAST(now() AS date) " +
                            "AND jugador = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(sql);
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
                        throw new MalaPeticionExcepcion(e.getMessage());
                    }
                    return false;
                }, executionContext
        );
    }

    private Timestamp convertir (LocalDate fecha){
        return Timestamp.valueOf(fecha.atStartOfDay());
    }
}
