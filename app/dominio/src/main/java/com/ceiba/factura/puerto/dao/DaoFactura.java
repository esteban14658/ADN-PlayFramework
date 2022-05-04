package dominio.src.main.java.com.ceiba.factura.puerto.dao;

import com.google.inject.ImplementedBy;
import dominio.src.main.java.com.ceiba.factura.modelo.dto.DtoFactura;
import infraestructura.src.main.java.com.ceiba.factura.adaptador.dao.DaoFacturaPostgreSQL;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(DaoFacturaPostgreSQL.class)
public interface DaoFactura {

    CompletionStage<List<DtoFactura>> listar();
}
