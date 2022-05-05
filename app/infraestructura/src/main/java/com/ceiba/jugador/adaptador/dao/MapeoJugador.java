package infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MapeoJugador {

    public DtoJugador mapRow(ResultSet rs) throws SQLException {

        DtoJugador dtoJugador = new DtoJugador();
        dtoJugador.setId(rs.getLong("id"));
        dtoJugador.setDocumento(rs.getLong("documento"));
        dtoJugador.setNombre(rs.getString("nombre"));
        dtoJugador.setApellido(rs.getString("apellido"));
        dtoJugador.setFechaNacimiento(extraerLocalDate(rs, "fecha_nacimiento"));
        dtoJugador.setPeso(rs.getFloat("peso"));
        dtoJugador.setAltura(rs.getFloat("altura"));
        dtoJugador.setPosicion(rs.getString("posicion"));
        dtoJugador.setPieHabil(rs.getString("pie_habil"));
        return dtoJugador;
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

