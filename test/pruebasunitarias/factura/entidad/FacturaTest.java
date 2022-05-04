package pruebasunitarias.factura.entidad;

import dominio.src.main.java.com.ceiba.factura.modelo.entidad.Factura;
import org.junit.Test;
import pruebasunitarias.factura.servicio.testdatabuilder.FacturaTestDataBuilder;

import static org.junit.Assert.assertEquals;

public class FacturaTest {

    @Test
    public void deberiaCrearCorrectamenteLaFactura(){
        // act
        Factura factura = new FacturaTestDataBuilder().conId(1L).build();
        // assert
        assertEquals("1", factura.getId().toString());
        assertEquals("420000", factura.getValor().toString());
        assertEquals("2022-01-10", factura.getFechaIngreso().toString());
        assertEquals("2022-04-09", factura.getFechaCaducidad().toString());
        assertEquals("10101010", factura.getJugador().getDocumento().toString());
        assertEquals("1", factura.getEstado().toString());
        assertEquals("tres meses", factura.getDescripcion());
    }
}
