package pruebasunitarias.jugador.entidad;

import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;
import org.junit.Test;
import pruebasunitarias.jugador.servicio.testdatabuilder.JugadorTestDataBuilder;

import static org.junit.Assert.assertEquals;

public class JugadorTest {

    @Test
    public void DeberiaCrearCorrectamenteElJugador(){
        Jugador jugador = new JugadorTestDataBuilder().conId(1L).build();

        assertEquals("1", jugador.getId().toString());
        assertEquals("10101010", jugador.getDocumento().toString());
        assertEquals("Juanito", jugador.getNombre());
        assertEquals("Alimaña", jugador.getApellido());
        assertEquals("2022-02-14", jugador.getFechaNacimiento().toString());
        assertEquals("54.6", String.valueOf(jugador.getPeso()));
        assertEquals("1.65", String.valueOf(jugador.getAltura()));
        assertEquals("Delantero", jugador.getPosicion());
        assertEquals("Derecho", jugador.getPieHabil());
    }

}
