package pruebasunitarias.asistencia.entidad;

import dominio.src.main.java.com.ceiba.asistencia.modelo.entidad.Asistencia;
import org.junit.Test;
import pruebasunitarias.asistencia.servicio.testdatabuilder.AsistenciaTestDataBuilder;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AsistenciaTest {

    @Test
    public void deberiaCrearUnaAsistenciaCorrectamente(){
        LocalDate fecha = LocalDate.now();
        Asistencia asistencia = new AsistenciaTestDataBuilder().build();

        assertEquals(fecha.toString(), asistencia.getFecha().toString());
        assertEquals("10101010", asistencia.getJugador().getDocumento().toString());
    }
}
