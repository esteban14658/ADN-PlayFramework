package excepciones;

public class NoSeEncuentraExcepcion extends RuntimeException{

    public NoSeEncuentraExcepcion(String mensaje){
        super(mensaje);
    }
}
