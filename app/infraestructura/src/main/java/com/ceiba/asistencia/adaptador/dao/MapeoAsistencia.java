package infraestructura.src.main.java.com.ceiba.asistencia.adaptador.dao;

import dominio.src.main.java.com.ceiba.asistencia.modelo.dto.DtoAsistencia;
import infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao.MapeoJugador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MapeoAsistencia {

    public DtoAsistencia mapRow(ResultSet rs) throws SQLException {
        return new DtoAsistencia(
            rs.getLong("id"),
            extraerLocalDate(rs, "fecha"),
            new MapeoJugador().mapRow(rs)
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
