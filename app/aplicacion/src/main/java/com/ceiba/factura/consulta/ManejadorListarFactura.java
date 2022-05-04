package aplicacion.src.main.java.com.ceiba.factura.consulta;

import dominio.src.main.java.com.ceiba.factura.modelo.dto.DtoFactura;
import dominio.src.main.java.com.ceiba.factura.puerto.dao.DaoFactura;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ManejadorListarFactura {

    private final DaoFactura daoFactura;

    @Inject
    public ManejadorListarFactura(DaoFactura daoFactura) {
        this.daoFactura = daoFactura;
    }

    public CompletionStage<List<DtoFactura>> ejecutar(){
        return this.daoFactura.listar();
    }
}
