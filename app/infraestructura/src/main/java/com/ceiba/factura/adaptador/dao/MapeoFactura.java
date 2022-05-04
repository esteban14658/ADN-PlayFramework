package infraestructura.src.main.java.com.ceiba.factura.adaptador.dao;

import dominio.src.main.java.com.ceiba.factura.modelo.dto.DtoFactura;
import infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao.MapeoJugador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MapeoFactura {

    public DtoFactura mapRow(ResultSet rs) throws SQLException {
        DtoFactura record = new DtoFactura();
        record.setId(rs.getLong("id"));
        record.setValor(rs.getInt("valor"));
        record.setFechaIngreso(extraerLocalDate(rs, "fecha_ingreso"));
        record.setFechaCaducidad(extraerLocalDate(rs, "fecha_caducidad"));
        record.setJugador(new MapeoJugador().mapRow(rs));
        record.setEstado(rs.getInt("estado"));
        record.setDescripcion(rs.getString("descripcion"));
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
