package dominio.src.main.java.com.ceiba.jugador.servicio;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoFiltro;
import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.puerto.dao.DaoJugador;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ThreadLocalRandom;

public class ServicioObtenerEquipo {

    public static final long CANTIDAD_MAXIMA_JUGADORES_PERMITIDA = 11L;
    public static final long PORTERO_NUMERO = 1L;
    private final DaoJugador daoJugador;

    @Inject
    public ServicioObtenerEquipo(DaoJugador daoJugador) {
        this.daoJugador = daoJugador;
    }

    public CompletionStage<List<DtoJugador>> ejecutar(DtoFiltro dtoFiltro){
        Long defensasNumero = Long.parseLong(dtoFiltro.getDefensas());
        Long mediocampistasNumero = Long.parseLong(dtoFiltro.getMediocampistas());
        Long delanterosNumero = Long.parseLong(dtoFiltro.getDelanteros());
        String[] posiciones = new String[] {"Portero","Defensa", "Mediocampista", "Delantero"};
        Long[] keys = new Long[] {PORTERO_NUMERO, defensasNumero, mediocampistasNumero, delanterosNumero};
        Long sumatoria = PORTERO_NUMERO + defensasNumero + mediocampistasNumero + delanterosNumero;
        if (sumatoria > CANTIDAD_MAXIMA_JUGADORES_PERMITIDA){
            throw new IndexOutOfBoundsException("No puede sobrepasar la cantidad permitida de jugadores");
        }
        List<DtoJugador> equipo = new ArrayList<>();
        for (int i = 0; i < keys.length ; i++){
            List<DtoJugador> prueba = obtenerJugadores(posiciones[i], keys[i]);
            prueba.stream().forEach((x)->
                    equipo.add(x)
            );
        }
        CompletionStage<List<DtoJugador>> completionStage = CompletableFuture.completedFuture(equipo);
        return completionStage;
    }

    @SneakyThrows
    private List<DtoJugador> obtenerJugadores(String posicion, Long cantidad) {
        List<DtoJugador> jugadores = this.daoJugador.listarPorPosicion(posicion)
                .thenApplyAsync(r -> {
                    return r;
                })
                .toCompletableFuture().get();
        List<Integer> registroObtenido = new ArrayList<>();
        List<DtoJugador> resultado = new ArrayList<>();
        for (int i = 0; i < cantidad; i++){
            if (cantidadDeLista(posicion) < 1){
                resultado = new ArrayList<>();
                break;
            }
            int random = numeroAleatorioEnRango(0, cantidadDeLista(posicion));
            if (!registroObtenido.contains(random)){
                registroObtenido.add(random);
                resultado.add(jugadores.get(random));
            }
            else {
                cantidad++;
            }
        }
        return resultado;
    }

    @SneakyThrows
    private int cantidadDeLista(String posicion){
        return this.daoJugador.listarPorPosicion(posicion)
                .thenApplyAsync(r -> {
                    return r;
                })
                .toCompletableFuture().get().size();
    }

    private static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo);
    }
}
