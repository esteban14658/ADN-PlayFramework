package excepciones;

public class MalaPeticionExcepcion extends RuntimeException{

    public MalaPeticionExcepcion(String mensaje){
        super(mensaje);
    }
}
