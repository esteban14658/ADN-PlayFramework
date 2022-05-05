package infraestructura.src.main.java.com.ceiba.factura.adaptador.dao;

import dominio.src.main.java.com.ceiba.factura.modelo.dto.DtoFactura;
import infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao.MapeoJugador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MapeoFactura {

    public DtoFactura mapRow(ResultSet rs) throws SQLException {
        DtoFactura dtoFactura = new DtoFactura();
        dtoFactura.setId(rs.getLong("id"));
        dtoFactura.setValor(rs.getInt("valor"));
        dtoFactura.setFechaIngreso(extraerLocalDate(rs, "fecha_ingreso"));
        dtoFactura.setFechaCaducidad(extraerLocalDate(rs, "fecha_caducidad"));
        dtoFactura.setJugador(new MapeoJugador().mapRow(rs));
        dtoFactura.setEstado(rs.getInt("estado"));
        dtoFactura.setDescripcion(rs.getString("descripcion"));
        return dtoFactura;
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
