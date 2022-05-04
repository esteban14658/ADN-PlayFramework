package infraestructura.src.main.java.com.ceiba.factura.adaptador.dao;

import dominio.src.main.java.com.ceiba.factura.modelo.dto.DtoFactura;
import dominio.src.main.java.com.ceiba.factura.puerto.dao.DaoFactura;
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

public class DaoFacturaPostgreSQL implements DaoFactura {

    private final Database db;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public DaoFacturaPostgreSQL(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<DtoFactura>> listar() {
        List<DtoFactura> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * from public.factura c inner join public.jugador j\n" +
                                "on c.jugador = j.id\n" +
                                "WHERE CAST(fecha_caducidad AS date) >= CAST(now() AS date)\n" +
                                "  and CAST(fecha_ingreso AS date) <= CAST(now() AS date)";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            list.add(new MapeoFactura().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return list;
                }, executionContext
        );
    }
}
