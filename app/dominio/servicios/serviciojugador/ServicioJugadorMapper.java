package dominio.servicios.serviciojugador;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ServicioJugadorMapper implements ResultSetMapper<ServicioJugadorRecord> {

    @Override
    public ServicioJugadorRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new ServicioJugadorRecord(
                r.getLong("id"),
                r.getLong("documento"),
                r.getString("nombre"),
                r.getString("apellido"),
                extraerLocalDate(r, "fecha_nacimiento"),
                r.getFloat("peso"),
                r.getFloat("altura"),
                r.getString("posicion"),
                r.getString("pie_habil")
        );
    }

    private LocalDate extraerLocalDate(ResultSet resultSet, String label) throws SQLException {
        Timestamp fecha = resultSet.getTimestamp(label);
        LocalDate resultado = null;
        if (!resultSet.wasNull()) {
            resultado = fecha.toLocalDateTime().toLocalDate();
        }
        return resultado;
    }
}
