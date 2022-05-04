package dominio.src.main.java.com.ceiba.jugador.puerto.repositorio;

import com.google.inject.ImplementedBy;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import infraestructura.src.main.java.com.ceiba.jugador.adaptador.repositorio.RepositorioJugadorPostgreSQL;

import java.util.concurrent.CompletionStage;

@ImplementedBy(RepositorioJugadorPostgreSQL.class)
public interface RepositorioJugador {

    CompletionStage<Long> crear(Jugador jugador);

    CompletionStage<Void> actualizar(Jugador jugador);

    CompletionStage<Void> eliminar(Long id);

    CompletionStage<Boolean> existe(String nombre);

    CompletionStage<Boolean> existePorId(Long id);

    CompletionStage<Boolean> existePorDocumento(Long documento);

    CompletionStage<Boolean> existeJugadorConFactura(Long id);

    CompletionStage<Boolean> existeJugadorConAsistencias(Long id);
}
