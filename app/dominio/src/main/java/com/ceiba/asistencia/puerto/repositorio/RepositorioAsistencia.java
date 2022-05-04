package dominio.src.main.java.com.ceiba.asistencia.puerto.repositorio;

import com.google.inject.ImplementedBy;
import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;
import infraestructura.src.main.java.com.ceiba.asistencia.adaptador.repositorio.RepositorioAsistenciaPostgreSQL;

import java.util.concurrent.CompletionStage;

@ImplementedBy(RepositorioAsistenciaPostgreSQL.class)
public interface RepositorioAsistencia {

    CompletionStage<Long> crear(Asistencia asistencia);
    CompletionStage<Boolean> registroDiario(Long id);
}
