package pruebasunitarias.jugador.servicio.testdatabuilder;

import dominio.src.main.java.com.ceiba.jugador.modelo.dto.DtoJugador;
import dominio.src.main.java.com.ceiba.jugador.modelo.entidad.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JugadorTestDataBuilder {

    private Long id;
    private Long documento;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private float peso;
    private float altura;
    private String posicion;
    private String pieHabil;

    public JugadorTestDataBuilder() {
        documento = Long.valueOf(10101010);
        nombre = "Juanito";
        apellido = "Alimaña";
        fechaNacimiento = LocalDate.parse("2022-02-14");
        peso = (float) 54.6;
        altura = (float) 1.65;
        posicion = "Delantero";
        pieHabil = "Derecho";
    }


    public JugadorTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public JugadorTestDataBuilder conDocumento(Long documento){
        this.documento = documento;
        return this;
    }

    public JugadorTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public JugadorTestDataBuilder conApellido(String apellido){
        this.apellido = apellido;
        return this;
    }

    public JugadorTestDataBuilder conFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public JugadorTestDataBuilder conPeso(float peso){
        this.peso = peso;
        return this;
    }

    public JugadorTestDataBuilder conAltura(float altura){
        this.altura = altura;
        return this;
    }

    public JugadorTestDataBuilder conPosicion(String posicion){
        this.posicion = posicion;
        return this;
    }

    public JugadorTestDataBuilder conPieHabil(String pieHabil){
        this.pieHabil = pieHabil;
        return this;
    }

    public List<DtoJugador> listaDeJugadores(){
        List<DtoJugador> listaJugadores = new ArrayList<>();
        for (int i = 0; i < 18; i++){
            if (i < 5) {
                DtoJugador dtoJugador = new DtoJugador(id, documento + i, nombre, apellido,
                        fechaNacimiento, peso, altura, "Delantero", pieHabil);
                listaJugadores.add(dtoJugador);
            } else if (i >= 5 && i < 10) {
                DtoJugador dtoJugador = new DtoJugador(id, documento + i, nombre, apellido,
                        fechaNacimiento, peso, altura, "Mediocampista", pieHabil);
                listaJugadores.add(dtoJugador);
            } else if (i >= 10 && i < 15) {
                DtoJugador dtoJugador = new DtoJugador(id, documento + i, nombre, apellido,
                        fechaNacimiento, peso, altura, "Defensa", pieHabil);
                listaJugadores.add(dtoJugador);
            }
            else {
                DtoJugador dtoJugador = new DtoJugador(id, documento + i, nombre, apellido,
                        fechaNacimiento, peso, altura, "Portero", pieHabil);
                listaJugadores.add(dtoJugador);
            }
        }
        return listaJugadores;
    }

    public DtoJugador dtoJugador(){
        return new DtoJugador(id, 80808080L, nombre, apellido, fechaNacimiento,
                peso, altura, posicion, pieHabil);
    }

    public Jugador build() { return new Jugador(id, documento, nombre, apellido,
            fechaNacimiento, peso, altura, posicion, pieHabil); }

    public Jugador build(Long documento, String nombre, String apellido, LocalDate fechaNacimiento,
                         float peso, float altura, String posicion, String pieHabil) {
        return new Jugador(id, documento, nombre, apellido, fechaNacimiento, peso, altura, posicion, pieHabil);
    }
}
