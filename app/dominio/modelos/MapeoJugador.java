package dominio.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MapeoJugador {

    public JugadorDTO mapRow(ResultSet rs) throws SQLException{

        JugadorDTO record = new JugadorDTO();
        record.setId(rs.getLong("id"));
        record.setDocumento(rs.getLong("documento"));
        record.setNombre(rs.getString("nombre"));
        record.setApellido(rs.getString("apellido"));
        record.setFechaNacimiento(extraerLocalDate(rs, "fecha_nacimiento"));
        record.setPeso(rs.getFloat("peso"));
        record.setAltura(rs.getFloat("altura"));
        record.setPosicion(rs.getString("posicion"));
        record.setPieHabil(rs.getString("pie_habil"));
        return record;
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
