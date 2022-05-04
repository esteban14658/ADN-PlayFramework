package dominio.repositorios;

import dominio.modelos.JugadorDTO;

import java.util.List;

public interface JugadorDAO {

    List<JugadorDTO> listarJugadores();
    JugadorDTO listarPorDocumento(Long documento);
}
