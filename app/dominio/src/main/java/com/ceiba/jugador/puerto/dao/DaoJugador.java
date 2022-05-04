package dominio.src.main.java.com.ceiba.jugador.puerto.dao;

import com.google.inject.ImplementedBy;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import infraestructura.src.main.java.com.ceiba.jugador.adaptador.dao.DaoJugadorPostgreSQL;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@ImplementedBy(DaoJugadorPostgreSQL.class)
public interface DaoJugador {

    CompletionStage<List<DtoJugador>> listar();

    CompletionStage<List<DtoJugador>> listarPorPosicion(String posicion);

    CompletionStage<List<DtoJugador>> listarJugadoresSinFactura();

    CompletionStage<List<DtoJugador>> listarSinAsistencias();

    CompletionStage<List<DtoJugador>> listarPorCategoria(String categoria);

    CompletionStage<Optional<DtoJugador>> obtenerPorDocumento(Long documento);
}
