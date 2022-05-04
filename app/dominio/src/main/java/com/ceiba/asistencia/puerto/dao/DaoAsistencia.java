package dominio.src.main.java.com.ceiba.asistencia.puerto.dao;

import com.google.inject.ImplementedBy;
import dominio.src.main.java.com.ceiba.asistencia.modelo.dto.DtoAsistencia;
import infraestructura.src.main.java.com.ceiba.asistencia.adaptador.dao.DaoAsistenciaPostgreSQL;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(DaoAsistenciaPostgreSQL.class)
public interface DaoAsistencia {

    CompletionStage<List<DtoAsistencia>> listar();
}
