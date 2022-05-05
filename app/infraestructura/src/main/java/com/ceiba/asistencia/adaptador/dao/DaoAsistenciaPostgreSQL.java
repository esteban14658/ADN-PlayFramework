package infraestructura.src.main.java.com.ceiba.asistencia.adaptador.dao;

import dominio.src.main.java.com.ceiba.asistencia.modelo.dto.DtoAsistencia;
import dominio.src.main.java.com.ceiba.asistencia.puerto.dao.DaoAsistencia;
import excepciones.MalaPeticionExcepcion;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class DaoAsistenciaPostgreSQL implements DaoAsistencia {

    private final Database db;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public DaoAsistenciaPostgreSQL(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<DtoAsistencia>> listar() {
        List<DtoAsistencia> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * from public.asistencia a inner join public.jugador j\n" +
                                "on a.jugador = j.id";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            list.add(new MapeoAsistencia().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new MalaPeticionExcepcion(e.getMessage());
                    }
                    return list;
                }, executionContext
        );
    }
}
