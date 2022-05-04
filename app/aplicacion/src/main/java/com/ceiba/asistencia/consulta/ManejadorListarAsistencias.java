package aplicacion.src.main.java.com.ceiba.asistencia.consulta;

import dominio.src.main.java.com.ceiba.asistencia.modelo.dto.DtoAsistencia;
import dominio.src.main.java.com.ceiba.asistencia.puerto.dao.DaoAsistencia;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ManejadorListarAsistencias {

    private final DaoAsistencia daoAsistencia;

    @Inject
    public ManejadorListarAsistencias(DaoAsistencia daoAsistencia) {
        this.daoAsistencia = daoAsistencia;
    }

    public CompletionStage<List<DtoAsistencia>> ejecutar(){
        return this.daoAsistencia.listar();
    }
}
