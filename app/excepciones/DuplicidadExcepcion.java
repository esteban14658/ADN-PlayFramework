package excepciones;

public class DuplicidadExcepcion extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public DuplicidadExcepcion(String mensaje){
        super(mensaje);
    }
}
